package com.example.hustagram;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;


import com.android.volley.toolbox.Volley;
import com.google.common.util.concurrent.ListenableFuture;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import java.util.concurrent.ExecutionException;



public class MainActivity extends AppCompatActivity {
    //declare things needed for network, camera, and stored global str var for image upload
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private PreviewView previewView;
    private ImageCapture imageCapture;

    //queue for volley
    RequestQueue queue;
    //global str var for storing image b64
    private String stored_b64;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //get buttons
        Button ViewImage = findViewById(R.id.view_button);
        Button UploadImage = findViewById(R.id.upload_button);
        Button CaptureImage = findViewById(R.id.capture_button);
        //get comment

        View comment = findViewById(R.id.comment_space);

        //get and start volley queue
        queue = Volley.newRequestQueue(this);
        queue.start();


        // get previewView
        previewView = findViewById(R.id.previewView);

        // get camprovider
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        //add a listener to cam provider, and bind preview to the cam provider
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace(); // Print the error for debugging
            }
        }, ContextCompat.getMainExecutor(this));


        // define onclick for view button:
        ViewImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
            startActivity(intent);
        });

        //onclicks for each button
        UploadImage.setOnClickListener(v -> {
            upload(stored_b64);
        });

        //onclick for capture image
        CaptureImage.setOnClickListener(v -> {
            //DO STUFF TO CAPTURE THE IMAGE
            //route to func that does it
            captureImage();
        });


    }
    //not really sure what this all does, was on CameraX tutorial on android dev site and is a required method
    private void bindPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        // Initialize ImageCapture
        imageCapture = new ImageCapture.Builder().build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);  // Important binding
    }

    //func that actually captures image
    private void captureImage() {
        //create a temp file named photo.jpg
        File photoFile = new File(getExternalFilesDir(null), "photo.jpg");
        if (!photoFile.exists()) {
            //if it doesnt exist, try to make it
            try {
                photoFile.createNewFile();
                //else, something went wrong
            } catch (IOException e) {
                Log.e("Main", "Error creating file: " + e.getMessage());
                return;
            }
        }

        //options for saving our image
        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(photoFile).build();

        //actual takePicture method which takes options, and the context of the picture we are taking. takes a callback as well.
        imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this),
                //callback for when the image is saved
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    //when image is saved:
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        //do base64 conversions and store to var
                        //convert the File to a bitmap
                        //first decode photofile path to a bitmap
                        Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getPath());
                        //then encode to base64
                        stored_b64 = encodeToBase64(bitmap, Bitmap.CompressFormat.PNG, 100);
                        //once stored, delete file from device.
                        boolean Delete = photoFile.delete();
                    }
                    @Override
                    //error logging
                    public void onError(@NonNull ImageCaptureException exception) {
                        //error Logging
                        Log.d("Main", "ISSUE WITH TAKING PICTURE.");


                    }
                });


    }
    //b64 encoding from PGrim example
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }


    //upload code, im not sure if there was errors on my end, but I had to create a network_security_config xml to allow it to comm with flask API. Could be a student dorm thing.
    private void upload (final String stored_b64){

        //url for flask server:

        String url = "http://10.2.105.189:5000/save";

        //when upload is requested, request the date, and the comment in the box.
        String Date = LocalDateTime.now().toString();
        //get text currently inside text box
        String Comment = ((EditText) findViewById(R.id.comment_space)).getText().toString();
        //create a JSON object for upload
        JSONObject json = new JSONObject();
        try {
            //flask API data entry
            json.put("image", stored_b64);
            json.put("date", Date);
            json.put("comment", Comment);
        } catch (JSONException e) {
            //if there is an error
            Log.d("Upload", "Error in file upload");
            throw new RuntimeException(e);
        }

        //actually upload the object.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Hello", "Response: " + response.toString());
                        //once response recieved, reset str var to none
                        final String stored_b64 = "";
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Hello", error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
    }
}


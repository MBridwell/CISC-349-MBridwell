package com.example.hustagram;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private PreviewView previewView; // Declare PreviewView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button ViewImage = findViewById(R.id.view_button);
        Button UploadImage = findViewById(R.id.upload_button);
        Button CaptureImage = findViewById(R.id.capture_button);


        // Initialize PreviewView
        previewView = findViewById(R.id.previewView);

        // Initialize cameraProviderFuture
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);


        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace(); // Print the error for debugging
            }
        }, ContextCompat.getMainExecutor(this));


        // define onclick for view button: working.
        ViewImage.setOnClickListener( v -> {
            Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
            startActivity(intent);
        });

        UploadImage.setOnClickListener( v -> {
            //DO STUFF FOR UPLOAD IMAGE BUTTON IDK WHAT TO DO.
        });

        CaptureImage.setOnClickListener(v -> {
            //DO STUFF TO CAPTURE THE IMAGE.
        });



    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {
        // Build a Preview instance
        Preview preview = new Preview.Builder()
                .build();

        // Select the back camera
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        // Set the PreviewView as the surface provider for the preview
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        // Bind camera to lifecycle
        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview);
    }


    //IDK WHAT TO DO HERE.


    private void uploadImage(){}
}


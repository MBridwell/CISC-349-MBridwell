package com.example.hustagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);

        ArrayList<String> listImage = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<String> commentList = new ArrayList<>();

        gridView = findViewById(R.id.grid_view); //
        String url = "http://10.2.105.189:5000/get_data";
        ImageView backbutton = findViewById(R.id.arrowBack);


        backbutton.setOnClickListener(v -> {
            Intent intent = new Intent(GridViewActivity.this, MainActivity.class);
            startActivity(intent);
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Loop through each object in the response array
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // Get each object (image) from the array
                                JSONObject obj = response.getJSONObject(i);
                                String b64_image = obj.getString("image");

                                //because b64 is too big to send, we might have to do checking based on the date.
                                String date = obj.getString("date");
                                String comment = obj.getString("comment");



                                // Log the data to verify it's working
                                Log.d("Image Base64", b64_image);

                                listImage.add(b64_image);
                                dateList.add(date);
                                commentList.add(comment);




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ImageAdapter adapter = new ImageAdapter(GridViewActivity.this, listImage);
                        gridView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        Log.e("Volley", "Error: " + error.getMessage());
                    }
                });


        queue.add(jsonArrayRequest);

        gridView.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(GridViewActivity.this, ViewImageActivity.class);
            String date = dateList.get(position);
            String comment = commentList.get(position);

            intent.putExtra("comment", comment);
            intent.putExtra("date", date);
            startActivity(intent);
        });
    }

 //  private Bitmap decode(String b64){
 //      byte[] decodedString = Base64.decode(b64, Base64.DEFAULT);
 //      Bitmap decoded = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
 //      ImageView imageView = findViewById(R.id.image_view);
 //      imageView.setImageBitmap(decoded);
 //      return decoded;
 //  }
}

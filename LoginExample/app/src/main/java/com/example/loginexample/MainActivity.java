package com.example.loginexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    Button loginButton;

    TextView usernameButton;

    TextView passwordButton;
    String url = "http://127.0.0.1:5000/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.login_button);
        usernameButton = findViewById(R.id.user_input);
        passwordButton = findViewById(R.id.password_input);

        queue = Volley.newRequestQueue(this);
        queue.start();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = usernameButton.getText().toString();
                String Password = passwordButton.getText().toString();

                String Data = String.format("{ \"username\":\"%s\", \"password\":\"%s\"}", Username, Password);

                JsonRequest<String> jsonRequest =
                        new JsonRequest<String>(Request.Method.POST,
                                url, Data,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT)
                                                .show();
                                        Log.d("Login", "responded " + response);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d("Login Error", "Error:" + error);
                                    }
                                }) {
                            @Override
                            protected Response parseNetworkResponse(NetworkResponse response) {
                                String data = new String(response.data);
                                Response<String> res = Response.success(data, null);
                                Log.d("Login", "parseNetworkResponse called");
                                return res;
                            }
                        };
                queue.add(jsonRequest);
            }
        });
    }
}

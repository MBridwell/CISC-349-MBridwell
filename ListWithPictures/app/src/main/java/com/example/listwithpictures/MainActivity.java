package com.example.listwithpictures;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView listView;
    TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LayoutInflater factory = getLayoutInflater();
        final View myView = factory.inflate(R.layout.my_list, null);
        textView = (TextView) myView.findViewById(R.id.textView);

      listView = findViewById(R.id.listView);


 //     for (String s : listItem) {
 //         Log.d(TAG, s);
 //     }

        //create an array of users
        ArrayList<User> arrayOfUsers = new ArrayList<>();
        arrayOfUsers.add(new User("Mason", "123-456-3244", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("Dvin", "717-555-5034", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("Phil", "717-555-3183", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("John", "717-555-3943", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("Jeremy", "777-777-3428", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("Joe", "717-555-0348", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("LaDainian", "717-555-9384", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));

        UserAdapter adapter = new UserAdapter(this, arrayOfUsers);
        listView.setAdapter(adapter);  // Set the adapter on the correct listView


        listView.setAdapter(adapter);

        listView.setOnItemClickListener((AdapterView.OnItemClickListener) adapter);

    }
}
package com.example.walmartstores;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {

            WalmartFragment fragment = new WalmartFragment();


            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
    }
}
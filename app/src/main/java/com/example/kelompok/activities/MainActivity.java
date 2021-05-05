package com.example.kelompok.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelompok.R;

public class MainActivity extends AppCompatActivity {
    private int waktu_loading = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dashboard = new Intent(MainActivity.this, Dashboard.class);
                startActivity(dashboard);
                finish();
            }
        }, waktu_loading);
    }
}
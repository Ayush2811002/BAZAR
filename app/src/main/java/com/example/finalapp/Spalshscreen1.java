package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Spalshscreen1 extends AppCompatActivity {
    private  static int Splash_timeout= Integer.parseInt("1200");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalshscreen1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash=new Intent(Spalshscreen1.this,MainActivity.class);
                startActivity(splash);
                finish();
            }
        },Splash_timeout);
    }
}
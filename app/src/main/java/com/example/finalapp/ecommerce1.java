package com.example.finalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Field;


public class ecommerce1 extends AppCompatActivity {
ImageButton jwe,sd,cs;
ImageView ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce1);
        jwe=findViewById(R.id.pro);
        sd=findViewById(R.id.home);
        cs=findViewById(R.id.cart);
        jwe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Regis=new Intent(ecommerce1.this,userProfile.class);
                startActivity(Regis);
            }
        });
        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Regis=new Intent(ecommerce1.this,kart.class);
                startActivity(Regis);
            }
        });
        sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent Regis=new Intent(ecommerce1.this,MainActivity.class);
                startActivity(Regis);
                finish();
            }
        });
    }
    //on back pressed
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alterDialoag= new AlertDialog.Builder(ecommerce1.this);
        alterDialoag.setTitle("BAZAR");
        alterDialoag.setMessage("Do you want to exit ?");
        alterDialoag.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alterDialoag.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alterDialoag.show();
    }
}
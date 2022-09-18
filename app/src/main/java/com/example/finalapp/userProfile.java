package com.example.finalapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class userProfile extends AppCompatActivity {
TextView fullname,email,phone,adress;
FirebaseAuth fauth;
FirebaseFirestore fstore;
String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        phone=findViewById(R.id.phones);
        fullname=findViewById(R.id.names);
        email=findViewById(R.id.Email);
        adress=findViewById(R.id.Adress);
         fauth=FirebaseAuth.getInstance();
         fstore=FirebaseFirestore.getInstance();
         userId=fauth.getCurrentUser().getUid();
         //retrieving data
        DocumentReference documentReference=fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                phone.setText(value.getString("Phone"));
                fullname.setText(value.getString("FName"));
                email.setText(value.getString("Email"));
                adress.setText(value.getString("ADDRESS"));

            }
        });
    }
}
package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registeration extends AppCompatActivity {

    public static final String TAG = "TAG";
    private EditText editTextname,editTextEmail,editTextMobile,editTextPassword,editTextAddress;
private Button RegisterButton;
FirebaseAuth fauth;
FirebaseFirestore fstore;
String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        changeStausBarColor();
        editTextAddress=findViewById(R.id.editTextAddress);
        editTextname=findViewById(R.id.editTextname);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextMobile=findViewById(R.id.editTextMobile);
        editTextPassword=findViewById(R.id.editTextPassword);
        RegisterButton=findViewById(R.id.RegiterButton);
        //fire base
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
     /*   if(fauth.getCurrentUser() !=null)
        {
            startActivity(new Intent(getApplicationContext(),Dashbord.class));
            finish();
        }*/

        /* set on click listener for Registeration Button  */
//Start
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //string data
                String email=editTextEmail.getText().toString().trim();
                String password=editTextPassword.getText().toString().trim();
                String Name=editTextname.getText().toString();
                String phone=editTextMobile.getText().toString();
                String adress=editTextAddress.getText().toString();
                //condidtion
                if(TextUtils.isEmpty(email)){
                    editTextEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    editTextPassword.setError("password is Required");
                    return;
                }

                if(password.length()<6){
                    editTextPassword.setError("Password Must be >=6 charcters");
                    return;
                }
                //phone no
                if(phone.length() != 10){
                    editTextMobile.setError("Phone number should be of 10 numbers ");
                    return;
                }
                //condition ends
                //Register the user in firebase
                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // condition
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Registeration.this, "Details Added Sucessfuly ", Toast.LENGTH_SHORT).show();
                            //get details for current register user
                            userID=fauth.getCurrentUser().getUid();
                            DocumentReference documentReference=fstore.collection("users").document(userID);
                            Map<String,Object> user=new HashMap<>();
                            //inserting data
                            user.put("FName",Name);
                            user.put("Phone",phone);
                            user.put("Email",email);
                            user.put("ADDRESS",adress);
                            //add on sucesslisterner
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created  for  " +userID);
                                }
                                //add on failer

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString()  );

                                }
                            });
                            Intent Regis=new Intent(Registeration.this,ecommerce1.class);
                            startActivity(Regis);

                        }
                        else {

                            Toast.makeText(Registeration.this, "Error in uploding "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });

    }



    private void changeStausBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onregisClick(View view){
        Intent Regis=new Intent(Registeration.this,MainActivity.class);
        startActivity(Regis);
        //finish();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }


}

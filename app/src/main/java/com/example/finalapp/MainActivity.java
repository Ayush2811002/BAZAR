package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText email,password;
    private Button Login;
    FirebaseAuth futh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.editTextEmail);
        password=findViewById(R.id.editTextPassword);
        Login=findViewById(R.id.LoginButton);
        futh=FirebaseAuth.getInstance();
        if(futh.getCurrentUser() !=null)
        {
            startActivity(new Intent(getApplicationContext(),ecommerce1.class));
            finish();
        }
        //onclick listerner
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ema=email.getText().toString().trim();
                String pss=password.getText().toString().trim();
                //condidtion
                if(TextUtils.isEmpty(ema)){
                    email.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(pss)){
                    password.setError("password is Required");
                    return;
                }

                if(password.length()<6){
                    password.setError("Password Must be >=6 charcters");
                    return;
                }
                //condition ends
                // Firebase auth
                futh.signInWithEmailAndPassword(ema,pss).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // condition
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Sucessfuly  Login", Toast.LENGTH_SHORT).show();
                            Intent Regis=new Intent(MainActivity.this,ecommerce1.class);
                            startActivity(Regis);

                        }
                        else {

                            Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }
// onLoginClick
    public void onLoginClick(View View) {
        Intent Regis = new Intent(MainActivity.this, Registeration.class);
        startActivity(Regis);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
    //on back pressed
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alterDialoag= new AlertDialog.Builder(MainActivity.this);
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

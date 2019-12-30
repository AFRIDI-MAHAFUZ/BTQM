package com.example.tourmatetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText nameET,emailET,idET,passwordET;
    private TextView loginTV;
    private Button signUpBtn;
    private String name,email,password,id;
    FirebaseAuth mfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mfirebaseAuth =FirebaseAuth.getInstance();
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameET.getText().toString();
                email = emailET.getText().toString();
                password = passwordET.getText().toString();
                id = idET.getText().toString();

                if (name.isEmpty()) {
                    nameET.setError("Enter name please");
                    nameET.requestFocus();
                } else if (email.isEmpty()) {

                    emailET.setError("Enter email ID");
                    emailET.requestFocus();
                } else if(id.isEmpty()) {
                    idET.setError("please enter your id");
                    idET.requestFocus();
                } else if (password.isEmpty()) {
                    passwordET.setError("Enter your password");
                    passwordET.requestFocus();
                } else if (!(name.isEmpty() && email.isEmpty() && password.isEmpty() && id.isEmpty())) {

                    mfirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {

                                Toast.makeText(MainActivity.this, "Sign Up UInsuccesful, please try again", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(MainActivity.this, "Succesfully Register", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Loging_Activity.class));
                            }
                        }
                    });


                } else {

                    Toast.makeText(MainActivity.this, "Error Ocurred", Toast.LENGTH_SHORT).show();
                }


            }
        });

        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Loging_Activity.class));
            }
        });

    }

    private void init() {

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        signUpBtn = findViewById(R.id.signUpBtn);
        loginTV = findViewById(R.id.loginTV);
        idET = findViewById(R.id.idET);
    }


}

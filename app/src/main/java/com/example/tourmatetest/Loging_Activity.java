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
import com.google.firebase.auth.FirebaseUser;

public class Loging_Activity extends AppCompatActivity {
    private EditText emailET,passwordET;
    private TextView signUpTV;
    private Button logingBtn;
    private String email,password;
    FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mauthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging_);

        init();

       mauthStateListener = new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

               FirebaseUser mfirebaseUser = mfirebaseAuth.getCurrentUser();
               if(mfirebaseUser != null){
                   Toast.makeText(Loging_Activity.this, "Succesfully Loging", Toast.LENGTH_SHORT).show();

                   startActivity(new Intent(Loging_Activity.this,HomeActivity.class));
               }
           }
       };

       logingBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               email = emailET.getText().toString();
               password = passwordET.getText().toString();

               if (email.isEmpty()) {
                   emailET.setError("Enter email ID");
                   emailET.requestFocus();
               } else if (password.isEmpty()) {
                   passwordET.setError("Enter your password");
                   passwordET.requestFocus();
               } else if (!(email.isEmpty() && password.isEmpty())) {

                   mfirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Loging_Activity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                           if(!task.isSuccessful()){
                               Toast.makeText(Loging_Activity.this, "Loging Error", Toast.LENGTH_SHORT).show();
                           }
                           else if(password.equals("Principal") && email.equals("mizanurrahman@gmail.com")){
                               startActivity(new Intent(Loging_Activity.this,PrincipalActivity.class));
                           }
                           else if(password.equals("Teacher") && email.equals("btqmteacher@gmail.com") ){
                               startActivity(new Intent(Loging_Activity.this,TeacherActivity.class));
                           }

                           else if(password.equals("Student") && email.equals("btqmstudent@gmail.com")){
                               startActivity(new Intent(Loging_Activity.this,StudentActivity.class));
                           }
                       }
                   });

               }
           }
       });

       signUpTV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(Loging_Activity.this,MainActivity.class));
           }
       });





    }
    private void init() {

        mfirebaseAuth = FirebaseAuth.getInstance();
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        signUpTV = findViewById(R.id.signUpTV);
        logingBtn = findViewById(R.id.loginBtn);
    }
}

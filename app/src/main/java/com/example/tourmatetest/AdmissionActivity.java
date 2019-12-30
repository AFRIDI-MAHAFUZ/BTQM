package com.example.tourmatetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdmissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission);
        init();

    }

    private void init() {

       BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_nav);
       bottomNavigationView .setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

               switch (menuItem.getItemId()){

                   case R.id.studentItem:
                       FragmentManager fragmentManager1 = getSupportFragmentManager();
                       FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                       fragmentTransaction1.replace(R.id.frameLayout,new StudentAdmissionFramlayout());
                       fragmentTransaction1.commit();
                       return true;

                   case R.id.techerItem:
                       FragmentManager fragmentManager2 = getSupportFragmentManager();
                       FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                       fragmentTransaction2.replace(R.id.frameLayout,new TeacherJoiningForm());
                       fragmentTransaction2.commit();
                       return true;
               }
               return false;
           }
       });
    }
}

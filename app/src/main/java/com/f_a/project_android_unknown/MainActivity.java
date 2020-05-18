package com.f_a.project_android_unknown;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        FragLogin Login = new FragLogin();
//        transaction.add(R.id.Frame, Login);
//        transaction.addToBackStack("Login");
//        transaction.commit();

        if(savedInstanceState == null){
            FragLogin Login = new FragLogin();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.Frame, Login);
            fragmentTransaction.commit();
        }



    }
}

package com.fes.ui_exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializedUI();

    }
    public void initializedUI(){

    }

    public void btn_click(View view){
    Intent i = new Intent(getApplicationContext(),Activity2.class);
    startActivity(i);
    }
}
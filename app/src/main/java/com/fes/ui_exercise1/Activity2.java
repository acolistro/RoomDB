package com.fes.ui_exercise1;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    EditText edtEmail,edtPass,edtRepeat;
    Button btnNext;
    private String email,password,repeat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        getSupportActionBar().setTitle("Create an account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        initializedUI();

        edtEmail.addTextChangedListener(loginTextWatcher);
        edtPass.addTextChangedListener(loginTextWatcher);
        edtRepeat.addTextChangedListener(loginTextWatcher);

       btnNext.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               login();
           }
       });

    }
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            email = edtEmail.getText().toString();
            password = edtPass.getText().toString();
            repeat = edtRepeat.getText().toString();
        btnNext.setEnabled(!email.isEmpty() && !password.isEmpty() && !repeat.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void initializedUI() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        edtRepeat = findViewById(R.id.edtRepeat);
        btnNext = findViewById(R.id.btnNext);
    }

    public void login() {
        Log.d("Login", "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        } else {

        Intent i = new Intent(getApplicationContext(),RegistrationDetails.class);
        startActivity(i);
        }
    }

    public boolean validate() {
        boolean valid = true;

        email = edtEmail.getText().toString();
        password = edtPass.getText().toString();
        repeat = edtRepeat.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("enter a valid email address");
            valid = false;
        } else {
            edtEmail.setError(null);
        }

        if (password.isEmpty() || password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$") == false) {
            edtPass.setError("password is invalid");
            valid = false;
        } else {
            edtPass.setError(null);
        }

        if(repeat.isEmpty() || password.equals(repeat) == false){
            edtRepeat.setError("password does not match");
            valid = false;
        }else {
            edtRepeat.setError(null);
        }

        return valid;
    }


    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

    }
}

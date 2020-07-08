package com.fes.ui_exercise1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fes.ui_exercise1.R;
import com.fes.ui_exercise1.RegistrationDetails;


public class Fragment_Registration extends Fragment {

    EditText edtEmail,edtPass,edtRepeat;
    Button btnNext;
    private String email,password,repeat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__registration, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        edtEmail = getActivity().findViewById(R.id.edtEmail);
        edtPass = getActivity().findViewById(R.id.edtPass);
        edtRepeat = getActivity().findViewById(R.id.edtRepeat);
        btnNext = getActivity().findViewById(R.id.btnNext);
    }

    public void login() {
        Log.d("Login", "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        } else {

            Fragment fr_registration_details = new Fragment_RegistrationDetails();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,fr_registration_details);
            fragmentTransaction.commit();
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
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();

    }
}

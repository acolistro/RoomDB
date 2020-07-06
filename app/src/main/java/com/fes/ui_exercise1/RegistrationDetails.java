package com.fes.ui_exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class RegistrationDetails extends AppCompatActivity implements View.OnClickListener {
    Button btnDob, btnCreate, btnCancel;
    TextView txtDob;
    ImageView img;
    EditText edtName, edtNi, edtPassport, edtPass, edtConfirm;
    Spinner spnCountry;
    RadioGroup radioGroup;
    private int mYear, mMonth, mDay;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private Uri photoUri;
    private String name,ni,passport,password,confirm,country,gender,bdate,photo;
    SharedPreferences keyRegistrationDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_details);
        getSupportActionBar().setTitle("Registration Details");

        initializedUI();
        try {
            loadSpinner();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializedUI() {
        btnCreate = findViewById(R.id.btnCreate);
        btnCancel = findViewById(R.id.btnCancel);
        btnDob = findViewById(R.id.btnDOB);
        txtDob = findViewById(R.id.txtDob);
        img = findViewById(R.id.imgView);
        edtName = findViewById(R.id.edtName);
        edtNi = findViewById(R.id.edtNi);
        edtPassport = findViewById(R.id.edtPassport);
        edtPass = findViewById(R.id.edtPassword);
        edtConfirm = findViewById(R.id.edtConfirm);
        spnCountry = findViewById(R.id.spnCountry);
        radioGroup = findViewById(R.id.radioGroup);

        btnDob.setOnClickListener(this);
        img.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDob) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == img) {

            Intent getImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getImageIntent.setType("image/*");
            startActivityForResult(getImageIntent, IMAGE_GALLERY_REQUEST);

        }

        if (v == btnCreate) {

            if (!Validate()) {
                return;

            } else {
                GetText();
                Shared();
                Intent i = new Intent(getApplicationContext(), ViewDetails.class);
                startActivity(i);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_GALLERY_REQUEST && resultCode == RESULT_OK) {
            photoUri = data.getData();
            img.setImageURI(photoUri);
        }
    }

    //To load Country list from .txt file from Assest folder
    public void loadSpinner() throws IOException {
        List<String> countryList = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new InputStreamReader(getAssets().open("CountryList.txt")));

        String line = in.readLine();
        int index = 0;
        while (line != null) {

            countryList.add(line);
            line = in.readLine();
        }

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, countryList);

        spnCountry.setAdapter(adapter);
    }

    private void GetText() {
        name = edtName.getText().toString();
        ni = edtNi.getText().toString();
        passport = edtPassport.getText().toString();
        password = edtPass.getText().toString();
        confirm = edtConfirm.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        gender = radioButton.getText().toString();
        bdate = txtDob.getText().toString();
        country = spnCountry.getSelectedItem().toString();
        photo = photoUri.toString();

        Log.i("Image",photo);
    }

    private void Shared() {
        keyRegistrationDetails = getApplicationContext().getSharedPreferences("RegistrationDetails",MODE_PRIVATE);
        SharedPreferences.Editor prefeditor = keyRegistrationDetails.edit();
        prefeditor.putString("name",name);
        prefeditor.putString("ni",ni);
        prefeditor.putString("passport",passport);
        prefeditor.putString("password",password);
        prefeditor.putString("confirm",confirm);
        prefeditor.putString("gender",gender);
        prefeditor.putString("bdate",bdate);
        prefeditor.putString("country",country);
        prefeditor.putString("photo",photo);
        prefeditor.commit();

    }

    private boolean Validate()
    {
        if(edtName.getText().toString().equals("") || edtNi.getText().toString().equals("") ||
                edtPassport.getText().toString().equals("") || edtPass.getText().toString().equals("") || edtConfirm.getText().toString().equals("")
        || txtDob.getText().toString().equals("") || spnCountry.getSelectedItem().toString().equals("Please Select Country") || photoUri.toString().equals(""))
        {
            Toast t1= Toast.makeText(getApplicationContext(), "All fields are mandatory",
                    Toast.LENGTH_LONG);
            t1.setGravity(Gravity.CENTER, 0, 0);
            t1.show();
            return false;
        }

        if(edtPass.getText().toString().equals(edtConfirm.getText().toString()) == false) {
            Toast t1= Toast.makeText(getApplicationContext(), "Password and Confirm password should be same",
                    Toast.LENGTH_LONG);
            t1.setGravity(Gravity.CENTER, 0, 0);
            t1.show();
            return false;
        }

        return true;
    }
}
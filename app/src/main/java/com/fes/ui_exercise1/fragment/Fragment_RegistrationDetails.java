package com.fes.ui_exercise1.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


import com.fes.ui_exercise1.R;
import com.fes.ui_exercise1.ViewDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class Fragment_RegistrationDetails extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_details, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializedUI();

        try {
            loadSpinner();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            com = (Communicator)getActivity();
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement communicator");
//        }
//    }
    public void initializedUI() {
        btnCreate = getActivity().findViewById(R.id.btnCreate);
        btnCancel = getActivity().findViewById(R.id.btnCancel);
        btnDob = getActivity().findViewById(R.id.btnDOB);
        txtDob = getActivity().findViewById(R.id.txtDob);
        img = getActivity().findViewById(R.id.imgView);
        edtName = getActivity().findViewById(R.id.edtName);
        edtNi = getActivity().findViewById(R.id.edtNi);
        edtPassport = getActivity().findViewById(R.id.edtPassport);
        edtPass = getActivity().findViewById(R.id.edtPassword);
        edtConfirm = getActivity().findViewById(R.id.edtConfirm);
        spnCountry = getActivity().findViewById(R.id.spnCountry);
        radioGroup = getActivity().findViewById(R.id.radioGroup);

        btnDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getImageIntent.setType("image/*");
                startActivityForResult(getImageIntent, IMAGE_GALLERY_REQUEST);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Validate()) {
                    return;

                } else {
                    GetText();
                    Shared();
//                    com.sendData(name,passport,ni,password,confirm,gender,country);

                    Fragment fr_view_details = new Fragment_View_Details();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container,fr_view_details);
                    fragmentTransaction.commit();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setText("");
                edtNi.setText("");
                edtPassport.setText("");
                edtPass.setText("");
                edtConfirm.setText("");
            }
        });
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
        BufferedReader in = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("CountryList.txt")));

        String line = in.readLine();
        int index = 0;
        while (line != null) {

            countryList.add(line);
            line = in.readLine();
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),
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
        RadioButton radioButton = (RadioButton) getActivity().findViewById(selectedId);
        gender = radioButton.getText().toString();
        bdate = txtDob.getText().toString();
        country = spnCountry.getSelectedItem().toString();
        photo = photoUri.toString();

        Log.i("Image",photo);
    }

    private void Shared() {
        keyRegistrationDetails = getActivity().getSharedPreferences("RegistrationDetails",MODE_PRIVATE);
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
            Toast t1= Toast.makeText(getActivity(), "All fields are mandatory",
                    Toast.LENGTH_LONG);
            t1.setGravity(Gravity.CENTER, 0, 0);
            t1.show();
            return false;
        }

        if(edtPass.getText().toString().equals(edtConfirm.getText().toString()) == false) {
            Toast t1= Toast.makeText(getActivity(), "Password and Confirm password should be same",
                    Toast.LENGTH_LONG);
            t1.setGravity(Gravity.CENTER, 0, 0);
            t1.show();
            return false;
        }

        return true;
    }


}
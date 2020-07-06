package com.fes.ui_exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewDetails extends ListActivity {
    private String name,ni,passport,password,confirm,country,gender,bdate,photo;
    SharedPreferences keyRegistrationDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_view_details);
        keyRegistrationDetails = getApplicationContext().getSharedPreferences("RegistrationDetails",MODE_PRIVATE);
        name = keyRegistrationDetails.getString("name","").toString();
        ni = keyRegistrationDetails.getString("ni","").toString();
        passport = keyRegistrationDetails.getString("passport","").toString();
        password = keyRegistrationDetails.getString("password","").toString();
        confirm = keyRegistrationDetails.getString("confirm","").toString();
        country = keyRegistrationDetails.getString("country","").toString();
        gender = keyRegistrationDetails.getString("gender","").toString();
        bdate = keyRegistrationDetails.getString("bdate","").toString();
        photo = keyRegistrationDetails.getString("photo","").toString();

        ArrayList<String> list = new ArrayList<>();
        list.add(name);
        list.add(ni);
        list.add(passport);
        list.add(password);
        list.add(confirm);
        list.add(country);
        list.add(gender);
        list.add(bdate);
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.textview, list));
        ListView lv = getListView();

    }
}
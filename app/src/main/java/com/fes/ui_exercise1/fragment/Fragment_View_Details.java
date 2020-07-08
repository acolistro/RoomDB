package com.fes.ui_exercise1.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fes.ui_exercise1.DetailsAdaptor;
import com.fes.ui_exercise1.R;
import com.fes.ui_exercise1.helper.RecyclerTouchListener;
import com.fes.ui_exercise1.model.Person;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_View_Details extends Fragment {
    private String name,ni,passport,password,country,gender,bdate,photo;
    SharedPreferences keyRegistrationDetails;
    private List<Person> personList = new ArrayList<>();
    private RecyclerView rvPerson;
    //TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // tv = getActivity().findViewById(R.id.tv);
        rvPerson= getActivity().findViewById(R.id.recycler_view);


        keyRegistrationDetails = getActivity().getSharedPreferences("RegistrationDetails",MODE_PRIVATE);
        name = keyRegistrationDetails.getString("name","").toString();
        ni = keyRegistrationDetails.getString("ni","").toString();
        passport = keyRegistrationDetails.getString("passport","").toString();
        password = keyRegistrationDetails.getString("password","").toString();
        country = keyRegistrationDetails.getString("country","").toString();
        gender = keyRegistrationDetails.getString("gender","").toString();
        bdate = keyRegistrationDetails.getString("bdate","").toString();
        photo = keyRegistrationDetails.getString("photo","").toString();

        getDataSource();
        setUpRecyclerView();

    }

    public void getDataSource() {
        Person person =new Person(name,ni,passport,gender,bdate);
        personList.add(person);

//        Details detail =new Details(name,ni,passport,gender,bdate);
//        detailList.add(detail);
    }

    public void setUpRecyclerView() {
        rvPerson.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPerson.setHasFixedSize(true);
        rvPerson.setAdapter(new DetailsAdaptor(personList));
        rvPerson.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvPerson.setItemAnimator(new DefaultItemAnimator());

        rvPerson.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvPerson, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Person person = personList.get(position);
                Toast.makeText(getActivity(), person.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

}

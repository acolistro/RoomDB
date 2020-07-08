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
import android.widget.TextView;
import android.widget.Toast;

import com.fes.ui_exercise1.DetailsAdapter;
import com.fes.ui_exercise1.R;
import com.fes.ui_exercise1.helper.RecyclerTouchListener;
import com.fes.ui_exercise1.model.Details;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_Viewdetails extends Fragment {
    private String name,ni,passport,password,confirm,country,gender,bdate,photo;
    SharedPreferences keyRegistrationDetails;
    private List<Details> detailList = new ArrayList<>();
    private RecyclerView rvDetails;
    //TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewdetails, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // tv = getActivity().findViewById(R.id.tv);
        rvDetails= getActivity().findViewById(R.id.recycler_view);


        keyRegistrationDetails = getActivity().getSharedPreferences("RegistrationDetails",MODE_PRIVATE);
        name = keyRegistrationDetails.getString("name","").toString();
        ni = keyRegistrationDetails.getString("ni","").toString();
        passport = keyRegistrationDetails.getString("passport","").toString();
        password = keyRegistrationDetails.getString("password","").toString();
        confirm = keyRegistrationDetails.getString("confirm","").toString();
        country = keyRegistrationDetails.getString("country","").toString();
        gender = keyRegistrationDetails.getString("gender","").toString();
        bdate = keyRegistrationDetails.getString("bdate","").toString();
        photo = keyRegistrationDetails.getString("photo","").toString();

        getDataSource();
        setUpRecyclerView();

    }

    public void getDataSource() {
        Details detail =new Details(name,ni,passport,gender,bdate);
        detailList.add(detail);

//        Details detail =new Details(name,ni,passport,gender,bdate);
//        detailList.add(detail);
    }

    public void setUpRecyclerView() {
        rvDetails.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDetails.setHasFixedSize(true);
        rvDetails.setAdapter(new DetailsAdapter(detailList));
        rvDetails.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvDetails.setItemAnimator(new DefaultItemAnimator());

        rvDetails.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvDetails, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Details details = detailList.get(position);
                Toast.makeText(getActivity(), details.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

}

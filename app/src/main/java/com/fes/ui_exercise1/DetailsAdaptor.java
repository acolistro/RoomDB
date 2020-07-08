package com.fes.ui_exercise1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fes.ui_exercise1.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdaptor extends RecyclerView.Adapter<DetailsAdaptor.MyViewHolder>{
    List<Person> list =new ArrayList<>();

    public DetailsAdaptor(List<Person> list){
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_details, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person person= list.get(position);
        holder.name.setText(person.getName());
        holder.ni.setText(person.getNi());
        holder.passport.setText(person.getPassport());
        holder.gender.setText(person.getGender());
        holder.bdate.setText(person.getBdate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name,ni,passport,gender,bdate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tvName);
            ni = (TextView)itemView.findViewById(R.id.tvNi);
            passport = (TextView)itemView.findViewById(R.id.tvPassport);
            gender = (TextView)itemView.findViewById(R.id.tvGender);
            bdate = (TextView)itemView.findViewById(R.id.tvBdate);
        }
    }
}

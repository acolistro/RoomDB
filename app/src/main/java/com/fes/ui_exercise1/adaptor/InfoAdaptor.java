package com.fes.ui_exercise1.adaptor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fes.ui_exercise1.Constant.Constants;
import com.fes.ui_exercise1.R;
import com.fes.ui_exercise1.RegistrationDetails;
import com.fes.ui_exercise1.ViewDetails;
import com.fes.ui_exercise1.database.AppDatabase;
import com.fes.ui_exercise1.database.AppExecutors;
import com.fes.ui_exercise1.model.Info;

import org.w3c.dom.Text;

import java.util.List;

public class InfoAdaptor extends RecyclerView.Adapter<InfoAdaptor.MyViewHolder> {
    private Context context;
    private List<Info> mInfoList;

    public InfoAdaptor(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_details, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.name.setText(mInfoList.get(i).getName());
        holder.ni.setText(mInfoList.get(i).getNi());
        holder.passport.setText(mInfoList.get(i).getPassport());
        holder.passport.setText(mInfoList.get(i).getPassword());
        holder.confirm.setText(mInfoList.get(i).getConfirm());
        holder.gender.setText(mInfoList.get(i).getGender());
        holder.bdate.setText(mInfoList.get(i).getDob());
        holder.country.setText(mInfoList.get(i).getCountry());
        holder.photo.setImageURI(Uri.parse(mInfoList.get(i).getPhoto()));

    }

    @Override
    public int getItemCount() {
        if(mInfoList == null) {
            return 0;
        }
        return  mInfoList.size();
    }

    public void setTask(List<Info> infoList){
        mInfoList = infoList;
        //to update adaptor
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,ni,passport,password,confirm,gender,bdate,country;
        ImageView photo,delete,edit;
        AppDatabase mDB;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDB =AppDatabase.getInstance(context);
            photo = itemView.findViewById(R.id.imgPhoto);
            name = itemView.findViewById(R.id.tvName);
            ni = itemView.findViewById(R.id.tvNi);
            passport = itemView.findViewById(R.id.tvPassport);
            password = itemView.findViewById(R.id.tvPassword);
            confirm = itemView.findViewById(R.id.tvConfirm);
            gender = itemView.findViewById(R.id.tvGender);
            bdate = itemView.findViewById(R.id.tvBdate);
            country = itemView.findViewById(R.id.tvCountry);
            edit = itemView.findViewById(R.id.imgEdit);
            delete = itemView.findViewById(R.id.imgDelete);

//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int elementId = mInfoList.get(getAdapterPosition()).getId();
//                    Intent i = new Intent(context, RegistrationDetails.class);
//                    i.putExtra(Constants.Update_Info_Id, elementId);
//                    context.startActivity(i);
//                }
//            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                                return;
                            }else{
                                mDB.infoDao().deleteInfo(mInfoList.get(getAdapterPosition()));
                            }

                        }
                    });

                    //To remove data from adaptor
                    mInfoList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}

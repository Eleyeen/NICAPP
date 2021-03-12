package com.example.nicapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nicapp.Models.FamilyMemberModel;
import com.example.nicapp.R;

import java.util.ArrayList;
import java.util.List;

public class FamilyMemberAdapter extends RecyclerView.Adapter<FamilyMemberAdapter.ViewHolder>  {
   Context context;
    private List<FamilyMemberModel> familyModelArrayList;
    private ArrayList<FamilyMemberModel> listFamily;

    public FamilyMemberAdapter(Context context, ArrayList<FamilyMemberModel> familyModelArrayList) {
        this.context = context;
        this.familyModelArrayList = familyModelArrayList;
        this.listFamily = familyModelArrayList;

    }



    @NonNull
    @Override
    public FamilyMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview_user, parent, false);
        return new FamilyMemberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyMemberAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(familyModelArrayList.get(position).getName());
        holder.tvNum.setText(familyModelArrayList.get(position).getNum());

    }


    @Override
    public int getItemCount() {
        return familyModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvNum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvUserName);
            tvNum = itemView.findViewById(R.id.tvNum);


        }
    }
}

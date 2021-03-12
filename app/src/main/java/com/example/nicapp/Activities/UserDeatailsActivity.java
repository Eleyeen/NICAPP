package com.example.nicapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nicapp.Adapters.FamilyMemberAdapter;
import com.example.nicapp.Models.FamilyMemberModel;
import com.example.nicapp.R;
import com.example.nicapp.Models.DataBaseMobelSQLite;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDeatailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.rvUser)
    RecyclerView rvUser;
    @BindView(R.id.btnCheckinUser)
    Button btnCheckIn;
    Context context;
    LinearLayoutManager linearLayoutManager;
    ArrayList<DataBaseMobelSQLite> itemModel;
   private String[] MembeName = new String[]{"Eleyeen", "Umer", "Saqib", "Shahzeb", "Arslan"};
   private String[] MembeNum = new String[]{"1", "2", "3", "4", "5"};
    private ArrayList<FamilyMemberModel> familyModelArrayList;

    boolean vaid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_deatails);
        ButterKnife.bind(this);
        itemModel = new ArrayList<>();
        btnCheckIn.setOnClickListener(this);
        familyModelArrayList = FamilyMember();
        recylcerView();
//        String strName = GeneralUtills.getSharedPreferences(this).getString("sale", "");


    }

    private void recylcerView() {
        rvUser.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvUser.setLayoutManager(linearLayoutManager);
        FamilyMemberAdapter adapter = new FamilyMemberAdapter(this,familyModelArrayList);
        rvUser.setAdapter(adapter);
    }


    private ArrayList<FamilyMemberModel> FamilyMember() {

        ArrayList<FamilyMemberModel> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            FamilyMemberModel member = new FamilyMemberModel();
            member.setName(MembeName[i]);
            member.setNum(MembeNum[i]);
            list.add(member);
        }

        return list;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCheckinUser:
                startActivity(new Intent(this, UserPaymentActivity.class));
                break;
        }
    }

}
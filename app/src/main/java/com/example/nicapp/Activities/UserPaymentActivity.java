package com.example.nicapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nicapp.R;
import com.example.nicapp.Models.DataBaseMobelSQLite;
import com.example.nicapp.Adapters.ItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserPaymentActivity extends AppCompatActivity {
    @BindView(R.id.rvTotalTable)
    RecyclerView rvTotalTable;
    @BindView(R.id.tvFamilyNumTitle)
    TextView tvFamilyNumTitle;
    Context context;
    LinearLayoutManager linearLayoutManager;
    private ArrayList<DataBaseMobelSQLite> itemModel;
    ItemAdapter itemAdapter;
    String url = "https://iosapp.mobiroots.com/Ustore.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_payment);
        ButterKnife.bind(this);
        itemModel = new ArrayList<>();

        volley();

        recylcerView();

    }

    private void recylcerView() {
        rvTotalTable.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTotalTable.setLayoutManager(linearLayoutManager);
    }

    private void volley() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    //Log.d("amjid", "onResponse: "+response);

                    for(int i=0;i<jsonArray.length();i++){

                        JSONObject employee = jsonArray.getJSONObject(i);
                        String id=employee.getString("id");
                        String name = employee.getString("name");
                        String total_amount = employee.getString("total_amount");
                        String remaining_amount = employee.getString("remaining_amount");
                        DataBaseMobelSQLite dataBaseMobelSQLite = new DataBaseMobelSQLite();


                        dataBaseMobelSQLite.setName(name);
                        dataBaseMobelSQLite.setTotalAmount(total_amount);
                        dataBaseMobelSQLite.setReaminngAmount(remaining_amount);
                        dataBaseMobelSQLite.setId(id);

                        //Toast.makeText(UserPaymentActivity.this, name, Toast.LENGTH_LONG).show();
                        itemModel.add(dataBaseMobelSQLite);

                        Log.d("amjid",""+itemModel.get(i).getName());

                    }
                    itemAdapter = new ItemAdapter(UserPaymentActivity.this, itemModel);
                    rvTotalTable.setAdapter(itemAdapter);
                    itemAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserPaymentActivity.this,"error"+ error.toString(), Toast.LENGTH_LONG).show();

            }
        }
        );


        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);


    }
}
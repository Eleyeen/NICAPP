package com.example.nicapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nicapp.Models.DataBaseMobelSQLite;
import com.example.nicapp.R;
import com.example.nicapp.utills.GeneralUtills;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DataBaseMobelSQLite> itemList;
    private RecyclerView rvProgram;
    private GeneralUtills  generalUtills=new GeneralUtills();

    public ItemAdapter(Context context, ArrayList<DataBaseMobelSQLite> itemList) {
        this.context = context;
        this.itemList = itemList;
    }



    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_cardview_buy, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataBaseMobelSQLite databaseModel = itemList.get(position);
        holder.tvName.setText(databaseModel.getName());
        holder.tvTotalAmount.setText(databaseModel.getTotalAmount());
        holder.tvReamingAmount.setText(databaseModel.getReaminngAmount());

        holder.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = 0;
                String namv=holder.tvName.getText().toString();
                int salev= Integer.valueOf(holder.etSaleAmount.getText().toString());
                int totalv= Integer.valueOf(holder.tvTotalAmount.getText().toString());
                int remmv= Integer.valueOf(holder.tvReamingAmount.getText().toString());

                GeneralUtills.putIntegerValueInEditor(context,"sale",salev);
                sum=remmv-salev;
                holder.tvTotalAmount.setText(""+totalv);
                holder.tvReamingAmount.setText(""+sum);
//                databaseModel.setReaminngAmount(""+sum);
//                databaseModel.setSaleAmount(""+salev);
//
//                notifyDataSetChanged();
                DataBaseMobelSQLite databaseModel1 = itemList.get(position);

                updateData(databaseModel1.getId(),""+sum,""+salev);
                Log.d("ali", "onClick: "+remmv+" "+databaseModel1.getId());


            }
        });

    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvTotalAmount, tvReamingAmount;
        EditText etSaleAmount;
        View vAlert;
        ImageView btnClick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameCardView);
            tvTotalAmount = itemView.findViewById(R.id.tvTotalAmountCardView);
            tvReamingAmount = itemView.findViewById(R.id.tvReamingAmountCardView);
            etSaleAmount = itemView.findViewById(R.id.etSaleAmountCardView);
            btnClick = itemView.findViewById(R.id.btnClick);



        }
    }


    private void updateData(final String id, final String remain, final String sale)
    {
        String url=generalUtills.updateVal;
        Log.d("amjid", "onResponse: "+url);

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("amjid", "onResponse: "+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String mesg = jsonObject.getString("message");
                    Toast.makeText(context, ""+mesg, Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("amjid", "onErrorResponse: "+error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();


                map.put("id",id);
                map.put("remain",remain);
                map.put("sale",sale);

                return map;
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS));
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


}

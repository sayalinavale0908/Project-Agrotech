package com.example.kishor.farmersbid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kishor.farmersbid.Helper.Product;
import com.example.kishor.farmersbid.R;
import com.example.kishor.farmersbid.URLs.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.kishor.farmersbid.activities.LoginActivity.user;

/**
 * Created by kishor on 16-02-2018.
 */

public class Consumer_Bidding_Product extends AppCompatActivity{
    EditText newBidPrice;
    Button Add;
    String date;
    static String service;
    TextView transpotationCost;
    TextView farmername;
    ListView listView_bidder;
    ListAdapter_Win adapter_win;
    Product product;

    List<Product> list_bidder_After_Refresh;

    RadioButton radioButton1,radioButton2;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bid);

        listView_bidder=(ListView)findViewById(R.id.product_list1);
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        farmername=(TextView)findViewById(R.id.farmer_name);
        transpotationCost=(TextView)findViewById(R.id.transpotation_cost);

        farmername.setText(BidList.farmername);
        transpotationCost.setText(BidList.trans);

        list_bidder_After_Refresh = new ArrayList<>();

        Add = (Button)findViewById(R.id.btn_add_bid);
        newBidPrice = (EditText)findViewById(R.id.input_edit_consumer_bid_price);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = new Date();
        date = dateFormat.format(date1);

       adapter_win = new ListAdapter_Win(BidList.list_bidder,getApplicationContext());
       listView_bidder.setAdapter(adapter_win);


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = radioGroup.getCheckedRadioButtonId();
                if(selected==R.id.radio_yes){
                    service="yes";

                }else{
                    service="no";
                }
                AddBidPrice();
            }
        });



    }

    private void AddBidPrice() {


   final  String newBidprice1= newBidPrice.getText().toString().trim();
        newBidPrice.setText("");
   final  String date1 =date;
   final String cid= String.valueOf(user.getId());

        if (TextUtils.isEmpty(newBidprice1)) {
            newBidPrice.setError("Please enter Aadhar");
            newBidPrice.requestFocus();
            return;
        }


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_Bidser,
                    new Response.Listener<String>() {


                        @Override
                        public void onResponse(String response) {
                            //progressBar.setVisibility(View.GONE);
                            try {
                                list_bidder_After_Refresh.clear();
                                JSONObject obj = new JSONObject(response);
                                JSONArray bidderlist = obj.getJSONArray("mybidlist");

                                for (int i = 0; i < bidderlist.length(); i++) {
                                    JSONObject obj1 = bidderlist.getJSONObject(i);

                                    product = new Product();

                                    product.setConsumer_Name(obj1.getString("CName"));
                                    product.setCid(obj1.getString("CId"));
                                    product.setNew_bid_price(obj1.getString("NewBidPrice"));
                                    list_bidder_After_Refresh.add(product);

                                }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            adapter_win = new ListAdapter_Win(list_bidder_After_Refresh,getApplicationContext());
                            listView_bidder.setAdapter(adapter_win);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("BidId",BidList.list.get(0).getBid());
                    params.put("FName",BidList.list.get(0).getFname());
                    params.put("PCategory",BidList.list.get(0).getPcategory());
                    params.put("PName",BidList.list.get(0).getPname());
                    params.put("PQunatity",BidList.list.get(0).getPquntity());
                    params.put("PRate",BidList.list.get(0).getPrate());
                    params.put("PGrade",BidList.list.get(0).getPgrade());
                    params.put("PBidprice",BidList.list.get(0).getPbidprice());

                    params.put("AddCName",user.getName());
                    params.put("AddNewBidPrice",newBidprice1);
                    params.put("AddCAddress",user.getAddress());

                    params.put("TPrice",BidList.trans);

                    params.put("BDate",date1);
                    params.put("AddCId", cid);
                    params.put("Service", service);
                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        }



    }


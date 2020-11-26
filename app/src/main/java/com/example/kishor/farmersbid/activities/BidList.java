package com.example.kishor.farmersbid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.kishor.farmersbid.activities.LoginActivity.user;

/**
 * Created by student on 04-02-2018.
 */

public class BidList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner pro_Category,pro_Name;
    ListView listview;
    static List<Product> list;
    List<Product> list1;
    static List<Product> list_bidder,list_win,list_history;
    List<String> list_pro_name_spinner;
    List<String> list_cate_spinner;
    Product product;
    Set<String> spinn1;

    TextView consumer_H,consumer_W;

    String Bidid;
    static String trans;
    static String farmername;

    ArrayAdapter<String> adapter_cate;
    ArrayAdapter<String> adapter_name;
    ListViewAdapter1 adapter1,adapter2,adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_list);

       pro_Category = (Spinner)findViewById(R.id.spinner_pro_cat);
       pro_Name =(Spinner)findViewById(R.id.spinner_pro_name);
       consumer_H=(TextView)findViewById(R.id.consumer_history);
       consumer_W=(TextView)findViewById(R.id.consumer_a_history);

       listview = (ListView)findViewById(R.id.product_list1);
       list = new ArrayList<>();
        list1 = new ArrayList<>();
        list_bidder = new ArrayList<>();
        list_win = new ArrayList<>();
        list_history = new ArrayList<>();
       list_cate_spinner=new ArrayList<>();
       list_pro_name_spinner=new ArrayList<>();
       spinn1=new HashSet<>();


       for(int i=0;i<LoginActivity.Con_product.size();i++){
           list_cate_spinner.add(LoginActivity.Con_product.get(i).getPcategory());

       }
        spinn1.addAll(list_cate_spinner);
        list_cate_spinner.clear();

        list_cate_spinner.addAll(spinn1);


        spinn1.clear();

       for(int i=0;i<LoginActivity.Con_product.size();i++){
           list_pro_name_spinner.add(LoginActivity.Con_product.get(i).getPname());
       }

        spinn1.addAll(list_pro_name_spinner);
        list_pro_name_spinner.clear();
        list_pro_name_spinner.addAll(spinn1);


        adapter_cate = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,  list_cate_spinner);
        adapter_cate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pro_Category.setAdapter(adapter_cate);

        adapter_name = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,  list_pro_name_spinner);
        adapter_name.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pro_Name.setAdapter(adapter_name);



        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);


            adapter1= new ListViewAdapter1(LoginActivity.Con_product,getApplicationContext());
            listview.setAdapter(adapter1);

            if(timeOfDay==8){/////////////////////////////////////////////////time set
                consumer_H.setVisibility(View.VISIBLE);
                consumer_W.setVisibility(View.VISIBLE);
                consumer_H.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WinnerList();
                    }
                });

                consumer_W.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showHistory();
                    }
                });

            }else{
                consumer_H.setVisibility(View.INVISIBLE);
                consumer_W.setVisibility(View.INVISIBLE);
                pro_Category.setOnItemSelectedListener(this);
                pro_Name.setOnItemSelectedListener(this);
            }


    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;


        if (spinner.getId()==R.id.spinner_pro_cat||spinner.getId()==R.id.spinner_pro_name){

            String c =parent.getItemAtPosition(position).toString();
            String cd= pro_Name.getSelectedItem().toString();
            list1.clear();
            for(int i=0;i<LoginActivity.Con_product.size();i++) {
                if (LoginActivity.Con_product.get(i).getPcategory().contains(c) || LoginActivity.Con_product.get(i).getPname().contains(cd)) {

                    Product product = new Product();
                    product.setFname(LoginActivity.Con_product.get(i).getFname());
                    product.setBid(LoginActivity.Con_product.get(i).getBid());

                    product.setPcategory(LoginActivity.Con_product.get(i).getPcategory());
                    product.setPquntity(LoginActivity.Con_product.get(i).getPquntity());
                    product.setPname(LoginActivity.Con_product.get(i).getPname());
                    product.setPbidprice(LoginActivity.Con_product.get(i).getPbidprice());
                    product.setPgrade(LoginActivity.Con_product.get(i).getPgrade());
                    product.setPrate(LoginActivity.Con_product.get(i).getPrate());
                    list1.add(product);


                }
//                else {
//                    Product product = new Product();
//                    product.setPcategory(LoginActivity.Con_product.get(i).getPcategory());
//                    product.setPquntity(LoginActivity.Con_product.get(i).getPquntity());
//                    product.setPname(LoginActivity.Con_product.get(i).getPname());
//                    product.setPbidprice(LoginActivity.Con_product.get(i).getPbidprice());
//                    list1.add(product);
//
//                }


            }
            adapter1= new ListViewAdapter1(list1,getApplicationContext());
            adapter1.notifyDataSetChanged();
            listview.setAdapter(adapter1);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Product product =(Product) parent.getItemAtPosition(position);
                     Bidid=product.getBid();
                    list.add(product);
                    FarmerInfo();

                }
            });


        }


    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void FarmerInfo() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_Bidser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject obj = new JSONObject(response);
                            String mycons=obj.getString("myprice");


                            JSONArray bidderlist = obj.getJSONArray("mybidlist");

                            trans =mycons;
                            farmername =list.get(0).getFname();

                                for (int i = 0; i < bidderlist.length(); i++) {
                                    JSONObject obj1 = bidderlist.getJSONObject(i);

                                    product = new Product();

                                    product.setConsumer_Name(obj1.getString("CName"));
                                    product.setCid(obj1.getString("CId"));
                                    product.setNew_bid_price(obj1.getString("NewBidPrice"));
                                    list_bidder.add(product);

                                }

                                Intent intent = new Intent(BidList.this,Consumer_Bidding_Product.class);

                                startActivity(intent);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Intent intent = new Intent(BidList.this,Consumer_Bidding_Product.class);

                            startActivity(intent);
                        }


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

                params.put("BidId",list.get(0).getBid());
                params.put("FName",list.get(0).getFname());
                params.put("PCategory",list.get(0).getPcategory());
                params.put("PName",list.get(0).getPname());
                params.put("PQuantity",list.get(0).getPquntity());
                params.put("PRate",list.get(0).getPrate());
                params.put("PGrade",list.get(0).getPgrade());
                params.put("PBidprice",list.get(0).getPbidprice());
                params.put("AddCName",user.getName());
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);



    }

    private void WinnerList() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_product_serv,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray bidderlist = obj.getJSONArray("Bidlist");

                              if((obj==null)){
                                  for (int i = 0; i < bidderlist.length(); i++) {
                                      JSONObject obj1 = bidderlist.getJSONObject(i);

                                      product = new Product();

                                      product.setBid(obj1.getString("WBidId"));
                                      product.setConsumer_Name(obj1.getString("WCName"));
                                      product.setCid(obj1.getString("WCId"));
                                      product.setNew_bid_price(obj1.getString("WNewBidPrice"));
                                      list_win.add(product);

                                  }
                                  Intent intent = new Intent(BidList.this,Consumer_win_list.class);
                                  startActivity(intent);

                              }else{
                                  Toast.makeText(BidList.this,"You Not Win Bid",Toast.LENGTH_LONG).show();
                              }
                        } catch (JSONException e) {
                          e.printStackTrace();
                        }


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

                params.put("CId", String.valueOf(user.getId()));
                params.put("CName", user.getName());
                params.put("op","See Win History");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);




    }

    private void showHistory() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_product_serv,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray bidderlist = obj.getJSONArray("AllList");
                        if(!(obj==null)){

                            for (int i = 0; i < bidderlist.length(); i++) {
                                JSONObject obj1 = bidderlist.getJSONObject(i);

                                product = new Product();

                                product.setBid(obj1.getString("BidId"));
                                product.setConsumer_Name(obj1.getString("CName"));
                                product.setBidDate(obj1.getString("BDate"));
                                product.setNew_bid_price(obj1.getString("NewBidPrice"));
                                list_history.add(product);

                            }

                            Intent intent = new Intent(BidList.this,consumer_history.class);

                            startActivity(intent);
                        }else{
                            Toast.makeText(BidList.this,"Nothing To Show ",Toast.LENGTH_LONG).show();
                        }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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

                params.put("CId", String.valueOf(user.getId()));
                params.put("CName", user.getName());
                params.put("op","See All History");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}

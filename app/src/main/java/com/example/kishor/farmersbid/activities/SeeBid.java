package com.example.kishor.farmersbid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kishor.farmersbid.Helper.Product;
import com.example.kishor.farmersbid.R;
import com.example.kishor.farmersbid.URLs.URLs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.kishor.farmersbid.activities.LoginActivity.user;

/**
 * Created by kishor on 05-02-2018.
 */


public class SeeBid extends AppCompatActivity  {
    List<Product> list;
  static   List<Product> productList;
    Product product;
    ListView listview;


    ListViewAdapter adapter;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_history);

        productList = new ArrayList<>();
        listview = (ListView) findViewById(R.id.product_listseed);


        SeeBidHistory();




        // ListViewAdapter adapter = new ListViewAdapter(productList,getApplicationContext());
        //adding the adapter to listview


    }

    private void SeeBidHistory() {
        final int fid=user.getId();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_product_serv,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
try {



                        try {

                            Gson gson = new Gson();
                            Type type = new TypeToken<Map<String,Object>>() {}.getType();
                            Map<String,Object> FileList = gson.fromJson(response, type);
                            ArrayList<Product> myname = (ArrayList<Product>) FileList.get("myname");
                            ArrayList<Product> myprice = (ArrayList<Product>) FileList.get("myprice");
                            JSONArray  myname1=new JSONArray();
                            JSONArray myprice1=new JSONArray();

                            for (Object al:myname) {
                                myname1.put(al);
                            }

                            for(Object al:myprice){
                           if(!(al==null)) {
                               myprice1.put(al);
                            }else{

                               myprice1.put(0);
                            }
                        }
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray productListJsonArray = jsonObject.getJSONArray("myproduct");
                            JSONArray consumer_name_array=myname1;
                            JSONArray highest_bid_Array =myprice1;

                            for (int i = 0; i < productListJsonArray.length(); i++) {
                                JSONObject obj = productListJsonArray.getJSONObject(i);
                                String name =consumer_name_array.getString(i);
                                String price =highest_bid_Array.getString(i);
                                product = new Product();

                                product.setBid(obj.getString("BidId"));
                                product.setPcategory(obj.getString("PCategory"));
                                product.setPname(obj.getString("PName"));
                                product.setPquntity(obj.getString("PQuantity"));
                                product.setPbidprice(obj.getString("PBidprice"));
                                product.setConsumer_Name(name);
                                product.setNew_bid_price(price);
                                productList.add(product);
                            }

                            adapter = new ListViewAdapter(productList,getApplicationContext());
                            listview.setAdapter(adapter);


                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    try {

                                        Bundle bundle = new Bundle();
                                       // bundle.putBundle("data",productList.get(position);
                                        Intent intent = new Intent(SeeBid.this,Farmer_History_Product.class);
                                        Product product =(Product) parent.getItemAtPosition(position);

                                        String pos= String.valueOf(position);
                                        String proBid=product.getBid();
                                        String proC=product.getPcategory();
                                        String proN=product.getPname();
                                        String proQ=product.getPquntity();
                                        String proB=product.getPbidprice();
                                        String proConsumer_N=product.getConsumer_Name();
                                        String proConsumer_P=product.getNew_bid_price();

                                        intent.putExtra("pos",pos);
                                        intent.putExtra("productbid",proBid );
                                        intent.putExtra("productcate",proC );
                                        intent.putExtra("productName",proN );
                                        intent.putExtra("productQuantity",proQ );
                                        intent.putExtra("productBidprice",proB );
                                        intent.putExtra("productConsumerName",proConsumer_N );
                                        intent.putExtra("productConsumerP",proConsumer_P );
                                        startActivity(intent);
                                    }catch (Exception e){
                                        Toast.makeText(SeeBid.this, e.getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        } catch (Exception e) {
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



                params.put("FId", String.valueOf(fid));
                params.put("op", "See_Bid");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}

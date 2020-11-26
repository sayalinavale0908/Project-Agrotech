package com.example.kishor.farmersbid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TableRow;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kishor on 10-02-2018.
 */

public class Farmer_History_Product extends AppCompatActivity {

    Product product;
    List<Product> products;
    ListViewAdapter2_His adapter;
    ListView listView11;
    String bidid;
    TextView text_procategory,text_productname,text_productquantity,text_productbodprice,text_consumer_name,text_consumerprice,text_no;
    TableRow linearLayout,tableRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_farmer);
        Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
        listView11=(ListView)findViewById(R.id.product_list);
        text_procategory=(TextView)findViewById(R.id.text_pro_cat1);
        text_productname=(TextView)findViewById(R.id.pro_name111);
        text_productquantity=(TextView)findViewById(R.id.pro_price1);
        text_productbodprice=(TextView)findViewById(R.id.text_b_price1);
        text_no=(TextView)findViewById(R.id.noofWpostion);
        text_consumer_name=(TextView)findViewById(R.id.wpostionn);
        text_consumerprice=(TextView)findViewById(R.id.wprice);
        linearLayout=(TableRow) findViewById(R.id.linear1);
        tableRow=(TableRow)findViewById(R.id.linear2);
        products = new ArrayList<>();
        String pos=getIntent().getStringExtra("pos");
        bidid=getIntent().getStringExtra("productbid");

        text_procategory.setText(getIntent().getStringExtra("productcate"));
        text_productquantity.setText(getIntent().getStringExtra("productQuantity"));
        text_productbodprice.setText(getIntent().getStringExtra("productBidprice"));
        text_productname.setText(getIntent().getStringExtra("productName"));

        if(!getIntent().getStringExtra("productConsumerName").equals("")){
            linearLayout.setVisibility(View.VISIBLE);
            tableRow.setVisibility(View.VISIBLE);
            text_no.setVisibility(View.VISIBLE);
            text_consumer_name.setVisibility(View.VISIBLE);
            text_consumerprice.setVisibility(View.VISIBLE);

            text_no.setText(pos);
            text_consumer_name.setText(getIntent().getStringExtra("productConsumerName"));
            text_consumerprice.setText(getIntent().getStringExtra("productConsumerP"));
            SeeBidHistory1();
        }


    }



    private void SeeBidHistory1() {
        final String Bidid=bidid;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_BidHistory,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {



                            try {

                                JSONObject jsonObject = new JSONObject(response);

                                JSONArray productListJsonArray = jsonObject.getJSONArray("BidHistory");


                                for (int i = 0; i < productListJsonArray.length(); i++) {
                                    JSONObject obj = productListJsonArray.getJSONObject(i);
                                    product = new Product();

                                    product.setAddress(obj.getString("CAddress"));
                                    product.setConsumer_Name(obj.getString("CName"));
                                    product.setNew_bid_price(obj.getString("NewBidPrice"));
                                    products.add(product);
                                }

                                adapter = new ListViewAdapter2_His(products,getApplicationContext());
                                listView11.setAdapter(adapter);

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
                params.put("BidId",Bidid);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}

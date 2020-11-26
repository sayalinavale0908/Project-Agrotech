package com.example.kishor.farmersbid.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kishor.farmersbid.R;
import com.example.kishor.farmersbid.URLs.URLs;
import com.reginald.editspinner.EditSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.kishor.farmersbid.activities.LoginActivity.user;

/**
 * Created by kishor on 04-02-2018.
 */

public class FarmerAddProduct extends AppCompatActivity{

    Spinner pro_category,pro_grade;
    EditText pro_rate,pro_quantity,pro_bid_price;
    Button cancel,add;
    EditSpinner pro_name;
    static String product_name;
    String date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_addproduct);

        initView();
        pro_category = (Spinner)findViewById(R.id.spinner_pro_cat);
        pro_grade = (Spinner)findViewById(R.id.spinner_pro_grade);


        pro_rate = (EditText)findViewById(R.id.product_rate);
        pro_quantity = (EditText)findViewById(R.id.product_quntity);
        pro_bid_price = (EditText)findViewById(R.id.product_price);

        add =(Button)findViewById(R.id.btn_add);
        cancel=(Button)findViewById(R.id.btn_cancel);


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = new Date();
        date = dateFormat.format(date1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_name=pro_name.getText().toString().trim();
                AddProduct();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FarmerAddProduct.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {

        pro_name = (EditSpinner)findViewById(R.id.edit_product_name);

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.productN));
        pro_name.setAdapter(adapter);

        pro_name.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                Log.d("Dismiss", "mEditSpinner1 popup window dismissed!");
            }
        });

        pro_name.setOnShowListener(new EditSpinner.OnShowListener() {
            @Override
            public void onShow() {
                // maybe you want to hide the soft input panel when the popup window is showing.
                hideSoftInputPanel();

            }
        });
    }

    private void hideSoftInputPanel() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow( pro_name.getWindowToken(), 0);

        }
    }

    private void AddProduct() {

        final int fid= user.getId();
        final String fname = user.getName();
        final String pro_cat1 = pro_category.getSelectedItem().toString();
        final String pro_name1 = product_name;
        final String pro_rate1 =pro_rate.getText().toString().trim();
        final  String pro_quant=pro_quantity.getText().toString().trim();
        final  String pro_grade1 = pro_grade.getSelectedItem().toString();
        final  String pro_bid = pro_bid_price.getText().toString().trim();

        if (TextUtils.isEmpty(pro_rate1)) {
            pro_rate.setError("Please enter rate");
            pro_rate.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pro_bid)) {
            pro_bid_price.setError("Please enter bid price");
            pro_bid_price.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pro_quant)) {
            pro_quantity.setError("Please enter qauntity");
            pro_quantity.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pro_name1)) {
            pro_name.setError("Please enter name");
            pro_name.requestFocus();
            return;
        }
        EdittextEmpty();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_product_serv,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!(obj==null)) {
                                Toast.makeText(getApplicationContext(), "Product Add", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(FarmerAddProduct.this,SeeBid.class);
                                startActivity(intent);


                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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

                //params.put("BidId",user.getBidId());
                params.put("BidDate",date);
                params.put("FId", String.valueOf(fid));
                params.put("FName", fname);
                params.put("PCategory", pro_cat1);
                params.put("PName", pro_name1);
                params.put("PQuantity", pro_quant);
                params.put("PRate", pro_rate1);
                params.put("PGrade", pro_grade1);
                params.put("PBidprice",pro_bid);
                params.put("op","Create_Bid");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void EdittextEmpty() {
        pro_name.setText("");
        pro_rate.setText("");
        pro_quantity.setText("");
        pro_bid_price.setText("");
    }
}

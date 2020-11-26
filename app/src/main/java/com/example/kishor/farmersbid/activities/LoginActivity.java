package com.example.kishor.farmersbid.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kishor.farmersbid.Helper.Product;
import com.example.kishor.farmersbid.Helper.User;
import com.example.kishor.farmersbid.R;
import com.example.kishor.farmersbid.URLs.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    final Context context=this;
    Spinner userType;
    EditText inputAadher,inputpassword;

    Button Login,Register,Forgot;
    public  static String TypeOfUser="";
    static User user;
    static String userAdher1,password;
    Product product;
   static List<Product> Con_product;

RecyclerView obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       Con_product = new ArrayList<>();
        inputAadher = (EditText)findViewById(R.id.input_edit_Aadhar);
        inputpassword = (EditText)findViewById(R.id.input_edit_password);
        
        Login = (Button)findViewById(R.id.btn_login);
        Register = (Button)findViewById(R.id.btn_new_registration);
        Forgot = (Button)findViewById(R.id.btn_forgot_password);
        userType = (Spinner)findViewById(R.id.userType);
        
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeOfUser = userType.getSelectedItem().toString();
                LoginUser();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeOfUser = userType.getSelectedItem().toString();
                if(TypeOfUser.equals("Consumer")){
                    Intent intent = new Intent(LoginActivity.this,Register_cust.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(LoginActivity.this,Register_Farmer.class);
                    startActivity(intent);
                }
            }
        });

        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.anctivity_dilog, null);
                TypeOfUser = userType.getSelectedItem().toString();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);
                final EditText F_adhar =(EditText)promptsView.findViewById(R.id.forgot_adhar);

                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        userAdher1 = F_adhar.getText().toString().trim();
                                        Forgotpassword();

                                    }
                                }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

    private void LoginUser() {

        final String userAdher= inputAadher.getText().toString().trim();
        final String Userpass=inputpassword.getText().toString().trim();

        if (TextUtils.isEmpty(userAdher)) {
            inputAadher.setError("Please enter Aadhar");
            inputAadher.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Userpass)) {
            inputpassword.setError("Please enter Password");
            inputpassword.requestFocus();
            return;
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_Login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);

                        try {

                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!(obj==null)) {

                                //getting the user from the response
                                //JSONObject userJson = obj.getJSONObject("user");
                            if(TypeOfUser.equals("Farmer")) {
                                //creating a new user object
                                 user = new User(
                                        obj.getInt("FId"),
                                        obj.getString("FName"),
                                        obj.getString("FDate"),
                                        obj.getString("FAddress"),
                                        obj.getString("FLatitude"),
                                        obj.getString("FLongitude"),
                                        obj.getString("FContact"),
                                        obj.getString("FEmail"),
                                        obj.getString("FAdhar"),
                                        obj.getString("FPan"),
                                        obj.getString("FAccount"),
                                        obj.getString("FIFSC"),
                                        obj.getString("FBranch"),
                                        obj.getString("FBank"),
                                        obj.getString("FPassword")
                                );
                                finish();
                                Intent intent = new Intent(LoginActivity.this,FarmerAddProduct.class);
                                startActivity(intent);
                            }else{

                                String mycons=obj.getString("myconsumer");
                                JSONObject object = new JSONObject(mycons);

                                JSONArray productList_consumerside = obj.getJSONArray("myproduct");
                                // Con_product.clear();
                                for (int i = 0; i < productList_consumerside.length(); i++) {
                                    JSONObject obj1 = productList_consumerside.getJSONObject(i);

                                    product = new Product();
                                    product.setBid(obj1.getString("BidId"));
                                    product.setFname(obj1.getString("FName"));
                                    product.setPcategory(obj1.getString("PCategory"));
                                    product.setPname(obj1.getString("PName"));
                                    product.setPquntity(obj1.getString("PQuantity"));
                                    product.setPbidprice(obj1.getString("PBidprice"));

                                    product.setPgrade(obj1.getString("PGrade"));
                                    product.setPrate(obj1.getString("PRate"));
                                    Con_product.add(product);
                                }

                                 user = new User(
                                        object.getInt("CId"),
                                         object.getString("CName"),
                                         object.getString("CDate"),
                                         object.getString("CAddress"),
                                         object.getString("CLatitude"),
                                         object.getString("CLongitude"),
                                         object.getString("CContact"),
                                         object.getString("CEmail"),
                                         object.getString("CAdhar"),
                                         object.getString("CPan"),
                                         object.getString("CAccount"),
                                         object.getString("CIFSC"),
                                         object.getString("CBranch"),
                                         object.getString("CBank"),
                                         object.getString("CPassword")
                                );
                                finish();
                                Intent intent = new Intent(LoginActivity.this,BidList.class);
                                startActivity(intent);

                            }
//


                            } else {
                                Toast.makeText(getApplicationContext(),"Enter Adhar No/Password Correct", Toast.LENGTH_SHORT).show();
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
                params.put("Adhar",userAdher);
                params.put("password",Userpass );
                params.put("UserType",TypeOfUser);
                params.put("op","Login");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void Forgotpassword(){

        final String Adharno = userAdher1;

        if (TextUtils.isEmpty(Adharno)) {
            inputAadher.setError("Please enter Aadhar");
            inputAadher.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_forgot,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject obj = new JSONObject(response);

                            if(obj!=null){

                               Toast.makeText(LoginActivity.this,"Your password is send to your mobile no",Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(LoginActivity.this,"Enter correct Adhar no/User Type",Toast.LENGTH_LONG).show();
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
                params.put("op","submit");
                params.put("Adhar",Adharno);
                params.put("UserType", TypeOfUser);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}

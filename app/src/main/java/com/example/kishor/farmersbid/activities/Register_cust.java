package com.example.kishor.farmersbid.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kishor.farmersbid.R;
import com.example.kishor.farmersbid.URLs.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by kishor on 04-02-2018.
 */

public class Register_cust extends AppCompatActivity {

    EditText edit_name,edit_dob,edit_address,edit_contact,edit_lat,edit_long,edit_email,edit_adhar,edit_pan,edit_account,edit_ifsc,edit_branch,edit_bank,edit_password;
    Button Register,Login;
    Calendar myCalendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_registration);

       myCalendar = Calendar.getInstance();
        

        edit_name =(EditText)findViewById(R.id.input_edit_name);
        edit_dob  =(EditText)findViewById(R.id.input_edit_dob);
        edit_address =(EditText)findViewById(R.id.input_edit_address);
        edit_contact=(EditText)findViewById(R.id.input_edit_contact);
        edit_email =(EditText)findViewById(R.id.input_edit_email);
        edit_adhar =(EditText)findViewById(R.id.input_edit_adhar);
        edit_pan =(EditText)findViewById(R.id.input_edit_pan);
        edit_account =(EditText)findViewById(R.id.input_edit_account);
        edit_ifsc =(EditText)findViewById(R.id.input_edit_ifsc);
        edit_branch  =(EditText)findViewById(R.id.input_edit_branch);
        edit_bank =(EditText)findViewById(R.id.input_edit_bank_name);
        edit_password =(EditText)findViewById(R.id.input_edit_password);
        edit_lat = (EditText)findViewById(R.id.input_edit_lat);
        edit_long = (EditText)findViewById(R.id.input_edit_long);

        Register =(Button)findViewById(R.id.btn_registration);
        Login =(Button)findViewById(R.id.btn_new_registration);

        edit_dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Register_cust.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register_user();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_cust.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Register_user() {

        final String username =edit_name.getText().toString().trim();
        final String date = edit_dob.getText().toString().trim();
        final String address =edit_address.getText().toString().trim();
        final String contact = edit_contact.getText().toString().trim();
        final String email =edit_email.getText().toString().trim();
        final String adhar =edit_adhar.getText().toString().trim();
        final String pan =edit_pan.getText().toString().trim();
        final String account =edit_account.getText().toString().trim();
        final String ifsc =edit_ifsc.getText().toString().trim();
        final String branch =edit_branch.getText().toString().trim();
        final String bank =edit_bank.getText().toString().trim();
        final String cpassword =edit_password.getText().toString().trim();
        final String Latitude =edit_lat.getText().toString().trim();
        final String Longitude=edit_long.getText().toString().trim();


        if (TextUtils.isEmpty(username)) {
            edit_name.setError("Please enter Name");
            edit_name.requestFocus();
            return;
        }
        String MobilePattern="[0-9]{10}";
        if (TextUtils.isEmpty(contact)||!contact.toString().matches(MobilePattern)||contact.toString().trim().length()!=10) {
            edit_contact.setError("Please enter correct contact");
            edit_contact.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            edit_address.setError("Please enter address");
            edit_address.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            edit_email.setError("Please enter email");
            edit_email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(adhar)) {
            edit_adhar.setError("Please enter Aadhar");
            edit_adhar.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(account)) {
            edit_account.setError("Please enter account");
            edit_account.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(ifsc)) {
            edit_ifsc.setError("Please enter ifsc");
            edit_ifsc.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(bank)) {
            edit_bank.setError("Please enter bank");
            edit_bank.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(cpassword)) {
            edit_password.setError("Please enter password");
            edit_password.requestFocus();
            return;
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_register_consumer,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!(obj==null)) {
                                Toast.makeText(getApplicationContext(),"Successfully registration ", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(Register_cust.this,LoginActivity.class);
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
                params.put("CName", username);
                params.put("CDate", date);
                params.put("CAddress", address);
                params.put("lat",Latitude);
                params.put("lon",Longitude);
                params.put("CContact", String.valueOf(contact));
                params.put("CEmail", email);
                params.put("CAdhar", adhar);
                params.put("CPan", pan);
                params.put("CAccount",account );
                params.put("CIFSC", ifsc);
                params.put("CBranch", branch);
                params.put("CBank", bank);
                params.put("CPassword", cpassword);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edit_dob.setText(sdf.format(myCalendar.getTime()));


    }

}

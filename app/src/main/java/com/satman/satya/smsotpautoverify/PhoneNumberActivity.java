package com.satman.satya.smsotpautoverify;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberActivity extends AppCompatActivity {

    EditText etphone;
    String sephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);


        etphone = (EditText) findViewById(R.id.etphone);


    }




    public void textclicked(View view){

        sephone = etphone.getText().toString().trim();

        if (TextUtils.isEmpty(sephone)){
            etphone.setError("Please Enter Mobile Number");
            etphone.setFocusable(true);
        }  else if (phoneValidator(sephone)==false){
            etphone.setError("Please Enter Valid Mobile Number");
            etphone.setFocusable(true);
        }else {

            sendphone();


            Log.d("satphone",sephone);
        }


    }



    public boolean phoneValidator(String phone){
        Pattern pattern;
        Matcher matcher;
        final String PHONE_PATTERN ="[7-9]\\d{9}";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phone);
        return matcher.matches();

    }



    public void sendphone(){


        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "url here",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server

                        Log.d("phoneres",response);
                        pDialog.hide();
                        JSONObject json = null;

                        //    String k = response;

                        try {
                            json = new JSONObject(response);
                            JSONObject e = json.getJSONObject("CompanyRegistration");
                            String statuscode = e.getString("StatusCode");
                            String statusmessage = e.getString("StatusText");


                            // json= new JSONObject(response.toString());
                            //    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                            //     Toast.makeText(getApplicationContext(),statuscode+"\n"+statusmessage,Toast.LENGTH_SHORT).show();

                            if (statuscode.equals("200")) {


                                Log.d("testlogin",response+"\n\n"+statuscode);


                                SharedPreferences sp = getSharedPreferences("my_prefer_login",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("keyphone",sephone);
                                editor.commit();


                                startActivity(new Intent(getApplicationContext(),NewOtpActivity.class));
                                finish();



                            }  else{
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toast.makeText(getApplicationContext(), statusmessage , Toast.LENGTH_LONG).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                // params.put("name", nameString);
                params.put("mobile", sephone);
                // params.put("type", "Android");

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);






    }





}

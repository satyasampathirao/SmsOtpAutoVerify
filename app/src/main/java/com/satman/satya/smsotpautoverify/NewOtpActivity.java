package com.satman.satya.smsotpautoverify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import swarajsaaj.smscodereader.interfaces.OTPListener;
import swarajsaaj.smscodereader.receivers.OtpReader;

public class NewOtpActivity extends AppCompatActivity  implements OTPListener {

    EditText ed_otp;

    //reference: for auto otp detection
    //https://github.com/swarajsaaj/otpReader

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_otp);
        OtpReader.bind(this,"INVITE");

        ed_otp = (EditText) findViewById(R.id.ed_otp);
    }


    @Override
    public void otpReceived(String smsText) {
        //Do whatever you want to do with the text


        String strfinal = smsText.replaceAll("\\D+","");
        Log.d("Otp",strfinal);
        Toast.makeText(this,"Got "+strfinal,Toast.LENGTH_LONG).show();


        ed_otp.setText(strfinal);




    }



}

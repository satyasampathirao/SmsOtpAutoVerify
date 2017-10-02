package com.satman.satya.smsotpautoverify;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;


public class OtpActivity extends AppCompatActivity {


    EditText et;
    String User_Permissions[] = {Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS,Manifest.permission.SEND_SMS,Manifest.permission.INTERNET,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if((ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS))== PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this,User_Permissions,101);
        }
        if ((ContextCompat.checkSelfPermission(this,Manifest.permission.READ_SMS))==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,User_Permissions,102);
        }
        if ((ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS))==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,User_Permissions,103);
        }
        if ((ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET))==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,User_Permissions,104);
        }
        else {
            setContentView(R.layout.activity_otp);
            et = (EditText) findViewById(R.id.ed_otp);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101)
        {
            if (User_Permissions[0].equals( permissions[0]))
            {
                if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (requestCode == 102)
        {
            if (User_Permissions[1].equals( permissions[1]))
            {
                if (grantResults[1]== PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (requestCode == 103)
        {
            if (User_Permissions[2].equals( permissions[2]))
            {
                if (grantResults[2]== PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (requestCode == 104)
        {
            if (User_Permissions[3].equals( permissions[3]))
            {
                if (grantResults[3]== PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");
                //Do whatever you want with the code here
                Toast.makeText(context, "Message am getting is : "+message, Toast.LENGTH_SHORT).show();
            }
        }
    };



}

package com.example.asus.emailsender;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import java.util.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button sendEmail;
    String CSV = "roshan.kumar01@jungleworks.co,rajat.dhamija@jungleworks.com,ankit@jungleworks.com,chanderveer.singh@jugnoo.in,asim.mehta@jungleworks.com";


     @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendEmail=findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.sendEmail){
            try {

                Log.i("xyz","btn was clicked");
                sendemail();
            }catch (Exception e){
                Log.e("SendMail", e.getMessage(), e);
            }
        }

    }

    private void sendemail() {

        new Thread(new Runnable() {
            public void run() {
                try {
                    GmailSender sender = new GmailSender("roshankumarroshi@gmail.com", "kumar@123");
                    String[] values = CSV.split(",");
                    for(int i=0;i<=values.length-1;i++){
                        sender.sendMail("Bulk email","Email sent from Android app.","roshankumarroshi@gmail.com",values[i]);
                    }
                    Toast.makeText(MainActivity.this, "Email sent", Toast.LENGTH_SHORT).show();

 } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

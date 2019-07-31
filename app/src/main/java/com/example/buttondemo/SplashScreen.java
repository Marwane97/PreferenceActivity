package com.example.buttondemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
     TextView txt;
     int dateHour;
     int dateMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
     //   SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        try {
         //   Date date = calendar.getTime();
            dateMinute = calendar.get(Calendar.MINUTE);
            dateHour = calendar.get(Calendar.HOUR);
        } catch (Exception e) {
            e.printStackTrace();
        }

        txt = (TextView) findViewById(R.id.txt);
        doControl();
       // txt.setText(String.valueOf(dateHour));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
    void doControl(){
        if(dateHour >= 5 && dateHour < 18){
            txt.setText("Iyi günlerr");
        }else if(dateHour >= 18 && dateHour < 23){
            txt.setText("Iyi akşamlar");
        }else{
            txt.setText("Iy geceler");
        }
    }
}

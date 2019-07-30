package com.example.buttondemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
int count ;
Button btn;
SharedPreferences preferences;
SharedPreferences ayarlar;
RelativeLayout layout;
boolean sound;
boolean vibration;

Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
      // preferences = PreferenceManager.getDefaultSharedPreferences(this);
        ayarlar = PreferenceManager.getDefaultSharedPreferences(this);
        layout = (RelativeLayout) findViewById(R.id.layout);
       if(savedInstanceState != null){
           count = savedInstanceState.getInt("count");
       }else{
           count = 0;
       }
        //count = preferences.getInt("count",0);
        setAyarlar();
        btn.setText(String.valueOf(count));
        toolbar.setTitle("My Button Demo");
        toolbar.setTitleTextColor(Color.WHITE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                btn.setText(String.valueOf(count));
            }
        });
        setSupportActionBar(toolbar);
    }
    /**
     * //second way to save items
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("count",count);
        editor.commit();
        super.onPause();
    }**/
    void setAyarlar(){
        String posBackground = ayarlar.getString("background","3");
        switch (Integer.valueOf(posBackground)){
            case 1 : layout.setBackgroundColor(Color.RED);
                break;
            case 2 : layout.setBackgroundColor(Color.YELLOW);
                break;
            case 3 : layout.setBackgroundColor(Color.GREEN);
                break;
            case 4 : layout.setBackgroundColor(Color.BLUE);
                break;
            case 5 : layout.setBackgroundColor(Color.GRAY);
                break;
            case 6 : layout.setBackgroundColor(Color.rgb(128,0,128));
                break;
                default: layout.setBackgroundResource(R.color.colorSecondary);
        }

        String posButton = ayarlar.getString("button","4");
        switch (Integer.valueOf(posButton)){
            case 1 : btn.setBackgroundColor(Color.RED);
                break;
            case 2 : btn.setBackgroundColor(Color.YELLOW);
                break;
            case 3 : btn.setBackgroundColor(Color.GREEN);
                break;
            case 4 : btn.setBackgroundColor(Color.BLUE);
                break;
            case 5 : btn.setBackgroundColor(Color.GRAY);
                break;
            case 6 : btn.setBackgroundColor(Color.rgb(128,0,128));
                break;
                default: btn.setBackgroundResource(R.drawable.btn_selector);
        }
        sound = ayarlar.getBoolean("sound",false);
        vibration = ayarlar.getBoolean("vibration", false);
        ayarlar.registerOnSharedPreferenceChangeListener(MainActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sittings ){
          Intent intent = new Intent(getApplicationContext(),Settings.class);
          startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /** way to save items **/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count",count);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        setAyarlar();
    }
}

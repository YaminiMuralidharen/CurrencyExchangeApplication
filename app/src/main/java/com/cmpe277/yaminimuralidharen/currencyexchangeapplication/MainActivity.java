package com.cmpe277.yaminimuralidharen.currencyexchangeapplication;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView dol_TextView,country_TextView;
    private int dollar_amount;
    private double converted_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dol_TextView=(TextView)findViewById(R.id.dlr_value_txt_view);
        country_TextView=(TextView)findViewById(R.id.country_txt_view);
        Bundle bundle=getIntent().getExtras();
       if(bundle!=null)
        {
            dollar_amount= bundle.getInt("dol_val");
           converted_amount= bundle.getDouble("con_currency");
            Log.d("currencyexchange",dollar_amount + "ff ");
            dol_TextView.setText(String.valueOf(dollar_amount));
            country_TextView.setText(bundle.getString("country"));
        }
    }


    public void ApplyConversion(View view) {
    Intent convertedIntent=  new Intent();
    convertedIntent.setAction("com.currencyconverterReceiver.value");
    convertedIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    convertedIntent.putExtra("convamt",converted_amount);
    convertedIntent.setComponent(new ComponentName("com.cmpe277.yaminimuralidharen.currencyconverter","com.cmpe277.yaminimuralidharen.currencyconverter.ConvertedReceiver"));
    sendBroadcast(convertedIntent);
    MainActivity.this.finish();
    }


    public void CloseApplication(View view) {
        MainActivity.this.finish();
    }



}

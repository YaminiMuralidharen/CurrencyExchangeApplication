package com.cmpe277.yaminimuralidharen.currencyexchangeapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import android.util.Log;
public class CurrencyExchangeReceiver extends BroadcastReceiver {
private int dollar_value;
private double converted_value;
private String country;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d("currencyReceiver","inside on receive");

        Bundle bundle=intent.getExtras();
        if(intent.getAction().equals("com.currencyexchangeReceiver.value")) {
            if (bundle !=null) {
                dollar_value=bundle.getInt("dlr_amt");
                country=bundle.getString("cur_country");
                Toast.makeText(context, String.valueOf(dollar_value),Toast.LENGTH_SHORT).show();

            }
            switch (country) {
                case "Euro":
                    converted_value = (double)Math.round(dollar_value * 0.81);
                    break;
                case "Indian Rupee":
                    converted_value = (double)Math.round(dollar_value * 64.39);
                    break;
                case "British Pound":
                    converted_value = (double)Math.round(dollar_value * 0.71);
                    break;
                    default:
                        break;

            }
            PackageManager pm = context.getPackageManager();
            Intent exchangeIntent = pm.getLaunchIntentForPackage("com.cmpe277.yaminimuralidharen.currencyexchangeapplication");
            exchangeIntent.putExtra("dol_val", dollar_value);
            exchangeIntent.putExtra("country",country);
            exchangeIntent.putExtra("con_currency",converted_value);
            context.startActivity(exchangeIntent);
            return;
        }
    }
}

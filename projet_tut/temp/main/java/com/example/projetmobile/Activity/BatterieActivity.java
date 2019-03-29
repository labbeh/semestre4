package com.example.projetmobile.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.example.projetmobile.R;

/**
 * Created by jc162098 on 20/03/19.
 */

public class BatterieActivity extends AppCompatActivity {


    private TextView batteryTxt;
    private TextView chargeTxt;
    private TextView mcTxt;
    DisplayMetrics metrics = new DisplayMetrics();
    private static int level;


    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent)
        {
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            batteryTxt.setText("La batterie fonctione à : "+String.valueOf(level) + "%");

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            if(status == BatteryManager.BATTERY_STATUS_FULL)
            {
                chargeTxt.setText("La battrerie est chargé");
            }
            else
            if(status == BatteryManager.BATTERY_STATUS_CHARGING)
            {
                chargeTxt.setText("La battrerie est en cours de chargement");
            }
            else
                chargeTxt.setText("La battrerie n'est pas chargé");




            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if(usbCharge == true)
            {
                mcTxt.setText("La battrerie ce charge par port usb");
            }

            if(acCharge == true)
            {
                mcTxt.setText("La battrerie ce charge par port ac");
            }

            if(acCharge == false && usbCharge == false)
                mcTxt.setText("Aucun moyen de recharge");


        }
    };

    public static int getLevel(){return level;}

    @Override
    public void onCreate(Bundle b)
    {


        super.onCreate(b);
        setContentView(R.layout.stat_batterie);
        batteryTxt  = (TextView) this.findViewById(R.id.mainBatterie);
        chargeTxt   = (TextView)this.findViewById(R.id.chargeBatterie);
        mcTxt       = (TextView)this.findViewById(R.id.moyenCharge);
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


    }




}
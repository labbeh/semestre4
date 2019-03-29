package com.example.projetmobile.Activity;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.projetmobile.Object.GameLink;
import com.example.projetmobile.Object.GameManager;

import static java.lang.System.out;


public class GameActivity extends AppCompatActivity implements SensorEventListener
{

    public GameLink         gameLink;


    private SensorManager   mSensorManager;
    private Sensor          mAccelerometer;



    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        gameLink = new GameLink(GameActivity.this);
        setContentView(gameLink);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);



    }

    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event)
    {

        Sensor mySensor = event.sensor;


        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            gameLink.sensorEvent(x,y,z);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}


}

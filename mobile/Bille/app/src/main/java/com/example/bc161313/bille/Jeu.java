package com.example.bc161313.bille;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import java.util.ArrayList;

public class Jeu extends AppCompatActivity implements SensorEventListener {
    /**
     * Score incrémenté à chaque fois que le joueur tape
     * dans le goal
     * */
    private int score = 0;

    /**
     * Représentation du joueur
     * */
    BilleJoueur bille;

    /**
     * Représentation du goal
     * */
    Goal goal;

    /**
     * Billes ennemies
     * */
    List<Ennemi> ennemis = new ArrayList<>();

    /**
     * Pour tout dessiner
     * */
    Canvas canvas = new Canvas();

    /**
     * Vue du jeu
     * */
    private Vue vue;

    private SensorManager   mSensorManager;
    private Sensor          mAccelerometer;

    /**
     * Position x max sur l'écran de l'appareil courant
     * */
    private int xMaxEcran;

    /**
     * Position y max sur l'écran de l'appareil courant
     * */
    private int yMaxEcran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // on s'adapte à la taille de l'écran
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        bille = new BilleJoueur(size.x/2,size.y/2);
        goal = new Goal((int)(Math.random() * size.x), (int)(Math.random() * size.y));

        this.xMaxEcran = size.x;
        this.yMaxEcran = size.y;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_GAME);

        vue = new Vue(this);
        afficherToutesBilles();
        setContentView(vue);
    }

    /**
     * Permet de dessiner les billes sur l'écran
     * */
    public void afficherBille(Bille bille) {
        vue.draw(canvas);
    }

    /**
     * Affiche l'ensembles des billes présentes
     * */
    public void afficherToutesBilles(){
        afficherBille(bille);
        afficherBille(goal);

        for(Bille b : ennemis)
            afficherBille(b);
    }

    /**
     * Retourne la couleur android.graphics.Color équivalent au nom de la couleur passée en paramètre
     * @param coul nom de la couleur
     * @return une couleur définie dans les constantes android.graphics.Color en int
     * */
    private int getColorByName(String coul){
        if(coul.equals("rouge")) return Color.RED;
        else if(coul.equals("bleu")) return Color.BLUE;
        return Color.GREEN;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;


        if(mySensor.getType() == mySensor.TYPE_ACCELEROMETER){
            float posX = sensorEvent.values[0];
            float posY = sensorEvent.values[1];

            if(bille.getX()-(int)posX > 0 && bille.getX()-(int)posX < xMaxEcran)
                bille.setX(bille.getX()-(int)posX);

            if(bille.getY()+(int)posY > 0 && bille.getY()+(int)posY < yMaxEcran)
                bille.setY(bille.getY()+(int)posY);

            // si la bille touche le goal
            if(bille.memePosition(goal)) {
                ennemis.add(new Ennemi((int) (Math.random() * xMaxEcran), (int) (Math.random() * yMaxEcran)));
                goal.setX( (int)(Math.random() * xMaxEcran) );
                goal.setY( (int)(Math.random() * yMaxEcran) );
                score++;
            }

            for(Ennemi e: ennemis)
                if(bille.memePosition(e))
                    finJeu();

            vue.invalidate();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /**
     * Retourne a l'activité 1 avec passage du score
     * */
    public void finJeu(){
        Intent intent = new Intent();
        intent.putExtra("score", score);
        setResult(RESULT_OK, intent);
        finish();
    }

    class Vue extends View{
        public Vue(Jeu jeu){
            super(jeu);
        }

        @Override
        protected void onDraw(Canvas c){
            Paint paint = new Paint();

            // on dessine le joueurs
            paint.setColor(getColorByName(bille.getCoul()));
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);

            c.drawCircle(bille.getX(), bille.getY(), bille.TAILLE, paint);

            // on dessine le goal
            paint.setColor(getColorByName(goal.getCoul()));
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);

            c.drawCircle(goal.getX(), goal.getY(), bille.TAILLE, paint);

            // on affiche les ennemis
            for(Ennemi e : ennemis){
                paint.setColor(getColorByName(e.getCoul()));
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);

                c.drawCircle(e.getX(), e.getY(), bille.TAILLE, paint);
            }
        }
    }
}

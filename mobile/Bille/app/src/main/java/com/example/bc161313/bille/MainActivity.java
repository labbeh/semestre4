package com.example.bc161313.bille;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int nbPts;

    /**
     * Instance de la classe qui va gérer la lecture et l'écriture de fichiers
     * */
    private GestionFichiers gf = new GestionFichiers(this);

    /**
     * Boradcast receiver pour les informations de la batterie
     * */
    private  GestionBatterie batterie = new GestionBatterie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on enregistre les BR pour les infos batterie
        registerReceiver(batterie, new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(batterie, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));
        registerReceiver(batterie, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        gf.ecrire("yolo");
    }

    public void onExit (View view) { finish();}

    public void ptsGagne(View view){
        TextView score = (TextView) findViewById(R.id.nbPoint);
        this.nbPts ++;
        score.setText("Score : " + nbPts);
    }

    /**
     * Lance l'Activity du jeu
     * */
    public void lancerJeu(View view){
        //gf.lire();
        Intent intent = new Intent(this, Jeu.class);
        //intent.putExtra("nbClicksInit", nbClick);
        startActivity(intent);
    }

    /**
     * Lance l'activity d'affichage des stats batterie
     * */
    public void lancerBatterie(View view){
        //String infosBatterie = gf.lire();
        Intent intent = new Intent(this, ActivityBatterie.class);
        //intent.putExtra("infos", "proute");
        startActivity(intent);
    }


    public void onSaveInstanceState(Bundle bagOfData){
        bagOfData.putInt("nb_calls", nbPts);
        super.onSaveInstanceState(bagOfData);
    }

    public void onRestoreInstanceState(Bundle bagOfData){
        super.onRestoreInstanceState(bagOfData);
        nbPts = bagOfData.getInt("nb_calls");
        TextView score = (TextView) findViewById(R.id.nbPoint);
        score.setText("Score : " + nbPts);
    }
}

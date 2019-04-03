package com.example.bc161313.bille;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int score = 0;

    /**
     * Instance de la classe qui va gérer la lecture et l'écriture de fichiers
     * */
    private GestionFichiers gf = new GestionFichiers(this);

    /**
     * Boradcast receiver pour les informations de la batterie
     * */
    private  GestionBatterie batterie = new GestionBatterie(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on enregistre les BR pour les infos batterie
        registerReceiver(batterie, new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(batterie, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));
        registerReceiver(batterie, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public void onExit (View view) { finish();}

    /**
     * Lance l'Activity du jeu
     * */
    public void lancerJeu(View view){
        Intent intent = new Intent(this, Jeu.class);
        startActivityForResult(intent, 1);
    }

    /**
     * Lance l'activity d'affichage des stats batterie
     * */
    public void lancerBatterie(View view){
        Intent intent = new Intent(this, ActivityBatterie.class);
        intent.putExtra("infos", gf.lire());
        startActivity(intent);
    }

    /**
     * Permet d'écrire dans le fichier d'infos batterie
     * @param aEcrire chaine de caractère à écrire
     * */
    public void ecrire(String aEcrire){
        gf.ecrire(aEcrire);
    }


    public void onSaveInstanceState(Bundle bagOfData){
        bagOfData.putInt("score", score);
        super.onSaveInstanceState(bagOfData);
    }

    public void onRestoreInstanceState(Bundle bagOfData){
        super.onRestoreInstanceState(bagOfData);
        this.score = bagOfData.getInt("score");

        TextView tv = findViewById(R.id.tvScore);
        tv.setText("Score: " +score);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int score = data.getIntExtra("score", 0);
        this.score = score;

        TextView tv = findViewById(R.id.tvScore);
        tv.setText("Score: " +score);
    }
}

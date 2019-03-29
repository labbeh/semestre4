package com.example.projetmobile.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.projetmobile.R;

public class MainActivity extends AppCompatActivity {

    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ptsGagne(View view){
        TextView score = (TextView) findViewById(R.id.score);
        this.score ++;
        score.setText("Score : " + score);
    }

    public void jeu(View v) {
        Intent intentJeu = new Intent(this,GameActivity.class);
        startActivity(intentJeu);
    }

    public void statBatterie(View v)
    {
        Intent intentBatterie = new Intent(this, BatterieActivity.class);
        startActivity(intentBatterie);
    }


    public void onSaveInstanceState(Bundle bagOfData){
        bagOfData.putInt("nb_calls", score);
        super.onSaveInstanceState(bagOfData);
    }

    public void onRestoreInstanceState(Bundle bagOfData){
        super.onRestoreInstanceState(bagOfData);
        score = bagOfData.getInt("nb_calls");
        TextView score = (TextView) findViewById(R.id.score);
        score.setText("Score : " + score);
    }
    public void onExit (View view) { finish();}
}
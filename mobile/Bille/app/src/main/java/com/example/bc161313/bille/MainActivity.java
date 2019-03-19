package com.example.bc161313.bille;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int nbPts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onExit (View view) { finish();}

    public void ptsGagne(View view){
        TextView score = (TextView) findViewById(R.id.nbPoint);
        this.nbPts ++;
        score.setText("Score : " + nbPts);
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

package com.ds.lh150094.projetds;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class Activity2 extends AppCompatActivity {
    /**
     * Nombre de clicks initial transmis par l'activité principal
     * */
    private int nbClickInit;

    /**
     * Nombre de clicks dans l'activité courante
     * */
    private int nbClick;

    /**
     * Texte clicks init
     * */
    private String txtInit;

    /**
     * Texte nbClicks courant
     * */
    private String txtCourant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        TextView clickInit = findViewById(R.id.clickInit);
        TextView clickCourant = findViewById(R.id.nbClick);

        txtInit = clickInit.getText().toString();
        txtCourant = clickCourant.getText().toString();

        Intent intent = getIntent();
        nbClickInit = nbClick = intent.getIntExtra("nbClicksInit", 0);

        clickInit.setText(txtInit +nbClickInit);
        clickCourant.setText(txtCourant +nbClick);
    }

    /**
     * Incrémente le nombre de clicks
     * */
    public void incClick(View view){
        TextView tv = findViewById(R.id.nbClick);

        nbClick++;
        tv.setText(txtCourant +nbClick);
    }

    /**
     * Retourne a l'activité 1
     * */
    public void retour(View view){
        Intent intent = new Intent();
        intent.putExtra("nbClicks", nbClick);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Sauvegarde le nombre de click lors d'un changement d'état
     * */
    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putInt("nbClick", nbClick);
        bundle.putInt(("nbClickInit"), nbClickInit);
    }

    /**
     * Restauration du nombre de click après changement d'état
     * */
    @Override
    public void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);

        nbClick = bundle.getInt("nbClick");
        nbClickInit = bundle.getInt("nbClickInit");

        TextView tv = findViewById(R.id.nbClick);
        tv.setText(txtCourant +nbClick);

        tv = findViewById(R.id.clickInit);
        tv.setText(txtInit +nbClickInit);
    }

}

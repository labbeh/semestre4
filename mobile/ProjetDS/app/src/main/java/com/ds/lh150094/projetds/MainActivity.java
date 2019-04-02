package com.ds.lh150094.projetds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /**
     * Nombre de fois ou l'utilisateur a toucher le bouton "cliquez moi"
     * */
    private int nbClick;

    /**
     * Texte de base du TextView qui contient le nombre de click
     * dont on changera le nombre à chaque incrémentation
     * */
    private String txtBase;

    /**
     * Point d'entrée de l'activité principal
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nbClick = 0;

        TextView tv = findViewById(R.id.viewClick);
        txtBase = tv.getText().toString();
        tv.setText(txtBase +nbClick);
    }

    /**
     * Méthode lancée par le bouton "cliquez moi" qui incrémente le nombre de click
     * */
    public void incClick(View view){
        TextView tv = findViewById(R.id.viewClick);

        nbClick++;
        tv.setText(txtBase +nbClick);
    }

    /**
     * Lancer la deuxième activity
     * */
    public void activity2(View view){
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("nbClicksInit", nbClick);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            nbClick = data.getIntExtra("nbClicks", 0);

            TextView tv = findViewById(R.id.viewClick);
            tv.setText(txtBase + nbClick);
        }
    }

    /**
     * Ferme complètement l'application
     * */
    public void quitter(View view){
        finish();
    }

    /**
     * Sauvegarde le nombre de click lors d'un changement d'état
     * */
    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putInt("nbClick", nbClick);
    }

    /**
     * Restauration du nombre de click après changement d'état
     * */
    @Override
    public void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);
        nbClick = bundle.getInt("nbClick");
        TextView tv = findViewById(R.id.viewClick);
        tv.setText(txtBase +nbClick);
    }
}
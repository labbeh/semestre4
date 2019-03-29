package com.example.bc161313.bille;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * @author hugo labbé, loan cadorel, clément baron
 * @version 2018-03-28, 1.0
 * Cette classe s'occupe de récupérer les informations sur la batterie
 * et d'écrire les modifications dans un fichier lors des changement
 * d'état (taux de chargement, branchement et débranchment secteur)
 * Les informations seront stockées sous la forme:
 * tauxCharge;enCharge;dateHeure
 * */
public class GestionBatterie extends BroadcastReceiver {
    /**
     * Niveau de chargement de la batterie en %
     * */
    private int niveau = 0;

    /**
     * Vrai si la téléphone est sur batterie
     * */
    private boolean surBatterie;

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder(); // pour construire la chaine à écrire dans le fichier
        Date dateCourante = Calendar.getInstance().getTime();

        niveau = intent.getIntExtra("level", 0);

        sb.append(niveau).append(';');
       /* if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED))
            Toast.makeText(context,"Chargé a : " +niveau, Toast.LENGTH_SHORT).show();*/

        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
            Toast.makeText(context,"Branché sur secteur", Toast.LENGTH_SHORT).show();
        else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED))
            Toast.makeText(context,"Débranché", Toast.LENGTH_SHORT).show();
    }
}
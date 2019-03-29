package com.example.bc161313.bille;

import android.hardware.Sensor;

/**
 * Created by bc161313 on 19/03/19.
 */

public class BilleJoueur extends Bille{
    /**
     * Accès à l'accéléromètre
     * */
    //Sensor accel =

    /**
     * Retourne le couleur de la bille
     * */
    @Override
    public String getCoul() {
        return "bleu";
    }

    /**
     * Action a effectuer lorsque la bille est touchée
     */
    @Override
    public void onTouch() {

    }
}

package com.example.bc161313.bille;

import android.graphics.Point;
import android.hardware.Sensor;
import android.view.Display;

/**
 * Created by bc161313 on 19/03/19.
 */

public class BilleJoueur extends Bille{

    public BilleJoueur(int posX, int posY){
        super(posX, posY);
    }

    public BilleJoueur(){

    }

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

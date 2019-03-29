package com.example.bc161313.bille;

/**
 * Created by bc161313 on 19/03/19.
 */

public class Ennemi extends Bille {

    /**
     * Retourne le couleur de la bille
     * */
    @Override
    public String getCoul() {
        return "rouge";
    }

    /**
     * Action a effectuer lorsque la bille est touchée
     */
    @Override
    public void onTouch() {

    }
}

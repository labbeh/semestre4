package com.example.bc161313.bille;

/**
 * Created by bc161313 on 19/03/19.
 */

public abstract class Bille {
    protected int posX;
    protected int posY;

    /**
     * Retourne le couleur de la bille
     * */
    public abstract String getCoul();

    /**
     * Action a effectuer lorsque la bille est touchée
     * */
    public abstract void onTouch();

    /**
     * Position X sur l'écran
     * */
    public int getX(){
        return posX;
    }

    /**
     * Position Y sur l'écran
     * */
    public int getY(){
        return posY;
    }
}

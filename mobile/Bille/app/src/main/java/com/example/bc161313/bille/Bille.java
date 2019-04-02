package com.example.bc161313.bille;

import android.graphics.Point;
import android.view.Display;

/**
 * Created by bc161313 on 19/03/19.
 */

public abstract class Bille {
    /**
     * Taille des billes lors de leur affichage
     * */
    public static final int TAILLE = 20;

    protected int posX;
    protected int posY;

    /**
     * Constructeur par défaut
     * */
    public Bille(){
    }

    public Bille(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

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

    public void setX(int posX){
        this.posX = posX;
    }

    public void setY(int posY){
        this.posY = posY;
    }

    /**
     * Afin de comparer la position avec un intervalle de marge
     * */
    public boolean memePosition(Bille b){
        if(posX == b.posX && posY == b.posY)
            return true;
        if((posX >= b.posX-20 && posX <= b.posX+20) && (posY >= b.posY-20 && posY <= b.posY+20))
            return true;

        return false;
    }
}

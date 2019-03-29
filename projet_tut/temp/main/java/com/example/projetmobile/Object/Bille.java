package com.example.projetmobile.Object;


/**
 * Created by jc162098 on 21/03/19.
 */

public class Bille
{
    public Vector2  pos;
    public float    taille;
    public int color; //Pour relier android graphics

    public Bille(Vector2 pos, float taille, int c)
    {
        this.pos = pos;
        this.taille = taille;
        this.color  = c;
    }

    /**
     * Retourne le couleur de la bille
     * */
    public int getCoul() { return this.color; }

    public Vector2 getPos(){return this.pos;}

    public void setPos(Vector2 pos)
    {
        this.pos = pos;
    }

    public  boolean isTouching(Bille other)
    {
        return this.isTouching(other.pos,other.taille);
    }

    public boolean isTouching(Vector2 pos, float taille)
    {
        float dist = Vector2.distance(pos,this.pos);
        float radius = (this.taille +taille);

        return dist <= radius;
    }
}
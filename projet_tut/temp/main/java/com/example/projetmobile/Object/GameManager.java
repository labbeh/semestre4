package com.example.projetmobile.Object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import static java.lang.System.out;

public class GameManager
{
    /*Object de type GameLink*/
    public  GameLink gameLink;

    /*Objects de type bille*/
    Bille               billeJoueur;
    Bille               billeObjectif;
    ArrayList<Bille>    billeMines;

    /*Déclaration de la taille d'une Bille*/
    final float         tailleBille = 20;

    /*Déclaration de la taille de l'écran du portable*/
    Vector2             screenSize;

    /*Déclaration de la de la variable score du jeu*/
    int                 score;

    /*Déclaration de la variable end pour définir la condition de défaite du jeu */
    boolean             end;


    /*
    * Retourne la bille joueur
    *
    * */
    public Bille getBilleJoueur()
    {
        return this.billeJoueur;
    }

    /*
     * Constructeur de la classe manager prenant en parametre un objet GameLink
     *
     * */
    public GameManager(GameLink gameLink)
    {
        super();
        this.gameLink = gameLink;
        initialize();
    }


    /**
     * Cette méthode initialise les différente bille du jeu, ainsi que le score du jeu à zéro , l'attribut end est à false, initialisation de screenSize
     */

    public void initialize()
    {
        screenSize = gameLink.getScreenSize();
        this.end        = false;
        billeJoueur     = new Bille(getRandomPos(tailleBille),tailleBille,Color.BLUE);
        billeObjectif   = new Bille(getRandomPos(tailleBille),tailleBille,Color.GREEN);
        billeMines      = new ArrayList<>();

        score = 0;
    }

    /**
     * Cette méthode permet de dessiner le jeu
     */

    public void draw(Canvas canvas)
    {

        gameLink.canvas = canvas;
        gameLink.drawBackGround();
        gameLink.drawBille(billeJoueur);
        gameLink.drawBille(billeObjectif);

        for(Bille mine : billeMines)
        {
            gameLink.drawBille(mine);
        }
    }

    /*
    * Méthode permettant de déplacer une bille à un nouveau Vector
    * */
    public void moveTo(Bille b ,Vector2 pos )
    {
        b.setPos(pos);
    }

    /*
     * Méthode permettant de déplacer une bille à un nouveau Vector aléatoire sur l'écran
     * */
    public Vector2 getRandomPos(float tailleBille)
    {
        boolean valid =false;
        Vector2 pos = Vector2.zero;

        ArrayList<Bille> listBille = new ArrayList<>();
        listBille.add(billeJoueur);
        listBille.add(billeObjectif);
        if(billeMines != null)
        {
            listBille.addAll(billeMines);
        }

        if(tailleBille >= screenSize.x ||tailleBille >= screenSize.y)
        {
            return new Vector2(screenSize.x/2,screenSize.y/2);
        }
        while(!valid)
        {
            float x = (float) (Math.random()*(screenSize.x - tailleBille));
            float y = (float) (Math.random()*(screenSize.y - tailleBille));

            pos = new Vector2(x,y);
            boolean hasTouched = false;

            for(Bille b : listBille)
            {
                if(b != null && b.isTouching(pos,tailleBille))
                {
                    hasTouched =true;
                }
            }
            valid = !hasTouched;
        }

        return pos;

    }



    /*
     * Méthode permettant de mettre à jour le déplacement du joueur, l'objectifs, et la conditon de défaite
     * */
    public void update()
    {
        /*
        * Déplacer le joueur
        * */
        Vector2 newPos = gameLink.getInput(billeJoueur);
        moveTo(billeJoueur,newPos);

        /*
        * Vérifier l'objectif + Déplacement de l'objectif
        * */

        if(billeJoueur.isTouching(billeObjectif))
        {
            moveTo(billeObjectif,getRandomPos(billeObjectif.taille));
            createMine();
            score++;
        }

        /*
        * Condition de défaite
        * */
        for(Bille b : billeMines)
        {
            if(billeJoueur.isTouching(b))
            {
                end = true;
                gameLink.finishGame();
            }
        }

    }

    /*
    * Méthode permettant de crée une mine et de l'ajouter à la liste de mine
    * */
    public void createMine()
    {
        billeMines.add(new Bille(getRandomPos(tailleBille),tailleBille,Color.RED));
    }

}

package com.example.projetmobile.Object;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.example.projetmobile.Activity.BatterieActivity;
import com.example.projetmobile.Activity.MainActivity;

public class GameLink extends SurfaceView implements SurfaceHolder.Callback
{
    /*Object de type Canvas*/
    public Canvas canvas;

    /*Object de type GameThread*/
    public GameThread gameThread;

    /*Object de type GameManager*/
    public GameManager gameManager;

    /*Variable de type int définit la largeur de l'écran*/
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /*Variable de type int définit la hauteur de l'écran*/
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /*Object de ype Context*/
    Context context;

    Vector2 accelerometre = Vector2.zero;

    private float xAcceleration, yAcceleration,xVelocity,yVelocity;


    long lastUpdate;
    float last_x,last_y,last_z;


    public GameLink(Context context)
    {
        super(context);
        this.context = context;
        getHolder().addCallback(this);
        setFocusable(true);
        canvas = new Canvas();
        gameManager = new GameManager(this);
        gameThread = new GameThread(gameManager,getHolder());

    }

    /*Méthode permettant de peindre une Bille sur le canvas*/
    public void drawBille(Bille bille)
    {
        Paint paint =new Paint();

        paint.setColor(bille.color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(bille.pos.x,bille.pos.y,bille.taille,paint);
    }

    /*Méthode permettant de peindre la couleur de l'arriere plan du canvas*/
    public void drawBackGround()
    {
        canvas.drawColor(Color.WHITE);
    }

    /*Méthode permettant d'ajouter*/
    public Vector2 getInput(Bille bille)
    {

        Vector2 initPos = bille.pos;
        Vector2 test = new Vector2(last_x,last_y);
        Vector2 value = Vector2.add(initPos,test);
        System.out.println(value);
        System.out.println("Screen Width : "+screenWidth);
        System.out.println("Screen Height : "+screenHeight);
        //Vector2 value = initPos;


        if(value.x < bille.taille )
        {
            value.x = bille.taille;
        }
        if(value.y < bille.taille)
        {
            value.y = bille.taille;
        }

        if(value.y > screenHeight - bille.taille)
        {
            value.y = screenHeight - bille.taille;
        }

        if(value.x > screenWidth - bille.taille)
        {
            value.x = screenWidth -bille.taille;
        }
        return value;
    }

    /*Méthode permettant de retourner un vecteur avec en parametre la largeur et la hauteur de l'écran */
    public Vector2 getScreenSize()
    {
        return new Vector2(screenWidth,screenHeight);
    }


    /*Méthode permettant de lancer un processus liées à la surface de l'écran */
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        gameThread.running = true;
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }


    /*Méthode permettant de détruire le processus liées à la surface de l'écran */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while (retry) {
            try {
                gameThread.running = false;
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /*Méthode permettant de retourner à la main activity */
    public void finishGame()
    {
        Intent intentMainActivity = new Intent(context, MainActivity.class);
        context.startActivity(intentMainActivity);

    }

    /*Méthode permettant de récuperer les mouvements du portable */
    public void sensorEvent(float x, float y , float z)
    {
        long curTime = System.currentTimeMillis();


        if ((curTime - lastUpdate) > 100)
        {
            long diffTime = (curTime - lastUpdate);

            float frameTime = diffTime/10000;

            lastUpdate = curTime;

            float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

            last_x = x;
            last_y = y;
            last_z = z;

            xAcceleration = x;
            yAcceleration = y;

            xVelocity += xAcceleration * frameTime;
            yVelocity += yAcceleration * frameTime;

            float xPosition = (xVelocity/2)*frameTime;
            float yPosition = (yVelocity/2)*frameTime;


            System.out.println("xposition " +xPosition);
            System.out.println("yposition " +yPosition);
            System.out.println(diffTime);


            accelerometre.add(new Vector2(xPosition,yPosition));

        }
    }
}

package com.example.projetmobile.Object;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameThread extends Thread
{
    public GameManager gameManager;
    public SurfaceHolder surfaceHolder;
    public boolean running;
    public static Canvas canvas;
    public int targetFPS = 60;
    private float averageFPS;

    public GameThread(GameManager gameManager, SurfaceHolder surfaceHolder)
    {
        super();
        this.gameManager =gameManager;
        this.surfaceHolder =surfaceHolder;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / targetFPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.gameManager.update();
                    this.gameManager.draw(canvas);
                    if(this.gameManager.end)
                    {
                        running = false;

                    }

                }
            } catch (Exception e) {       }
            finally {
                if (canvas != null)            {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == targetFPS)        {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;

            }
        }

    }

}

package com.tp.lh150094.asynctask;

import android.content.Intent;
import android.graphics.*;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /**
     * Tableau avec le nom des fichiers images du dossier drawable
     * */
    final int[] imgs = new int[]{R.drawable.board, R.drawable.depot, R.drawable.engine};

    /**
     * Indice ou on en est dans le tableau de photo
     * A chaque changement de photo on passe à l'indice suivant
     * dans le tableau puis on repasse à 0 quand on a atteint
     * l'indice maximal
     * */
    private int i;

    /**
     * TextViiwer avec la barre de progression
     * */
    TextView tvProg = findViewById(R.id.status);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = findViewById(R.id.photo);
        iv.setImageResource(imgs[0]);

        i = 0;
    }

    public void toNvGris(View view) {
        int imageID = imgs[i];
        Bitmap original = BitmapFactory.decodeResource(getResources(), imageID);
        Bitmap nvlImg = original.copy(original.getConfig(), true);

        ImageView iv = findViewById(R.id.photo);
        iv.setImageBitmap(nvlImg);

        // traitement conversion en gris
        int width = nvlImg.getWidth();
        int height = nvlImg.getHeight();
        int[] pixels = new int[width * height];

        nvlImg.getPixels(pixels, 0, width, 0, 0, width, height);

        for(int lig=0; lig<height; lig++){
            for(int col=0; col<width; col++){
                int i = col + width*lig;

                int alpha = Color.alpha(pixels[i]);
                int rouge = Color.red(pixels[i]);
                int vert  = Color.green(pixels[i]);
                int bleu  = Color.blue(pixels[i]);

                // gris = (R+V+B)/3
                // Gris = 0.2125 Rouge + 0.7154 Vert + 0.0721 Bleu
                int enGris = (rouge + vert + bleu) / 3;
                pixels[i] = Color.argb(alpha, enGris, enGris, enGris);
            }
        }
        nvlImg.setPixels(pixels, 0, width, 0, 0, width, height);
        iv.setImageBitmap(nvlImg);
    }

    public void pixeliseImage(View view) {
    }

    public void quit(View view) {
        finish();
    }

    /**
     * Change l'image avec une autre du dossier drawable au click
     * sur l'image actuelle
     * */
    public void changerImage(View view) {
        //Intent intent = new Intent(Intent.ACTION_PICK);
        i++;
        ImageView iv = findViewById(R.id.photo);
        if(i == imgs.length) i=0;
        iv.setImageResource(imgs[i]);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

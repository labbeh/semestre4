package com.example.bc161313.bille;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Cette classe permet à l'application de lire et d'écrire dans des fichiers
 * de configuration, ici dédiés aux informations récupérées de la batterie
 * */
public class GestionFichiers {
    /**
     * Nom du fichier propre à l'application où seront stockées les données
     * de la batterie
     * */
    private static final String FILENAME = "BatterieData.dat";

    /**
     * Pointeur vers l'Activity principale
     * */
    private AppCompatActivity activite;

    public GestionFichiers(AppCompatActivity activite){
        this.activite = activite;
    }

    public String lire(){
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = activite.openFileInput(FILENAME);
            Scanner sc = new Scanner(fis);

            while(sc.hasNextLine())
                sb.append(sc.nextLine()).append('\n');

            //Toast.makeText(activite, sb.toString(), Toast.LENGTH_LONG).show();
        }
        catch (IOException evt){Toast.makeText(activite, "Fichier inexistant", Toast.LENGTH_SHORT).show();}
        return sb.toString();
    }

    public void ecrire(String aEcrire){
        try {
            FileOutputStream fos = activite.openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(aEcrire.getBytes());
            fos.close();
        }
        catch (IOException evt){
            Toast.makeText(activite, "Erreur lors de l'écriture", Toast.LENGTH_SHORT).show();
        }
    }
}

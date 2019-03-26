import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Traitement {
	
	private static final int CONTOUR_EPAISSEUR = 4;
	
	private BufferedImage img;
	
	private ArrayList<Coordonnees> listeContour; // Liste des coordonnnées de contour de l'image
	private ArrayList<Coordonnees> listePointsMasque;
	
	private File        imageTraite;
	private Coordonnees barycentre;
	private int         largeurImage;
	private int         hauteurImage;
	
	public Traitement ( String nomFichierEntree ) {
		this.listeContour      = new ArrayList<Coordonnees>();
		this.listePointsMasque = new ArrayList<Coordonnees>();
		this.img               = null;
		
		// Lecture de l'image
		this.lectureImage(nomFichierEntree);
		
		this.largeurImage = img.getWidth();
        this.hauteurImage = img.getHeight();
	
        this.creationMasque();
        // Sauvegarde des coordonnées du contour dans une liste
        this.sauvegardeCoordonneesContour();
        // Création du contour
        this.creationContour();
        // Ecriture de l'image
        this.sauvegarderImage(nomFichierEntree);
	}
	
	/*
	 * Permet de lire l'image en entr�e
	 */
	private void lectureImage ( String nomFichierEntree ) {
		System.out.println( nomFichierEntree );
		try { this.img = ImageIO.read( new File(nomFichierEntree) ); }
		catch ( IOException e ) { e.printStackTrace(); }  
	}
	
	/*
	 * Permet de sauvegarder l'image
	 */
	private void sauvegarderImage ( String nomFichierSortie ) {
		try {
			this.imageTraite = new File(nomFichierSortie);
			ImageIO.write(this.img, "png", this.imageTraite);
		} catch ( IOException e ) { e.printStackTrace(); }
	}
	
	
	private void creationMasque () {
		 Color cBlack = new Color(0,0,0);
		 for(int colonne = 0; colonne < this.largeurImage; colonne++){
			 for(int ligne = 0; ligne < this.hauteurImage; ligne++){
				 if(!estTransparent(colonne, ligne)) {
					 img.setRGB( colonne, ligne, cBlack.getRGB() );
					 	this.listePointsMasque.add( new Coordonnees(colonne, ligne) );	
	                }
	            }
	        }
	}
	
	private Coordonnees barycentre() {
		int calculX = 0;
		int calculY = 0;
		int nbPoints = 0;
		
		for(Coordonnees c : this.listePointsMasque)
		{
			calculX += c.getX();
			calculY += c.getY();
			nbPoints++;
		}
		
		this.barycentre = new Coordonnees(calculX/nbPoints, calculY/nbPoints); 
		return new Coordonnees(calculX/nbPoints, calculY/nbPoints); 
		
	}
	
	private void sauvegardeCoordonneesContour () {
		Color cWhite = new Color(255,255,255);
		for ( int colonne = 0; colonne < largeurImage; colonne++ ){
            for ( int ligne = 0; ligne < hauteurImage; ligne++ ) {
           
              if ( !estTransparent(colonne, ligne) ) {	 
            	 // On recolorie le masque en blanc
            	 img.setRGB(colonne, ligne, cWhite.getRGB());
            	 if ( aUnVoisinTransparent(colonne, ligne) )
            	 {
            		 // On ajoute � une liste les coordonn�es des points de contour de l'image
            		 this.listeContour.add(new Coordonnees(colonne, ligne));
            	 }
              }
            }
		}	
	}
	
	private void creationContour () {
		 Color cBlack = new Color(  0, 0, 0);
		 Color cRed   = new Color(255, 0, 0);
		 for ( Coordonnees c : this.listeContour ) {
	        img.setRGB(           c.getX(),            c.getY(), cBlack.getRGB() );
	        img.setRGB(barycentre().getX(), barycentre().getY(),   cRed.getRGB() );
	     }
	}
	
	/*
	 * Vérifie si le pixel est transparent
	 */
	private boolean estTransparent ( int x, int y ) {
		  int pixel = img.getRGB(x,y);
		  if( ( pixel >> 24 ) == 0x00 ) {
		      return true;
		  }
		  return false;
	}
	
	/*
	 * Vérifie si un voisin est transparent
	 */
	private boolean aUnVoisinTransparent ( int x, int y ) {
		
		boolean bOk = false;
		for ( int cptLig=-CONTOUR_EPAISSEUR; cptLig<CONTOUR_EPAISSEUR && !bOk; cptLig++ ) {
			for ( int cptCol=-CONTOUR_EPAISSEUR; cptCol<CONTOUR_EPAISSEUR && !bOk; cptCol++ ) {
				// Erreur si hors de l'image
				try { bOk = estTransparent(cptLig+x, cptCol+y); }
				catch ( Exception e ) { return true; }
			}
		}
		return bOk;
		
	}
	
	public File getImageTraite () { return this.imageTraite; }

	public Coordonnees getBarycentre() { return this.barycentre; }
	
	/*public boolean AUnVoisinBlanc(int x, int y) {
		return (img.getRGB(x, y) & 0xFFFFFF) == this.getRVB(255, 255, 255);
	}*/
	
	/*public int getRVB(int rouge, int vert, int bleu) { 
		return rouge*65536 + vert*256 + bleu*1 ; 
	}*/

}

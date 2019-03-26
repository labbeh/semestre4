import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Traitement {
	
	
	private BufferedImage img;
	
	private ArrayList<Coordonnees> listeContour; // Liste des coordonnnées de contour de l'image
	private ArrayList<Coordonnees> listePointsMasque;
	
	
	private Coordonnees barycentre;
	private int largeurImage;
	private int hauteurImage;
	
	public Traitement(String nomFichierEntree, String nomFichierSortie)
	{
			traitementImage(nomFichierEntree, nomFichierSortie);
	}

	
	public void traitementImage(String nomFichierEntree, String nomFichierSortie) {
	   
			this.listeContour = new ArrayList<Coordonnees>();
			this.listePointsMasque = new ArrayList<Coordonnees>();
			this.img = null;
			
			// Lecture de l'image
			this.lectureImage(this.img, nomFichierEntree);
			
			this.largeurImage = img.getWidth();
	        this.hauteurImage = img.getHeight();
		
	        creationMasque();
	        // Sauvegarde des coordonnées du contour dans une liste
	        sauvegardeCoordonneesContour();
	        // Création du contour
	        creationContour();
	        // Ecriture de l'image
	        this.sauvegarderImage(this.img, nomFichierSortie);
	}
	    
	
	
	
	
	/*
	 * Permet de lire l'image en entrée
	 */
	public void lectureImage(BufferedImage img, String nomFichierEntree)
	{
		 try {
				this.img = ImageIO.read(this.getClass().getResource(nomFichierEntree));
		 } catch (IOException e) {
				e.printStackTrace();
		 }  
	}
	
	/*
	 * Permet de sauvegarder l'image
	 */
	public void sauvegarderImage(BufferedImage img, String nomFichierSortie)
	{
		try {
			ImageIO.write(img, "png", new File(nomFichierSortie));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void creationMasque()
	{
		 Color cBlack = new Color(0,0,0);
		 for(int colonne = 0; colonne < this.largeurImage; colonne++){
	            for(int ligne = 0; ligne < this.hauteurImage; ligne++){
	                if(!estTransparent(colonne, ligne)) {
	                	img.setRGB(colonne, ligne, cBlack.getRGB());
	                	this.listePointsMasque.add(new Coordonnees(colonne, ligne));
	                	
	                }

	            }
	        }
	}
	
	
	public Coordonnees barycentre()
	{
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
	
	
	
	public void sauvegardeCoordonneesContour()
	{
		Color cWhite = new Color(255,255,255);
		for(int colonne = 0; colonne < largeurImage; colonne++){
            for(int ligne = 0; ligne < hauteurImage; ligne++){
           
              if(!estTransparent(colonne, ligne))
              {	 
            	 // On recolorie le masque en blanc
            	 img.setRGB(colonne, ligne, cWhite.getRGB());
            	 if(aUnVoisinTransparent(colonne, ligne))
            	 {
            		 // On ajoute à une liste les coordonnées des points de contour de l'image
            		 this.listeContour.add(new Coordonnees(colonne, ligne));
            	 }
              }
      
            }
        }
		
	}
	
	public void creationContour()
	{
		 Color cBlack = new Color(0,0,0);
		 Color cRed = new Color(255,0,0);
		 for(Coordonnees c : this.listeContour)
	        {
	        	img.setRGB(c.getX(), c.getY(), cBlack.getRGB());
	        	img.setRGB(barycentre().getX(), barycentre().getY(), cRed.getRGB());
	        }
	}
	
	/*
	 * Vérifie si le pixel est transparent
	 */
	public boolean estTransparent( int x, int y ) {
		  int pixel = img.getRGB(x,y);
		  if( (pixel>>24) == 0x00 ) {
		      return true;
		  }
		  return false;
	}
	
	
	/*
	 * Vérifie si un voisin est transparent
	 */
	public boolean aUnVoisinTransparent(int x, int y)
	{

		if(x<this.largeurImage)
		{
			if(estTransparent(x+1, y)) return true;
			
			if(y<this.hauteurImage)
				if(estTransparent(x+1, y+1)) return true;
			
			if(y>0)
				if(estTransparent(x+1, y-1)) return true;
				
				
		}
		else if(x>0) {
			
			if(estTransparent(x-1, y)) return true;
			
			if(y<this.hauteurImage)
				if(estTransparent(x-1, y+1)) return true;
			
			if(y>0)
				if(estTransparent(x-1, y-1)) return true;
		}
		else if(x>=0 || x<=this.largeurImage) {
			
			if(y>0)
				if(estTransparent(x, y-1)) return true;
			
			if(y<this.hauteurImage)
				if(estTransparent(x, y+1)) return true;
			
		}
		
		
		return false;
	}

	public Coordonnees getBarycentre()
	{
		return this.barycentre;
	}
	
	
	
	
	/*public boolean AUnVoisinBlanc(int x, int y)
	{
		return (img.getRGB(x, y) & 0xFFFFFF) == this.getRVB(255, 255, 255);
	}*/
	
	/*public int getRVB(int rouge, int vert, int bleu) { 
		return rouge*65536 + vert*256 + bleu*1 ; 
	}*/
	
	
	
	
	public static void main(String[] args) {
		new Traitement("robot1.png", "R:\\images\\robot4.png");
	}

}

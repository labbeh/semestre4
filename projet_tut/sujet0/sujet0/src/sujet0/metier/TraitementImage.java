package sujet0.metier;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Classe pour le traitement d'une image .png
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */

public class TraitementImage {
	/**
	 * Epaisseur du contour
	 * */
	private static final int CONTOUR_EPAISSEUR = 4;
	
	/**
	 * Chemin de l'image sur laquelle opérer
	 * */
	private String nomImg;
	
	/**
	 * Image contenant le masque de l'image initiale
	 * */
	private BufferedImage img;
	
	/**
	 * Coordonnées des pixels du masque de l'image
	 * */
	private List<Coordonnees> coords;
	
	/**
	 * Coordonnées des pixels qui composent le contour
	 * */
	private List<Coordonnees> contour;
	
	public TraitementImage(String nomImg) {
		this.nomImg = nomImg;
		
		img = null;
		
		coords  = new ArrayList<>();
		contour = new ArrayList<>();
	}
	
	/**
	 * Charge l'image initiale
	 * @return true si l'image à été chargée avec succès
	 * */
	public boolean chargerImageInitiale() {
		// on charge l'image
		try {
			img = ImageIO.read(new File(nomImg));
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Permet de d�tecter les contours d'une image (masque) avec canal alpha et fond transparent
	 * @return une List de coordonn�es contenant la position de chaque
	 * pixel constituant le masque, null si l'image n'est pas valide
	 * fichier introuvable ou innaccessible en lecture, image sans canal
	 * alpha de transparence ...
	 * */
	public List<Coordonnees> creerMasque(){
		// traitement
		if(!img.getColorModel().hasAlpha()) // on v�rifie que l'image � un canal alpha
			return null;
		
		// pour le moment on retourne les pixels transparents
		for(int x=0; x<img.getWidth(); x++)
			for(int y=0; y<img.getHeight(); y++)
				if(!estTransparent(img, x, y)) {
					coords.add(new Coordonnees(x, y));
					img.setRGB(x, y, Color.BLACK.getRGB());
				}
		return coords;
	}
	
	/**
	 * Retourne l'image du dernier traitement effectué
	 * @return image du masque dans une BufferedImage
	 * */
	public BufferedImage getImage() {
		return img;
	}
	
	/**
	 * Retourne les coordonnées des pixels composants le masque de l'image initial
	 * @return liste de coordonnées dans une List
	 * */
	public List<Coordonnees> getCoordsMasque(){
		return coords;
	}
	
	/**
	 * Retourne les coordonnées des pixels composants contour du masque
	 * @return liste de coordonnées dans une List
	 * */
	public List<Coordonnees> getCoordsContour(){
		return contour;
	}
	
	public Coordonnees getBaryCentre(){
		int calculX = 0;
		int calculY = 0;
		int nbPoints = 0;
		
		for(Coordonnees c : coords){
			calculX += c.getX();
			calculY += c.getY();
			nbPoints++;
		}
		
		return new Coordonnees(calculX/nbPoints, calculY/nbPoints); 
	}
	
	/**
	 * Vérifie si un voisin est transparent
	 */
	private boolean aUnVoisinTransparent ( int x, int y ) {
		
		boolean bOk = false;
		
		for ( int cptLig=-CONTOUR_EPAISSEUR; cptLig<=CONTOUR_EPAISSEUR && !bOk; cptLig++ ) {
			for ( int cptCol=-CONTOUR_EPAISSEUR; cptCol<=CONTOUR_EPAISSEUR && !bOk; cptCol++ ) {
				// Erreur si hors de l'image
				try { bOk = estTransparent(img, cptLig+x, cptCol+y); }
				catch ( Exception e ) { return true; }
			}
		}
		return bOk;
		
	}
	
	private void sauvegardeCoordonneesContour() {
		int largeurImage = img.getWidth();
        int hauteurImage = img.getHeight();
        
		for ( int colonne = 0; colonne < largeurImage; colonne++ ){
            for ( int ligne = 0; ligne < hauteurImage; ligne++ ) {
           
              if ( !estTransparent(img, colonne, ligne) ) {	 
            	 // On recolorie le masque en blanc
            	 img.setRGB(colonne, ligne, Color.WHITE.getRGB());
            	 if ( aUnVoisinTransparent(colonne, ligne) ){
            		 // On ajoute � une liste les coordonn�es des points de contour de l'image
            		 this.contour.add(new Coordonnees(colonne, ligne));
            	 }
              }
            }
		}	
	}
	
	/**
	 * Permet de créer l'image du contour
	 * */
	public void creerImageContour() {
		if(contour.size() == 0) sauvegardeCoordonneesContour();
		
		 for ( Coordonnees c : contour ) {
	        img.setRGB(c.getX(), c.getY(), Color.BLACK.getRGB());
	        img.setRGB(getBaryCentre().getX(), getBaryCentre().getY(), Color.RED.getRGB());
	     }
	}
	
	
	/**
	 * M�thode qui retourne si oui ou non un pixel donn� dans une image
	 * est transparent
	 * @param img image dans laquelle le pixel � analyser se trouve
	 * @param x position horizontal du pixel
	 * @param y position vertical du pixel
	 * @return vrai si le pixel analys� est transaprent
	 * */
	private static boolean estTransparent(BufferedImage img, int x, int y) {
		int pixel = img.getRGB(x,y);
        return (pixel>>24) == 0x00;
	}

}

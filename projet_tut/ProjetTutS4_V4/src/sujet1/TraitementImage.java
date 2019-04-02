package sujet1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
	
	private Coordonnees barycentre;
	
	private int epaisseurContour;
	
	private Dimension dimensions;
	
	public TraitementImage ( String fichierImage, Dimension dimensions ) {
		this.nomImg     = fichierImage;
		this.dimensions = dimensions;
		this.img        = null;
		this.coords     = new ArrayList<>();
		this.contour    = new ArrayList<>();
		
		this.epaisseurContour = 2;
		
		this.chargerImageInitiale();
		this.redimensionnerImage();
		this.creerMasque(false);
		this.sauvegardeCoordonneesContour(false);
		this.creerImageContour(false);
		
	}
	
	/**
	 * Charge l'image initiale
	 * @return true si l'image à été chargée avec succès
	 * */
	public boolean chargerImageInitiale () {
		try {
			this.img = ImageIO.read( new File(this.nomImg) );
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** Redimensionne l'image pour que le contour corresponde parfaitement à l'image.
	 */
	public void redimensionnerImage () {		
		BufferedImage scrCapt = this.img;
		AffineTransform atscr = new AffineTransform();
		
		atscr.scale(0.1, 0.1);
		AffineTransformOp scaleOp = new AffineTransformOp(atscr, AffineTransformOp.TYPE_BILINEAR);
		this.img = scaleOp.filter(scrCapt,null);
	}
	
	/**
	 * Permet de détecter les contours d'une image (masque) avec canal alpha et fond transparent
	 * @return une List de coordonnées contenant la position de chaque
	 * pixel constituant le masque, null si l'image n'est pas valide
	 * fichier introuvable ou innaccessible en lecture, image sans canal
	 * alpha de transparence ...
	 * */
	public List<Coordonnees> creerMasque ( boolean afficher) {
		// traitement
		if( !img.getColorModel().hasAlpha() ) // on vérifie que l'image a un canal alpha
			return null;
		
		// pour le moment on retourne les pixels transparents
		for ( int x=0; x<img.getWidth(); x++ )
			for ( int y=0; y<img.getHeight(); y++ )
				if( !estTransparent(img, x, y) ) {
					coords.add( new Coordonnees(x, y) );
					if ( afficher )
						img.setRGB(x, y, Color.BLACK.getRGB());
				}
		return coords;
	}
	
	/**
	 * Retourne l'image du dernier traitement effectué
	 * @return image du masque dans une BufferedImage
	 * */
	public BufferedImage getImage () { return img; }
	
	/**
	 * Retourne les coordonnées des pixels composants le masque de l'image initial
	 * @return liste de coordonnées dans une List
	 * */
	public List<Coordonnees> getCoordsMasque () { return coords; }
	
	/**
	 * Retourne les coordonnées des pixels composants contour du masque
	 * @return liste de coordonnées dans une List
	 * */
	public List<Coordonnees> getCoordsContour () { return contour; }
	
	public Coordonnees calcBarycentre () {
		int calculX  = 0;
		int calculY  = 0;
		int nbPoints = 0;
		
		for ( Coordonnees c : coords ) {
			calculX += c.getX();
			calculY += c.getY();
			nbPoints++;
		}
		
		return new Coordonnees(calculX/nbPoints, calculY/nbPoints);
	}
	
	public Coordonnees getBarycentre () { return this.barycentre; }
	
	/**
	 * Vérifie si un voisin est transparent
	 */
	private boolean aUnVoisinTransparent ( int x, int y ) {
		
		boolean bOk = false;
		
		for ( int cptLig=-this.epaisseurContour; cptLig<=this.epaisseurContour && !bOk; cptLig++ ) {
			for ( int cptCol=-this.epaisseurContour; cptCol<=this.epaisseurContour && !bOk; cptCol++ ) {
				// Erreur si hors de l'image
				try { bOk = estTransparent(img, cptLig+x, cptCol+y); }
				catch ( Exception e ) { return true; }
			}
		}
		return bOk;
		
	}
	
	/** Permet de créer le contour
	 * @param afficher
	 */
	public void sauvegardeCoordonneesContour ( boolean afficher ) {
		int largeurImage = img.getWidth();
        int hauteurImage = img.getHeight();
        
		for ( int colonne = 0; colonne < largeurImage; colonne++ ){
            for ( int ligne = 0; ligne < hauteurImage; ligne++ ) {
           
              if ( !estTransparent(img, colonne, ligne) ) {	 
            	 // On recolorie le masque en blanc
            	 if ( afficher )
            		 img.setRGB(colonne, ligne, Color.WHITE.getRGB());
            	 if ( aUnVoisinTransparent(colonne, ligne) ){
            		 // On l'ajoute à une liste les coordonnées des points de contour de l'image
            		 this.contour.add( new Coordonnees(colonne, ligne) );
            	 }
              }
            }
		}	
	}
	
	/**
	 * Permet de créer le contour de l'image.
	 * @param visuContour
	 *         Si true, il dessinera le contour.
	 * */
	public void creerImageContour ( boolean visuContour ) {
		if ( contour.size() == 0 ) sauvegardeCoordonneesContour(false);
		
		this.barycentre = this.calcBarycentre();
		
		 for ( Coordonnees c : contour ) {
	        if ( visuContour ) {
			 img.setRGB( (int)c.getX(),               (int)c.getY(),               Color.BLACK.getRGB() );
			 img.setRGB( (int)getBarycentre().getX(), (int)getBarycentre().getY(), Color.RED.getRGB()   );
	        }
		 }
	}
	
	/** Retourne l'emplacement de l'image chargée.
	 * @return L'emplacement de l'image chargée.
	 */
	public String getFichierImage () { return this.nomImg; }
	
	/**
	 * Méthode qui retourne si oui ou non un pixel donné dans une image
	 * est transparent
	 * @param img image dans laquelle le pixel � analyser se trouve
	 * @param x position horizontal du pixel
	 * @param y position vertical du pixel
	 * @return vrai si le pixel analysé est transaprent
	 * */
	private static boolean estTransparent ( BufferedImage img, int x, int y ) {
		int pixel = img.getRGB(x,y);
        return (pixel>>24) == 0x00;
	}

}

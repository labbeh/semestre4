package sujet0;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Classe utilitaire pour le traitement d'une image .png
 * */

public class TraitementImage {
	
	/**
	 * Pour que cette classe utilitaire ne soit jamais instanciée
	 * */
	private TraitementImage() {}
	
	/**
	 * Permet de détecter les contours d'une image avec canal alpha et fond transparent
	 * @param nomImage nom du fichier image sur lequel opérer
	 * @return une List de coordonnées contenant la position de chaque
	 * pixel contour de l'image
	 * */
	public static List<Coordonnees> detecterContour(String nomImg){
		List<Coordonnees> coords = new ArrayList<>();
		BufferedImage img = null;
		
		// on charge l'image
		try {
			img = ImageIO.read(new File(nomImg));
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// traitement
		if(!img.getColorModel().hasAlpha()) // on vérifie que l'image à un canal alpha
			return null;
		// pour le moment on retourne les pixels transparents
		for(int x=0; x<img.getWidth(); x++)
			for(int y=0; y<img.getHeight(); y++)
				if(estTransparent(img, x, y))
					coords.add(new Coordonnees(x, y));
		return coords;
	}
	
	/**
	 * Méthode qui retourne si oui ou non un pixel donné dans une image
	 * est transparent
	 * @param img image dans laquelle le pixel à analyser se trouve
	 * @param x position horizontal du pixel
	 * @param y position vertical du pixel
	 * @return vrai si le pixel analysé est transaprent
	 * */
	private static boolean estTransparent(BufferedImage img, int x, int y) {
		int pixel = img.getRGB(x,y);
        return (pixel>>24) == 0x00;
	}

}

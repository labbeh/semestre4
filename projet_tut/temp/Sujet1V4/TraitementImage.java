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
public class TraitementImage
{
	private static final int CONTOUR_EPAISSEUR = 4;
	
	private Controleur        ctrl;
	private BufferedImage     img;
	private List<Coordonnees> coords;
	private List<Coordonnees> contour;
	
	public TraitementImage(Controleur ctrl, String nomImg)
	{
		this.ctrl        = ctrl;
		this.img         = null;
		this.coords      = new ArrayList<Coordonnees>();
		this.contour     = new ArrayList<Coordonnees>();
		
		// on charge l'image
		try
		{
			this.img = ImageIO.read(new File(nomImg));
		}
		catch (IOException e) { e.printStackTrace(); }
		
		creerMasque();
		sauvegardeCoordonneesContour();
	}
	
	public List<Coordonnees> creerMasque()
	{
		// traitement
		if(!img.getColorModel().hasAlpha()) // on v�rifie que l'image � un canal alpha
			return null;
		
		// pour le moment on retourne les pixels transparents
		for(int x=0; x<img.getWidth(); x++)
			for(int y=0; y<img.getHeight(); y++)
				if(!estTransparent(img, x, y))
					coords.add(new Coordonnees(x, y));
		
		return coords;
	}
	
	/**
	 * Vérifie si un voisin est transparent
	 */
	private boolean aUnVoisinTransparent ( int x, int y )
	{
		boolean bOk = false;
		
		for ( int cptLig=-CONTOUR_EPAISSEUR; cptLig<=CONTOUR_EPAISSEUR && !bOk; cptLig++ )
		{
			for ( int cptCol=-CONTOUR_EPAISSEUR; cptCol<=CONTOUR_EPAISSEUR && !bOk; cptCol++ )
			{
				// Erreur si hors de l'image
				try                  { bOk = estTransparent(img, cptLig+x, cptCol+y); }
				catch( Exception e ) { return true;                                   }
			}
		}
		return bOk;
	}
	
	private void sauvegardeCoordonneesContour()
	{
		int largeurImage = img.getWidth();
		int hauteurImage = img.getHeight();
		
		for ( int colonne = 0; colonne < largeurImage; colonne++ )
		{
			for ( int ligne = 0; ligne < hauteurImage; ligne++ )
			{
				if ( !estTransparent(img, colonne, ligne) )
				{
					// On ajoute � une liste les coordonn�es des points de contour de l'image
					if ( aUnVoisinTransparent(colonne, ligne) )
						this.contour.add(new Coordonnees(colonne, ligne));
				}
			}
		}
	}
	
	private static boolean estTransparent(BufferedImage img, int x, int y)
	{
		int pixel = img.getRGB(x,y);
		return (pixel>>24) == 0x00;
	}
	
	public BufferedImage     getImage()         {return img;    }
	public List<Coordonnees> getCoordsMasque()  {return coords; }
	public List<Coordonnees> getCoordsContour() {return contour;}
	
	public Coordonnees getBaryCentre()
	{
		int calculX, calculY, nbPoints;
		calculX = calculY = nbPoints = 0;
		
		for(Coordonnees c : coords)
		{
			calculX += c.getX();
			calculY += c.getY();
			nbPoints++;
		}
		
		return new Coordonnees(calculX/nbPoints, calculY/nbPoints);
	}
}


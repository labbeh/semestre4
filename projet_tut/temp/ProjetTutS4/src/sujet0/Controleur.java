package sujet0;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Classe utilitaire pour le traitement d'une image .png
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */

public class Controleur {
	/**
	 * Nom de l'image
	 * */
	private String nomImg;
	
	/**
	 * Fenetre graphique
	 * */
	private Fenetre ihm;
	
	/**
	 * Instance de la classe du traitement de l'image
	 * */
	private TraitementImage traitementImg;
	
	/**
	 * Degré de rotation
	 * */
	private int degre;
	
	public Controleur () {
		this.nomImg = (new RecupererImage()).getFileImage();
		
		//this.nomImg =  "./" +nomImg;
		this.traitementImg = new TraitementImage(nomImg);
		
		init();
		ihm = new Fenetre(this);
	}
	
	/**
	 * Initialise la situation de départ
	 * */
	public void init() {
		traitementImg.chargerImageInitiale();
		traitementImg.creerMasque();
		traitementImg.creerImageContour();
	}
	
	public List<Coordonnees> getCoords     () { return traitementImg.getCoordsMasque(); }
	public BufferedImage     getImage      () { return traitementImg.getImage();        }
	public Coordonnees       getBaryCentre () { return traitementImg.getBaryCentre();   }
	
	/**
	 * Rotation horaire de l'image
	 */
	public void rotationHoraire () {
		int degreModifie = 5;
		degre += degreModifie;
		majIHM();
	}
	
	/**
	 * Rotation anti-horaire de l'image
	 */
	public void rotationAntiHoraire () {
		int degreModifie = -5;
		degre += degreModifie;
		majIHM();
	}
	
	/**
	 * Permet de rafraichir le panel d'affichage
	 * */
	public void majIHM() {
		ihm.majIHM();
	}
	
	/**
	 * Retourne le degré de rotation de l'image
	 * @return le degr de rotation de l'image
	 * */
	public int getDegre() { return this.degre; }
	
	/**
	 * Méthode pour des tests A SUPPRIMER
	 * */
	/*public void test() {
		try {
			FileWriter fw = new FileWriter("test.txt");
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println("test");
			
			pw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public static void main(String[] args)
	{
		/*
		if(args.length < 1) {
			System.out.println("Usage: java Controleur nomImage.png");
			System.exit(1);
		}
		*/
		
		new Controleur();
	}

}

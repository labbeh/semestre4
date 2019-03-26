package sujet0.controleur;

import java.awt.image.BufferedImage;
import java.util.List;

import sujet0.ihm.Fenetre;
import sujet0.lanceur.RecupererImage;
import sujet0.metier.Coordonnees;
import sujet0.metier.TraitementImage;

/**
 * Controleur de l'application
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
	
	public Controleur() {
		nomImg = (new RecupererImage()).getFileImage();
	}
	
	/**
	 * Initialise la situation de d�part
	 * */
	public void init() {
		traitementImg = new TraitementImage(nomImg);
		traitementImg.chargerImageInitiale();
		traitementImg.creerMasque();
		traitementImg.creerImageContour();
		
		ihm = new Fenetre(this);
	}
	
	public List<Coordonnees> getCoords(){
		return traitementImg.getCoordsMasque();
	}
	
	public BufferedImage getImage() {
		return traitementImg.getImage();
	}
	
	public Coordonnees getBaryCentre(){
		return traitementImg.getBaryCentre();
	}
	
	/**
	 * Rotation horaire de l'image
	 */
	public void rotationHoraire()
	{
		int degreModifie = 5;
		degre += degreModifie;
		majIHM();
		
	}
	
	/**
	 * Rotation anti-horaire de l'image
	 */
	public void rotationAntiHoraire()
	{

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
	public int getDegre() {
		return degre;
	}
}

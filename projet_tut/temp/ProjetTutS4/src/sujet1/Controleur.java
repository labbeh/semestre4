package sujet1;

import sujet1.ihm.Fenetre;
import sujet1.metier.plateau.*;

public class Controleur {
	
	private static final int FENETRE_LARGEUR = 1000;
	private static final int FENETRE_HAUTEUR = 1000;
	
	/**
	 * Instance du plateau de jeu
	 * */
	private Plateau plateau;
	
	/**
	 * Pointeur vers l'ihm
	 * */
	private Fenetre ihm;
	
	public Controleur() {
		ihm = new Fenetre(this, FENETRE_LARGEUR, FENETRE_HAUTEUR);
	}
	
	/**
	 * Retourne le plateau
	 * @return largeur du plateau en int
	 * */
	public Plateau getPlateau() { return this.plateau;}
	
	public static void main(String[] args) {
		new Controleur();
	}
}

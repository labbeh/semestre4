package sujet1;

import sujet1.ihm.Fenetre;
import sujet1.metier.plateau.*;

public class Controleur {
	/**
	 * Instance du plateau de jeu
	 * */
	private Plateau plateau;
	
	/**
	 * Pointeur vers l'ihm
	 * */
	private Fenetre ihm;
	
	public Controleur() {
		plateau = PlateauFactory.creerPlateau(1000, 1000);
		init();
	}
	
	public void init() {
		ihm = new Fenetre(this);
	}
	
	/**
	 * Retourne la largeur du plateau
	 * @return largeur du plateau en int
	 * */
	public int getLargeur() {
		return plateau.getLargeur();
	}
	
	/**
	 * Retourne la hauteur du plateau
	 * @return hauteur du plateau en int
	 * */
	public int getHauteur() {
		return plateau.getHauteur();
	}
	
	public static void main(String[] args) {
		new Controleur();
	}
}

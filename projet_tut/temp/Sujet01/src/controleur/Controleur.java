package controleur;

import jeu.Plateau;
import jeu.PlateauFactory;
import vue.GUI;

public class Controleur {
	/**
	 * Instance du plateau de jeu
	 * */
	private Plateau plateau;
	
	/**
	 * Pointeur vers l'ihm
	 * */
	private GUI gui;
	
	public Controleur() {
		plateau = PlateauFactory.creerPlateau(1000, 1000);
		init();
	}
	
	public void init() {
		gui = new GUI(this);
	}
	
	/**
	 * Retourne la largeur du plateau
	 * @return largeur du plateau en int
	 * */
	public int getLargeur() {
		return plateau.getLargeur();
	}
	
	public void rotationHoraire()
	{
		plateau.getFusee().rotation('+');
	}
	
	public void rotationAntiHoraire()
	{
		plateau.getFusee().rotation('-');
	}
	
	public void acceleration()
	{
		plateau.getFusee().acceleration();
	}
	
	public void deceleration()
	{
		plateau.getFusee().deceleration(System.currentTimeMillis());
	}
	
	
	public Plateau getPlateau()
	{
		return this.plateau;
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

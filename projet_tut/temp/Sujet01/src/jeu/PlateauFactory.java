package jeu;


import java.awt.Dimension;

/**
 * Classe fabrique d'une instance de Plateau
 * @author am�lie nioche hugo labb�, yann reibel, cl�ment jeanne dit fouque, louis-pierre aubert
 * */
public class PlateauFactory {
	/**
	 * Pour qu'elle ne soit jamais instanci�e
	 * */
	private PlateauFactory() {}
	
	/**
	 * Fabrique d'un plateau
	 * @param largeur largeur du plateau
	 * @param hauteur hauteur du plateau
	 * @return une instance de Plateau, null en cas
	 * de param�tre invalide
	 * */
	public static Plateau creerPlateau(int largeur, int hauteur) {
		if(largeur <=0 || hauteur <= 0) return null;
		return new Plateau(new Dimension(largeur, hauteur));
	}
}


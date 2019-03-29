package jeu;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

/**
 * Repr�sentation d'un plateau de jeu
 * @author am�lie nioche hugo labb�, yann reibel, cl�ment jeanne dit fouque, louis-pierre aubert
 * */
public class Plateau {
	/**
	 * Dimension du plateau de jeu
	 * */
	private Dimension dimensions;
	
	/**
	 * Fus�e du jeu sur le plateau
	 * */
	
	private Fusee fusee;
	
	/**
	 * Ensemble des plan�tes du jeu
	 * */
	private Set<Planete> planetes;
	
	/**
	 * Constructeur par d�faut, cr�� un plateau de jeu vide
	 * � partir de ses dimensions
	 * */
	Plateau(Dimension dimensions){
		this.dimensions = dimensions;
		planetes = new HashSet<>();
		this.fusee = new Fusee("D:\\images\\vaisseau1.png", 30, 30);
	}
	
	/**
	 * Attribue une fus�e au plateau
	 * @param instance de Fusee � ajouter au plateau
	 * @return vrai si tout se passe bien, faut si le param�tre est null
	 * */
	public boolean setFusee(Fusee fusee) {
		if(fusee == null) return false;
		
		this.fusee = fusee;
		this.fusee.setPlateau(this);
		
		return true;
	}
	
	public Fusee getFusee()
	{
		return this.fusee;
	}
	
	/**
	 * Permet d'ajouter une plan�te au plateau
	 * @param planete plan�te � ajouter au plateau courant
	 * */
	public void ajouterPlanete(Planete planete) {
		planetes.add(planete);
	}
	
	/**
	 * Retourne la largeur du plateau
	 * @return largeur du plateau en int
	 * */
	public int getLargeur() {
		return dimensions.width;
	}
	
	/**
	 * Retourne la hauteur du plateau
	 * @return hauteur du plateau en int
	 * */
	public int getHauteur() {
		return dimensions.height;
	}
}

package sujet1.metier.plateau;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

import sujet1.Fusee;
import sujet1.Planete;

/**
 * Représentation d'un plateau de jeu
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */
public class Plateau {
	/**
	 * Dimension du plateau de jeu
	 * */
	private Dimension dimensions;
	
	/**
	 * Fusée du jeu sur le plateau
	 * */
	private Fusee fusee;
	
	/**
	 * Ensemble des planètes du jeu
	 * */
	private Set<Planete> planetes;
	
	/**
	 * Constructeur par défaut, créé un plateau de jeu vide
	 * à partir de ses dimensions
	 * */
	Plateau(Dimension dimensions){
		this.dimensions = dimensions;
		planetes = new HashSet<>();
	}
	
	/**
	 * Attribue une fusée au plateau
	 * @param instance de Fusee à ajouter au plateau
	 * @return vrai si tout se passe bien, faut si le paramètre est null
	 * */
	public boolean setFusee(Fusee fusee) {
		if(fusee == null) return false;
		
		this.fusee = fusee;
		this.fusee.setPlateau(this);
		
		return true;
	}
	
	/**
	 * Permet d'ajouter une planète au plateau
	 * @param planete planète à ajouter au plateau courant
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

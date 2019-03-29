package sujet1.metier.plateau;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

import sujet1.Controleur;
import sujet1.metier.Coordonnees;
import sujet1.metier.Fusee;
import sujet1.metier.Planete;

/**
 * Représentation d'un plateau de jeu
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */
public class Plateau {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	private Controleur ctrl;
	
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
	 * Matrice représentant l'espace de jeu, elle permettra de vérifier
	 * si le contour de la fusée touche le contour d'une planète
	 * */
	private char[][] matriceEspace;
	
	/**
	 * Constructeur par défaut, créé un plateau de jeu vide
	 * à partir de ses dimensions
	 * */
	Plateau(Controleur ctrl, Dimension dimensions){
		this.ctrl = ctrl;
		this.dimensions = dimensions;
		planetes = new HashSet<>();
		
		matriceEspace = new char[dimensions.width][dimensions.height];
	}
	
	public void initMatrice() {
		for(Planete p: planetes)
			for(Coordonnees c: p.getContour())
				matriceEspace[c.getX()][c.getY()] = 'p';
	}
	
	public void setMatrice(char[][] matrice) {
		this.matriceEspace = matrice;
	}
	
	/**
	 * Retourne vrai si le pixel concerné est un contour de planètre
	 * @param x position x
	 * @param y position y
	 * @return vrai le le pixel est un contour de planete
	 * */
	public boolean getContenuPixel(int x, int y) {
		return matriceEspace[x][y] == 'p';
	}
	
	public int getXfusee() {
		return fusee.getPosX();
	}
	
	public int getYfusee() {
		return fusee.getPosY();
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
	 * Permet entre autres aux éléments du plateau d'agir sur le controleur
	 * */
	public Controleur getCtrl() {
		return ctrl;
	}
	
	/**
	 * Accès à la fusee
	 * */
	public Fusee getFusee() {
		return fusee;
	}
	
	/**
	 * Permet d'ajouter une planète au plateau
	 * @param planete planète à ajouter au plateau courant
	 * */
	public void ajouterPlanete(Planete planete) {
		planetes.add(planete);
	}
	
	/**
	 * Retourne l'ensemble des planètes
	 */
	public Set<Planete> getPlanetes(){
		return planetes;
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

package sujet1;

import java.awt.image.BufferedImage;
import java.util.Set;

import sujet1.ihm.Fenetre;
import sujet1.metier.Coordonnees;
import sujet1.metier.Fusee;
import sujet1.metier.Planete;
import sujet1.metier.TraitementImage;
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
		plateau = PlateauFactory.creerPlateau(this, 1000, 1000);
		init();
	}
	
	public void init() {
		enDure();
		
		plateau.setFusee(new Fusee("vaisseau1.png", 0, 0, 0));
		java.awt.EventQueue.invokeLater(new ThreadIhm(this));
	}
	
	/**
	 * A SUPPRIMER
	 * JUSTE POUR TESTS
	 * */
	public void enDure() {
		Planete[] p = new Planete[20];
		
		for(int i=0; i<p.length; i++) {
			p[i] = new Planete();
			plateau.ajouterPlanete(p[i]);
		}
		
	}
	
	/**
	 * Permet de rafraichir le panel d'affichage
	 * */
	public void majIHM() {
		ihm.majIHM();
	}
	
	/**
	 * Permet de charger une image et de la retourner
	 * */
	public BufferedImage chargerImage(String nom) {
		TraitementImage t = new TraitementImage(nom);
		t.chargerImageInitiale();
		BufferedImage ret = t.getImage();
		
		t = null;
		
		return ret;
	}
	
	/**
	 * Génère la matrice pour détecter les collisions
	 * */
	public void genererMatrice(BufferedImage img) {
		TraitementImage t = new TraitementImage(img);
		//t.chargerImageInitiale();
		t.creerMasque();
		t.creerImageContour();
		
		char[][] matrice = new char[1000][1000];
		for(Coordonnees c: t.getCoordsContour())
			matrice[c.getX()][c.getY()] = 'p';
		
		plateau.setMatrice(matrice);
		
		for(int cptLig=0; cptLig<matrice.length; cptLig++)
			for(int cptCol=0; cptCol<matrice[cptLig].length; cptCol++)
				if(matrice[cptLig][cptCol] == 'p')
				System.out.println(matrice[cptLig][cptCol]);
	}
	
	/**
	 * Retourne l'ensemble des planètes
	 */
	public Set<Planete> getPlanetes(){
		return plateau.getPlanetes();
	}
	
	/**
	 * Accès au plateau
	 * */
	public Plateau getPlateau() {
		return plateau;
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
	
	public int getXfusee() {
		return plateau.getXfusee();
	}
	
	public int getYfusee() {
		return plateau.getYfusee();
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
	
	/**
	 * Retourne vrai si le pixel concerné est un contour de planètre
	 * @param x position x
	 * @param y position y
	 * @return vrai le le pixel est un contour de planete
	 * */
	public boolean getContenuPixel(int x, int y) {
		return plateau.getContenuPixel(x, y);
	}
	
	public static void main(String[] args) {
		new Controleur();
	}

	private class ThreadIhm implements Runnable{
        private Controleur ctrl;
        
        public ThreadIhm(Controleur ctrl){
            this.ctrl = ctrl;
        }

        @Override
        public void run() {
            ihm = new Fenetre(ctrl);
        }
        
    }
}

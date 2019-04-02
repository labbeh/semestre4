package sujet1.ihm;

import javax.swing.JFrame;
import sujet1.Controleur;
import sujet1.metier.plateau.Plateau;

public class Fenetre extends JFrame {
	/**
	 * Panel représentant l'espace
	 * */
	private Plateau espace;
	
	public Fenetre ( Controleur ctrl, int largeur, int hauteur ) {
		this.setTitle("Space Explorer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(largeur, hauteur);
		this.setResizable(false);
		setVisible(true);
	}
	
	
	public void ajouterPlateau ( Plateau p ) {
		this.espace = p;
		this.add(espace);
	}
	
	public void fermerFenetre() { setVisible(false); }
}

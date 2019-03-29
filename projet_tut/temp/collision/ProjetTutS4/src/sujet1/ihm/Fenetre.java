package sujet1.ihm;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
		this.espace = new Plateau( this, new Dimension(largeur, hauteur) );
		
		this.add(espace);
		
		setVisible(true);
	}
	
}

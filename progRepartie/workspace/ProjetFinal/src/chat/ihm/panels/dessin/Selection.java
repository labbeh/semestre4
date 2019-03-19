package chat.ihm.panels.dessin;

import java.awt.GridLayout;
import java.awt.LayoutManager;

/**
 * Panel qui affiche l'item que l'on veut dessiner (rond, rectangle ...)
 * */

import javax.swing.JButton;
import javax.swing.JPanel;

public class Selection extends JPanel {
	/**
	 * Permet de dessiner un carré
	 * */
	private JButton carre;
	
	/**
	 * Permet de dessiner un rond
	 * */
	private JButton rond;
	
	/**
	 * Permet de dessiner une flèche
	 * */
	private JButton fleche;
	
	/**
	 * Permet de gommer un élément de la zone de dessin
	 * */
	private JButton gomme;
	
	/**
	 * Annule la dernière action
	 * */
	private JButton undo;
	
	/**
	 * Permet d'ajouter du texte sur la zone de dessin
	 * */
	private JButton texte;
	
	/**
	 * Permet de choisir si on dessine un élément plein ou vide
	 * */
	private JButton pleinVide;
	
	

	public Selection() {
		super();
		this.carre = new JButton("Carré");
		this.rond = new JButton("Rond");
		this.fleche = new JButton("Flèche");
		this.gomme = new JButton("Gomme");
		this.undo = new JButton("Undo");
		this.texte = new JButton("Texte");
		this.pleinVide = new JButton("Plein/vide");
		
		setLayout(new GridLayout());
		
		add(carre);
		add(rond);
		add(fleche);
		add(gomme);
		add(undo);
		add(texte);
		add(pleinVide);
	}

}

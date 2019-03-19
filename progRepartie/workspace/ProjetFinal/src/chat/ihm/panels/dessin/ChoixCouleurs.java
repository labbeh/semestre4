package chat.ihm.panels.dessin;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ChoixCouleurs extends JPanel {
	/**
	 * Selection couleur rouge
	 * */
	private JButton rouge;
	
	/**
	 * Selection couleur bleu
	 * */
	private JButton bleu;
	
	/**
	 * Selection couleur vert
	 * */
	private JButton vert;
	
	/**
	 * Selection couleur jaune
	 * */
	private JButton jaune;
	
	/**
	 * Selection couleur noir
	 * */
	private JButton noir;
	
	/**
	 * Selection couleur blanc
	 * */
	private JButton blanc;
	
	public ChoixCouleurs(){
		super();
		
		rouge = new JButton();
		bleu = new JButton();
		vert = new JButton();
		jaune = new JButton();
		noir = new JButton();
		blanc = new JButton();
		
		// coloration des btns
		rouge.setBackground(Color.RED);
		bleu.setBackground(Color.BLUE);
		vert.setBackground(Color.GREEN);
		jaune.setBackground(Color.YELLOW);
		noir.setBackground(Color.BLACK);
		blanc.setBackground(Color.WHITE);
		
		// gestion layout
		setLayout(new GridLayout());
		
		// ajout des btns
		add(rouge);
		add(bleu);
		add(vert);
		add(jaune);
		add(noir);
		add(blanc);
	}
}

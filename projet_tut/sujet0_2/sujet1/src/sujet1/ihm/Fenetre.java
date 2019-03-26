package sujet1.ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sujet1.Controleur;

public class Fenetre extends JFrame{
	/**
	 * Panel représentant l'espace
	 * */
	private JPanel espace;
	
	public Fenetre(Controleur ctrl) {
		espace = new JPanel();
		
		setTitle("Mon espace");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(espace);
		
		setSize(ctrl.getLargeur(), ctrl.getHauteur());
		setVisible(true);
	}
}

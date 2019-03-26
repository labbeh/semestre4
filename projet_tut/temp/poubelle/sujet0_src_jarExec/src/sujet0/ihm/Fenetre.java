package sujet0.ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sujet0.controleur.Controleur;

/**
 * Unique fenetre graphique de l'application
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */
public class Fenetre extends JFrame {
	
	/**
	 * SerialUID par défaut
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Panel pour l'affichage des contours de l'image
	 * */
	private JPanel controurImage;

	public Fenetre(Controleur ctrl) {
		controurImage = new PanelImage(ctrl);
		
		setTitle("Détection de contour et rotation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(controurImage);
		
		setSize(1024, 768);
		setVisible(true);
	}
	
	public void majIHM() {
		controurImage.repaint();
	}

}

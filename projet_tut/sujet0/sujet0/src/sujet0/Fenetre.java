package sujet0;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	private Controleur ctrl;
	
	/**
	 * Panel pour l'affichage des contours de l'image
	 * */
	private JPanel controurImage;

	public Fenetre(Controleur ctrl) {
		this.ctrl = ctrl;
		controurImage = new PanelImage(ctrl);
		
		setTitle("Détection de contour et rotation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(controurImage);
		
		pack();
		setVisible(true);
	}

}

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
		
		setTitle("Détection et rotation d'une image");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(controurImage);
		
		setSize(1024, 768);
		setVisible(true);
	}
	
	public void majIHM() {
		controurImage.repaint();
	}

}

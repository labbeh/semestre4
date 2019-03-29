package vue;

import javax.swing.JFrame;

import controleur.Controleur;

public class GUI extends JFrame {
	
	private PanelMap map;
	private Controleur controleur;
	
	public GUI(Controleur controleur)
	{
		this.controleur = controleur;
		this.setTitle("Rotation image");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.map = new PanelMap(controleur);
		
		this.setContentPane(map);
		
		this.setVisible(true);
	}
	
	
}



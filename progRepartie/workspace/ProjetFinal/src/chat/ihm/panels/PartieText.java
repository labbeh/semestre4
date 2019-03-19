package chat.ihm.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PartieText extends JPanel {
	/**
	 * Zone d'apparition des msg envoyés et reçus
	 * */
	private JTextArea convers;
	
	/**
	 * Zone pour taper le texte à envoyer
	 * */
	private JTextField saisie;
	
	public PartieText(){
		super();
		
		// gestion layout
		setLayout(new BorderLayout());
		
		// conversation
		convers = new JTextArea();
		add(convers, BorderLayout.CENTER);
		
		// saisie
		saisie = new JTextField();
		add(saisie, BorderLayout.PAGE_END);
	}
}
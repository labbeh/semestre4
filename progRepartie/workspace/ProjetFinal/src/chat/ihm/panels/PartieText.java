package chat.ihm.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.ihm.ecouteurs.EnvoieMessage;

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
		//saisie.addActionListener(new EnvoieMessage());
		add(saisie, BorderLayout.PAGE_END);
	}
	
	public void setEcouteurEnvoieMsg(EnvoieMessage ecouteur){
		saisie.addActionListener(ecouteur);
	}
}

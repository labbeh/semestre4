package chat.ihm.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.ihm.ecouteurs.ListenerEnvoieMessage;

public class PartieText extends JPanel {
	/**
	 * Chaine vide afin de nettoyer la zone de texte
	 * sans avoir à recrééer un objet String à chaque appel
	 * de la méthode de nettoyage
	 * */
	private static final String CHAINE_VIDE = "";
	
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
	
	/**
	 * Permet de nettoyer la zone ou on taape le message
	 * après son envoi
	 * */
	public void nettoyerZoneTexte(){
		saisie.setText(CHAINE_VIDE);
	}
	
	public void setEcouteurEnvoieMsg(ListenerEnvoieMessage ecouteur){
		saisie.addActionListener(ecouteur);
	}

	public void ajouterMessage(String message) {
		convers.setText(convers.getText() + message +"\n");
		
	}
}

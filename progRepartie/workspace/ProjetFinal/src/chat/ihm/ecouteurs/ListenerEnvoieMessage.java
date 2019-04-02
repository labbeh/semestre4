package chat.ihm.ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import chat.Controleur;

public class ListenerEnvoieMessage implements ActionListener {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	private Controleur ctrl;
	
	public ListenerEnvoieMessage(Controleur ctrl){
		this.ctrl = ctrl;
	}
	
	/**
	 * Permet au client d'envoyer un message sur le serveur
	 * N'envoie le message que si la zone de texte n'est
	 * pas vide
	 * */
	@Override
	public void actionPerformed(ActionEvent evt) {
		JTextField txt = (JTextField)evt.getSource();
		
		if(!txt.getText().equals("")){
			ctrl.envoyerMessage(txt.getText());
			ctrl.nettoyerZoneTexte();
		}
		
	}

}

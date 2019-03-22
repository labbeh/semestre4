package chat.metier.local;

import chat.Controleur;

/**
 * Classe métier permettant la gestion des éléments
 * puis les renvoies au controleur
 * */

public class Gestion {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	private Controleur ctrl;
	
	/**
	 * Instance du mécanisme de gestion des messages
	 * */
	private GestionMessages messages;
	
	public Gestion(Controleur ctrl){
		this.ctrl = ctrl;
		
		this.messages = new GestionMessages();
	}
	
	/**
	 * Envoie un message sur le serveur
	 * @param message chaine de caractère contenant le message
	 * à envoyer vers le serveur de chat
	 * @param text 
	 * */
	public void envoyerMessage(String emetteur, String message){
		messages.ajouterMsg(emetteur, message);
	}
}

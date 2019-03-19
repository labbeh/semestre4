package chat.metier.local;

import java.util.Vector;

public class GestionMessages {
	/**
	 * Ensembles des messages partagées sur la session de chat
	 * */
	private Vector<String> msgs;
	
	/**
	 * Constructeur par défaut
	 * */
	public GestionMessages(){
		msgs = new Vector<>();
	}
	
	/**
	 * Permet d'insérer un message dans la session de chat courante
	 * du client
	 * @param msg le message à insérer en String
	 * */
	public synchronized void ajouterMsg(String msg){
		msgs.add(msg);
	}
}

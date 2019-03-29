package chat;

import chat.ihm.Fenetre;
import chat.ihm.ecouteurs.EnvoieMessage;
import chat.metier.local.Gestion;
import chat.metier.reseau.Client;
import chat.metier.reseau.Serveur;

public class Controleur {
	/**
	 * Instance vers l'ihm
	 * */
	private Fenetre ihm;
	
	/**
	 * Gestion
	 * */
	private Gestion gestion;
	
	/**
	 * Pointeur vers l'instance de serveur
	 * Null si on est juste client
	 * */
	private Serveur serveur;
	
	/**
	 * Instance de la gestion de connexion au serveur pour le client
	 * */
	private Client client;
	
	
	public Controleur(){
		this.gestion = new Gestion(this);
		init();
		initClient();
		initServeur();
	}
	
	/**
	 * Initialise l'application
	 * */
	public void init(){
		// instanciation des listener
		EnvoieMessage listenerMsg = new EnvoieMessage(this);
		
		// instanciation de l'ihm
		ihm = new Fenetre();
		
		// on met en place les écouteurs
		ihm.setEcouteurEnvoieMsg(listenerMsg);
	}
	
	/**
	 * Permet d'initialiser la partie client
	 * */
	public void initClient(){
		client = new Client(this, 4597, "224.0.4.22");
	}
	
	/**
	 * Permet d'initialiser la partie serveur
	 * */
	public void initServeur(){
		serveur = new Serveur(this, "224.0.4.22", 4597);
		serveur.lancement();
	}
	
	/**
	 * Permet d'envoyer un message sur le serveur
	 * @param text contenu du message
	 * */
	public void envoyerMessage(String message) {
		//gestion.envoyerMessage(emetteur, text);
		//System.out.println(message);
		client.envoyerMessage(message);
	}
	
	
}

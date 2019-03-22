package chat.metier.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionMessages {
	/**
	 * Ensembles des messages partagées sur la session de chat
	 * associés à l'adresse de leur émetteur
	 * */
	private Map<String, ArrayList<String>> msgs;
	
	/**
	 * Constructeur par défaut
	 * */
	public GestionMessages(){
		msgs = new HashMap<>();
	}
	
	/**
	 * Permet d'insérer un message dans la session de chat courante
	 * du client
	 * @param emetteur adresse de l'émetteur
	 * @param msg le message à insérer en String
	 * */
	public synchronized void ajouterMsg(String emetteur, String msg){
		if(!msgs.containsKey(emetteur))
			msgs.put(emetteur, new ArrayList<String>());
		msgs.get(emetteur).add(msg);
	}
	
	/**
	 * Permet d'obtenir le dernier message envoyé
	 * */
	/*public synchronized String getDernierMsg(){
		return msgs.get(msgs.size()-1);
	}*/
}

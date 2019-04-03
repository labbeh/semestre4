package chat.metier.local;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import chat.Controleur;

public class GestionMessages {
	/**
	 * Pointeur vers l'instance du controleur de l'application
	 * */
	private Controleur ctrl;
	
	/**
	 * Ensembles des messages partagées sur la session de chat
	 * associés à l'adresse de leur émetteur
	 * */
	private Map<String, ArrayList<String>> msgs;
	
	/**
	 * Constructeur par défaut
	 * */
	public GestionMessages(Controleur ctrl){
		this.ctrl = ctrl;
		msgs = new HashMap<>();
	}
	
	/**
	 * Permet d'insérer un message dans la session de chat courante
	 * du client
	 * @param emetteur adresse de l'émetteur
	 * @param msg le message à insérer en String
	 * */
	public synchronized void ajouterMsg(String emetteur, String msg){
		if(msg.startsWith("DESSIN") && msg.contains(";")){
			Scanner sc = new Scanner(msg);
			sc.useDelimiter(";");

			// on passe le mot DESSIN
			sc.next();
			
			int x1 = Integer.parseInt(sc.next());
			int y1 = Integer.parseInt(sc.next());
			int x2 = Integer.parseInt(sc.next());
			int y2 = Integer.parseInt(sc.next());
			
			Color coul = Controleur.getColorByName(sc.next());
			// des \00 se mettent à la fin de la ligne pour une raison inconnu
			boolean estVide = Boolean.parseBoolean(sc.next().replaceAll("\00", ""));
			
			sc.close();
			ctrl.ajouterRectangle(x1, y1, x2, y2, coul, estVide);
		}
		
		if(!msgs.containsKey(emetteur))
			msgs.put(emetteur, new ArrayList<String>());
		msgs.get(emetteur).add(msg);

		ctrl.ajouterMessageConvers("[ " +emetteur +" ]" + " dit: " +msg);
		ctrl.majIHM();
	}
}

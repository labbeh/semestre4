//package chat.metier.reseau;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

//import chat.Controleur;
//import chat.metier.local.GestionMessages;

public class Serveur {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	//private Controleur ctrl;

	/**
	 * Gestion des messages que le serveur reçoit
	 * */
	//private GestionMessages gestionMsgs;

	/**
	 * Adresse ip
	 */
	private InetAddress mcast;

	/**
	 * Port récepteur
	 */
	private MulticastSocket ms;

	/**
	 * Constructeur de la classe Serveur
	 * @param ctrl pointeur vers l'instance du controleur
	 * @param ip ip ou nom DNS du serveur en String
	 * @param port port du serveur en int
	 * @throws IOException
	 */
	public Serveur (String ip, int port){
		/*this.ctrl = ctrl;
		this.gestionMsgs = new GestionMessages();*/

		try{
			mcast = InetAddress.getByName(ip);
			ms = new MulticastSocket(port);
			ms.joinGroup(mcast);
		}
		catch(IOException evt){evt.printStackTrace();}
	}

	/**
	 * A SUPPRIMER
	 * */
	public static void main(String[] args) {
		new Serveur( "224.0.4.22", 4597);
	}

	/**
	 * Méthode permettant de lancer le serveur
	 * @throws IOException
	 */

}

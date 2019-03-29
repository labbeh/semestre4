package chat.metier.reseau;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import chat.Controleur;
import chat.metier.local.GestionMessages;

public class Serveur {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	private Controleur ctrl;
	
	/**
	 * Gestion des messages que le serveur reçoit
	 * */
	private GestionMessages gestionMsgs;
	
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
	public Serveur (Controleur ctrl, String ip, int port){
		this.ctrl = ctrl;
		this.gestionMsgs = new GestionMessages();
		
		try{
			mcast = InetAddress.getByName(ip);
			ms = new MulticastSocket(port);
			ms.joinGroup(mcast);
		}
		catch(IOException evt){System.out.println("SERVEUR: " +evt);}
	}
	
	/**
	 * Méthode permettant de lancer le serveur
	 * @throws IOException 
	 */
	public void lancement (){
		try{
			while(true) {
				DatagramPacket msg = new DatagramPacket(new byte[512], 512);
				//System.out.println("TEST: attente de msg ...");
				ms.receive(msg);
				
				/*System.out.println(msg.getAddress() + ":" +msg.getPort() +
				"a envoyé " + new String(msg.getData()));*/
				gestionMsgs.ajouterMsg(msg.getAddress().toString(), new String(msg.getData()));
			}
		}
		catch(IOException evt){
			System.out.println(evt);
		}
	}
}

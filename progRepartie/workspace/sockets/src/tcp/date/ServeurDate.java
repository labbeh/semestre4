package tcp.date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurDate {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket  serveur;
		Socket connexion;
		
		serveur = new ServerSocket(11111);
		System.out.println(serveur + " en attente !");
		
		connexion = serveur.accept();
		System.out.println(connexion + " connecté");
		
		// pour voir blocage
		/*BufferedReader recep = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
		String texte = recep.readLine();
		System.out.println("Texte reçu: " +texte);*/
		
		PrintWriter flux = new PrintWriter(connexion.getOutputStream());
		flux.println(new java.util.Date());
		
		flux.flush();
		connexion.close();
		serveur.close();

	}

}

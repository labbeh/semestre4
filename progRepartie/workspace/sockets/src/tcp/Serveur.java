package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket socketserver;
		Socket connexion;
		
		try{
			socketserver = new ServerSocket(2009);
			connexion = socketserver.accept();
			
			System.out.println("Connexion d’un client !");
			
			socketserver.close();
			connexion.close();
		}
		catch (IOException e) {e.printStackTrace();}

	}

}

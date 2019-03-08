package tcp.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDateT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Socket socket = new Socket("di-718-10", 2009);
			BufferedReader fluxHeure = new BufferedReader(
			new InputStreamReader(socket.getInputStream()));
			String dateHeure = fluxHeure.readLine();
			System.out.println("La date et l’heure:  " + dateHeure);
			pause(10000);
			socket.close();
		}
		catch (UnknownHostException err){System.err.println ("ClientDateT : " + err);}
		catch (IOException 			err){System.err.println ("ClientDateT : " + err);}
	}

	private static void pause(long temps) {
		try{
			Thread.sleep(temps);
		}
		catch(InterruptedException exception){}
		
	}
}

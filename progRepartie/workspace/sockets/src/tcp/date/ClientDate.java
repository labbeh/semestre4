package tcp.date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Socket  socket = new Socket(InetAddress.getByName(args[0]),2009);
			BufferedReader fluxHeure = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// pour voir blocage
			PrintWriter flux = new PrintWriter(socket.getOutputStream());
			flux.println("Message du client");
			
			String dateHeure = fluxHeure.readLine();
			System.out.println("La date et l’heure :  " + dateHeure);
			
			socket.close();
		}
		catch (UnknownHostException err) {System.err.println ("ClientDate : " + err);}
		catch (IOException err) {System.err.println ("ClientDate : " + err);}

	}

}

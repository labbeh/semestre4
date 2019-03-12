package multicast;

import java.net.*;
import java.util.Scanner;

public class EmetteurMulticastIP {
	
	/**
	 * 
	 * */
	public static void main (String args[]) throws Exception{
		String message = "Hello world!!!";
		
		MulticastSocket ms = new MulticastSocket();
		InetAddress mcast = InetAddress.getByName("230.1.2.3");
		//DatagramPacket dp = new DatagramPacket(message.getBytes(),message.length(), mcast,4567);
		
		/*for (int i=0; i<10; i++)
			ms.send(dp);*/
		Scanner clavier = new Scanner(System.in);
		while(true){
			System.out.print("Tapez message: ");
			message = clavier.nextLine();
			
			DatagramPacket dp = new DatagramPacket(message.getBytes(),message.length(), mcast,4567);
			ms.send(dp);
		}
		
		//ms.close();
	}
}

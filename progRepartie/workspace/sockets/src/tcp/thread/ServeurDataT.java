package tcp.thread;

import java.net.ServerSocket;
import java.net.Socket;

public class ServeurDataT {

	/**
	 * @param args
	 */
	public static void main(String args[]) throws Exception{
		int nbrclient = 1;
		ServerSocket socket = new ServerSocket(2009);
		
		while(true){
			System.out.println("attente des clients!!!");
			Socket client_Socket = socket.accept();
			
			System.out.println("Le client "+nbrclient+ " est connecte !");
			nbrclient++;
			
			Service myService = new Service(client_Socket);
			myService.start();
		}
	}
}
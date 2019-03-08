package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Serveur {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(2009);
		DatagramPacket msg = new DatagramPacket(new byte[512], 512);
		
		ds.receive(msg);
		
		System.out.println("message Recu : " + new String(msg.getData()));
		
		String texteReponse = "OK";
		
		DatagramPacket reponse = new DatagramPacket(texteReponse.getBytes(),
				
		texteReponse.length(),msg.getAddress(), msg.getPort());
		
		ds.send(reponse);
		ds.close();

	}

}

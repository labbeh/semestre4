package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String message = "Hello world!";
		
		DatagramSocket ds = new DatagramSocket();
		DatagramPacket envoi = new DatagramPacket(message.getBytes(),message.length(),InetAddress.getByName("localhost"),2009);
		
		ds.send(envoi);
		
		DatagramPacket msg = new DatagramPacket(new byte[512], 512);
		
		ds.receive(msg);
		
		System.out.println ( new String(msg.getData()) );
		
		ds.close();

	}

}

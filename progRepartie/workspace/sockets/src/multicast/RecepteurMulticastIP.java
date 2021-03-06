package multicast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.*;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class RecepteurMulticastIP {
	/**
	 * Liste des messages, qq il y en a 10, ils sont sauver sur fichier
	 * et la liste est vidée
	 * */
	static List<String> msgs = new Vector<>();
	
	/**
	 * 
	 * */
	public static void main (String args[]) throws Exception {
		InetAddress mcast = InetAddress.getByName("230.1.2.3");
		MulticastSocket ms = new MulticastSocket(4567);
		
		ms.joinGroup(mcast);
		
		while (true) {
			DatagramPacket msg = new DatagramPacket(new byte[512], 512);
			ms.receive(msg);
			
			String recu = msg.getAddress() + ":" +msg.getPort()+ "a envoyé " + new String(msg.getData());
			System.out.println(recu);
			msgs.add(recu);
			
			if(msgs.size() == 10)
				toFile();
		}
	}
	
	private static void toFile() throws IOException{
		Vector<String> temp = new Vector<>(msgs);
		msgs.clear();
		
		FileWriter fw = new FileWriter("log.txt", true);
		PrintWriter pw = new PrintWriter(fw);
		
		//StringBuilder sb = new StringBuilder();
		for(String s: temp)
			pw.println(s);
		//pw.print()
		
		fw.close();
		pw.close();
	}
}

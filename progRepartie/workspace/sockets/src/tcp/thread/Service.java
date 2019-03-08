package tcp.thread;

import java.io.PrintWriter;
import java.net.Socket;

public class Service extends Thread {
	/**
	 * Socket TCP
	 * */
	private Socket socket;
	
	public Service (Socket s){
		socket = s;
	}
	
	public void run() {
		try {
			PrintWriter flux = new PrintWriter(socket.getOutputStream());
			flux.println(new java.util.Date());
			flux.flush();
		}
		catch (Exception e) {}
		
		finally {
			try{socket.close();}
			catch (Exception e) {}
		}
	}
}

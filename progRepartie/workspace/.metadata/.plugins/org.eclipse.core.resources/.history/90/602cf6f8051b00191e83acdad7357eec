package tp1;

import java.io.*;

public class Timer extends Thread{
	
	/**
	 * Sera incrémenté chaque seconde qui passe après lancement du thread
	 * */
	int compteur;
	
	public Timer(){
		compteur = 0;
	}
	
	@Override
	public void run(){
		while(true){
			try{
				sleep(1000);
			}
			catch(Exception evt){}
			compteur++;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		Timer crono = new Timer();
		crono.start();
		
		System.out.println("Saisie clavier...");
		
		InputStreamReader clavier = new InputStreamReader(System.in);
		try{
			clavier.read();
		}
		catch(Exception evt){}
		
		System.out.println("vous avez mis crono " +crono.compteur+ " secondes ");
	}
}
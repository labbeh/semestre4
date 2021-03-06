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
		boolean terminer = false;
		
		while(!terminer){
			try{
				sleep(1000);
			}
			catch(InterruptedException evt){terminer = true;}
			//System.out.println("thread timer");
			compteur++;
		}
		System.out.println("thread fils fini");
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException{
		Timer crono = new Timer();
		
		System.out.println("Etat fils: " +crono.getState() + "  priorité: " + crono.getPriority()+ 
				   " id: " +crono.getId());
		
		crono.start();
		
		System.out.println("Etat fils: " +crono.getState() + "  priorité: " + crono.getPriority()+ 
						   " id: " +crono.getId());
		
		System.out.println("Saisie clavier...");
		InputStreamReader clavier = new InputStreamReader(System.in);
		afficherThreads();
		try{
			clavier.read();
		}
		catch(Exception evt){}
		
		System.out.println("Etat fils: " +crono.getState() + "  priorité: " + crono.getPriority()+ 
				   " id: " +crono.getId());
		
		System.out.println("vous avez mis " +crono.compteur+ " secondes ");
		crono.interrupt();
		crono.join();
		
		System.out.println("Etat fils: " +crono.getState() + "  priorité: " + crono.getPriority()+ 
				   " id: " +crono.getId());
		
		afficherThreads();
		System.out.println("fin du main");
	}
	
	public static void afficherThreads(){
		Thread[] result = new Thread[5];
		
		int nb = Thread.enumerate(result);
		
		System.out.println("liste des threads" +"(" + nb + ") : ");
		
		for(Thread t: result)
			if(t != null)
				System.out.println(t.getName());
	}
}
package tpSemaphore.parking;

import java.util.concurrent.Semaphore;

public class Parking {
	/**
	 * Semaphore pour gerer les accès multiples
	 * */
	private Semaphore semaphore;

	/**
	 * Nombre de places dans le parking
	 * */
	private final int nbPlaces;

	/**
	 * Nombre de véhicule dans le parking
	 * */
	private int nbVh;

	/**
	 * Nombre de places restantes
	 * */
	private int restant;

	public Parking(int nbPlaces, int nbVh) {
		this.semaphore = new Semaphore(nbPlaces, true); // true pour garantie fifo
		
		this.nbVh 	  = nbVh;
		this.nbPlaces = nbPlaces;
		this.restant  = nbPlaces - nbVh;
	}
	
	public void sortir() {

		semaphore.release();
		if(nbVh > 0) {
			nbVh--;
			restant++;
		}
	}
	
	public void entrer() {
		try{
			semaphore.acquire();
			if(restant > 0) {
				nbVh++;
				restant--;
			}
		}
		catch(InterruptedException evt){
			evt.printStackTrace();
		}
		
	}
	
	public Semaphore getSemaphore() {
		return semaphore;
	}

	public int getNbVh() {
		return nbVh;
	}

	public int getRestant() {
		return restant;
	}
	
}

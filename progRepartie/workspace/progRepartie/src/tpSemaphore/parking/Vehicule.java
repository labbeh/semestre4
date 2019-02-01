package tpSemaphore.parking;

import java.util.concurrent.Semaphore;

public class Vehicule extends Thread {
	private static int nbInst = 0;
	private int num;
	
	/**
	 * Semaphore
	 * */
	private Semaphore semaphore;
	
	/**
	 * Accès au parking
	 * */
	private Parking parking;
	
	public Vehicule(Parking parking) {
		this.semaphore = parking.getSemaphore();
		this.parking = parking;
		
		this.num = ++ nbInst;
	}
	
	@Override
	public void run() {
		//boolean enCours = true;
		int cpt = 0;
		
		while(cpt < 3){

			//semaphore.acquire();
			
			deplacer();
			entrerParking();
			stationner();
			sortirParking();
				
			//semaphore.release();
			//Thread.sleep(2000);
			cpt++;
		}
		System.out.println("Vehicule " +num+ " a fini");
	}

	private void sortirParking() {
		parking.sortir();
		System.out.println("v" +num+ " sort reste " +parking.getRestant());
		
	}

	private void stationner() {
		try{
			Thread.sleep(2000 +num);
		}
		catch(InterruptedException evt){
			evt.printStackTrace();
		}
	}

	private void entrerParking() {
		parking.entrer();
		System.out.println("v" +num+ " entre reste " +parking.getRestant());
		
	}

	private void deplacer() {
		try{
			Thread.sleep(2000 +num*1000);
		}
		catch(InterruptedException evt){
			evt.printStackTrace();
		}
	}
}

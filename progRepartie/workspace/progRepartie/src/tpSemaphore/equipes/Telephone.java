package tpSemaphore.equipes;

import java.util.concurrent.Semaphore;

public class Telephone {
	/**
	 * Semaphore mutex
	 * */
	private Semaphore semaphore;
	
	public Telephone(){
		this.semaphore = new Semaphore(1);
	}
	
	public void appeler() {
		semaphore.tryAcquire();
	}
}

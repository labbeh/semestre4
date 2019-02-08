package philosophes.metier;

import java.util.concurrent.Semaphore;

public class Fourchette {
	/**
	 * Référence vers un des deux philosophes ayant accès à la fourchette
	 * */
	private Philosophe ph1;
	
	/**
	 * Référence vers un des deux philosophes ayant accès à la fourchette
	 * */
	private Philosophe ph2;
	
	/**
	 * Semaphore pour que les 2 philosophes ne prennent pas la fourchette en même temps
	 * */
	private Semaphore semaphore;
	
	/**
	 * Compteur du nombre d'instance de Fourchette pour numéro unique
	 * */
	private static int nbInst = 0;
	
	/**
	 * Identifiant unique d'une instance
	 * */
	private int num;
	
	/**
	 * Construit une Fourchette qui sera partagée entre les philosophes à partir
	 * des références vers les deux philosophes qui la partageront
	 * @param ph1 premier philosophe ayant accès à la fourchette
	 * @param ph2 deuxième philosophe ayant accès à la fourchette
	 * */
	public Fourchette() {
		this.num = ++nbInst;
		
		/*this.ph1 = ph1;
		this.ph2 = ph2;*/
		
		 semaphore = new Semaphore(1, true);
	}
	
	/**
	 * Permet à un philosphe de prendre la fourchette
	 * */
	public void prendre() {
		try{
			semaphore.acquire();
		}
		catch(InterruptedException evt){
			evt.printStackTrace();
		}
	}
	
	/**
	 * Libère la fourchette après utilisation
	 * */
	public void poser() {
		semaphore.release();
	}
	
	@Override
	public String toString(){
		return "f" +num;
	}

}

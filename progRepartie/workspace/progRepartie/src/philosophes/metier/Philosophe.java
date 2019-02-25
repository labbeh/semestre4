package philosophes.metier;

import philosophes.Controleur;

public class Philosophe extends Thread {
	/**
	 * Instance du controleur
	 * */
	private Controleur ctrl;
	
	/**
	 * Référence vers la fourchette qui lui est associée
	 * */
	private Fourchette fourchette;
	
	/**
	 * Nom du philosophe
	 * */
	private String nom;
	
	/**
	 * Construit un philosophe à partir de son nom
	 * @param nom nom du philosophe en String doit être unique
	 * */
	public Philosophe(String nom, Controleur ctrl){
		this.nom = nom;
		this.ctrl = ctrl;
	}

	@Override
	public void run() {
		super.run();
		while(ctrl.resteAssiette()){
			System.out.println("Philosophe " +nom+ " réfléchi");
			reflechir();
			
			System.out.println("Philosophe " +nom+ " mange");
			manger();
			
			System.out.println("Philosophe " +nom+ " dort");
			dormir();
		}
	}
	
	/**
	 * Permet au philosophe de dormir
	 * */
	private void dormir() {
		sleep();
	}
	
	/**
	 * Permet au philosophe de manger, aura pour effet d'enelver une assiette de la pile
	 * */
	private void manger() {
		Assiette a = ctrl.prendreAssiete();
		System.out.println(nom +" a pris l'assiete " +a.getNum());
		
		sleep(); // le temps de manger ...
		
		System.out.println(nom +" a fini de manger dans l'assiette " +a.getNum());
	}

	/**
	 * Permet au philosphe de réfléchir...
	 * */
	private void reflechir() {
		sleep();
	}
	
	/**
	 * Temporisation qui sere lancée pour dormir et réfléchir
	 * */
	private void sleep() {
		try{
			Thread.sleep(2000 +nom.length());
		}
		catch(InterruptedException evt){
			evt.printStackTrace();
		}
	}

	/**
	 * Retourne le nom du philosophe
	 * @return nom du philosophe
	 * */
	public String getNom() {
		return nom;
	}
	
	public void setFourchette(Fourchette fourchette) {
		this.fourchette = fourchette;
	}

	@Override
	public String toString(){
		return this.nom;
	}
	
}

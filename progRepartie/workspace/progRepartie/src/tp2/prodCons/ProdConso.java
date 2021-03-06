package tp2.prodCons;

import java.util.Random;

public class ProdConso extends Thread {
	/**
	 * Numéro séquentiel auto-incrémenté
	 * */
	private static int nbInst = 0;
	
	/**
	 * Id
	 * */
	private int id;
	
	/**
	 * Nombre de producteur ET de consommateur
	 * */
	private int nbProdCons;
	
	/**
	 * Génère des choses aléatoirement pour simuler le comportement de producteurs/consommateurs
	 * */
	private Random alea;
	
	/**
	 * Référence au buffer
	 * */
	private Buffer buffer;
	
	/**
	 * Construit un ProdCons à partir d'une référence au buffer et du nombre de producteur/consommateur voulut
	 * @param buffer un Buffer
	 * @param nbProdCons un int
	 * */
	public ProdConso(Buffer buffer, int nbProdCons){
		this.id = ++nbInst;
		
		this.buffer = buffer;
		this.nbProdCons = nbProdCons;
		
		this.alea = new Random(System.currentTimeMillis());
	}
	
	/**
	 * Lancement
	 * */
	@Override
	public void run(){
		int currentNb = 0;
		
		while(currentNb < nbProdCons){
			if(alea.nextBoolean()) { // producteur
				System.out.println("prodconso: " +id+ " va produire");
				buffer.ajouter();
				System.out.println("prodconso: " +id+ " a fini de produire nbProdCons= " +nbProdCons);
				currentNb++;
			}
			
			else{ // consommateur
				System.out.println("prodconso: " +id+ " va consommer");
				buffer.retirer();
				System.out.println("prodconso: " +id+ " a fini de consommer nbProdCons= " +nbProdCons);
				currentNb++;
			}
		}
		System.out.println("prodcons: " +id+ " a TERMINE");
	}
}

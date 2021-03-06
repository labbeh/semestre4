package tp2.jeuBalle;

/**
 * @author hugo labbé
 * */

public class Balle {
	
	/**
	 * Nombre de fois où la balle à été lancée
	 * */
	private int nbLance;
	
	/**
	 * Numéro du dernier joueur à avoir envoyé la balle
	 * */
	private int numJoueur;
	
	/**
	 * Pour lancer la baballe
	 * @param numJoueur numéro du joueur
	 * */
	synchronized void lancer(int numJoueur){
		
		
		if(numJoueur == this.numJoueur){
			try{
				wait();
			}
			catch(InterruptedException evt){evt.printStackTrace();}
		}
		
		if(numJoueur != this.numJoueur){
			nbLance++;
			this.numJoueur = numJoueur;
		}
		
		notify();
		System.out.println("Joueur " +numJoueur+ " a lancé la baballe " +Thread.currentThread().getName());
	}
	
	/**
	 * Retourne le nombre de fois ou la balle a été lancée
	 * @return nombre de fois ou la balle a été lancée
	 * */
	int getNbLance(){
		return nbLance;
	}
	
	/**
	 * Retourne le numéro du dernier joueur à avoir lancé la balle
	 * */
	int dernierJoueur(){
		return numJoueur;
	}
}
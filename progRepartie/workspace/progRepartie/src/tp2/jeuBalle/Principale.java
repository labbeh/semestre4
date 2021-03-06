package tp2.jeuBalle;

/**
 * @author hugo labbé
 * */

public class Principale {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Balle balle = new Balle();
		
		Joueur j1 = new Joueur(balle);
		Joueur j2 = new Joueur(balle);
		
		j1.start();
		j2.start();
		
		j1.join();
		j2.join();
		
		System.out.println();
		System.out.println("Nombre de lancé: " +balle.getNbLance());

	}
}
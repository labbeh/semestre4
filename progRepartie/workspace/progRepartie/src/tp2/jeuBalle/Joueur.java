package tp2.jeuBalle;

/**
 * @author hugo labbé
 * */

public class Joueur extends Thread {
	/**
	 * Nombre de lancé max que peut faire un joueur
	 * */
	private static final int LANCE_MAX = 10;
	
	/**
	 * Numéro auto-incrémenté pour définir le numéro du joueur
	 * */
	private static int numInst = 0;
	
	/**
	 * Numéro (unique) du joueur
	 * */
	private final int num;
	
	/**
	 * Balle partagée avec l'autre joueur
	 * */
	private Balle balle;
	
	Joueur(Balle balle){
		num = ++ numInst;
		this.balle = balle;
	}
	
	
	/**
	 * Lance la gestion du lancée de la balle par le joueur
	 * */
	@Override
	public void run() {
		super.run();
		
		//int cpt = 0;
		
		for(int cpt=0; cpt<LANCE_MAX; cpt++)
			balle.lancer(num);
		//notify();
		/*while(cpt<LANCE_MAX){
			if(balle.dernierJoueur() != num){
				balle.lancer(num);
				cpt++;
			}
			//else System.out.println("tentative avortée de " +Thread.currentThread().getName());
		}*/
	}
}
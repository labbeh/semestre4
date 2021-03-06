package tp2.prodCons;

public class Producteur extends Thread{
	/**
	 * Numéro séquentiel auto-incrémenté
	 * */
	private static int nbInst = 0;
	
	/**
	 * Numéro unique du producteur
	 * */
	private int id;
	
	/**
	 * Buffer partagé avec le Consommateur
	 * Doit attendre que le buffer en soit plus pleins
	 * avant de le remplir
	 * */
	private Buffer buffer;
	
	/**
	 * Numéro de l'élément inséré dans le buffer
	 * */
	private int numElt;
	
	public Producteur( Buffer buffer ) {
		this.buffer = buffer;
		this.id = ++ nbInst;
	}
	
	@Override
	public void run(){
		for (int i = 0 ; i <5 ; i++) {
			
			buffer.ajouter() ;
			System.out.println("Producteur #" + id+ " a ajouté un élément") ;
			
			try {
				Thread.sleep((int)(Math.random()*100)) ;
			}
			catch (InterruptedException e) {}
			}
	}
}

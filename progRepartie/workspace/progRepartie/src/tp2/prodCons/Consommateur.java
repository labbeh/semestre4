package tp2.prodCons;

public class Consommateur extends Thread{
	/**
	 * Numéro séquentiel auto-incrémenté
	 * */
	private static int nbInst = 0;
	
	/**
	 * Buffer partagé avec le Producteur
	 * Doit attendre qu'il ne soit pas vide pour consommé
	 * Il est remplit par le producteur
	 * */
	private Buffer buffer;
	
	/**
	 * Numéro unique du consommateur
	 * */
	private int id;

	public Consommateur( Buffer buffer ) {
		this.buffer = buffer;
		this.id = ++nbInst;
	}
	
	@Override
	public void run(){
		String val = new String("") ;
		
		for (int i = 0 ; i <5 ; i++) {
			buffer.retirer() ;
			System.out.println("Consommateur #" + id+ " a pris un élément") ;
		}
	}
}

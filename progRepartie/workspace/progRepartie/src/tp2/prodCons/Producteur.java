package tp2.prodCons;

public class Producteur extends Thread{
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
	}
	
	@Override
	public void run(){
		buffer.ajouter("Element " +numElt);
	}
}

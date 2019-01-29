package tp2.prodCons;

public class Consommateur extends Thread{
	/**
	 * Buffer partagé avec le Producteur
	 * Doit attendre qu'il ne soit pas vide pour consommé
	 * Il est remplit par le producteur
	 * */
	private Buffer buffer;

	public Consommateur( Buffer buffer ) {
		this.buffer = buffer;
	}
	
	@Override
	public void run(){
		System.out.println("Consomme: " +buffer.prendre());
	}
}

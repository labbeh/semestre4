package tp2.prodCons;

public class Lanceur {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Buffer buffer = new Buffer(100);
		
		Producteur 	 prod = new Producteur(buffer);
		Consommateur cons = new Consommateur(buffer);
		
		prod.start();
		cons.start();
		
		prod.join();
		cons.join();
	}

}

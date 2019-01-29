package tp2.prodCons;

public class Lanceur {
	/**
	 * Instance du buffer
	 * */
	private Buffer buffer;
	
	/**
	 * Ensemble de prodcons
	 * */
	private ProdConso[] prodconsos;
	
	/**
	 * Construit le lanceur à partir du nombre de prodconso voulut
	 * @param nbProdConso un int
	 * */
	public Lanceur(int nbProdConso){
		buffer = new Buffer(0);
		prodconsos = new ProdConso[nbProdConso];
		
		for(int p=0; p<prodconsos.length; p++){
			prodconsos[p] = new ProdConso(buffer, 5);
			prodconsos[p].start();
		}
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		new Lanceur(5);
	}
}

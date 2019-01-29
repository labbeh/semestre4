package tp2.prodCons;

public class Buffer {
	/**
	 * Nombre max possible d'elts dans le buffer
	 * */
	public static final int MAX = 300;
	
	/**
	 * Nombre d'éléments dans le buffer
	 * */
	private int nbElts;
	
	/**
	 * Construit un Buffer à partir du nom d'éléments initialiement dedans
	 * @param nbElt nombre d'éléments à mettre initialement dedans en int
	 * */
	public Buffer(int nbElt){
		this.nbElts = nbElt;
	}
	
	/**
	 * Permet de retirer un élément du buffer
	 * */
	public synchronized void retirer() {
		while(nbElts == 0){
			try{
				wait();
			}
			catch(InterruptedException evt){}
		}
		
		nbElts--;
		System.out.println("------ nbElts dans le buffer " +nbElts);
		notify();
	}
	
	/**
	 * Permet d'ajouter un élément au buffer
	 * */
	public synchronized void ajouter() {
		while(nbElts >= MAX){
			try{
				wait();
			}
			catch(InterruptedException evt){}
		}
		nbElts++;
		System.out.println("------ nbElts dans le buffer " +nbElts);
		notify();
	}
}
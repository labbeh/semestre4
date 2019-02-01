package tpSemaphore.equipes;

public class Equipe extends Thread {
	private static int nbInst = 0;
	private int num;
	
	/**
	 * Telephone du premier membre
	 * */
	private Telephone tel1;
	
	/**
	 * Telephone du second membre
	 * */
	private Telephone tel2;
	
	public Equipe(){
		this.tel1 = new Telephone();
		this.tel2 = new Telephone();
		
		this.num = ++nbInst;
	}
	
	@Override
	public void run(){
		
	}
}

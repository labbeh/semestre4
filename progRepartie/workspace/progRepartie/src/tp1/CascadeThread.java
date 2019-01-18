package tp1;

public class CascadeThread extends Thread {

	private int etapes;
	private Thread pere;

	public CascadeThread(int etapes, Thread pere) {
		this.etapes = etapes;
		this.pere = pere;
	}
	
	@Override
	public void run(){
		if(etapes > 0){
			new CascadeThread(etapes-1, currentThread()).start();
		}
	}

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package tp1;


/**
 * Première classe de test sur les Thread
 * @author: hugo labbé
* */

public class Principale{

	public static void main(String[] args){
		Thread1 th = new Thread1();
		th.start();
		System.out.println("Je suis dans le main");
		
		// autre exemple
		Runnable run = new Thread2();
		
		Thread th2 = new Thread(run);
		th2.start();
	}
}

class Thread1 extends Thread{
	@Override
	public void run(){
		System.out.println("Je suis un thread");
	}
}

class Thread2 implements Runnable{
	@Override
	public void run(){
		System.out.println("je suis un thread");
	}
}

package tp1;


public class PlusieursThreads {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PlusieursThreads().creerThreads();
		
		
	}
	
	
	public void creerThreads(){
		CreerThread2 t1 = new CreerThread2();
		CreerThread2 t2 = new CreerThread2();
		
		t1.start();
		t2.start();
	}
	
	class CreerThread1 extends Thread{
		private Thread th1;
		private Thread th2;
		
		public CreerThread1() {
			th1 = new Thread();
			th2 = new Thread();
			
			
		}
		
		@Override
		public void run(){
			th1.start();
			th2.start();
		}
	}
	
	class CreerThread2 extends Thread{
		private CreerThread1 th1;
		private CreerThread1 th2;
		
		public CreerThread2() {
			th1 = new CreerThread1();
			th2 = new CreerThread1();
			
			
		}
		
		@Override
		public void run(){
			th1.start();
			th2.start();
		}
	}

}


package tpSemaphore.parking;

public class Lanceur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Parking p = new Parking(150, 0);
		
		Vehicule[] vhs = new Vehicule[200];
		
		for(int i=0; i<vhs.length; i++)
			vhs[i] = new Vehicule(p);
		
		for(int i=0; i<vhs.length; i++)
			vhs[i].start();
		
		/*Vehicule v1 = new Vehicule(p);
		Vehicule v2 = new Vehicule(p);
		Vehicule v3 = new Vehicule(p);
		Vehicule v4 = new Vehicule(p);
		Vehicule v5 = new Vehicule(p);
		Vehicule v6 = new Vehicule(p);
		Vehicule v7 = new Vehicule(p);
		Vehicule v8 = new Vehicule(p);
		Vehicule v9 = new Vehicule(p);
		Vehicule v10 = new Vehicule(p);
		
		v1.start();
		v2.start();
		v3.start();
		v4.start();
		v5.start();
		v6.start();
		v7.start();
		v8.start();
		v9.start();
		v10.start();*/
	}
}

package tp2.bonbonniere;

public class Principale {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Bonbonniere b = new Bonbonniere(100);
		
		Enfant enf1 = new Enfant(b);
		Enfant enf2 = new Enfant(b);
		Enfant enf3 = new Enfant(b);
		Enfant enf4 = new Enfant(b);
		Enfant enf5 = new Enfant(b);
		
		enf1.start();
		enf2.start();
		enf3.start();
		enf4.start();
		enf5.start();
		
		enf1.join();
		enf2.join();
		enf3.join();
		enf4.join();
		enf5.join();
		
		System.out.println();
		System.out.println("enf1: " +enf1.getNbBonbonsAttrape());
		System.out.println("enf2: " +enf2.getNbBonbonsAttrape());
		System.out.println("enf3: " +enf3.getNbBonbonsAttrape());
		System.out.println("enf4: " +enf4.getNbBonbonsAttrape());
		System.out.println("enf5: " +enf5.getNbBonbonsAttrape());
		
		int total = enf1.getNbBonbonsAttrape() + enf2.getNbBonbonsAttrape()
					+ enf3.getNbBonbonsAttrape()
					+ enf4.getNbBonbonsAttrape()
					+ enf5.getNbBonbonsAttrape();
		
		System.out.println("Total: " +total);

	}

}

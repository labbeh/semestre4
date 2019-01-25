package tp2.bonbonniere;

public class Bonbonniere {
	
	/**
	 * Nombre de bonbons qu'il y a dans la bonbonniere
	 * */
	private int nbBonbons;
	
	/**
	 * Construit une bonbonniere à partir du nombre de bonbons voulut dedans
	 * @param un int qui est le nombre de bonbons
	 * */
	public Bonbonniere(int nbBonbons) {
		this.nbBonbons = nbBonbons;
	}
	
	/**
	 * Enleve un bonbon dans la Bonbonniere
	 * @return nombre de bonbons
	 * */
	public synchronized int attraperBonbon(){
		int bonbon = 0;
		
		if(nbBonbons > 0){
			bonbon = nbBonbons; // inst a
			
			// calcul inutile, juste pour faire pédaler pour tests...
			for(int i=0; i<1000; i++) Math.pow(7, Math.sqrt(1/(i+1)));
			
			nbBonbons--; // inst b
		}
		return bonbon;
	}
}

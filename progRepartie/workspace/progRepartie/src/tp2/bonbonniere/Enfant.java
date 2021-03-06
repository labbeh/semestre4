package tp2.bonbonniere;

public class Enfant extends Thread {
	/**
	 * Compte le nombre d'instance afin d'utiliser un numéro séquentiel auto-incrémenté
	 * pour donner un ID unique à chaque instance d'Enfant
	 * */
	private static int nbInstance = 0;
	
	/**
	 * Référence vers la Bonbonniere dans laquelle l'Enfant va pioché
	 * */
	private Bonbonniere b;
	
	/**
	 * Identifiant unique de l'Enfant
	 * */
	private int id;
	
	/**
	 * Nombre de bonbons que l'Efant a attrapé
	 * */
	private int nbBonbonsAttrape;
	
	/**
	 * Construit un Enfant avec une référence vers la Bonbonniere dans laquelle il va picohé
	 * @param b une Bonbonniere
	 * */
	public Enfant(Bonbonniere b) {
		this.b = b;
		this.id = ++nbInstance;
		
		this.nbBonbonsAttrape = 0;
	}
	
	@Override
	public void run(){
		int bonbon = b.attraperBonbon();
		
		while(bonbon != 0){
			System.out.println("enfant " +id+ " bonbon: " +bonbon);
			bonbon = b.attraperBonbon();
			//System.out.println("Enfant " +id+ " bonbon " +bonbon);
			nbBonbonsAttrape++;
		}
		
		System.out.println(id + ": plus de bonbon");
	}
	
	/**
	 * Retourne le nombre de bonbons que l'enfant à attrapé
	 * @return nombre de bonbons que l'enfant à attrapé
	 * */
	public int getNbBonbonsAttrape(){
		return nbBonbonsAttrape;
	}

}

package philosophes.metier;

public class Assiette {
	/**
	 * Numéro auto-incrémenté à chaque instance pour définir un numéro
	 * unique à chaque assiette
	 * */
	private static int nbInst = 0;
	
	/**
	 * Numéro unique de l'Assiette courante
	 * */
	private int num;
	
	/**
	 * Construit une Assiette en lui assignant un numéro unique
	 * */
	public Assiette() {
		this.num = ++nbInst;
	}

	/**
	 * Retourne le numéro unique de l'Assiette courante
	 * @return le numéro unique de l'Assiette courante en int
	 * */
	public int getNum() {
		return num;
	}
	
	@Override
	public String toString(){
		return "a" +num;
	}
}

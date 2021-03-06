package tp2.tprint;

public class TPrint extends Thread {
	
	/**
	 * Instance d'Affichage pour affichage sur la sortie standard
	 * */
	private static Affichage mAff = new Affichage();
	
	/**
	 * Texte à afficher
	 * */
	private String txt;
	
	/**
	 * Construteur de TPrint
	 * @param un String
	 * */
	public TPrint(String txt){
		this.txt = txt;
	}
	
	/**
	 * Lance l'affichage du texte
	 * */
	@Override
	public void run(){
		for(int i=0; i<20; i++) mAff.afficher(txt);
	}

}

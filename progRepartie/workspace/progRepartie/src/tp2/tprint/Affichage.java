package tp2.tprint;

public class Affichage {
	
	/**
	 * Affiche la chaine en paramètre caractère par caractère
	 * @param un String
	 * */
	synchronized void afficher(String t){
		for(int i=0; i<t.length(); i++)
			System.out.print(t.charAt(i));
		
		System.out.println();
	}
}

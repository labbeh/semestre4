package philosophes;

import philosophes.exceptions.NombreIncorrectExcpetion;

public class Main {

	/**
	 * @param args
	 * @throws NombreIncorrectExcpetion 
	 */
	public static void main(String[] args) throws NombreIncorrectExcpetion {
		String[] noms = new String[]{"Lao Tseu", "Pascal", "Socrate", "Avicenne", "Spinoza"};
		
		Controleur ctrl = new Controleur();
		ctrl.lancer(5, 10, noms);
		
		//System.out.println(ctrl);
	}

}

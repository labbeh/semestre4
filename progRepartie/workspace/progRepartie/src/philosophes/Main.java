package philosophes;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] noms = new String[]{"Lao Tseu", "Pascal", "Socrate", "Avicenne", "Spinoza"};
		
		Controleur ctrl = new Controleur();
		ctrl.lancer(5, 10, noms);
		
		System.out.println(ctrl);
	}

}

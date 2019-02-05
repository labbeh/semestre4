package philosophes.metier;

public class Philosophe extends Thread {
	/**
	 * Nom du philosophe
	 * */
	private String nom;
	
	public Philosophe(String nom){
		this.nom = nom;
	}

	@Override
	public void run() {
		super.run();
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString(){
		return this.nom;
	}
	
}

package philosophes;

import philosophes.metier.Assiette;
import philosophes.metier.Philosophe;
import philosophes.metier.ensembles.EnsemblePhilosophe;
import philosophes.metier.ensembles.PileAssiette;

public class Controleur {
	/**
	 * Ensemble de Philosophe
	 * */
	private EnsemblePhilosophe philosophes;
	
	/**
	 * Pile d'Assiette
	 * */
	private PileAssiette assiettes;
	
	public Controleur(){
		
	}
	
	/**
	 * Lance le scénario
	 * @param n nombre de Philosophe
	 * @param k nombre d'Assiette
	 * @param noms des Philosophe
	 * */
	public void lancer( int n, int k, String[] noms ) {
		this.philosophes = EnsemblePhilosophe.create(n);
		
		for(String nom: noms) philosophes.add(new Philosophe(nom));
		
		this.assiettes = PileAssiette.create(philosophes, k);
		
		for(int i=0; i<k; i++) assiettes.push(new Assiette());
	}
	
	public String toString(){
		return "Philosophes: " +philosophes+ "\n"
				+ "Assiettes: " +assiettes;
	}
}
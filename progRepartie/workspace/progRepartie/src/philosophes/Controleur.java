package philosophes;

import philosophes.exceptions.NombreIncorrectExcpetion;
import philosophes.metier.Assiette;
import philosophes.metier.Fourchette;
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
	
	/**
	 * Ensemble de fourchettes
	 * */
	private Fourchette[] fourchettes;
	
	/**
	 * Constructeur du controleur MVC
	 * */
	public Controleur(){
		
	}
	
	/**
	 * Lance le scénario
	 * @param n nombre de Philosophe
	 * @param k nombre d'Assiette
	 * @param noms des Philosophe
	 * @throws NombreIncorrectExcpetion 
	 * */
	public void lancer( int n, int k, String[] noms ) throws NombreIncorrectExcpetion {
		// création de l'ensemble de philosophes
		this.philosophes = EnsemblePhilosophe.create(n);
		if(philosophes == null)
			throw new NombreIncorrectExcpetion("Erreur avec " +n+ " philosphes");
		
		for(String nom: noms) philosophes.add(new Philosophe(nom, this));
		
		// création de l'ensebmle d'assiettes
		this.assiettes = PileAssiette.create(philosophes, k);
		if(assiettes == null)
			throw new NombreIncorrectExcpetion("Erreur avec " +k+ " assietes");
		
		for(int i=0; i<k; i++) assiettes.push(new Assiette());
		
		// création des fourchettes et association à leurs philosphes
		this.fourchettes = new Fourchette[n];
		for(int i=0; i<fourchettes.length; i++) {
			fourchettes[i] = new Fourchette();
			
			philosophes.get(i).setFourchette(fourchettes[i]);
			
			if(i+1 < philosophes.size()) philosophes.get(i+1).setFourchette(fourchettes[i]);
			else 						 philosophes.get(0  ).setFourchette(fourchettes[i]);
		}
	}
	
	/**
	 * Permet de savoir si il reste des assiettes ou non dans la pile
	 * @return vrai si il reste des assiettes
	 * */
	public boolean resteAssiette(){
		return !assiettes.empty();
	}
	
	/**
	 * Permet à un Philosophe de prendre une Assiette
	 * @return la référence à l'Assiette qu'il a pris
	 * */
	public Assiette prendreAssiete() {
		return assiettes.prendre();
	}
	
	@Override
	public String toString(){
		return "Philosophes: " +philosophes+ "\n"
				+ "Assiettes: " +assiettes;
	}
}

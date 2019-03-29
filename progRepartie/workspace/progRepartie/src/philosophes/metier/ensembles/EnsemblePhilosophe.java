package philosophes.metier.ensembles;

import java.util.Vector;

import philosophes.metier.Philosophe;

public class EnsemblePhilosophe extends Vector<Philosophe>  /*implements List<Philosophe>*/ {
	/**
	 * Tableau pour stocker les Philosophe
	 * */
	private Philosophe[] philosophes;
	
	/**
	 * Nombre actuel de Philosophe dans l'ensemble
	 * */
	private int n;
	
	/**
	 * Indice actuel dans le tableau
	 * */
	private int i;
	
	/**
	 * Factory pour vérifier que l'on crééer au moins 2 Philosophe
	 * */
	public static EnsemblePhilosophe create( int n ) {
		if(n > 2) return new EnsemblePhilosophe(n);
		return null;
	}
	
	/**
	 * Crééer un ensemble de n Philosophe
	 * @param n nombre de philosophe
	 * */
	private EnsemblePhilosophe( int n ) {
		super(n);
		//this.philosophes = new Philosophe[n];
		this.n = n;
		this.i = 0;
	}

	
	@Override
	public String toString(){
		StringBuilder ret = new StringBuilder();
		ret.append("[");
		ret.append(philosophes[0].getNom());
		
		for(int i=1; i< philosophes.length; i++){
			ret.append("; ");
			ret.append(philosophes[i].getNom());
		}
		
		ret.append("]");
		
		return ret.toString();
	}

}

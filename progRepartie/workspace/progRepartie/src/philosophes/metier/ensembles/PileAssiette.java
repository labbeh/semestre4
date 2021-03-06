package philosophes.metier.ensembles;

import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

import philosophes.metier.Assiette;

public class PileAssiette extends Stack<Assiette> {
	/**
	 * Nombre n initial d'Assiette dans la pile
	 * */
	private int n;
	
	/**
	 * Semaphore pour gérer les accès aux assiettes
	 * */
	private Semaphore semaphore;
	
	/**
	 * Factory pour vérifier que le nombre d'assiete est bien supérieur au nombre
	 * de philosohpes
	 * @param philosophes ensemble de philosophes pour vérifier qu'il y a
	 * suffisament d'assiete
	 * @param k nombre d'assiettes
	 * @return une Assiette ou null en cas de paramètre invalide
	 * */
	public static PileAssiette create( EnsemblePhilosophe philosophes, int k ){
		if(k > philosophes.size()) return new PileAssiette(k);
		return null;
	}
	
	/**
	 * Construit une pile de n Assiette
	 * @param n un int, nombre d'assiettes
	 * */
	private PileAssiette( int n ) {
		super();
		this.n = n;
		new Semaphore(n, true);
	}
	
	@Override
	public Assiette push(Assiette a) {
		if(size() < n) return super.push(a);
		return null;
	}
	
	/**
	 * Permet de prendre une assiette
	 * */
	public synchronized Assiette prendre(){
		if(!empty()) return pop();
		return null;
	}

}

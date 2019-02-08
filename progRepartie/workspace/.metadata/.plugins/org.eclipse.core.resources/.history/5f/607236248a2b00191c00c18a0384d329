package philosophes.metier.ensembles;

import java.util.Stack;
import java.util.stream.Stream;

import philosophes.metier.Assiette;

public class PileAssiette extends Stack<Assiette> {
	/**
	 * Nombre n initial d'Assiette dans la pile
	 * */
	private int n;
	
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
		this.n = n;
	}
	
	@Override
	public Assiette push(Assiette a) {
		if(size() < n) return super.push(a);
		return null;
	}

	/*@Override
	public Stream<Assiette> parallelStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Assiette> stream() {
		// TODO Auto-generated method stub
		return null;
	}*/

}

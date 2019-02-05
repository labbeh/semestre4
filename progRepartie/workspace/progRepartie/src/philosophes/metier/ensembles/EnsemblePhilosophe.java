package philosophes.metier.ensembles;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import philosophes.metier.Philosophe;

public class EnsemblePhilosophe  implements List<Philosophe> {
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
		this.philosophes = new Philosophe[n];
		this.n = n;
		this.i = 0;
	}

	@Override
	public Stream<Philosophe> parallelStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeIf(Predicate<? super Philosophe> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Stream<Philosophe> stream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEach(Consumer<? super Philosophe> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean add(Philosophe philo) {
		if(i < philosophes.length) {
			philosophes[i++] = philo;
			return true;
		}
		return false;
	}

	@Override
	public void add(int arg0, Philosophe arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addAll(Collection<? extends Philosophe> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends Philosophe> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Philosophe get(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return i == 0;
	}

	@Override
	public Iterator<Philosophe> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<Philosophe> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<Philosophe> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Philosophe remove(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void replaceAll(UnaryOperator<Philosophe> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Philosophe set(int arg0, Philosophe arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		if(isEmpty()) return 0;
		return i+1;
	}

	@Override
	public void sort(Comparator<? super Philosophe> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Spliterator<Philosophe> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Philosophe> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
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

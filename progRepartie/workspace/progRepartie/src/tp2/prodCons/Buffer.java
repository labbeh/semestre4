package tp2.prodCons;

import java.util.List;
import java.util.Vector;

public class Buffer {
	/**
	 * Elements du Buffer
	 * */
	private List<String> items;
	
	/**
	 * Nombre d'éléments max du buffer
	 * */
	private int capacite;
	
	public Buffer(int capacite){
		this.items = new Vector<>(capacite);
		this.capacite = capacite;
	}
	
	/**
	 * Retourne la capacité max du buffer
	 * @return capacité max du buffer
	 * */
	int getCapacite(){
		return capacite;
	}
	
	/**
	 * Retourne le nombre d'éléments qu'il y a dans le buffer
	 * @return nombre d'éléments dans le buffer
	 * */
	public int getNbElts(){
		return items.size();
	}
	
	/**
	 * Permet d'insérer un élément dans le buffer
	 * @param un String
	 * */
	synchronized void ajouter(String elt){
		items.add(elt);
	}
	
	/**
	 * Permet de prendre un élément dans le buffer
	 * @return un String
	 * */
	synchronized String prendre(){
		return items.get(0);
	}
}
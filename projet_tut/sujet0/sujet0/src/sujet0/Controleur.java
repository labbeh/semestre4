package sujet0;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

public class Controleur {
	/**
	 * Nom de l'image
	 * */
	private String nomImg;
	
	/**
	 * Fenetre graphique
	 * */
	private Fenetre ihm;
	
	/**
	 * List des coordonnées des pixels contour de l'image
	 * */
	private List<Coordonnees> coords;
	
	public Controleur(String nomImg) {
		this.nomImg =  "./" +nomImg;
		
		init();
		ihm = new Fenetre(this);
		//test();
	}
	
	/**
	 * Initialise la situation de départ
	 * */
	public void init() {
		coords = TraitementImage.getMasque(nomImg);
		System.out.println(coords.size());
	}
	
	public List<Coordonnees> getCoords(){
		return coords;
	}
	/**
	 * Méthode pour des tests A SUPPRIMER
	 * */
	/*public void test() {
		try {
			FileWriter fw = new FileWriter("test.txt");
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println("test");
			
			pw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: java Controleur nomImage.png");
			System.exit(1);
		}
		
		new Controleur(args[0]);

	}

}

package sujet3;

import java.util.ArrayList;

import sujet1.Fusee;
import sujet1.Planete;
import sujet2.Meteorite;

/** Classe Intelligence artificielle.
 * @author louis
 * @version 2019-03-27
 */
public class IA extends Thread {
	
	private Fusee                fusee;
	private Planete              pandora;
	private ArrayList<Meteorite> meteorites;
	
	public IA ( Fusee fusee, Planete pandora, ArrayList<Meteorite> meteorites ) {
		this.fusee      = fusee;
		this.pandora    = pandora;
		this.meteorites = meteorites;
	}
	
	@Override
	public void run () {
		
	}
	
}

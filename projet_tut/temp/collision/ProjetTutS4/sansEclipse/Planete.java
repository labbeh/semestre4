//package sujet1;

import java.awt.Dimension;

//import sujet1.metier.plateau.Plateau;

public class Planete extends Sprite {
	
	public static final int TAILLE_MIN = 30;
	public static final int TAILLE_MAX = 300;
	
	private Plateau p;
	
	public Planete ( Plateau plateau, String img, double posX, double posY, int diametre ) {
		super( plateau, img, new Coordonnees(posX,posY), new Dimension(diametre, diametre) );
	}
	
}

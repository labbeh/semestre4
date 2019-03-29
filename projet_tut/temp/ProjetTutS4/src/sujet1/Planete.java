package sujet1;

import java.awt.Dimension;

public class Planete extends Sprite {
	
	public static final int TAILLE_MIN = 40;
	public static final int TAILLE_MAX = 150;
	
	public Planete ( String img, int posX, int posY, int diametre ) {
		super( null, img, new Coordonnees(posX,posY), new Dimension(diametre, diametre) );
	}
	
}

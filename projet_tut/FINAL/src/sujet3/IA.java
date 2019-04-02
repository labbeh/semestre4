package sujet3;

import sujet1.Coordonnees;
import sujet1.Fusee;

public class IA {
	private Coordonnees posFusee;
	private double      rotFusee;

	public IA () {
		this.rotFusee = 0.0;
	}

	// POUR LE VAISSEAU
	public int[] actionV ( double x, double y, double acceleration, double vitesse, int orientation ) {

		this.posFusee = new Coordonnees(x,y);
		this.rotFusee = orientation;

		return tableauAction(0, 0, 0, 0);
	}

	// POUR PANDORA
	public int[] actionP ( double x, double y, double diametre ) throws Exception
	{
		int b0, b1, b2, b3;
		b0 = b1 = b2 = b3 = 0;

		// on part du pricipe que la position de pandora est superieur a la position du vaisseau
		int rot = gestionDirection( x, y );
		if      ( rot > 180+5 ){
			b0 = 0;
			b2 = 1;
			b3 = 0;
		}
		else if ( rot < 180-5 ) {
			b0 = 0;
			b2 = 0;
			b3 = 1;
		}
		else b0 = 1;

		return tableauAction(b0, b1, b2, b3);
	}

	// POUR LES METEORITES
	public int[] actionM ( double x, double y, double diametre, double vitesse, int orientation ) throws Exception {
		int b0, b1, b2, b3;
		b0 = b1 = b2 = b3 = 0;

		// Evaluation distance
		double distX = this.posFusee.getX() - x;
		double distY = this.posFusee.getY() - y;
		double dist  = Math.sqrt( distX*distX + distY*distY );

		if ( dist < 100 ) {
			int rot = gestionDirection( x, y );
			if      ( rot > 180+5 ){
				b0 = 0;
				b2 = 0;
				b3 = 1;
			}
			else if ( rot < 180-5 ) {
				b0 = 0;
				b2 = 1;
				b3 = 0;
			}
			else b0 = 1;
		}

		return tableauAction(b0, b1, b2, b3);
	}

	/** Cette méthode n'est appellé que par la méthode actionV.
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	public int gestionDirection ( double x, double y ) throws Exception
	{
		int res = 0;

		if( this.posFusee != null ) {
			// On part du pricipe que la position de pandora est superieur a la position du vaisseau
			double origX = x-this.posFusee.getX();
			double origY = y-this.posFusee.getY();
			double angle = Math.toDegrees( Math.atan2(origY, origX) );

			res = Math.abs( (int)( this.rotFusee - angle ) +90 );

		}
		return res;
	}

	private int[] tableauAction ( int b0, int b1, int b2, int b3 ) {
		//           0 1 2 3 4 5 6 7
		int[] tab = {0,0,0,0,0,0,0,0};

		tab[0] = b0;
		tab[1] = b1;
		tab[2] = b2;
		tab[3] = b3;

		return tab;
	}

	public static void appliquerDecision ( int[] tab, Fusee fusee ) {

		if(tab[0] == 1) fusee.acceleration();
		if(tab[1] == 1) fusee.deceleration();
		if(tab[2] == 1) fusee.rotation('-');
		if(tab[3] == 1) fusee.rotation('+');
	}

}

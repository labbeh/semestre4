//package sujet1;

/**
 * Classe représentant les coordonnées d'un pixel
 * */
public class Coordonnees implements Comparable
{
	private static final double MAX_XY = 1000;
	private static final double MIN_XY =    0;
	
	/**
	 * Position horizontal
	 * */
	private double x;
	
	/**
	 * Position verticale
	 * */
	private double y;
	
	public Coordonnees(double x, double y)
	{
		this.x = (x<MAX_XY && x>MIN_XY ? x : 1);
		this.y = (y<MAX_XY && y>MIN_XY ? y : 1);
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	
	public void setX ( double x ) { this.x = (x<MAX_XY && x>MIN_XY ? x : this.x); }
	public void setY ( double y ) { this.y = (y<MAX_XY && y>MIN_XY ? y : this.y); }
	
	@Override
	public String toString () { return "Coordonnees [x=" + x + ", y=" + y + "]"; }

	/** Compare les coordonnées.
	 * @return 0 si collision.
	 */
	@Override
	public int compareTo ( Object autreCoordonnees ) {
		Coordonnees c = (Coordonnees) autreCoordonnees;
		if ( (int)this.getX() == (int)c.getX() && (int)this.getY() == (int)c.getY() ) {
			return 0;
		}
		return 1;
		
	}
}

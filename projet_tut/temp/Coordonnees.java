
/**
 * Classe représentant les coordonnées d'un pixel
 * */
public class Coordonnees
{
	private static final MAX_XY = 1000;
	private static final MIN_XY =    0;

	/**
	 * Position horizontal
	 * */
	private int x;

	/**
	 * Position verticale
	 * */
	private int y;

	public Coordonnees(int x, int y)
	{
		this.x = (x<MAX_XY && x>MIN_XY ? x : 1);
		this.y = (y<MAX_XY && y>MIN_XY ? y : 1);
	}

	public int getX() { return x; }
	public int getY() { return y; }

	public void setX(int x) { this.x = (x<MAX_XY && x>MIN_XY ? x : this.x); }
	public void setY(int y) { this.y = (y<MAX_XY && y>MIN_XY ? y : this.y); }

	@Override
	public String toString()
	{
		return "Coordonnees [x=" + x + ", y=" + y + "]";
	}
}

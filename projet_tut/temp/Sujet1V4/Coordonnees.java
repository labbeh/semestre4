public class Coordonnees
{
	private static final double MAX_XY = 1000;
	private static final double MIN_XY =    0;
	
	private double x;
	private double y;
	
	public Coordonnees(double x, double y)
	{
		this.x = (x<MAX_XY && x>MIN_XY ? x : 1);
		this.y = (y<MAX_XY && y>MIN_XY ? y : 1);
	}
	
	public void setX ( double x ) { this.x = (x<MAX_XY && x>MIN_XY ? x : ( x>MAX_XY ? MIN_XY : MAX_XY )); }
	public void setY ( double y ) { this.y = (y<MAX_XY && y>MIN_XY ? y : ( y>MAX_XY ? MIN_XY : MAX_XY )); }
	
	public double getX()     { return x;                                        }
	public double getY()     { return y;                                        }
	public String toString() { return "Coordonnees [x=" + x + ", y=" + y + "]"; }
}

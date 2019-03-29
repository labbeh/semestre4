//package sujet1;

public class Vecteur {
	
	public double forceX;
	public double forceY;
	
	public Vecteur( double forceX, double forceY ) {
		this.forceX = forceX;
		this.forceY = forceY;
	}
	
	public double getForceX () { return this.forceX; }
	public double getForceY () { return this.forceY; }
	public void   setForceX ( double forceX ) { this.forceX = forceX; }
	public void   setForceY ( double forceY ) { this.forceY = forceY; }

	public double getValeur() {
		return Math.sqrt( this.forceX*this.forceX + this.forceY*this.forceY );
	}
	
}

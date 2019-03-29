public class Vecteur
{
	private double vecX, vecY;
	
	public Vecteur( double vecX, double vecY )
	{
		this.vecX = vecX;
		this.vecY = vecY;
	}
	
	public double getX () { return this.vecX; }
	public double getY () { return this.vecY; }
	
	public void setX(double vitesse, double rot)
	{
		this.vecX = this.vecX + vitesse * rot;
	}
	
	public void setY(double vitesse, double rot)
	{
		this.vecY = this.vecY + vitesse * rot;
	}
	
	public double vitesse()
	{
		return Math.sqrt(Math.hypot(this.vecX,this.vecY));
	}
}

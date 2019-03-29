public class Planete
{
	private Coordonnees  	pos;
	private int         	taillePlanete;
	
	public Planete(Coordonnees pos)
	{
		this.pos.setX(Planete.random(1000));
		this.pos.setY(Planete.random(1000));
		this.taillePlanete = Planete.random(30);
	}
	
	public int getPosX()		 {return this.pos.getX();}
	public int getPosY()		 {return this.pos.getY();}
	public int getTaillePlanete(){return this.taillePlanete;}
	
	private static int random(int z) { return (int) (Math.random()*z) + 10; }
}
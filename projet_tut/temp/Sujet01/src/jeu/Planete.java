package jeu;

public class Planete
{
	private Coordonnees 	position;
	private int 		taillePlanete;
	
	
	public Planete(Coordonnees pos)
	{
		this.position.setX((int)Math.random()*30 + 10);
		this.position.setY((int)Math.random()*30 + 10);
		this.taillePlanete = (int)Math.random()*30 + 10;
	}
	
	public int getPosX(){return this.position.getX();}
	public int getPosY(){return this.position.getY();}
	public int getTaillePlanete(){return this.taillePlanete;}
}
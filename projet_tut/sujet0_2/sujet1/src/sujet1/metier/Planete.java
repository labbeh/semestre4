package sujet1.metier;

import java.util.List;

public class Planete
{
	private Coordonnees  	pos;
	private int         	taille;
	
	/**
	 * Liste des coordonnées des contours
	 * */
	private List<Coordonnees> contour;
	
	public Planete()
	{
		pos = new Coordonnees(0, 0);
		
		this.pos.setX(Planete.random(1000));
		this.pos.setY(Planete.random(1000));
		this.taille = Planete.random(30);
		
		//this.contour = contour;
	}
	
	public int getPosX()		 {return this.pos.getX();}
	public int getPosY()		 {return this.pos.getY();}
	public int getTaille(){return taille;}
	
	/**
	 * Retourne la liste des coordonnées qui composent le contour
	 * */
	public List<Coordonnees> getContour(){
		return contour;
	}
	
	private static int random(int z) { return (int) (Math.random()*z) + 10; }
}

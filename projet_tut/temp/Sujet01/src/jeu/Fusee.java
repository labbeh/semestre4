package jeu;

import traitement.Traitement;

public class Fusee
{
	// VTS = VITESSE en px/ms
	private static final double MAX_VTS = 100;
	private static final double MIN_VTS = 0.1; // à l'arret si inferieur
	
	// CELERATION en px/ms²
	private static final double ACCELERATION = 0.001;
	private static final double DECELERATION = 0.00001;
	
	// DGR = DEGREE
	private static final int MAX_DGR = 360;
	private static final int MIN_DGR =   0;
	
	private String      urlImage;
	private Coordonnees position;
	private double      vecX, vecY;
	private boolean     enColision;
	private int         degre;
	private Plateau     plateau;
	private Traitement traitement;
	
	public Fusee(String img, int posX, int posY)
	{
		this.urlImage   = img;
		this.position   = new Coordonnees(posX,posY);
		this.vecX = this.vecY = 0;
		this.enColision = false;
		this.degre      = 0;
		this.plateau    = null;
		this.traitement = new Traitement(img);
		
	}
	
	public String getNomImg()  {return this.urlImage;       }
	public int getPosX()    {return this.position.getX();}
	public int getPosY()    {return this.position.getY();}
	public int getDegre() { return this.degre; }
	public Plateau getPlateau() {return this.plateau;        }
	
	public void setPlateau(Plateau plateau) { this.plateau = plateau; }
	
	private void deplacer(double deltaT)
	{
		this.position.setX( this.getPosX() + (int)(this.vecX * deltaT) );
		this.position.setY( this.getPosY() + (int)(this.vecY * deltaT) );
	}
	
	public void acceleration()
	{
		this.vecX = this.vecX + ACCELERATION * Math.cos(Math.toRadians(this.degre));
		this.vecY = this.vecY + ACCELERATION * Math.sin(Math.toRadians(this.degre));
		
		if( vitesse(this.vecX,this.vecY) > MAX_VTS )
		{
			this.vecX = MAX_VTS;
			this.vecY = MAX_VTS;
		}
	}
	
	public void deceleration(double deltaT)
	{
		double vi = vitesse(this.vecX,this.vecY);
		double vf = vi - deltaT * DECELERATION;
		double vd = vf / vi;
		
		this.vecX = this.vecX * vd;
		this.vecY = this.vecY * vd;
		
		if( vitesse(this.vecX,this.vecY) < MIN_VTS )
		{
			this.vecX = MAX_VTS;
			this.vecY = MAX_VTS;
		}
	}
	
	private static double vitesse(double vx, double vy)
	{
		return Math.sqrt(Math.hypot(vx,vy));
	}
	
	public void rotation(char sens)
	{
		int val = 0;
		
		if(sens == '+') val = this.degre + 5; // Sens horaire
		else            val = this.degre - 5; // Sans anti-horaire
		
		this.degre = ( val<MAX_DGR && val>MIN_DGR ? val : this.degre );
	}
	
	public Traitement getTraitement()
	{
		return this.traitement;
	}
}
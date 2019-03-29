package sujet1.metier;

import java.util.ArrayList;
import java.util.List;

import sujet1.metier.plateau.Plateau;

/**
 * Classe représentant une fusée qui est l'objet contrôlé par l'utilisateur
 * */
public class Fusee
{
	// VTS = VITESSE en px/s
	private static final double MAX_VTS = 0.100;  // 100px/s
	private static final double MIN_VTS = 0.0001; // à l'arret si inferieur
	
	// CELERATION en px/s²
	private static final double ACCELERATION = 0.001;
	private static final double DECELERATION = 0.000000001;
	
	// DGR = DEGREE
	private static final int MAX_DGR = 360;
	private static final int MIN_DGR =   0;
	
	private String      urlImage;
	private Coordonnees position;
	private double      vitesse, vecX, vecY;
	private int         degre;
	
	/**
	 * Pointeur vers l'instance du plateau dans lequel la fusée est
	 * */
	private Plateau plateau;
	
	/**
	 * Corrdonnées des contours
	 * */
	private List<Coordonnees> contour;
	
	/**
	 * Traitement de l'image de la fusée
	 * */
	private TraitementImage traitement;
	
	public Fusee(String img, int posX, int posY, int dgr)
	{
		this.urlImage   = img;
		this.position   = new Coordonnees(posX,posY);
		this.vitesse    = MIN_VTS;
		this.vecX = this.vecY = 0;
		this.degre      = dgr;
		
		contour = new ArrayList<>();
		initContours();
	}
	
	public void initContours() {
		TraitementImage t = new TraitementImage(urlImage);
		t.chargerImageInitiale();
		t.creerMasque();
		t.creerImageContour();
		
		contour = t.getCoordsContour();
		
		this.traitement = t;
	}
	
	public int    getPosX()    {return this.position.getX();}
	public int    getPosY()    {return this.position.getY();}
	public int getDegre() 		{ return this.degre; }
	
	public TraitementImage getTraitement() {
		return traitement;
	}
	
	public void setPlateau(Plateau p) {
		this.plateau = p;
	}
	
	private void setVitesse(double v)
	{
		if	   ( v > MAX_VTS ) this.vitesse = MAX_VTS;
		else if( v < MIN_VTS ) this.vitesse = MIN_VTS;
		else 				   this.vitesse = v;
	}
	
	public void deplacer()
	{
		this.vecX = this.vecX + this.vitesse * Math.cos(this.degre);
		this.vecY = this.vecY + this.vitesse * Math.sin(this.degre);
		
		this.position.setX( (int)(this.getPosX()*this.vecX) );
		this.position.setY( (int)(this.getPosY()*this.vecY) );
		
		changementEtat();
	}
	
	/**
	 * Est appeler a chaque changement d'était de la fusee
	 * */
	private void changementEtat() {
		//initContours();
		for(Coordonnees c: contour)
			if(plateau.getContenuPixel(c.getX() + getPosX(), c.getY() + getPosY() ))
				System.out.println("collision en " +c.getX() +" ; " +c.getY());
		
		plateau.getCtrl().majIHM();
	}
	
	public void acceleration() { setVitesse(this.vitesse * ACCELERATION); }
	//public void deceleration() { setVitesse(this.vitesse * DECELERATION); }
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
		
		this.degre = ( val<MAX_DGR && val>MIN_DGR ? val : ( val>MAX_DGR ? val - MAX_DGR : MIN_DGR + val ) );
		//initContours();
		changementEtat();
	}
}

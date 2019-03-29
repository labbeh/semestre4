package sujet2;

import java.awt.Dimension;

import sujet1.Acteur;
import sujet1.Coordonnees;
import sujet1.Fusee;
import sujet1.Sprite;
import sujet1.Vecteur;
import sujet1.metier.plateau.Plateau;

public class Meteorite extends Sprite implements Acteur {
	
	/** Masse en kg */
	public static final double MASSE = Math.random()*1000;
	
	// VTS = VITESSE en px/s
	public static final double MAX_VTS = 0.100;  // 100px/s
	public static final double MIN_VTS = 0.0001; // à l'arret si inferieur
	
	// CELERATION en px/s²
	public static final double ACCELERATION = 0.001;
	public static final double DECELERATION = 0.000000001;
	
	// Modificateurs de vitesses
	public static final double POUSSEE = 100;
	
	private double  acceleration;
	private double  vitesse;
	private Vecteur vecteur;
	
	/** On applique, à cette instance de cette classe,une accélération et un angle aléatoire.
	 *  L'accélération varie entre 0.01 et 0.2 pixel/ms.
	 *  La direction varie entre 0 et 360°;
	 * @param img
	 *           Chemin de l'image.
	 * @param posX
	 *           Position initiale X.
	 * @param posY 
	 *           Position initiale Y.
	 * @param largeur
	 *           Largeur.
	 * @param hauteur
	 *           Hauteur.
	 */
	public Meteorite ( Plateau p, String img, double posX, double posY, int diametre ) {
		super(p, img, new Coordonnees(posX,posY), new Dimension(diametre,diametre) );
		this.acceleration = Math.random()*0.02 + 0.01;
		this.rot          = (int)( Math.random()*360) ;
		this.vitesse      = MIN_VTS;
		this.vecteur      = new Vecteur(MIN_VTS,MIN_VTS);
	}
		
	/** Applique les forces sur la météorite. Doit être appellé par un Thread.
	 */
	@Override
	public void deplacer () {
		
		Sprite objetTouche = this.verifierColisions();
		this.enColision = objetTouche != null;
		
		if ( this.enColision && !(objetTouche instanceof Fusee) ) {
			System.out.println("Météorie: Objet touché : "+objetTouche);
			this.calculChocs( (Meteorite)objetTouche );
		} /*else*/ {
			this.vecteur.setForceX( this.vecteur.forceX + POUSSEE * this.acceleration *  Math.sin( Math.toRadians(this.rot) ) );
			this.vecteur.setForceY( this.vecteur.forceY + POUSSEE * this.acceleration * -Math.cos( Math.toRadians(this.rot) ) );
		
			this.pos.setX( this.pos.getX()+ this.vecteur.forceX );
			this.pos.setY( this.pos.getY()+ this.vecteur.forceY );
		}
		
		// Limitations vitesse
		if      (  this.vecteur.forceX >  MAX_VTS ) this.vecteur.forceX =  MAX_VTS;
		else if (  this.vecteur.forceX < -MAX_VTS ) this.vecteur.forceX = -MAX_VTS;
		if      (  this.vecteur.forceY >  MAX_VTS ) this.vecteur.forceY =  MAX_VTS;
		else if (  this.vecteur.forceY < -MAX_VTS ) this.vecteur.forceY = -MAX_VTS;	
		
		this.pos = this.deplacementTorique(this.pos);
		
		this.vitesse = this.vecteur.getValeur();
	}
	
	private void calculChocs ( Meteorite objetTouche ) {
		
		CollisionMeteorite.calc(this, objetTouche);
		
	}
	
	/** Récupère le vecteur
	 * @return Le vecteur.
	 */
	public Vecteur getVecteur () { return this.vecteur; }
	
	public void setRot ( double rotTan ) { 
		if ( rotTan > 0 )
			this.rot = (int) rotTan;
		else if ( rotTan < 0 )
			this.rot = (int) rotTan + 360;
		
	}
	
	public double getRot () { return this.rot; }
	
	@Override
	public double getMasse() { return MASSE; }	
	
		
	/** Affiche les données des attributs de la météorite.
	 */
	public String toString () {
		String message = "Météorite: Pos[%4.3f;%4.3f] Rot[%3d] Accélération[%3f] Vitesse[%4.3f] Vecteur[%4.3f;%4.3f] Colision[%b]";
		return String.format(message, this.pos.getX(), this.pos.getY(), this.rot, this.acceleration,
				                      this.vitesse, this.vecteur.forceX, this.vecteur.forceY, this.enColision );
	}	

}

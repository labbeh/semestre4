package sujet1;

import java.awt.Dimension;
import sujet1.metier.plateau.Plateau;

public class Fusee extends Sprite implements Acteur {
	
	/** Masse en kg */
	public static final double MASSE = 100;
	
	// VTS = VITESSE en px/s
	public static final double MAX_VTS = 0.100;  // 100px/s
	public static final double MIN_VTS = 0.0001; // à l'arret si inferieur
	
	// CELERATION en px/s²
	public static final double ACCELERATION = 0.001;
	public static final double DECELERATION = 0.000000001;
	
	// Modificateurs de vitesses
	public static final double POUSSEE                = 30;
	public static final double INTENSITE_ACCELERATION = 10;
	
	private double  acceleration;
	private double  vitesse;
	private Vecteur vecteur;
	
	private boolean estDetruit;
	private boolean aGagne;
	
	/** Constructeur principal de la classe Fusee.
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
	public Fusee ( Plateau p, String img, int posX, int posY, int largeur, int hauteur ) {
		super(p, img, new Coordonnees(posX,posY), new Dimension(largeur,hauteur) );
		this.acceleration = DECELERATION;
		this.vitesse      = MIN_VTS;
		this.vecteur      = new Vecteur(MIN_VTS,MIN_VTS);
		this.estDetruit   = false;
		this.aGagne       = false;
	}
	
	public Fusee ( Fusee fusee ) {
		super( fusee.plateau, fusee.image.getFichierImage(), fusee.pos, fusee.dimensions );
		this.acceleration = fusee.acceleration;
		this.vitesse      = fusee.vitesse;
		this.vecteur      = fusee.vecteur;
		this.estDetruit   = fusee.estDetruit;
		this.aGagne       = fusee.aGagne;
	}

	/** Applique les forces sur la fusée. Doit être appellé par un Thread.
	 */
	@Override
	public void deplacer () {
		
		Sprite objetTouche = this.verifierColisions();
		//System.out.println("Fusée: Objet touché : "+objetTouche);
		this.enColision = objetTouche != null;
		
		if ( this.enColision ) {
			if ( objetTouche instanceof Acteur )
				this.estDetruit = true;
			else {
				this.aGagne = true;
			}
		}
		else {
			// Calcul du vecteur
			this.vecteur.setForceX( this.vecteur.forceX + POUSSEE * this.acceleration *  Math.sin( Math.toRadians(this.rot) ) );
			this.vecteur.setForceY( this.vecteur.forceY + POUSSEE * this.acceleration * -Math.cos( Math.toRadians(this.rot) ) );
			
			this.pos.setX( this.pos.getX()+ this.vecteur.forceX );
			this.pos.setY( this.pos.getY()+ this.vecteur.forceY );
		}
		
		// Appliquer l'accélération
		if      (  this.vecteur.forceX >  POUSSEE*MAX_VTS ) this.vecteur.forceX =  POUSSEE*MAX_VTS;
		else if (  this.vecteur.forceX < -POUSSEE*MAX_VTS ) this.vecteur.forceX = -POUSSEE*MAX_VTS;
		if      (  this.vecteur.forceY >  POUSSEE*MAX_VTS ) this.vecteur.forceY =  POUSSEE*MAX_VTS;
		else if (  this.vecteur.forceY < -POUSSEE*MAX_VTS ) this.vecteur.forceY = -POUSSEE*MAX_VTS;
		
		// Déplacement torique
		this.pos = this.deplacementTorique(this.pos);
		
		this.vitesse = this.getVitesse();
		
		//System.out.println( this.toString() );
	}
	
	/** Récupère le vecteur
	 * @return Le vecteur.
	 */
	public Vecteur getVecteur      () { return this.vecteur;      }
	public double  getAcceleration () { return this.acceleration; }
	
	@Override
	public double getMasse() { return MASSE; }
	
	public double getVitesse() { return this.vecteur.getValeur(); }
	
	/** Accélère la fusée.
	 */
	public void acceleration () { this.acceleration = INTENSITE_ACCELERATION*ACCELERATION; }
	
	/** Décélère la fusée.
	 */
	public void deceleration () { this.acceleration = DECELERATION; }
	
	public boolean estDetruit () { return this.estDetruit; }
	public boolean aGagne     () { return this.aGagne;     }
	
	/** Affiche les données des attributs de la fusée.
	 */
	public String toString () {
		String message = "Fusée: Pos[%4.3f;%4.3f] Rot[%3d] Accélération[%3f] Vitesse[%4.3f] Vecteur[%4.3f;%4.3f] Colision[%b]";
		return String.format(message, this.pos.getX(), this.pos.getY(), this.rot, this.acceleration,
				                      this.vitesse, this.vecteur.forceX, this.vecteur.forceY, this.enColision );
	}
	
}

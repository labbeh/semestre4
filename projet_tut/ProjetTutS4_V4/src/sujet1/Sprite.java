package sujet1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import sujet1.TraitementImage;
import sujet1.metier.plateau.Plateau;
import sujet2.Meteorite;

public abstract class Sprite {
	
	// DGR = DEGREE
	public static final int MAX_DGR = 360;
	public static final int MIN_DGR =   0;
	public static final int INCREMENT_ROT = 5;
	
	public static final double MARGE_TORICITE = 30.0;
	
	protected int             rot;
	protected Coordonnees     pos;
	protected Coordonnees     posOrig;
	protected Dimension       dimensions;
	protected boolean         enColision;
	protected Plateau         plateau;
	protected TraitementImage image;
	
	public Sprite ( Plateau plateau, String fichierImage, Coordonnees pos, Dimension dimensions ) {
		this.image      = new TraitementImage(fichierImage, dimensions);
		this.enColision = false;
		this.pos        = pos;
		this.dimensions = dimensions;
		this.plateau    = plateau;
		this.posOrig    = this.image.calcBarycentre();
	}
	
	/** Vérifie les collisions en parcourant tout les points du contour de cette instance,
	 * de tous les obstacles et de tout les point du contour d'un obstacle.
	 * @return Le sprite qui a touché ce sprite.
	 */
	public Sprite verifierColisions () {
		
		List<Coordonnees> contour = this.image.getCoordsContour();
		ArrayList<Sprite> obstacles = this.getSpritesPres(250);
		
		if ( obstacles != null ) {
			for ( Sprite obstacle : obstacles ) {
				for ( Coordonnees cSprite : contour ) {
					for ( Coordonnees cObstacle : obstacle.image.getCoordsContour() ) {
						Coordonnees c0 = this.localToWorld( this, cSprite  );
						Coordonnees c1 = this.localToWorld( obstacle, cObstacle );
						if ( c0.compareTo(c1) == 0  ) {
							if ( this.origineColisionEstValide(obstacle) )
								return obstacle;
						}
					}
				}
			}
		}
		return null;
	}
	
	private boolean origineColisionEstValide ( Sprite objetTouche ) {
		
		boolean estFusee     = this instanceof Fusee;
		boolean estMeteorite = this instanceof Meteorite;
		
		// Dans le cas où on est une fusée
		if ( estFusee ) {
			if ( objetTouche instanceof Meteorite
			  || objetTouche instanceof Planete )
				return true;
		}
		
		// Dans le cas où on est une météorite
		else if ( estMeteorite ) {
			if ( objetTouche instanceof Fusee
			  || objetTouche instanceof Meteorite)
				return true;
		}
		return false;
	}
	
	/** Méthode qui convertie les position locale en position du plateau.
	 * @param c
	 * @return Une coordonnee dans le monde.
	 */
	public Coordonnees localToWorld ( Sprite s, Coordonnees cW ) {
		return new Coordonnees( s.pos.getX() + cW.getX(), s.pos.getY() + cW.getY() );
	}
	
	/** Pivote la météorite en fonction de la caractère passée en paramètre.
	 * @param sens
	 *        + : Rotation horaire
	 *        - : Rotation anti-horaire
	 */
	public void rotation ( char sens ) {
		int val = 0;
		
		if ( sens == '+' )
			val = this.rot + INCREMENT_ROT; // Sens horaire
		else
			val = this.rot - INCREMENT_ROT; // Sans anti-horaire
	
		if ( val > MAX_DGR )
			this.rot = MIN_DGR;
		else if ( val < MIN_DGR )
			this.rot = MAX_DGR;
		else
			this.rot = val;			
	}
	
	/** Régler la position avec la position passé en paramètre.
	 * Il appliquera une position torique.
	 * @param pos
	 */
	public Coordonnees deplacementTorique ( Coordonnees pos ) {
		int posX = (int) pos.getX();
		int posY = (int) pos.getY();
		int    dimPlateauX = (int) this.plateau.getDimensions().getWidth(); 
		int    dimPlateauY = (int) this.plateau.getDimensions().getHeight();
		
		// Vérifier sur les limites la largeur
		if ( posX > dimPlateauX-MARGE_TORICITE )
			return new Coordonnees( MARGE_TORICITE, posY );
		else if ( posX < MARGE_TORICITE )
			return new Coordonnees( dimPlateauX-MARGE_TORICITE, posY );
		// Vérifier sur les limites de la hauteur
		if ( posY > dimPlateauY-MARGE_TORICITE )
			return new Coordonnees( posX, MARGE_TORICITE );
		else if ( posY < MARGE_TORICITE )
			return new Coordonnees( posX, dimPlateauY-MARGE_TORICITE );
		/*
		if ( posX > this.plateau.getWidth() )
			return new Coordonnees( MARGE_TORICITE, posY );
		else if ( posX < MARGE_TORICITE )
			return new Coordonnees( this.plateau.getWidth(), posY );
		// Vérifier sur les limites de la hauteur
		if ( posY > this.plateau.getHeight() )
			return new Coordonnees( posX, MARGE_TORICITE );
		else if ( posY < MARGE_TORICITE )
			return new Coordonnees( posX, this.plateau.getHeight() );
		*/
		return pos;
		
	}
	
	/** Récupère les sprites à proximité des coordonnés passés en paramètre.
	 * @return Sprites générés.
	 */
	public ArrayList<Sprite> getSpritesPres ( int distanceDetect ) {
		ArrayList<Sprite> spritesProxi = new ArrayList<Sprite>();
		ArrayList<Sprite> sprites      = this.plateau.getSprites();
		
		for ( Sprite s : sprites ) {
			double distanceX = Math.abs( this.getPos().getX() ) - Math.abs( s.getPos().getX() );
			double distanceY = Math.abs( this.getPos().getY() ) - Math.abs( s.getPos().getY() ); 
			double distance  = Math.sqrt( distanceX*distanceX - distanceY*distanceY);
			
			if ( distance < distanceDetect && s != this )
				spritesProxi.add(s);
		}
		
		return spritesProxi;
	}
	
	public Dimension   getDimension () { return this.dimensions; }
	public int         getRotation  () { return this.rot;        }
	public Coordonnees getPos       () { return this.pos;        }
	
	public Graphics dessiner ( Graphics g, ImageObserver c ) {
		int hauteur        = this.image.getImage().getHeight();
		int largeur        = this.image.getImage().getWidth();
		double posX        = this.pos.getX();
		double posY        = this.pos.getY();
		double barycentreX = this.image.getBarycentre().getX();
		double barycentreY = this.image.getBarycentre().getY();
		
		Graphics2D g2 = (Graphics2D) g;
	    g2.rotate(Math.toRadians(rot), posX + barycentreX, posY + barycentreY );
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    //g2.drawOval((int)posX, (int)posY, largeur, hauteur);
	    g2.drawImage( this.image.getImage(), (int)posX, (int)posY, largeur, hauteur, c);
	    g2.rotate(-Math.toRadians(rot), posX + barycentreX, posY + barycentreY );
	    return g2;
	}
	
	public abstract void deplacer();
}

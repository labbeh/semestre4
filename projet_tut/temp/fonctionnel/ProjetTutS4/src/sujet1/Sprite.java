package sujet1;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import sujet1.TraitementImage;
import sujet1.metier.plateau.Plateau;
import sujet2.Meteorite;

public class Sprite {
	
	public static final double MARGE_TORICITE = 10.0;
	
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
			if ( objetTouche instanceof Fusee )
				return true;
		}
		return false;
	}
	
	/** Méthode qui convertie les position locale en position du plateau.
	 * @param c
	 * @return Une coordonnee dans le monde.
	 */
	public Coordonnees localToWorld ( Sprite s, Coordonnees c ) {
		return new Coordonnees( s.pos.getX() + c.getX(), s.pos.getY() + c.getY() );
	}
	
	public Coordonnees getPos () { return this.pos; }
	
	/** Régler la position avec la position passé en paramètre.
	 * Il appliquera une position torique.
	 * @param pos
	 */
	public Coordonnees deplacementTorique ( Coordonnees pos ) {
		double posX = pos.getX();
		double posY = pos.getY();
		
		// Vérifier sur les limites la largeur
		if ( posX > this.plateau.getWidth() )
			return new Coordonnees( MARGE_TORICITE, posY );
		else if ( posX < MARGE_TORICITE )
			return new Coordonnees( this.plateau.getWidth(), posY );
		// Vérifier sur les limites de la hauteur
		if ( posY > this.plateau.getHeight() )
			return new Coordonnees( posX, MARGE_TORICITE );
		else if ( posY < MARGE_TORICITE )
			return new Coordonnees( posX, this.plateau.getHeight() );
		
		return pos;
		
	}
	
	/** Récupère les sprites à proximité des coordonnés passés en paramètre.
	 * @return Sprites générés.
	 */
	public ArrayList<Sprite> getSpritesPres ( int distanceDetect ) {
		ArrayList<Sprite> spritesProxi      = new ArrayList<Sprite>();
		
		// Retirer cette instance de la liste des sprites scannables.
		ArrayList<Sprite> spritesScannables = new ArrayList<Sprite>( this.plateau.getSprites() );
		spritesScannables.remove(this);
		
		for ( Sprite s : this.plateau.getSprites() ) {
			double distanceX = Math.abs( this.getPos().getX() ) - Math.abs( s.getPos().getX() );
			double distanceY = Math.abs( this.getPos().getY() ) - Math.abs( s.getPos().getY() ); 
			double distance  = Math.sqrt( distanceX*distanceX - distanceY*distanceY);
			
			if ( distance < distanceDetect )
				spritesProxi.add(s);
		}
		
		return spritesProxi;
	}
	
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

}

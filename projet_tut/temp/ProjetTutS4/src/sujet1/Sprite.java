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

public class Sprite {
	
	protected int             rot;
	protected Coordonnees     pos;
	protected Coordonnees     posOrig;
	protected Dimension       dimensions;
	protected boolean         enColision;
	protected Plateau         plateau;
	protected TraitementImage image;
	
	public Sprite ( Plateau plateau, String fichierImage, Coordonnees pos, Dimension dimensions ) {
		this.image      = new TraitementImage(fichierImage, 1);
		this.enColision = false;
		this.pos        = pos;
		this.dimensions = dimensions;
		this.plateau    = plateau;
		this.posOrig    = this.image.calcBarycentre();
	}
	
	public boolean verifierColisions () {
		
		List<Coordonnees> contour = this.image.getCoordsContour();
		ArrayList<Sprite> obstacles = this.getSpritesPres();
		
		if ( obstacles != null ) {
			for ( Coordonnees cSprite : contour ) {
				for ( Sprite obstacle : obstacles ) {
					for ( Coordonnees cObstacle : obstacle.image.getCoordsContour() ) {
						if ( cObstacle.compareTo(cSprite) == 0  ) {
							return true;
						}
					}
				}
			}
		}
		return false;
		
	}
	
	public Coordonnees getPos () { return this.pos; }
	
	/** Récupère les sprites à proximité des coordonnés passés en paramètre.
	 * @return Sprites générés.
	 */
	public ArrayList<Sprite> getSpritesPres () {
		ArrayList<Sprite> spritesProxi = new ArrayList<Sprite>();
		
		for ( Sprite s : this.plateau.getSprites() ) {
			double distanceX = Math.abs( this.getPos().getX() ) - Math.abs( s.getPos().getX() );
			double distanceY = Math.abs( this.getPos().getY() ) - Math.abs( s.getPos().getY() ); 
			double distance  = Math.sqrt( distanceX*distanceX - distanceY*distanceY);
			
			if ( !(s instanceof Fusee) && distance < 500.0 ) spritesProxi.add(s);
		}
		
		return spritesProxi;
	}
	
	public Graphics dessiner ( Graphics g, ImageObserver c ) {
		int hauteur = (int)this.dimensions.getHeight();
		int largeur = (int)this.dimensions.getWidth();
		int posX    = (int)this.pos.getX();
		int posY    = (int)this.pos.getY();
	    Graphics2D g2 = (Graphics2D) g;
	    //g2.translate( posX, posY );
	    g2.rotate(Math.toRadians(rot), posX+this.dimensions.getWidth()/2, posY+this.dimensions.getHeight()/2 );
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    //g2.drawOval(posX, posY, largeur, hauteur);
	    g2.drawImage( this.image.getImage(), posX, posY, largeur, hauteur, c);
	    return g2;
	}

}

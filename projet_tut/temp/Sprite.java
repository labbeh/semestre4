package sujet1;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

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
	
	public Sprite ( String fichierImage, Coordonnees pos, Dimension dimensions ) {
		this.image = new TraitementImage(fichierImage, 10, 10);
		this.pos   = pos;
		this.dimensions = dimensions;
		calcRotPos();
	}
	
	public void calcRotPos () {
		this.posOrig    = this.image.getBarycentre();
		this.enColision = false;
	}
	
	public int getPosX () { return this.pos.getX(); }
	public int getPosY () { return this.pos.getY(); }

	public Graphics dessiner ( Graphics g, Canvas c ) {
		int hauteur = (int)this.dimensions.getHeight();
		int largeur = (int)this.dimensions.getWidth();
	    Graphics2D g2 = (Graphics2D) g;
	    g2.translate( this.getPosX(), this.getPosY() );
		g2.rotate(Math.toRadians(rot), this.getPosX(), this.getPosY() );
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(this.image.getImage(), this.getPosX(), this.getPosY(), largeur, hauteur, c);
		return g2;
	}

}

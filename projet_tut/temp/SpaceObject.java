import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class SpaceObject
{
	// DGR = DEGREE
	private static final int MAX_DGR = 360;
	private static final int MIN_DGR =   0;
	
	private Controleur  ctrl;
	private Coordonnees pos;       // Position sur le plateau
	private int         degree;
	private Traitement  traiterImage;
	
	public SpaceObject( Controleur ctrl, String fichierImage, Coordonnees coord)
	{
		this.ctrl         = ctrl;
		this.traiterImage = new Traitement(fichierImage, diam);
		this.pos          = coord;
		this.degree       = MIN_DGR;
	}
	
	private int verifierDgr(int dgr)
	{
		return ( dgr<MAX_DGR && dgr>MIN_DGR ? dgr : ( val>MAX_DGR ? dgr - MAX_DGR : MIN_DGR + dgr ) );
	}
	
	public double getX()        {return this.pos.getX();}
	public double getY()        {return this.pos.getY();}
	public int    getDegree()   {return this.degree;    }
	public double getXRadiant() { return   Math.sin(Math.toRadians(this.degree)); }
	public double getYRadiant() { return - Math.cos(Math.toRadians(this.degree)); }
	
	public void setX(double x)     { this.pos.setX(x);               }
	public void setY(double y)     { this.pos.setY(y);               }
	public void setDegree(int dgr) { this.degree = verifierDgr(dgr); }
	
	public Coordonnees getPosition()    { return this.pos;                           }
	public Coordonnees getCentreImage() { return this.traiterImage.calcBarycentre(); }
	
	public abstract void deplacer(double deltaT);
	public abstract void rotation(char sens);
	
	/*public Graphics dessiner ( Graphics g, ImageObserver c );
	{
		int hauteur = (int)this.dimensions.getHeight();
		int largeur = (int)this.dimensions.getWidth();
		int posX    = (int)this.pos.getX();
		int posY    = (int)this.pos.getY();
		Graphics2D g2 = (Graphics2D) g;
		//g2.translate( posX, posY );
		g2.rotate(Math.toRadians(rot), posX+this.dimensions.getWidth()/2, posY+this.dimensions.getHeight()/2 );
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawOval(posX, posY, largeur, hauteur);
		g2.drawImage( this.image.getImage(), posX, posY, largeur, hauteur, c);
		return g2;
	}*/
}

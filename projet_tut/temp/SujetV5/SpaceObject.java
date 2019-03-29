import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.List;

public abstract class SpaceObject
{
	// DGR = DEGREE
	private static final int MAX_DGR = 360;
	private static final int MIN_DGR =   0;
	
	protected Controleur       ctrl;
	private   Coordonnees      pos;       // Position sur le plateau
	private   int              degree;
	private   TraitementImage  traiterImage;
	
	public SpaceObject( Controleur ctrl, String fichierImage, Coordonnees coord )
	{
		this.ctrl         = ctrl;
		this.traiterImage = new TraitementImage(ctrl, fichierImage);
		this.pos          = coord;
		this.degree       = 180;
	}
	
	private int verifierDgr(int dgr)
	{
		return ( dgr<MAX_DGR && dgr>MIN_DGR ? dgr : ( dgr>MAX_DGR ? dgr - MAX_DGR : MAX_DGR - dgr ) );
	}
	
	public void setX(double x)     { this.pos.setX(x); }
	public void setY(double y)     { this.pos.setY(y); }
	public void setDegree(int dgr) { this.degree = verifierDgr(dgr);     }
	
	public double getX()        { return this.pos.getX();}
	public double getY()        { return this.pos.getY();}
	public int    getDegree()   { return this.degree;    }
	public double getXRadiant() { return   Math.sin(Math.toRadians(this.degree)); } // cos
	public double getYRadiant() { return - Math.cos(Math.toRadians(this.degree)); } // sin
	
	public Coordonnees getPosition()    { return this.pos;                          }
	public Coordonnees getCentreImage() { return this.traiterImage.getBaryCentre(); }
	
	public BufferedImage     getImage()                   { return this.traiterImage.getImage();       }
	public List<Coordonnees> creerContour(Coordonnees co) { return this.traiterImage.creerContour(co); }
	
	public boolean appartient(Coordonnees pos)
	{
		List<Coordonnees> list = this.traiterImage.creerContour(pos);
		
		if(! list.isEmpty())
			for(Coordonnees co : list)
				if(co.getX() == pos.getX() && co.getY() == pos.getY())
					return true;
		
		return false;
	}
	
	public abstract Graphics dessiner( Graphics g );
	public abstract void     deplacer(double deltaT);
	public abstract void     rotation(char sens);
	
	public String toString ()
	{
		return this.getClass().getName() + " : "
			 + "Pos["+this.pos.getX()+";"+this.pos.getY()+"] "
			 + "Rot["+this.degree+"] ";
	}
}

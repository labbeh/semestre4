import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Planete extends SpaceObject
{
	private int diametre;
	
	public Planete(Controleur ctrl)
	{
		super(ctrl, Controleur.IMAGE_PLANETE, new Coordonnees((Math.random()*900 + 10),(Math.random()*900 + 10)));
		
		this.diametre = (int)(Math.random()* 500 + 100);
		
		System.out.println("Planete cree");
	}
	
	public void deplacer(double deltaT){} // Ne se deplace pas
	public void rotation(char sens)    {} // Ne tourne pas
	
	public int getDiametre() {return this.diametre;     }
	public int getRayon()    {return this.diametre / 2; }
	
	public Graphics dessiner( Graphics g )
	{
		int hauteur = super.ctrl.HAUTEUR;
		int largeur = super.ctrl.LARGEUR;
		int posX    = (int)super.getX();
		int posY    = (int)super.getY();
		//int rotX    = super.getCentreImage().getX(); // posX+largeur/2
		//int rotY    = super.getCentreImage().getY(); // posY+hauteur/2
		
		Graphics2D g2 = (Graphics2D) g;
		//g2.translate( posX, posY );
		//g2.rotate(Math.toRadians(super.getDegree()), rotX, rotY );
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		//g2.drawOval(posX, posY, hauteur, largeur);
		g2.drawImage( super.getImage(), posX, posY,getRayon(),getRayon(),null);
		
		return g2;
	}
	
	public String toString ()
	{
		return super.toString() + " "
			 + "Diametre["+this.diametre+"] ";
	}
}
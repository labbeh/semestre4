import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Fusee extends SpaceObject
{
	// VTS = VITESSE en px/ms
	private static final double MAX_VTS = 100; // 0.100
	private static final double MIN_VTS = 0.1; // 0.0001
	
	// CELERATION en px/ms²
	private static final double ACCELERATION = 0.1;
	private static final double DECELERATION = 0.00001;
	
	private static final int    ROTATION = 5;
	
	private Vecteur vecteur;
	
	public Fusee(Controleur ctrl)
	{
		super(ctrl, Controleur.IMAGE_FUSEE, new Coordonnees(0,0));
		this.vecteur = new Vecteur(0,0);
		
		System.out.println("Fusee cree");
	}
	
	public Fusee(Controleur ctrl, int posX, int posY)
	{
		super(ctrl, Controleur.IMAGE_FUSEE, new Coordonnees(posX,posY));
		this.vecteur = new Vecteur(0,0);
	}
	
	public void deplacer(double deltaT)
	{
		double posX = super.getX() + (int)(this.vecteur.getX() * deltaT);
		double posY = super.getY() + (int)(this.vecteur.getY() * deltaT);
		
		if( posX < 0 || posX > 1000 ) this.vecteur.setX(0, super.getDegree());;
		if( posY < 0 || posY > 1000 ) this.vecteur.setY(0, super.getDegree());
		
		super.setX( posX ); System.out.println("X : "+super.getX());
		super.setY( posY ); System.out.println("Y : "+super.getY());
		
		ctrl.testCollision();
		
		System.out.println("DEPLACEMENT : vecx="+this.vecteur.getX()+" vecy="+this.vecteur.getY()+" time="+deltaT);
	}
	
	public void acceleration()
	{
		//          |  vitesse actuelle   | acceleration |        angle du vaisseau spacial      |
		double vecX = this.vecteur.getX() + ACCELERATION * super.getXRadiant();
		double vecY = this.vecteur.getY() + ACCELERATION * super.getYRadiant();
		
		// On limite la vitesse d'acceleration à MAX_VTS
		if( this.vecteur.vitesse() > MAX_VTS ) vecX = vecY = MAX_VTS;
		
		this.vecteur.setX(vecX, super.getXRadiant());
		this.vecteur.setY(vecY, super.getYRadiant());
		
		System.out.println("Accelere");
	}
	
	public void deceleration(double deltaT)
	{
		/*double vi = vitesse(this.vecX,this.vecY);
		double vf = vi - deltaT * DECELERATION;
		double vd = vf / vi;
		
		int vecX = this.vecX * vd;
		int vecY = this.vecY * vd;
		
		if( Vecteur.vitesse() < MIN_VTS )
			vecX = vecY = MAX_VTS;
		
		this.vecteur.setX(vecX);
		this.vecteur.setY(vecY);*/
		
		System.out.println("Freine");
	}
	
	public void rotation(char sens)
	{
		// + : Sens horaire; - : Sans anti-horaire
		super.setDegree( super.getDegree() + (sens == '+' ? +ROTATION : -ROTATION ) );
	}
	
	public Graphics dessiner( Graphics g )
	{
		int hauteur = super.ctrl.HAUTEUR;
		int largeur = super.ctrl.LARGEUR;
		int posX    = (int)super.getX();
		int posY    = (int)super.getY();
		int rotX    =  posX + (int)super.getCentreImage().getX(); // posX+largeur/2
		int rotY    =  posY + (int)super.getCentreImage().getY(); // posY+hauteur/2
		
		Graphics2D g2 = (Graphics2D) g;
		//g2.translate( posX, posY );
		g2.rotate(Math.toRadians(super.getDegree()), rotX, rotY );
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		//g2.drawOval(posX, posY, largeur, hauteur);
		g2.drawImage( super.getImage(), posX, posY,null);
		return g2;
	}
	
	public String toString ()
	{
		return super.toString() + " "
			 + "Vitesse["+this.vecteur.vitesse()+"] "
			 + "Vecteur["+this.vecteur.getX()+";"+this.vecteur.getY()+"] ";
	}
}
public class Fusee
{
	// VTS = VITESSE en px/s
	private static final double MAX_VTS = 0.100;  // 100px/s
	private static final double MIN_VTS = 0.0001; // à l'arret si inferieur
	
	// CELERATION en px/s²
	private static final double ACCELERATION = 0.001;
	private static final double DECELERATION = 0.000000001;
	
	// DGR = DEGREE
	private static final int MAX_DGR = 360;
	private static final int MIN_DGR =   0;
	
	private String      urlImage;
	private Coordonnees position;
	private double      vitesse, vecX, vecY;
	private boolean     enColision;
	private int         degre;
	
	public Fusee(String img, int posX, int posY, int dgr)
	{
		this.urlImage   = img;
		this.position   = new Coordonnees(posX,posY);
		this.vitesse    = MIN_VTS;
		this.vecX = this.vecY = 0;
		this.enColision = false;
		this.degre      = dgr;
	}
	
	public int    getPosX()    {return this.position.getX();}
	public int    getPosY()    {return this.position.getY();}
	
	private void setVitesse(double v)
	{
		if( v > MAX_VTS )     this.vitesse = MAX_VTS;
		else
			if( v < MIN_VTS ) this.vitesse = MIN_VTS;
			else              this.vitesse = v;
	}
	
	public void deplacer()
	{
		this.vecX = this.vecX + this.vitesse * Math.cos(this.degre);
		this.vecY = this.vecY + this.vitesse * Math.sin(this.degre);
		
		this.position.setX( (int)(this.getPosX()*this.vecX) );
		this.position.setY( (int)(this.getPosY()*this.vecY) );
	}
	
	public void acceleration() { setVitesse(this.vitesse * ACCELERATION); }
	public void deceleration() { setVitesse(this.vitesse * DECELERATION); }
	
	public void rotation(char sens)
	{
		int val = 0;
		
		if(sens == '+')
			val = this.degre + 5; // Sens horaire
		else
			val = this.degre - 5; // Sans anti-horaire
		
		this.degre = ( val<MAX_DGR && val>MIN_DGR ? val : this.degre );
	}
}
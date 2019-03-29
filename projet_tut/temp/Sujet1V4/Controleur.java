import java.awt.Graphics;

public class Controleur
{
	public static final String IMAGE_FOND    = "images/background.jpg";
	public static final String IMAGE_FUSEE   = "images/vaisseau1.png";
	public static final String IMAGE_PLANETE = "images/world.png";
	public static final int    LARGEUR       = 1000;
	public static final int    HAUTEUR       = 1000;
	
	private Plateau plateau;
	private GUI     gui;
	private boolean estFini;
	
	public Controleur()
	{
		plateau = new Plateau(this);
		gui     = new GUI(this);
		estFini = false;
		action();
	}
	
	public void rotation(char c) { plateau.getFusee().rotation(c);                             }
	public void acceleration()   { plateau.getFusee().acceleration();                          }
	public void deceleration()   { plateau.getFusee().deceleration(System.currentTimeMillis());}
	
	public void action()
	{
		double time = 0;
		while(!estFini)
		{
			System.out.println("time="+time);
			
			plateau.getFusee().deplacer(time);
			this.gui.majIHM();
			try 
			{
				Thread.sleep(10) ;
			}
			catch (InterruptedException e) 
			{
			// gestion de l'erreur
			}
			//time = System.currentTimeMillis();
			time = 20;
		}
	}
	
	public void paintPlanete( Graphics g ) { this.plateau.paintPlanete(g); }
	public void paintFusee( Graphics g )   { this.plateau.paintFusee(g);   }
	
	public Plateau getPlateau(){ return this.plateau; }
	
	public void partiePerdu()  { estFini = true; System.out.println("PERDU!");  }
	public void partieGagner() { estFini = true; System.out.println("GAGNER!"); }
	
	public static void main(String[] args) { new Controleur(); }
}

package sujet1;

import java.awt.Dimension;

import sujet1.ihm.Fenetre;
import sujet1.metier.plateau.*;
import sujet2.Meteorite;
import sujet3.IA;

public class Controleur {
	
	private static final int FENETRE_LARGEUR = 1000;
	private static final int FENETRE_HAUTEUR = 1000;
	
	/**
	 * Instance du plateau de jeu
	 * */
	private Plateau plateau;
	
	private Fusee fusee;
	
	/**
	 * Pointeur vers l'ihm
	 * */
	private Fenetre ihm;
	
	private IA ia;
	
	private boolean activerIA;
	
	public Controleur ( boolean activerIA ) {
		initialisation(activerIA);
	}
	
	private void initialisation ( boolean activerIA )
	{
		if( this.ihm != null )
			this.ihm.fermerFenetre();
		
		this.ihm       = new Fenetre(this, Controleur.FENETRE_LARGEUR, Controleur.FENETRE_HAUTEUR);
		this.plateau   = new Plateau( ihm, new Dimension( ihm.getWidth(), ihm.getHeight() ) );
		this.fusee     = this.plateau.getFusee();
		this.activerIA = activerIA;
		if ( this.activerIA ) {
			this.ia = new IA();
			ihm.addKeyListener( new EcouteurClavier( null, this ) );
		}
		else
			ihm.addKeyListener( new EcouteurClavier( this.fusee, this ) );
		
		ihm.add(plateau);
		
		Thread actualiseur = new Thread() {
			@Override
			public void run () {
				boolean partieTerminee = false;
				while ( !partieTerminee ) {
					partieTerminee = fusee.estDetruit() || fusee.aGagne();
					for ( Sprite s : plateau.getSprites() ) {
						if ( activerIA )
						{
							int[] tab = new int[8];
							
							if ( s instanceof Fusee ) {
								Fusee f = (Fusee)s;
								try { tab = ia.actionV(s.getPos().getX(), s.getPos().getY(), f.getAcceleration(), f.getVitesse(), f.getRotation() );
								} catch ( Exception e ) { e.printStackTrace(); }
							}
							if ( s instanceof Planete ) {
								try { tab = ia.actionP( s.getPos().getX(), s.getPos().getY(), s.getDimension().getWidth() );
								} catch ( Exception e ) { e.printStackTrace(); }
							}
							if ( s instanceof Meteorite ) {
								try { tab = ia.actionM(s.getPos().getX(), s.getPos().getY(), s.getDimension().getWidth(), ((Meteorite) s).getVitesse(), s.getRotation() );
								} catch ( Exception e ) { e.printStackTrace(); }
							}
							
							IA.appliquerDecision( tab, plateau.getFusee() );
							s.deplacer();
						}
						else
						{
							if ( s instanceof Fusee ) {
								s.deplacer();
							}
							if ( s instanceof Meteorite )
								( (Meteorite) s).deplacer();
						}
					}
					plateau.rafraichir();
					try { Thread.sleep(15); }
					catch ( InterruptedException e ) { e.printStackTrace(); }
				}
				// Son � activer selon l'issue de la partie
				if(fusee.aGagne()) {
					plateau.lancerSonWin();
				}
				else {
					plateau.lancerSonExplosion();
					plateau.lancerSonFin();
				}
			}
		};
		// Lancement du jeu
		actualiseur.start();
	}
	
	public void redemarrer () { initialisation(this.activerIA); }
	
	/**
	 * Retourne le plateau
	 * @return largeur du plateau en int
	 * */
	public Plateau getPlateau() { return this.plateau; }
	
	/**
	 * Point d'entrée du programme
	 * @param args si son premier paramètre est "manuel" le jeu démarre en mode manuel
	 * s'il n'y a pas de paramètre ou que celui-ci est différent de "manuel" le jeu
	 * sera lancée avec l'IA
	 * */
	public static void main ( String[] args ) {
		boolean modeIA = true;
		
		if(args.length > 0 && args[0].equalsIgnoreCase("manuel"))
			modeIA = false;
		
		new Controleur(modeIA);
	}
}

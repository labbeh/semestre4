package sujet1.metier.plateau;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sujet1.Acteur;
import sujet1.Coordonnees;
import sujet1.EcouteurClavier;
import sujet1.Fusee;
import sujet1.Planete;
import sujet1.Sprite;
import sujet2.Meteorite;

/**
 * Représentation d'un plateau de jeu
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */
public class Plateau extends JPanel {
	
	private static final String EMPLACEMENT_IMAGE_FUSEE     = "../../../vaisseau1.png";
	private static final String EMPLACEMENT_IMAGE_PLANETE   = "../../../world.png";
	private static final String EMPLACEMENT_IMAGE_FOND      = "../../../background.jpg";
	private static final String EMPLACEMENT_IMAGE_METEORITE = "../../../meteorite0.png";
	
	private static final String FICHIER_IMAGE_FUSEE   
	                = Plateau.class.getResource(EMPLACEMENT_IMAGE_FUSEE     ).getFile();
	private static final String FICHIER_IMAGE_PLANETE
	                = Plateau.class.getResource(EMPLACEMENT_IMAGE_PLANETE   ).getFile();
	private static final String FICHIER_IMAGE_FOND
	                = Plateau.class.getResource(EMPLACEMENT_IMAGE_FOND      ).getFile();
	private static final String FICHIER_IMAGE_METEORITE 
					= Plateau.class.getResource(EMPLACEMENT_IMAGE_METEORITE ).getFile();
	
	private static final int NB_PLANETES  = 1;
	private static final int NB_METEORITE = 15;
	
	/**
	 * Tout les objets dessinables (sprites)
	 * */
	private ArrayList<Sprite> sprites;
	
	/** Représente tout les sprites qui peuvent bouger.
	 */
	private ArrayList<Acteur> acteurs;
	
	/**
	 * Fusée du jeu sur le plateau
	 * */
	private Fusee fusee;
	
	private Thread actualiseur;
	
	private BufferedImage imgFond;
	
	/**
	 * Constructeur par défaut, créé un plateau de jeu vide
	 * à partir de ses dimensions
	 * */
	public Plateau ( JFrame fenetre, Dimension dimensions ) {	
		// Initialisation de la fenêtre
		this.setFocusable(false);
		this.setSize(dimensions);
		
		// Initialisation des variables
		this.sprites = new ArrayList<Sprite>();
		this.acteurs = new ArrayList<Acteur>();
		
		// Initialisation du jeu
		this.creerPlanetes(NB_PLANETES);
		this.creerMeteorites(NB_METEORITE);
		this.creerFusee();
		fenetre.addKeyListener( new EcouteurClavier(this.fusee) );
		this.chargerImages();
		
		// Thread <-------------------------------------------------------------
		this.actualiseur = new Thread() {
			@Override
			public void run () {
				boolean partieTerminee = false;
				while ( !partieTerminee ) {
					for ( Acteur s : acteurs ) {
						if      ( s instanceof Fusee     ) {
							( (Fusee)     s).deplacer();
							partieTerminee = ((Fusee) s).estDetruit() || ((Fusee) s).aGagne();
						}
							
						else if ( s instanceof Meteorite )
							( (Meteorite) s).deplacer();
					}
					rafraichir();
					try { Thread.sleep(15); }
					catch ( InterruptedException e ) { e.printStackTrace(); }
				}
			}
		};
		// Lancement du jeu
		this.actualiseur.start();
	}
	
	private void creerFusee () {
		System.out.print("Création de fusée... ");
		this.fusee = new Fusee( this, FICHIER_IMAGE_FUSEE, 400, 400, 50, 50);
		this.sprites.add(this.fusee);
		this.acteurs.add(this.fusee);
		System.out.println("terminée !");
	}
	
	private void creerPlanetes ( int quantite ) {
		for ( int cpt=0; cpt<quantite; cpt++ ) {
			System.out.print("Création de planête n°"+(cpt+1)+"... ");
			double posX     = Math.random()*this.getWidth();
			double posY     = Math.random()*this.getHeight();
			int diametre    = (int)( Math.random()*Planete.TAILLE_MAX+Planete.TAILLE_MIN );
			this.sprites.add( new Planete(this, FICHIER_IMAGE_PLANETE, posX , posY, diametre) );
			System.out.println("terminée !");
		}
	}
	
	private void creerMeteorites ( int quantite ) {
		for ( int cpt=0; cpt<quantite; cpt++ ) {
			System.out.print("Création de météorite n°"+(cpt+1)+"... ");
			double posX  = Math.random()*this.getWidth();
			double posY  = Math.random()*this.getHeight();
			int diametre = (int)( Planete.TAILLE_MIN+Math.random()*Planete.TAILLE_MAX );
			Meteorite m = new Meteorite(this, FICHIER_IMAGE_METEORITE, posX , posY, diametre);
			this.sprites.add(m);
			this.acteurs.add(m);
			System.out.println("terminée !");
		}
	}
	
	private void chargerImages () {
		try {
			this.imgFond = ImageIO.read( new File(FICHIER_IMAGE_FOND) );
		} catch ( IOException e ) { e.printStackTrace(); }
	}
	
	/** Récupère tous les sprites
	 * @return Sprites générés.
	 */
	public ArrayList<Sprite> getSprites () { return this.sprites; }
	
	/**
	 * Permet d'ajouter une planète au plateau
	 * @param planete planète à ajouter au plateau courant
	 * */
	public void ajouterPlanete ( Planete planete ) { this.sprites.add(planete); }
	
	@Override
	public void paintComponent ( Graphics g ) {
		super.paintComponent(g);
		
		// Dessiner fond
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage( imgFond, 0, 0,this );
		
		for ( Sprite s : sprites ) {
			s.dessiner(g, this);
		}
	}
	
	/** Méthode pour rafraîchir l'écran
	 */
	public void rafraichir () {
		this.repaint();
		System.out.println( this.fusee.toString() );
	}
	
}

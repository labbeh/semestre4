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

import sujet1.Coordonnees;
import sujet1.EcouteurClavier;
import sujet1.Fusee;
import sujet1.Planete;
import sujet1.Sprite;

/**
 * Représentation d'un plateau de jeu
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */
public class Plateau extends JPanel {
	
	private static final String EMPLACEMENT_IMAGE_FUSEE   = "../../../vaisseau1.png";
	private static final String EMPLACEMENT_IMAGE_PLANETE = "../../../world.png";
	private static final String EMPLACEMENT_IMAGE_FOND    = "../../../background.jpg";
	
	private static final String FICHIER_IMAGE_FUSEE   
	                = Plateau.class.getResource(EMPLACEMENT_IMAGE_FUSEE  ).getFile();
	private static final String FICHIER_IMAGE_PLANETE
	                = Plateau.class.getResource(EMPLACEMENT_IMAGE_PLANETE).getFile();
	private static final String FICHIER_IMAGE_FOND
	                = Plateau.class.getResource(EMPLACEMENT_IMAGE_FOND   ).getFile();
	
	/**
	 * Tout les objets dessinables (sprites)
	 * */
	private ArrayList<Sprite> sprites;
	
	/**
	 * Fusée du jeu sur le plateau
	 * */
	private Fusee fusee;
	
	/**
	 * Ensemble des planètes du jeu
	 * */
	private Set<Sprite> planetes;
	
	//@Deprecated
	//private ArrayList<JPanel> plans;
	
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
		
		// Initialisation du jeu
		this.sprites = new ArrayList<Sprite>();
		this.creerPlanetes(1);
		this.creerFusee();
		fenetre.addKeyListener( new EcouteurClavier(this.fusee) );
		this.chargerImages();
		this.majListeSprites();
		this.actualiseur = new Thread() {
			@Override
			public void run () {
				while (true) {
					fusee.deplacer();
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
		System.out.println("terminée !");
	}
	
	private void creerPlanetes ( int quantite ) {
		this.planetes   = new HashSet<Sprite>();
		
		for ( int cpt=0; cpt<quantite; cpt++ ) {
			System.out.print("Création de planête n°"+(cpt+1)+"... ");
			int posX     = (int)( Math.random()*this.getWidth()            );
			int posY     = (int)( Math.random()*this.getHeight()           );
			int diametre = (int)( Planete.TAILLE_MIN+Math.random()*Planete.TAILLE_MAX );
			this.planetes.add( new Planete( FICHIER_IMAGE_PLANETE, posX , posY, diametre) );
			System.out.println("terminée !");
		}
	}
	
	private void chargerImages () {
		try {
			this.imgFond = ImageIO.read( new File(FICHIER_IMAGE_FOND) );
		} catch ( IOException e ) { e.printStackTrace(); }
	}
	
	/** Mettre à jour l'ArrayList de sprites en cas d'ajout de planètes.
	 */
	public void majListeSprites () {
		
		//this.plans   = new ArrayList<JPanel>();
		
		// Actualisation de la liste des sprites
		for ( Sprite s : this.planetes ) {
			this.sprites.add(s);
		}
		this.sprites.add( this.fusee );
		
	}
	
	/** Récupère tous les sprites
	 * @return Sprites générés.
	 */
	public ArrayList<Sprite> getSprites () { return this.sprites; }
	
	/**
	 * Permet d'ajouter une planète au plateau
	 * @param planete planète à ajouter au plateau courant
	 * */
	//public void ajouterPlanete ( Planete planete ) { planetes.add(planete); }
	
	@Override
	public void paintComponent ( Graphics g ) {
		super.paintComponent(g);
		
		// Dessiner fond
		Graphics2D g2 = (Graphics2D) g;
		//g2.drawImage( imgFond, 0, 0,this );
		
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

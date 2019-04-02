package sujet1.metier.plateau;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sons.Son;
import sujet1.Acteur;
import sujet1.Fusee;
import sujet1.Planete;
import sujet1.Sprite;
import sujet2.Meteorite;

/**
 * Représentation d'un plateau de jeu
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */
public class Plateau extends JPanel {
	
	private static final String EMPLACEMENT_IMAGE_FUSEE     = "./images/vaisseau1.png";
	private static final String EMPLACEMENT_IMAGE_PLANETE   = "./images/world.png";
	private static final String EMPLACEMENT_IMAGE_FOND      = "./images/background.jpg";
	private static final String EMPLACEMENT_IMAGE_METEORITE = "./images/meteorite0.png";
	
	private static final int NB_PLANETES  = 1;
	private static final int NB_METEORITE = 10;
	
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
	
	private Dimension dimensions;
	
	
	private BufferedImage imgFond;
	
	/**
	 * Constructeur par défaut, créé un plateau de jeu vide
	 * à partir de ses dimensions
	 * */
	
	public boolean partieTerminee;
	//private Controleur controleur;
	
	public Plateau (JFrame fenetre, Dimension dimensions ) {
		// Initialisation de la fenêtre
		this.setFocusable(false);
		this.dimensions = dimensions;
		this.setSize(this.dimensions);
		
		// Initialisation des variables
		this.sprites = new ArrayList<Sprite>();
		this.acteurs = new ArrayList<Acteur>();
		//this.controleur = ctrl;
		
		// Initialisation du jeu
		this.creerPlanetes(NB_PLANETES);
		this.creerMeteorites(NB_METEORITE);
		this.creerFusee();
		this.chargerImageFond();
		
	}
	
	private void creerFusee () {
		System.out.print("Création de fusée... ");
		this.fusee = new Fusee( this, EMPLACEMENT_IMAGE_FUSEE, 400, 400, 50, 50);
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
			this.sprites.add( new Planete(this, EMPLACEMENT_IMAGE_PLANETE, posX , posY, diametre) );
			System.out.println("terminée !");
		}
	}
	
	private void creerMeteorites ( int quantite ) {
		for ( int cpt=0; cpt<quantite; cpt++ ) {
			System.out.print("Création de météorite n°"+(cpt+1)+"... ");
			double posX  = Math.random()*this.getWidth();
			double posY  = Math.random()*this.getHeight();
			int diametre = (int)( Planete.TAILLE_MIN+Math.random()*Planete.TAILLE_MAX );
			Meteorite m = new Meteorite(this, EMPLACEMENT_IMAGE_METEORITE, posX , posY, diametre);
			this.sprites.add(m);
			this.acteurs.add(m);
			System.out.println("terminée !");
		}
	}
	
	/** Charge l'image de fond.
	 */
	private void chargerImageFond () {
		try {
			this.imgFond = ImageIO.read( new File(EMPLACEMENT_IMAGE_FOND) );
		} catch ( IOException e ) { e.printStackTrace(); }
	}
	
	/** Récupère la fusée.
	 * @return Une fusée.
	 */
	public Fusee getFusee () { return this.fusee; }
	
	/** Récupère tous les sprites. Est accédé par tous les acteurs.
	 * @return Sprites générés.
	 */
	public ArrayList<Sprite> getSprites () { return this.sprites; }
	
	/** Récupère tous les acteurs.
	 * @return Une ArrayList d'acteur.
	 */
	public ArrayList<Acteur> getActeurs() { return this.acteurs; }
	
	/** Récupère les dimensions statique du plateau.
	 * @return Les dimensions du plateau.
	 */
	public Dimension getDimensions () { return this.dimensions; }
	
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
	}
	
	public void lancerSonExplosion() {
		Son.jouerSon(Son.EXPLOSION_SON);
	}
	
	public void lancerSonFin()
	{
		Son.jouerSon(Son.FIN_SON);
	}
	
	public void lancerSonWin()
	{
		Son.jouerSon(Son.WIN_SON);
	}
	
	public void arreterSonCourant()
	{
		Son.arreterSonCourant();
	}
	
	public boolean estTerminee()
	{
		return this.partieTerminee;
	}
	
}

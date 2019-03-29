package sujet1.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import sujet1.Controleur;
import sujet1.metier.Planete;

public class PanelEspace extends JPanel {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	private Controleur ctrl;
	
	/**
	 * Image de la fusée
	 * */
	private BufferedImage fusee;
	
	private boolean imageCree;
	
	public PanelEspace(Controleur ctrl) {
		this.ctrl = ctrl;
		imageCree = false;
		setFocusable(true);
		
		fusee = ctrl.chargerImage("vaisseau1_v2.png");
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		// dessin des planètes
		g2d.setColor(Color.BLACK);
		for(Planete p: ctrl.getPlanetes()) {
			g2d.drawOval(p.getPosX(), p.getPosY(), p.getTaille(), p.getTaille());
		}
		
		// dessin fusée
		 int barycentreFuseeX = this.ctrl.getPlateau().getFusee().getTraitement().getBaryCentre().getX();
	     int barycentreFuseeY = this.ctrl.getPlateau().getFusee().getTraitement().getBaryCentre().getY();

	        //System.out.println(barycentreFuseeX+"  "+barycentreFuseeY);



	     g2d.translate(barycentreFuseeX,barycentreFuseeY);
	     g2d.rotate(Math.toRadians(this.ctrl.getPlateau().getFusee().getDegre()), barycentreFuseeX, barycentreFuseeY);
	     g2d.translate(-barycentreFuseeX,-barycentreFuseeY);
	     g2d.drawImage(fusee, ctrl.getXfusee(), ctrl.getYfusee(), this);
	     //g2d.drawIm
		
		
		// lors de la première exécution on créé une image de l'espace généré
		// afin de détecter les contours des planètes
		if(!imageCree) {
			BufferedImage img = new BufferedImage(ctrl.getLargeur(), ctrl.getHauteur(), BufferedImage.TYPE_INT_ARGB);
			Graphics g2 = img.createGraphics();
			
			
			g2.setColor(Color.BLACK);
			for(Planete p: ctrl.getPlanetes())
				g2.drawOval(p.getPosX(), p.getPosY(), p.getTaille(), p.getTaille());
			
			imageCree = true;
			ctrl.genererMatrice(img);
			
		}
	}
}

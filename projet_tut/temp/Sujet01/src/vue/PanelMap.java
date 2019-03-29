package vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controleur.Controleur;
import controleur.ControleurFusee;
import jeu.Fusee;

public class PanelMap extends JPanel {

	private Image imgFond;
	private Image imgFusee;
	private Image imgPlanetes;

	private Controleur controleur;

	public PanelMap(Controleur controleur)
	{

		this.controleur = controleur;
		try {
			this.imgFond = ImageIO.read(new File("D:\\images\\background.jpg"));
			this.imgFusee = ImageIO.read(new File(this.controleur.getPlateau().getFusee().getNomImg()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		this.addKeyListener(new ControleurFusee(controleur, this));
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);


	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;


		int barycentreFuseeX = this.controleur.getPlateau().getFusee().getTraitement().getBaryCentre().getX();
		int barycentreFuseeY = this.controleur.getPlateau().getFusee().getTraitement().getBaryCentre().getY();

		System.out.println(barycentreFuseeX+"  "+barycentreFuseeY);

		//g2.drawImage(imgFond,0,0,this);


		g2.translate(barycentreFuseeX,barycentreFuseeY);
		g2.rotate(Math.toRadians(this.controleur.getPlateau().getFusee().getDegre()), barycentreFuseeX, barycentreFuseeY);
		g2.translate(-barycentreFuseeX,-barycentreFuseeY);
		g2.drawImage(imgFusee, barycentreFuseeX, barycentreFuseeY, 50, 50, this);




	}	
}

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelMap extends JPanel
{
	private Controleur ctrl;
	private Image      imgFond;
	private Graphics2D dernierPlan;
	private Graphics2D premierPlan;
	
	public PanelMap(Controleur controleur)
	{
		this.ctrl    = controleur;
		this.imgFond = null;
		
		this.dernierPlan = null;
		this.premierPlan = null;
		
		try
		{
			this.imgFond = ImageIO.read(new File(Controleur.IMAGE_FOND));
		}
		catch (IOException e) { e.printStackTrace(); }
		
		this.setSize(Controleur.LARGEUR, Controleur.HAUTEUR);
		
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	/*public void repaint()
	{
		graph.drawImage(this.imgFond, 0,0,this);
		
		ctrl.paintSpaceObject(graph);
	}*/
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		dernierPlan = (Graphics2D) g;
		premierPlan = (Graphics2D) g;
		
		dernierPlan.drawImage(this.imgFond, 0,0,this);
		
		ctrl.paintPlanete(dernierPlan);
		ctrl.paintFusee(premierPlan);
	}
}

package sujet0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

public class PanelImage extends JPanel {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	private Controleur ctrl;
	
	/**
	 * Cordonnée x du centre
	 * */
	private int rotationX;
	
	/**
	 * Cordonnée y du centre
	 * */
	private int rotationY;
	
	

	public PanelImage(Controleur ctrl) {
		this.ctrl = ctrl;
		
		addKeyListener(new EcouteurClavier(ctrl));
		setFocusable(true);
		
		rotationX = ctrl.getBaryCentre().getX();
		rotationY = ctrl.getBaryCentre().getY();
	}
	
	/*@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.RED);
		List<Coordonnees> l = ctrl.getCoords();
		
		for(Coordonnees c: l)
			g2d.fillRect(c.getX(), c.getY(), 1, 1);
	}*/
	
	@Override
	public void paintComponent ( Graphics g ) {
		super.paintComponent(g);
		BufferedImage img = ctrl.getImage();
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.translate(rotationX, rotationY);
		g2.rotate(Math.toRadians(ctrl.getDegre()), rotationX, rotationY);
		g2.translate(-rotationX, -rotationY);
		g2.drawImage(img, rotationX, rotationY, this);
		
	}

}

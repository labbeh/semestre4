package sujet0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

public class PanelImage extends JPanel {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	private Controleur ctrl;

	public PanelImage(Controleur ctrl) {
		this.ctrl = ctrl;
		
		addKeyListener(new EcouteurClavier());
		setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.RED);
		List<Coordonnees> l = ctrl.getCoords();
		
		for(Coordonnees c: l)
			g2d.fillRect(c.getX(), c.getY(), 1, 1);
	}

}

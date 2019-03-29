import javax.swing.JFrame;
import java.awt.event.*;

public class GUI extends JFrame
{
	private PanelMap   map;
	private Controleur ctrl;
	
	public GUI(Controleur controleur)
	{
		this.ctrl = controleur;
		this.setTitle("Rotation image");
		this.setSize(Controleur.LARGEUR, Controleur.HAUTEUR);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.map = new PanelMap(ctrl);
		this.map.addKeyListener(new ListenerKey());
		
		this.setContentPane(map);
		
		this.setVisible(true);
	}
	
	public void majIHM()
	{
		this.map.repaint();
	}
	
	// class interne
	private class ListenerKey extends KeyAdapter
	{
		public void keyPressed(KeyEvent evt)
		{
			if     (evt.getKeyCode() == KeyEvent.VK_LEFT ) ctrl.rotation('-');
			else if(evt.getKeyCode() == KeyEvent.VK_RIGHT) ctrl.rotation('+');
			else if(evt.getKeyCode() == KeyEvent.VK_UP)    ctrl.acceleration();
			else if(evt.getKeyCode() == KeyEvent.VK_DOWN)  ctrl.deceleration();
			
			map.repaint();
		}
	}
}



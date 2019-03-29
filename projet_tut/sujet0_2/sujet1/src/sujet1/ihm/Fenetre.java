package sujet1.ihm;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import sujet1.Controleur;

public class Fenetre extends JFrame{
	/**
	 * Pointeur vers le controleur
	 * */
	private Controleur ctrl;
	
	/**
	 * Panel représentant l'espace
	 * */
	private PanelEspace espace;
	
	public Fenetre(Controleur ctrl) {
		this.ctrl = ctrl;
		
		espace = new PanelEspace(ctrl);
		
		setTitle("Mon espace");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		espace.addKeyListener(new ListenerKey());
		
		add(espace);
		
		setSize(ctrl.getLargeur(), ctrl.getHauteur());
		setVisible(true);
	}

	public void majIHM() {
		espace.repaint();
	}
	
	// class interne gestion listeners
	private class ListenerKey extends KeyAdapter {
		public void keyPressed(KeyEvent evt) {
			if     (evt.getKeyCode() == KeyEvent.VK_LEFT ) ctrl.rotationAntiHoraire();
			else if(evt.getKeyCode() == KeyEvent.VK_RIGHT) ctrl.rotationHoraire();
			else if(evt.getKeyCode() == KeyEvent.VK_UP)    ctrl.acceleration();
			else if(evt.getKeyCode() == KeyEvent.VK_DOWN)  ctrl.deceleration();
			
			//majIHM();
		}
	}
}

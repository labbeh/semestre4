package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import vue.PanelMap;

/**
 * Classe de gestion des �v�nements lors de l'appuie d'une touche au clavier
 * Permet ici de faire tourner l'image
 * */
public class ControleurFusee implements KeyListener {
	/**
	 * Pointeur vers l'instance de controleur
	 * */
	private Controleur ctrl;
	private PanelMap mp;
	
	
	public ControleurFusee(Controleur ctrl, PanelMap mp) {
		this.ctrl = ctrl;
		this.mp = mp;
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		if	   (evt.getKeyCode() == KeyEvent.VK_LEFT ) 	ctrl.rotationAntiHoraire();
		else if(evt.getKeyCode() == KeyEvent.VK_RIGHT) 	ctrl.rotationHoraire();
		else if(evt.getKeyCode() == KeyEvent.VK_UP)  	ctrl.acceleration();
		else if(evt.getKeyCode() == KeyEvent.VK_DOWN)  	ctrl.deceleration();
		
		mp.repaint();
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		// ne doit rien faire
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		// ne doit rien faire
	}

}

package sujet0;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe de gestion des �v�nements lors de l'appuie d'une touche au clavier
 * Permet ici de faire tourner l'image
 * */
public class EcouteurClavier implements KeyListener {
	/**
	 * Pointeur vers l'instance de controleur
	 * */
	private Controleur ctrl;
	
	public EcouteurClavier(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		if	   (evt.getKeyCode() == KeyEvent.VK_LEFT ) ctrl.rotationAntiHoraire();
		else if(evt.getKeyCode() == KeyEvent.VK_RIGHT) ctrl.rotationHoraire();
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

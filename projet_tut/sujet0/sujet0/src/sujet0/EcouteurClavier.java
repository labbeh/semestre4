package sujet0;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe de gestion des évènements lors de l'appuie d'une touche au clavier
 * Permet ici de faire tourner l'image
 * */
public class EcouteurClavier implements KeyListener {

	@Override
	public void keyPressed(KeyEvent evt) {
		if	   (evt.getKeyCode() == KeyEvent.VK_LEFT ) System.out.println("rotation gauche");
		else if(evt.getKeyCode() == KeyEvent.VK_RIGHT) System.out.println("rotation droite");
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

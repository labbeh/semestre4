//package sujet1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import sujet1.metier.plateau.Plateau;

/**
 * Classe de gestion des événements lors de l'appuie d'une touche au clavier
 * Permet ici de faire tourner l'image
 * */
public class EcouteurClavier implements KeyListener {
	/**
	 * Pointeur vers l'instance de la fusée
	 * */
	private Fusee fusee;
	
	public EcouteurClavier ( Fusee fusee ) {
		this.fusee = fusee;
	}

	@Override
	public void keyPressed ( KeyEvent evt ) {
		if	    ( evt.getKeyCode() == KeyEvent.VK_LEFT  ) this.fusee.rotation('-');
		else if ( evt.getKeyCode() == KeyEvent.VK_RIGHT ) this.fusee.rotation('+');
		if	    ( evt.getKeyCode() == KeyEvent.VK_UP    ) this.fusee.acceleration();
		else if	( evt.getKeyCode() == KeyEvent.VK_DOWN  ) this.fusee.deceleration();
	}

	// ne doit rien faire
	@Override
	public void keyReleased ( KeyEvent evt ) { }
	
	// ne doit rien faire
	@Override
	public void keyTyped ( KeyEvent evt ) { }

}

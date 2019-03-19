package chat.ihm.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import chat.ihm.panels.dessin.ChoixCouleurs;
import chat.ihm.panels.dessin.PanelDessin;
import chat.ihm.panels.dessin.Selection;

public class PartieDessin extends JPanel {
	/**
	 * Panel de sélection de l'item que l'on veut dessiner
	 * */
	private Selection selection;
	
	/**
	 * Panel représentant la zone de dessin
	 * */
	private PanelDessin dessin;
	
	/**
	 * Panel de sélection des couleurs
	 * */
	private ChoixCouleurs couleurs;
	
	public PartieDessin(){
		selection = new Selection();
		dessin = new PanelDessin();
		couleurs = new ChoixCouleurs();
		
		// gestion layout
		setLayout(new BorderLayout());
		
		// ajout des elts
		add(selection, BorderLayout.PAGE_START);
		add(dessin, BorderLayout.CENTER);
		add(couleurs, BorderLayout.PAGE_END);
	}

}

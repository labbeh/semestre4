package chat.ihm.panels.dessin;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import chat.Controleur;

public class ChoixCouleurs extends JPanel {
	/**
	 * Pointeur vers l'instance du controleur
	 * */
	private Controleur ctrl;
	
	/**
	 * Selection couleur rouge
	 * */
	private JButton rouge;
	
	/**
	 * Selection couleur bleu
	 * */
	private JButton bleu;
	
	/**
	 * Selection couleur vert
	 * */
	private JButton vert;
	
	/**
	 * Selection couleur jaune
	 * */
	private JButton jaune;
	
	/**
	 * Selection couleur noir
	 * */
	private JButton noir;
	
	/**
	 * Selection couleur blanc
	 * */
	private JButton blanc;
	
	public ChoixCouleurs(Controleur ctrl){
		super();
		
		this.ctrl = ctrl;
		
		rouge = new JButton(" ");
		bleu = new JButton(" ");
		vert = new JButton(" ");
		jaune = new JButton(" ");
		noir = new JButton(" ");
		blanc = new JButton(" ");
		
		// coloration des btns
		rouge.setBackground(Color.RED);
		bleu.setBackground(Color.BLUE);
		vert.setBackground(Color.GREEN);
		jaune.setBackground(Color.YELLOW);
		noir.setBackground(Color.BLACK);
		blanc.setBackground(Color.WHITE);
		
		// gestion layout
		setLayout(new GridLayout());
		
		// listener
		EcouteurCouleur ecouteur = new EcouteurCouleur();
		
		rouge.addActionListener(ecouteur);
		bleu.addActionListener(ecouteur);
		vert.addActionListener(ecouteur);
		jaune.addActionListener(ecouteur);
		noir.addActionListener(ecouteur);
		blanc.addActionListener(ecouteur);
		
		// ajout des btns
		add(rouge);
		add(bleu);
		add(vert);
		add(jaune);
		add(noir);
		add(blanc);
	}
	
	/**
	 * Cette classe écoute les boutons de choix de couleurs afin que l'utilisateur
	 * puisse choisir la couleur dans laquelle dessiner
	 * */
	private class EcouteurCouleur implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			JButton courant = (JButton)evt.getSource();
			ctrl.setCoul(courant.getBackground());
		}
		
	}
}

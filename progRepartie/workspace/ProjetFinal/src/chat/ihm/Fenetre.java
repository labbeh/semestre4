package chat.ihm;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import chat.ihm.panels.PartieDessin;
import chat.ihm.panels.PartieText;

public class Fenetre extends JFrame {
	/**
	 * Barre de menu de l'application
	 * */
	private JMenuBar barreMenu;
	
	/**
	 * Panel de la partie Dessin
	 * */
	private JPanel ptDessin;
	
	/**
	 * Panel de la partie chat textuel
	 * */
	private JPanel ptChat;

	public Fenetre() throws HeadlessException {
		setTitle("Chat");
		//setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Gestion Layout de la fenêtre
		setLayout(new GridLayout());
		//GridBagConstraints gbc = new GridBagConstraints();
		//setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// barre menu
		barreMenu = new JMenuBar();
		setJMenuBar(barreMenu);
		
		// partie chat textuel
		ptChat = new PartieText();
		/*gbc.fill = GridBagConstraints.VERTICAL;
		gbc.ipady = 40;
		gbc.weightx = 0.0;
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 1;*/
		add(ptChat);
		
		// partie dessin
		ptDessin = new PartieDessin();
		add(ptDessin);
		
		
		
		pack();
		setVisible(true);
	}
	
	/**
	 * A SUPPRIMER JUSTE POUR TESTS
	 * */
	public static void main(String[] arv){
		new Fenetre();
	}

}
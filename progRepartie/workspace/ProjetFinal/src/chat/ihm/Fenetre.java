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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import chat.Controleur;
import chat.ihm.ecouteurs.ListenerEnvoieMessage;
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
	private PartieDessin ptDessin;
	
	/**
	 * Panel de la partie chat textuel
	 * */
	private PartieText ptChat;

	public Fenetre(Controleur ctrl) throws HeadlessException {
		// Obligatoire pour un affichage correct sous macOS
        try{
        	UIManager.setLookAndFeel(new MetalLookAndFeel());
        }
        catch(UnsupportedLookAndFeelException e){ e.printStackTrace(); }
        
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
		ptDessin = new PartieDessin(ctrl);
		add(ptDessin);
		
		
		
		//pack();
		setSize(1024, 768);
		setVisible(true);
	}
	
	/**
	 * Permet de définir l'écouteur que déclenchera le text field lors de l'appuie sur la touche entrée
	 * */
	public void setEcouteurEnvoieMsg(ListenerEnvoieMessage ecouteur){
		((PartieText) ptChat).setEcouteurEnvoieMsg(ecouteur);
	}
	
	/**
	 * Permet de nettoyer la zone ou on taape le message
	 * après son envoi
	 * */
	public void nettoyerZoneTexte(){
		ptChat.nettoyerZoneTexte();
	}

	public void ajouterMessage(String message) {
		ptChat.ajouterMessage(message);
	}

	public void majIHM() {
		ptDessin.majIHM();
		
	}
	
	/**
	 * A SUPPRIMER JUSTE POUR TESTS
	 * */
	/*public static void main(String[] arv){
		new Fenetre();
	}*/

}

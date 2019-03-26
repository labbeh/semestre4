import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Image extends JPanel implements KeyListener {
	private BufferedImage img;
	private Traitement traitement;
	
	
	private Graphics2D g2;
	private Graphics g;
	
	private int degre;
	private int degreModifie;
	
	private int rotationX;
	private int rotationY;
	
	/**
	 * Constructeur par défaut
	 * @param img image a dessiner
	 * @param t pointeur vers une instance de Traitement
	 * */
	public Image(BufferedImage img, Traitement t) {
		this.img = img;

		this.traitement = t;
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		this.rotationX = this.traitement.getBarycentre().getX();
		this.rotationY = this.traitement.getBarycentre().getY();
	}

	/**
	 * peindre l'image
	 * @param g objet graphics ...
	 * */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = (getWidth() - img.getWidth()) / 2;
		int y = (getHeight() - img.getHeight()) / 2;
		
		
		
		//this.degre = 5;
		this.g = g;
		Graphics2D g2 = (Graphics2D) g;
		this.g2 = g2; 
		
		g2.translate(rotationX, rotationY);
		g2.rotate(Math.toRadians(this.degre), 0, 0);
		g2.translate(-rotationX, -rotationY);
		g2.drawImage(img, x, y, this);
		
	}
	
	
	/**
	 * Rotation horaire de l'image
	 */
	public void rotationHoraire()
	{
		this.degreModifie = 5;
		this.degre += this.degreModifie;
		
	}
	
	/**
	 * Rotation anti-horaire de l'image
	 */
	public void rotationAntiHoraire()
	{

		degreModifie = -5;
		this.degre += this.degreModifie;
	}
  
  
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			System.out.print("Bonjour");
			rotationHoraire();
		
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			rotationAntiHoraire();

		}
	
		repaint(); 
		
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



  


}
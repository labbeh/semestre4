import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GUI extends JFrame{

	private BufferedImage img;
	
	private Traitement traitement;
	
	public GUI(String imageAtraiter, String imageAAfficher) {
		
		this.setTitle("Rotation image");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.traitement = new Traitement(imageAtraiter, imageAAfficher);
		
		try {
			this.img = ImageIO.read(new File(imageAAfficher));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    Image contentPane = new Image(img, traitement);
	    this.setContentPane(contentPane);
	
		this.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new GUI("robot1.png","D:\\images\\robot4.png");

	}

}

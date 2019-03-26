package sujet0.lanceur;


import javax.swing.JFrame;
import java.awt.FileDialog;

/**
 * Classe qui au lancement de l'appli demande à l'utilisateur de choisir
 * un fichier source
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */
public class RecupererImage extends JFrame
{
	/**
	 * SerialUID par défaut
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Chemin complet du fichier image
	 * */
	private String fileImage;
	
	/**
	 * Nom du fichier image
	 * */
	private String nomImage ;

	public RecupererImage()
	{
		this.setTitle   ("Choix image");
		this.setLocation(50, 50);
		this.setSize    (200, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.nomImage = null;

		while(this.nomImage == null)
		{
			FileDialog fd = new FileDialog(this, "Choose a image", FileDialog.LOAD);
			fd.setDirectory(".");
			fd.setVisible(true);
			

			this.nomImage = fd.getFile();
			
			if(this.nomImage == null) System.exit(0); // si l'utilisateur clique sur annuler
			if(this.nomImage.contains(".png") || this.nomImage.contains(".jpg"))
			{
				System.out.println("You chose " + this.nomImage);
				this.setVisible(false);
				this.fileImage = fd.getDirectory()+this.nomImage;
			}
			else this.nomImage = null;
		}
	}

	public String getNomImage()  { return this.nomImage;  }
	public String getFileImage() { return this.fileImage; }
}


import javax.swing.JFrame;
import java.awt.FileDialog;

public class RecupererImage extends JFrame
{
	private String fileImage = "";
	private String nomImage  = "";

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

			if(this.nomImage.contains(".png") || this.nomImage.contains(".jpg"))
			{
				System.out.println("You chose " + this.nomImage);
				this.setVisible(false);
				this.fileImage = fd.getDirectory()+this.nomImage;
			}
			else
				this.nomImage = null;
		}
	}

	public String getNomImage()  { return this.nomImage;  }
	public String getFileImage() { return this.fileImage; }

	public static void main(String[] args)
	{
		new RecupererImage();
		System.exit(JFrame.EXIT_ON_CLOSE);
	}
}

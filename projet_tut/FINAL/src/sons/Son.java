package sons;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Son {

	public static Mixer mixer;
	public static Clip clip;
	
	public static String FIN_SON = "./sons/gameover.wav";
	public static String EXPLOSION_SON = "./sons/explosion.wav";
	public static String WIN_SON = "./sons/win.wav";

	
	
	private Son()
	{
		
	}
	
	public static void jouerSon(String urlSon)
	{
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		
		mixer = AudioSystem.getMixer(mixInfos[0]);
		
		DataLine.Info dataInfo = new DataLine.Info(Clip.class,  null);
		
		try {
			clip = (Clip)mixer.getLine(dataInfo);
			
		}catch(LineUnavailableException lue) {
			lue.printStackTrace();
			
		}
		
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(urlSon));
			clip.open(audioStream);
		}
		catch(LineUnavailableException lue ) { lue.printStackTrace();}
		catch(UnsupportedAudioFileException uafe) { uafe.printStackTrace();}
		catch(IOException ioe) { ioe.printStackTrace(); }
		
		clip.start();
		
		do {
			try { Thread.sleep(50);}
			catch(InterruptedException ie) { ie.printStackTrace(); }
		}while(clip.isActive());
		
			
		
		
		
	}
	
	public static void arreterSonCourant()
	{
		clip.stop();
	}
	
}

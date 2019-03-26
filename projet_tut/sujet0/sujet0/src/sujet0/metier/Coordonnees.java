package sujet0.metier;

/**
 * Classe représentant les coordonnées d'un pixel
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */
public class Coordonnees {
	/**
	 * Position horizontal
	 * */
	private int x;
	
	/**
	 * Position verticale
	 * */
	private int y;
	
	public Coordonnees(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coordonnees [x=" + x + ", y=" + y + "]";
	}
	
	
}

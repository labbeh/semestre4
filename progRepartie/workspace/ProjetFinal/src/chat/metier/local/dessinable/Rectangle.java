package chat.metier.local.dessinable;

import java.awt.Color;

import chat.Controleur;

/**
 * Classe qui représente un rectangle, forme dessinnable sur le panel des dessins
 * et qui peut être partager dans le tchat
 * */
public class Rectangle implements IDessinable{
	private Color coul;
	private boolean estVide;
	
	private Integer x1;
	private Integer y1;
	private Integer x2;
	private Integer y2;
	
	

	public Rectangle(Color coul, boolean estVide, Integer x1, Integer y1, Integer x2, Integer y2) {
		super();
		this.coul = coul;
		this.estVide = estVide;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	

	public Color getCoul() {
		return coul;
	}



	public boolean estVide() {
		return estVide;
	}



	public Integer getX1() {
		return x1;
	}



	public Integer getY1() {
		return y1;
	}



	public Integer getX2() {
		return x2;
	}



	public Integer getY2() {
		return y2;
	}



	@Override
	public String getType() {
		return "rectangle";
	}



	@Override
	public String toString() {
		return "DESSIN;" + x1 +";" +y1 + ";" +x2 +";" + y2 +";" +Controleur.getColorName(coul)+ ";" +estVide;
	}

	
}

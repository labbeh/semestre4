package chat.metier.local;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chat.Controleur;
import chat.metier.local.dessinable.IDessinable;
/**
 * Permet de mémoriser les paramètres de dessin choisis par l'utilisateur
 * tel que la forme et la couleur sélectionnée
 * */
public class GestionDessin {
	/**
	 * Pointeur vers l'instance du controleur de l'application
	 * */
	private Controleur ctrl;
	
	/**
	 * Ensembles des éléments dessinés sur le panel de dessin
	 * La clef est le type d'élément (Texte, Rectangle, Rond...)
	 * et la valeur l'objet représentant l'élément en lui même
	 * */
	private Map<String, Set<IDessinable>> dessinable;
	/**
	 * Couleur choisi pour la partie dessin
	 * */
	private Color coul;
	
	/**
	 * Elément dessinable choisi
	 * */
	private String type;
	
	/**
	 * Est vrai si la forme doit être vide
	 * */
	private boolean estVide;
	
	/**
	 * Constructeur par défaut
	 * Initialise par en rectangle noir vide
	 * */
	public GestionDessin(){
		//this.ctrl = ctrl;
		
		dessinable = new HashMap<>();
		
		coul = Color.BLACK;
		type = "Rectangle";
		estVide = true;
	}
	
	/**
	 * Ajoute un élément dessinable au tableau
	 * @param dessin objet implementant l'interface IDessinable
	 * */
	public synchronized void ajouterDessin(IDessinable dessin){
		if(!dessinable.containsKey(dessin.getType()))
			dessinable.put(dessin.getType(), new HashSet<IDessinable>());
		
		dessinable.get(dessin.getType()).add(dessin);
	}
	
	/**
	 * Retourne l'ensemble des dessin correspondant au type passé en paramètres
	 * @param type nom du type de dessin à retourner
	 * @return Set de IDessinables qu'il faudra caster dans le bon type,
	 * null si le type de dessin passé en paramètre n'est pas présent
	 * */
	public synchronized Set<IDessinable> getDessin(String type){
		if(!dessinable.containsKey(type)) return null;
		return dessinable.get(type);
	}

	public Color getCoul() {
		return coul;
	}

	public void setCoul(Color coul) {
		this.coul = coul;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean estVide() {
		return estVide;
	}

	public void setEstVide(boolean estVide) {
		this.estVide = estVide;
	}
	
	

}

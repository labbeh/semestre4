//Serveur(ids,nom,os)
package beans;

import java.util.*;

public class Serveur{
        private int ids;
        private String nom;
        private String os;

        public int getIds(){return ids;}
        public String getNom(){return nom;}
        public String getOs(){return os;}
        public void setIds(int ids){this.ids=ids;}
        public void setNom(String nom){this.nom=nom;}
        public void setOs(String os){this.os=os;}
        
    	public Serveur(int ids , String nom, String os){
	    setIds(ids);
	    setNom(nom);
	    setOs(os);
        }

	//constructeur Ã  utiliser quand on ne connait pas ids (nouveau tuple par exemple)
	public Serveur(String nom, String os){
	    setIds(-1);
	    setNom(nom);
	    setOs(os);
        }

        public String toString(){
                return ids+ "," + nom + "," + os;
        }
}
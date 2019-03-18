//Utilisateur(idp,nom,age)
package beans;

import java.util.*;

public class Utilisateur{
        private int idu;
        private String nom;
        private String role;

        public int getIdu(){return idu;}
        public String getNom(){return nom;}
        public String getRole(){return role;}
        public void setIdu(int idu){this.idu=idu;}
        public void setNom(String nom){this.nom=nom;}
        public void setRole(String role){this.role=role;}
        
    	public Utilisateur(int idu , String nom, String role){
	    setIdu(idu);
	    setNom(nom);
	    setRole(role);
        }

	//constructeur Ã  utiliser quand on ne connait pas idu (nouveau tuple par exemple)
	public Utilisateur(String nom, String role){
	    setIdu(-1);
	    setNom(nom);
	    setRole(role);
        }

        public String toString(){
                return idu+ "," + nom + "," + role;
        }
}
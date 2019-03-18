//Connexion(idu,ids,datec,login,duree)
package beans;

import java.util.*;
import java.sql.Timestamp;

public class Connexion{
        private int idu;
        private int ids;
        private Timestamp datec;
        private String login;
        private int duree;


        
    	public Connexion(int idu, int ids, Timestamp datec, String login, int duree){
	       this.idu = idu;
           this.ids = ids;
           this.datec = datec;
           this.login = login;
           this.duree = duree;
        }

        public int getIdu() {
            return idu;
        }



        public void setIdu(int idu) {
            this.idu = idu;
        }



        public int getIds() {
            return ids;
        }



        public void setIds(int ids) {
            this.ids = ids;
        }



        public Timestamp getDatec() {
            return datec;
        }

        public String getDatecString(){
            return datec.toString();
        }



        public void setDatec(Timestamp datec) {
            this.datec = datec;
        }



        public String getLogin() {
            return login;
        }



        public void setLogin(String login) {
            this.login = login;
        }



        public int getDuree() {
            return duree;
        }



        public void setDuree(int duree) {
            this.duree = duree;
        }

        public String toString(){
                return idu+ "," + ids + "," + datec.toString() + "," + login + "," +duree;
        }
}
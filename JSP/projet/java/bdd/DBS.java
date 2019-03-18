package bdd;

import java.sql.*;

public class DBS {

  private Connection cnx;
  private DB_UTILISATEUR db_utilisateur;
  //A DECOMMENTER QUAND LES CLASSES DB_SERVEUR et DB_CONNEXION seront implantées 
  private DB_SERVEUR db_serveur;
  private DB_CONNEXION db_connexion;
  
  private static DBS instance;

  public static DBS getInstance(){
	if(null==instance){instance=new DBS();}
	return instance;
  }

  
  private DBS() {
     String urlServeur = "jdbc:postgresql://woody/lh150094"; //A MODIFIER
     String login = "lh150094"; //A MODIFIER
     String mdp = "phppasswd"; //A MODIFIER

     try{
        	Class.forName ("org.postgresql.Driver");
		System.out.println("Driver installe");
        	cnx = DriverManager.getConnection (urlServeur,login,mdp);
		System.out.println("connexion etablie");
		db_utilisateur = new DB_UTILISATEUR(cnx);
		//A DECOMMENTER QUAND LES CLASSES DB_SERVEUR et DB_CONNEXION seront implantées 
		db_serveur = new DB_SERVEUR(cnx);
		db_connexion = new DB_CONNEXION(cnx);
     } catch(ClassNotFoundException e){System.out.println(e);}
      catch(SQLException e){System.out.println(e);}
  }

  public DB_UTILISATEUR getDB_UTILISATEUR(){return db_utilisateur;}
  //A DECOMMENTER QUAND LES CLASSES DB_SERVEUR et DB_CONNEXION seront implantées 
  public DB_SERVEUR getDB_SERVEUR(){return db_serveur;}
  public DB_CONNEXION getDB_CONNEXION(){return db_connexion;}
      
}


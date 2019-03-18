// sous module specfique de gestion des acces a la table UTILISATEUR
// Table : Utilisateur(idu, nom, role)
package bdd;

import java.sql.*;
import java.util.*;
import beans.*;

/*
 * Pour utiliser cette classe, il faut disposer d'un driver JDBC au minimum en version 4.
   (en raison de l'utilisation de getGeneratedKeys())
   Celui utilise pour le TP sur JDBC (séance 6) est normalement suffisant.
 */


public class DB_UTILISATEUR {

  Connection conn;
  PreparedStatement ps_select;
  PreparedStatement ps_insert;
  PreparedStatement ps_update;
  PreparedStatement ps_delete;

  public DB_UTILISATEUR(Connection conn) {
     this.conn=conn;
     try{
        ps_select = conn.prepareStatement("select nom,role from utilisateur where idu=?");
  	ps_insert = conn.prepareStatement("insert into utilisateur values(default,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
        ps_update = conn.prepareStatement("update utilisateur set nom=?, role=? where idu=?");
        ps_delete = conn.prepareStatement("delete from utilisateur where idu=?"); 
     } catch(SQLException ex){System.out.println(ex);}
  }
      
  public Utilisateur getUtilisateur(int idu) throws Exception{
      Utilisateur u=null;
      ps_select.setInt(1,idu);
      ResultSet rs = ps_select.executeQuery();
      if(rs.next()){
	  String nom = rs.getString("nom");
	  String role = rs.getString("role");
          u = new Utilisateur(idu,nom,role);        
      }
      return u;
  }

  public int insertUtilisateur(Utilisateur p) throws Exception{
			int clef = -1;

			ps_insert.setString(1,p.getNom());
			ps_insert.setString(2,p.getRole());
			//le paramètre passé à executeUpdate permet de récupérer les clefs 
			//éventuellement générées automatiquement (via le type serial) 
			//au moment de l'exécution de l'ordre SQL.
			ps_insert.executeUpdate();
			//on récupère les clefs générées (une seule ici)
			ResultSet clefs = ps_insert.getGeneratedKeys();
			if (clefs.next()) {
			    clef = clefs.getInt(1);
			}

			return clef;
  }
        
  public void updateUtilisateur(Utilisateur p) throws Exception{
			ps_update.setString(1,p.getNom());
			ps_update.setString(2,p.getRole());
			ps_update.setInt(3,p.getIdu());
			ps_update.executeUpdate();
  }

  public void deleteUtilisateur(int idu) throws Exception{
		ps_delete.setInt(1,idu);
		ps_delete.executeUpdate();
  }

  // cette méthode ne peut être utilisée que dans cette classe
  // elle ne peut pas être utilisée dans d'autres classes
  private ArrayList<Utilisateur> getUtilisateurs(String req) throws Exception{
	// pre-condition: req est de la forme "SELECT * FROM Participant ..."
	// car il s'agit d'extraire un ensemble de participants

        Utilisateur util;
	ArrayList<Utilisateur> arrutil = null;

		arrutil = new ArrayList<Utilisateur>(); 
		Statement st = conn.createStatement(); 
		ResultSet rs = st.executeQuery(req); 
		while(rs.next()){ 
		    util = new Utilisateur(rs.getInt("idu"), rs.getString("nom"), rs.getString("role")); 
		    arrutil.add(util); 
		} 
	return arrutil;
  }

  public ArrayList<Utilisateur> getUtilisateurs() throws Exception{
	return getUtilisateurs("select * from utilisateur");
  }


}

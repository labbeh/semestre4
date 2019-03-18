// sous module specfique de gestion des acces a la table SERVEUR
// Table : Serveur(ids, nom, os)
package bdd;

import java.sql.*;
import java.util.*;
import beans.*;

/**
* @author: hugo labbé
*/


public class DB_SERVEUR {

  Connection conn;
  PreparedStatement ps_select;
  PreparedStatement ps_insert;
  PreparedStatement ps_update;
  PreparedStatement ps_delete;

  public DB_SERVEUR(Connection conn) {
     this.conn=conn;
     try{

        ps_select = conn.prepareStatement("select nom,os from serveur where ids=?");
        ps_insert = conn.prepareStatement("insert into serveur values(default,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
        ps_update = conn.prepareStatement("update serveur set nom=?, os=? where ids=?");
        ps_delete = conn.prepareStatement("delete from serveur where ids=?"); 
     }
     catch(SQLException ex){System.out.println(ex);}
  }
      
  public Serveur getServeur(int ids) throws Exception{
      Serveur s=null;

      ps_select.setInt(1,ids);
      ResultSet rs = ps_select.executeQuery();

      if(rs.next()){
       String nom = rs.getString("nom");
       String os  = rs.getString("os" );
       s = new Serveur(ids,nom,os);        
      }
      return s;
  }

  public int insertServeur(Serveur s) throws Exception{
      int clef = -1;

      ps_insert.setString(1,s.getNom());
      ps_insert.setString(2,s.getOs());

      ps_insert.executeUpdate();

      ResultSet clefs = ps_insert.getGeneratedKeys();
      if (clefs.next()) {
          clef = clefs.getInt(1);
      }

      return clef;
  }
        
  public void updateServeur(Serveur s) throws Exception{
      ps_update.setString(1,s.getNom());
      ps_update.setString(2,s.getOs());
      ps_update.setInt(3,s.getIds());
      ps_update.executeUpdate();
  }

  public void deleteServeur(int ids) throws Exception{
    ps_delete.setInt(1,ids);
    ps_delete.executeUpdate();
  }

  // cette méthode ne peut être utilisée que dans cette classe
  // elle ne peut pas être utilisée dans d'autres classes
  private ArrayList<Serveur> getServeurs(String req) throws Exception{
  // pre-condition: req est de la forme "SELECT * FROM Participant ..."
  // car il s'agit d'extraire un ensemble de participants

        Serveur serveur;
  ArrayList<Serveur> serveurs = null;

    serveurs = new ArrayList<>(); 
    Statement st = conn.createStatement(); 
    ResultSet rs = st.executeQuery(req); 
    while(rs.next()){ 
        serveur = new Serveur(rs.getInt("ids"), rs.getString("nom"), rs.getString("os")); 
        serveurs.add(serveur); 
    } 
  return serveurs;
  }

  public ArrayList<Serveur> getServeurs() throws Exception{
  return getServeurs("select * from Serveur");
  }


}

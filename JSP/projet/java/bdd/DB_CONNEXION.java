// sous module specfique de gestion des acces a la table CONNEXION
// Table : Connexion(idu, ids, datec,nom,curee)
package bdd;

import java.sql.*;
import java.util.*;
import beans.*;

/**
* @author: hugo labbé
*/


public class DB_CONNEXION {

  Connection conn;
  PreparedStatement ps_select;
  PreparedStatement ps_insert;
  PreparedStatement ps_update;
  PreparedStatement ps_delete;

  public DB_CONNEXION(Connection conn) {
     this.conn=conn;
     try{

        ps_select = conn.prepareStatement("select nom,os from connexion where ids=?");
        ps_insert = conn.prepareStatement("insert into connexion values(?,?,?,?,?)");
        ps_update = conn.prepareStatement("update connexion set login=?, duree=? where idu=? and ids=? and datec=?");
        ps_delete = conn.prepareStatement("delete from connexion where ids=? and idu=? and datec=?"); 
     }
     catch(SQLException ex){System.out.println(ex);}
  }
      
  public Connexion getConnexion(int idu, int ids, Timestamp datec) throws Exception{
      Connexion s=null;

      ps_select.setInt(1,idu);
      ps_select.setInt(2,ids);
      ps_select.setTimestamp(3,datec);
      ResultSet rs = ps_select.executeQuery();

      if(rs.next()){
       String login = rs.getString("login");
       int duree  = rs.getInt("duree" );
       s = new Connexion(idu,ids,datec,login,duree);        
      }
      return s;
  }

  public int insertConnexion(Connexion s) throws Exception{
      int clef = -1;

      ps_insert.setInt(1,s.getIdu());
      ps_insert.setInt(2,s.getIds());
      ps_insert.setTimestamp(3,s.getDatec());
      ps_insert.setString(4,s.getLogin());
      ps_insert.setInt(5,s.getDuree());

      ps_insert.executeUpdate();

      /*ResultSet clefs = ps_insert.getGeneratedKeys();
      if (clefs.next()) {
          clef = clefs.getInt(1);
      }*/

      return clef;
  }
        
  public void updateConnexion(Connexion s) throws Exception{
      ps_update.setInt(1,s.getIdu());
      ps_update.setInt(2,s.getIds());
      ps_update.setTimestamp(3,s.getDatec());
      ps_update.setString(4,s.getLogin());
      ps_update.setInt(5,s.getDuree());
      ps_update.executeUpdate();
  }

  public void deleteConnexion(int idu, int ids, Timestamp datec) throws Exception{
    ps_delete.setInt(1,idu);
    ps_delete.setInt(2,ids);
    ps_delete.setTimestamp(3,datec);
    ps_delete.executeUpdate();
  }

  // cette méthode ne peut être utilisée que dans cette classe
  // elle ne peut pas être utilisée dans d'autres classes
  private ArrayList<Connexion> getConnexions(String req) throws Exception{
  // pre-condition: req est de la forme "SELECT * FROM Participant ..."
  // car il s'agit d'extraire un ensemble de participants

        Connexion connexion;
  ArrayList<Connexion> connexions = null;

    connexions = new ArrayList<>(); 
    Statement st = conn.createStatement(); 
    ResultSet rs = st.executeQuery(req); 
    while(rs.next()){ 
        connexion = new Connexion(rs.getInt("idu"), rs.getInt("ids"), rs.getTimestamp("datec"), rs.getString("login"), rs.getInt("duree")); 
        connexions.add(connexion); 
    } 
  return connexions;
  }

  public ArrayList<Connexion> getConnexions() throws Exception{
  return getConnexions("select * from Connexion");
  }


}

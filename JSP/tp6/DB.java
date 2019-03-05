package pac;

import java.sql.*;
import java.util.*;

public class DB {

  Connection cnx;

  PreparedStatement ps_select_pdts;

  // RAPPEL: la classe DB est un SINGLETON:
  private static DB instance;
  
  public static DB getInstance(){
	if(instance==null) instance = new DB();
	return instance;
  }
  private DB() {
     try{
	Class.forName("org.postgresql.Driver");
        cnx = DriverManager.getConnection ("jdbc:postgresql://woody/lh150094", "lh150094", "phppasswd");	// A MODIFIER

  	ps_select_pdts = cnx.prepareStatement ("select np,lib,coul,qs from produit order by np");

     } catch(ClassNotFoundException e){System.out.println(e);}
      catch(SQLException e){System.out.println(e);}
     
  }
      

  
  // =====================================================================================================
  // Produits:
  // =====================================================================================================
  public List<Produit> getProduits() {
     Produit p=null;
     List<Produit> pdts = null;
     try{
      pdts = new ArrayList<Produit>();
      ResultSet rs = ps_select_pdts.executeQuery();
      while(rs.next()){
          p = new Produit(rs.getInt("np"),rs.getString("lib"),rs.getString("coul"),rs.getInt("qs"));
	  pdts.add(p);
      }
     } catch(SQLException e){System.out.println(e);}
     return pdts;
  }
  
}



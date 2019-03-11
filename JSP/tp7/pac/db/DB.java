package pac;

import java.sql.*;
import java.util.*;

public class DB {

  Connection cnx;

  // Clients:
  PreparedStatement ps_select_client;
  PreparedStatement ps_select_clients;
  PreparedStatement ps_insert_client;
  PreparedStatement ps_update_client;
  PreparedStatement ps_delete_client;

  //achats:
  PreparedStatement ps_select_achatsClient;
  PreparedStatement ps_select_achatsClientCP;
  PreparedStatement ps_select_achatsProduit;
  PreparedStatement ps_select_achatsProduitCP;

  //produits:
  PreparedStatement ps_select_pdts;
  PreparedStatement ps_select_pdt;
  PreparedStatement ps_update_pdt;


  private static DB instance;
  public static DB getInstance() throws Exception {
	if(instance==null) instance = new DB();
	return instance;
  }
  private DB() throws Exception {
	Class.forName("org.postgresql.Driver");
        cnx = DriverManager.getConnection ("jdbc:postgresql://woody/lh150094", "lh150094", "phppasswd");	// A MODIFIER

  	// Clients:
        ps_select_client = cnx.prepareStatement ("select nom, adr from client where ncli=?");
        ps_select_clients = cnx.prepareStatement ("select ncli,nom, adr from client order by ncli");
  	ps_insert_client = cnx.prepareStatement ("insert into client values(?,?,?)");
        ps_update_client = cnx.prepareStatement ("update client set nom=?,  adr=? where ncli=?");
        ps_delete_client = cnx.prepareStatement ("delete from client where ncli=?"); 

	// achats:
  	ps_select_achatsClient = cnx.prepareStatement ("select ncli,np,qa from achat where ncli=? order by np");
  	ps_select_achatsClientCP = cnx.prepareStatement ("select a.ncli,a.np,qa,nom,lib from achat a, client c,produit p where p.np=a.np and c.ncli=a.ncli and a.ncli=? order by a.np");
  	ps_select_achatsProduit = cnx.prepareStatement ("select ncli,np,qa from achat where np=? order by ncli");
  	ps_select_achatsProduitCP = cnx.prepareStatement ("select a.ncli,a.np,qa,nom,lib from achat a, client c,produit p where p.np=a.np and c.ncli=a.ncli and a.np=? order by a.np");
  	
	// produits:
  	ps_select_pdts = cnx.prepareStatement ("select np,lib,coul,qs from produit order by np");
  	ps_select_pdt = cnx.prepareStatement ("select np,lib,coul,qs from produit where np=?");
    ps_update_pdt = cnx.prepareStatement ("update produit set qs=? where np=?");
    ps_insert_pdt = cnx.prepareStatement("insert into produit values(?,?,?,?)");
     
  }
      

  // =============================================================================================================
  // Clients:
  // =============================================================================================================
  public Client getClient(int ncli) throws Exception {
     Client c=null;
      ps_select_client.setInt(1,ncli);
      ResultSet rs = ps_select_client.executeQuery();
      if(rs.next()){
          c = new Client(ncli,rs.getString("nom"),rs.getString("adr"));        
      }
     return c;
  }

  public List<Client> getClients() throws Exception {
     Client c=null;
     List<Client> clients = null;
      clients = new ArrayList<Client>();
      ResultSet rs = ps_select_clients.executeQuery();
      while(rs.next()){
          c = new Client(rs.getInt("ncli"),rs.getString("nom"),rs.getString("adr"));
	  clients.add(c);
      }
     return clients;
  }

  public void insertClient(Client c) throws Exception {
			ps_insert_client.setInt(1,c.getNcli());
			ps_insert_client.setString(2,c.getNom());
			ps_insert_client.setString(3,c.getAdr());
			ps_insert_client.executeUpdate();  
  }
  public void updateClient(Client c) throws Exception {
			ps_update_client.setString(1,c.getNom());
			ps_update_client.setString(2,c.getAdr());
			ps_update_client.setInt(3,c.getNcli());
			ps_update_client.executeUpdate();     
  }

  public void deleteClient(int ncli) throws Exception {
		ps_delete_client.setInt(1,ncli);
		ps_delete_client.executeUpdate(); 
  }

  
  // =============================================================================================================
  // Produits:
  // =============================================================================================================
  public Produit getProduit(int np) throws Exception {
     Produit p=null;
      ps_select_pdt.setInt(1,np);
      ResultSet rs = ps_select_pdt.executeQuery();
      if(rs.next()){
          p = new Produit(rs.getInt("np"),rs.getString("lib"),rs.getString("coul"),rs.getInt("qs"));
      }
     return p;
  }

  public List<Produit> getProduits() throws Exception {
     Produit p=null;
     List<Produit> pdts = null;
      pdts = new ArrayList<Produit>();
      ResultSet rs = ps_select_pdts.executeQuery();
      while(rs.next()){
          p = new Produit(rs.getInt("np"),rs.getString("lib"),rs.getString("coul"),rs.getInt("qs"));
	  pdts.add(p);
      }
     return pdts;
  }

  public void insertProduit(Produit p) throws Exception {
      ps_insert_pdt.setInt(1,p.getNp());
      ps_insert_pdt.setString(2,p.getLib());
      ps_insert_pdt.setString(3,p.getCouleur());
      ps_insert_pdt.setInt(4,p.getQs());
      
      ps_insert_pdt.executeUpdate();  
  }
  
  public void updateProduit(Produit p) throws Exception {
			ps_update_pdt.setInt(1,p.getQs());
			ps_update_pdt.setInt(2,p.getNp());
			ps_update_pdt.executeUpdate();     
  }

  // =============================================================================================================
  // Achats
  // =============================================================================================================
  public List<Achat> getAchatsClient(int ncli) throws Exception {
     Achat a=null;
     List<Achat> liste = null;
      liste = new ArrayList<Achat>();
      ps_select_achatsClient.setInt(1,ncli);
      ResultSet rs = ps_select_achatsClient.executeQuery();
      while(rs.next()){
          a = new Achat(rs.getInt("ncli"),rs.getInt("np"),rs.getInt("qa"));
	  liste.add(a);
      }
     return liste;
  }

  public List<AchatCP> getAchatsClientCP(int ncli) throws Exception {
     AchatCP a=null;
     List<AchatCP> liste = null;
      liste = new ArrayList<AchatCP>();
      ps_select_achatsClientCP.setInt(1,ncli);
      ResultSet rs = ps_select_achatsClientCP.executeQuery();
      while(rs.next()){
          a = new AchatCP(rs.getInt("ncli"),rs.getInt("np"),rs.getInt("qa"),rs.getString("nom"),rs.getString("lib"));
	  liste.add(a);
      }
     return liste;
  }

  public List<Achat> getAchatsProduit(int np) throws Exception {
     Achat a=null;
     List<Achat> liste = null;
      liste = new ArrayList<Achat>();
      ps_select_achatsProduit.setInt(1,np);
      ResultSet rs = ps_select_achatsProduit.executeQuery();
      while(rs.next()){
          a = new Achat(rs.getInt("ncli"),rs.getInt("np"),rs.getInt("qa"));
	  liste.add(a);
      }
     return liste;
  }

  public List<AchatCP> getAchatsProduitCP(int np) throws Exception {
     AchatCP a=null;
     List<AchatCP> liste = null;
      liste = new ArrayList<AchatCP>();
      ps_select_achatsProduitCP.setInt(1,np);
      ResultSet rs = ps_select_achatsProduitCP.executeQuery();
      while(rs.next()){
          a = new AchatCP(rs.getInt("ncli"),rs.getInt("np"),rs.getInt("qa"),rs.getString("nom"),rs.getString("lib"));
	  liste.add(a);
      }
     return liste;
  }

}



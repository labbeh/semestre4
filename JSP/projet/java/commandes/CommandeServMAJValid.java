package commandes;

import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.*;
// Cette cde recupere update un pdt:

public class CommandeServMAJValid implements Commande {
  private final String next;

  public CommandeServMAJValid(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();
	  
	  DB_SERVEUR db_serv = db.getDB_SERVEUR();
	  int ids = Integer.parseInt(req.getParameter("ids"));
	  
	  Serveur serv = db_serv.getServeur(ids);
	  
	  String nom =req.getParameter("nom");
	  String os =req.getParameter("os");
	  
	  serv.setNom(nom);
	  serv.setOs(os);
	  
	  db_serv.updateServeur(serv);
	  req.setAttribute("serv", serv);
          return next;
  }

}

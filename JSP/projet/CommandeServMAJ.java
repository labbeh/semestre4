package commandes;

import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.*;

// Cette cde recupere update un pdt:
public class CommandeServMAJ implements Commande {
  private final String next;

  public CommandeServMAJ(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();

	  DB_SERVEUR db_serv = db.getDB_SERVEUR();

	  int ids = Integer.parseInt(req.getParameter("ids"));

	  Serveur serv = db_serv.getServeur(ids);

	  //db.updateProduit(util);
	  req.setAttribute("serveur", serv);

       return next;
  }

}
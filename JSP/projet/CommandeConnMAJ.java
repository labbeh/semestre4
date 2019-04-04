package commandes;

import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.*;

// Cette cde recupere update un pdt:
public class CommandeConnMAJ implements Commande {
  private final String next;

  public CommandeConnMAJ(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();

	  DB_CONNEXION db_conn = db.getDB_CONNEXION();

	  int idu = Integer.parseInt(req.getParameter("idu"));
	  int ids = Integer.parseInt(req.getParameter("ids"));
	  TimeStamp datec = req.getParameter("datec");

	  Connexion conn = db_conn.getConnexion(idu, ids, datec);

	  //db.updateProduit(util);
	  req.setAttribute("connexion", conn);

       return next;
  }

}
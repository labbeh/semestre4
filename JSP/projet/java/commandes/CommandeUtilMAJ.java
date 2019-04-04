package commandes;

import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.*;

// Cette cde recupere update un pdt:

public class CommandeUtilMAJ implements Commande {
  private final String next;

  public CommandeUtilMAJ(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();

	  DB_UTILISATEUR db_util = db.getDB_UTILISATEUR();

	  int idu = Integer.parseInt(req.getParameter("idu"));

	  Utilisateur util = db_util.getUtilisateur(idu);

	  //db.updateProduit(util);
	  req.setAttribute("utilisateur", util);

       return next;
  }

}

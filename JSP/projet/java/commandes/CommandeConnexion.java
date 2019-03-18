package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.Connexion;

/**
* @author: Hugo Labbé, Pierrick Bourdin, Loan Cadorel
*/
public class CommandeConnexion implements Commande {
  private final String next;

  public CommandeConnexion(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();
	  List<Connexion> connex = db.getDB_CONNEXION().getConnexions();
	  req.setAttribute("connexions", connex);
      return next;
  }

}
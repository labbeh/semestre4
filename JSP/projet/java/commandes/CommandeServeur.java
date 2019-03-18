package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.Serveur;

/**
* @author: Hugo Labbé, Pierrick Bourdin, Loan Cadorel
*/
public class CommandeServeur implements Commande {
  private final String next;

  public CommandeServeur(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();
	  List<Serveur> serveurs = db.getDB_SERVEUR().getServeurs();
	  req.setAttribute("serveurs", serveurs);
      return next;
  }

}


package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.Serveur;

/**
* @author: hugo labbé
*/
public class CommandeSupprServeur implements Commande {
  private final String next;

  public CommandeSupprServeur(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  Integer ids = Integer.parseInt(req.getParameter("ids"));
	  DB_SERVEUR db = DBS.getInstance().getDB_SERVEUR();
	  db.deleteServeur(ids);

      return next;
  }

}

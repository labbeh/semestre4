

package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.Connexion;

/**
* @author: hugo labbé
*/
public class CommandeSupprConnexion implements Commande {
  private final String next;

  public CommandeSupprConnexion(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  Integer idu = Integer.parseInt(req.getParameter("idu"));
	  Integer ids = Integer.parseInt(req.getParameter("ids"));
	  TimeStamp datec = new TimeStamp(req.getParameter("datec"));
	  DB_CONNEXION db = DBS.getInstance().getDB_CONNEXION();
	  db.deleteConnexion(idu);

      return next;
  }

}

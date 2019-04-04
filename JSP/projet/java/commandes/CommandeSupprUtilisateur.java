

package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.Utilisateur;

/**
* @author: hugo labbé
*/
public class CommandeSupprUtilisateur implements Commande {
  private final String next;

  public CommandeSupprUtilisateur(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  Integer idu = Integer.parseInt(req.getParameter("idu"));
	  DB_UTILISATEUR db = DBS.getInstance().getDB_UTILISATEUR();
	  db.deleteUtilisateur(idu);

      return next;
  }

}
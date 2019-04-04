

package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.Utilisateur;

/**
* @author: hugo labbé
*/
public class CommandeUtilisateurMAJ implements Commande {
  private final String next;

  public CommandeUtilisateurMAJ(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  /*Integer idu = (Integer)req.getAttribute("idu");
	  DB_UTILISATEUR db = DBS.getInstance().getDB_UTILISATEUR();
	  db.deleteUtilisateur(idu);*/
      return next;
  }

}
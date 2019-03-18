package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.Utilisateur;

/**
* @author: hugo labbé
*/
public class CommandeUtils implements Commande {
  private final String next;

  public CommandeUtils(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();
	  List<Utilisateur> utils = db.getDB_UTILISATEUR().getUtilisateurs();
	  req.setAttribute("utils", utils);
      return next;
  }

}
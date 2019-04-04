package commandes;

import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.*;
// Cette cde recupere update un pdt:

public class CommandeUtilMAJValid implements Commande {
  private final String next;

  public CommandeUtilMAJValid(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();
	  
	  DB_UTILISATEUR db_util = db.getDB_UTILISATEUR();
	  int idu = Integer.parseInt(req.getParameter("idu"));
	  
	  Utilisateur util = db_util.getUtilisateur(idu);
	  
	  String nom = req.getParameter("nom");
	  String role = req.getParameter("role");
	  
	  util.setNom(nom);
	  util.setRole(role);
	  
	  db_util.updateUtilisateur(util);
	  req.setAttribute("util", util);

      return next;
  }

}

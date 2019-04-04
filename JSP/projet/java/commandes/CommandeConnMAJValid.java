package commandes;

import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.*;
// Cette cde recupere update un pdt:

public class CommandeConnMAJValid implements Commande {
  private final String next;

  public CommandeConnMAJValid(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();
	  
	  DB_CONNEXION db_conn = db.getDB_CONNEXION();
	  int idu = Integer.parseInt(req.getParameter("idu"));
	  int ids = Integer.parseInt(req.getParameter("ids"));
	  TimeStamp datec = new TimeStamp(req.getParameter("datec"));
	  
	  Connexion conn = db_conn.getConnexion(idu, ids, datec);
	  
	  String login =req.getParameter("login");
	  int duree = Integer.parseInt(req.getParameter("duree"));
	  
	  conn.setLogin(login);
	  conn.setDuree(duree);
	  
	  db.updateConnexion(conn);
	  req.setAttribute("conn", conn);
          return next;
  }

}

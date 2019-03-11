package pac;

import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde recupere un client:
// L'identifiant est dans la requete HTTP
//
public class CommandeClient implements Commande {
  private final String next;

  public CommandeClient(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DB db = DB.getInstance();
	  int ncli = Integer.parseInt(req.getParameter("id"));
	  Client c = db.getClient(ncli);
	  req.setAttribute("client", c);
          return next;
  }

}

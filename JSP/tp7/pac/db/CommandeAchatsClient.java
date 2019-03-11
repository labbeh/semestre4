package pac;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde recupere l'ensemble des achats d'un client donne:
// L'identifiant du client est stocké dans la requete HTTP
//
public class CommandeAchatsClient implements Commande {
  private final String next;

  public CommandeAchatsClient(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  int ncli = Integer.parseInt(req.getParameter("ncli"));
	  DB db = DB.getInstance();

	  Client client = db.getClient(ncli);
	  List<AchatCP> achats = db.getAchatsClientCP(ncli);

	  req.setAttribute("client", client);
	  req.setAttribute("achats", achats);
          return next;
  }

}

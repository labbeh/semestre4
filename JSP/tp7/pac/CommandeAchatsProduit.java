package pac;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde recupere l'ensemble des achats d'un produit donne:
//
public class CommandeAchatsProduit implements Commande {
  private final String next;

  public CommandeAchatsProduit(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  int np = Integer.parseInt(req.getParameter("np"));
	  DB db = DB.getInstance();

	  Produit produit = db.getProduit(np);
	  List<AchatCP> achats = db.getAchatsProduitCP(np);

	  req.setAttribute("produit", produit);
	  req.setAttribute("achats", achats);
          return next;
  }

}

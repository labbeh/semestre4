package pac;

import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde supprime un produit

public class CommandeSupprimerProduit implements Commande {
  private final String next;

  public CommandeSupprimerProduit(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DB db = DB.getInstance();
	  int np = Integer.parseInt(req.getParameter("np"));

	  Produit produit = db.getProduit(np);
	  db.deleteProduit(produit);

	  req.setAttribute("produit", produit);

      return next;
  }

}
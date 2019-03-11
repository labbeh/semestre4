package pac;

import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde recupere update un pdt:

public class CommandeProduitMAJValid implements Commande {
  private final String next;

  public CommandeProduitMAJValid(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DB db = DB.getInstance();
	  int np = Integer.parseInt(req.getParameter("np"));
	  int qs = Integer.parseInt(req.getParameter("qs"));
	  Produit produit = db.getProduit(np);
	  produit.setQs(qs);
	  db.updateProduit(produit);
	  req.setAttribute("produit", produit);
          return next;
  }

}

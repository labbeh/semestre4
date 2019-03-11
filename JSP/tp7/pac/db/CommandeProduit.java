package pac;

import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde recupere un pdt:
// Le numero de produit est dans la requete HTTP
//
public class CommandeProduit implements Commande {
  private final String next;

  public CommandeProduit(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DB db = DB.getInstance();
	  int np = Integer.parseInt(req.getParameter("np"));
	  Produit produit = db.getProduit(np);
	  req.setAttribute("produit", produit);
          return next;
  }

}

package pac;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde recupere l'ensemble des produits 

public class CommandeProduits implements Commande {
  private final String next;

  public CommandeProduits(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DB db = DB.getInstance();

	  List<Produit> produits = db.getProduits();
	  req.setAttribute("produits", produits);
          return next;
  }

}

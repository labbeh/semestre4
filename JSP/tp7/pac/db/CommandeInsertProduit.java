package pac;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde 
//
public class CommandeInsertProduit implements Commande {
  private final String next;

  public CommandeInsertProduit(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DB db = DB.getInstance();

	  int    np   = Integer.parseInt(req.getParameter("np"));
    String lib  = req.getParameter("lib");
    String coul = req.getParameter("coul");
    int    qs   = Integer.parseInt(req.getParameter("qs"));

    Produit produit = new Produit(np, lib, coul, qs);
    db.insertProduit(produit);

    req.setAttribute("produit", produit);
    return next;
  }

}
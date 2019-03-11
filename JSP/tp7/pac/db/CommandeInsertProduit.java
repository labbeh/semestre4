package pac;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde recupere l'ensemble des clients:
//
public class CommandeInsertProduit implements Commande {
  private final String next;

  public CommandeClients(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DB db = DB.getInstance();
	  int np;
    String lib;
    String coul;
    int qs;
          return next;
  }

}
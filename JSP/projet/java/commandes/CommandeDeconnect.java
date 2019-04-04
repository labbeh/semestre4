package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
* Permet de déconnecter la session en cours et renvoie sur login.jsp
*/
public class CommandeDeconnect implements Commande {
  private String next;

  public CommandeDeconnect(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {

	  //variable contenant le nom de la JSP qui devra etre activee ensuite
	  //String cible = next;

	  // on récupère la session s'il y en a une en cours	  
	  HttpSession session = req.getSession(false);

	  if(session != null)
	  	session.invalidate();

      return next;
  }

}
package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CommandeErreurDroit implements Commande {
  private String next;

  public CommandeErreurDroit(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {

	  //variable contenant le nom de la JSP qui devra etre activee ensuite
	  String cible = next;
	  HttpSession session = req.getSession(true);

	  cible = "login.jsp";
	  req.setAttribute("erreur", "ERREUR DE DROIT");
	  
      return cible;
  }

}

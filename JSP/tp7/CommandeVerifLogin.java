package pac;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde permet de valider la connexion (ou non !)
// Si la connexion est validÃ©e, la session est initialisÃ©e avec le
// droit associÃ© Ã  l'utilisateur connectÃ©.
// La mÃ©thode execute de cette commande contrairement aux autres 
// ne renvoie pas toujours le mÃªme nom de JSP.
//
public class CommandeVerifLogin implements Commande {
  private String next;

  public CommandeVerifLogin(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {

	  //variable contenant le nom de la JSP qui devra etre activee ensuite
	  String cible = next;

	  //A COMPLETER
	  //analyse du login et du mot de passe stockes dans la requete
	  //Si erreur sur login ou mot de passe, alors faire en sorte que login.jsp soit activee 
	  //(via le controleur) en lui transmettant eventuellement un message d'erreur
	  //si login et mot de passe sont ok, alors il faut creer une nouvelle session et stocker dedans
	  //le droit de la personne connectee. Dans ce cas, c'est accueil.jsp qui doit etre activee.
	  //NB : on accepte deux comptes : user/userpwd et admin/adminpwd.
          return cible;
  }

}

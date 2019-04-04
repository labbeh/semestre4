/*Lorsque la liste des utilisateurs est affichée (voir fonctionnalités précédentes)
faire en sorte qu'un clic sur un nom d'utilisateur génère une page avec la liste
des serveurs auxquels il s'est connecté. Pour chaque connexion de l'utilisateur
on affichera le nom du serveur concerné, la date et la durée de la connexion
et enfin le login utilisé.*/
package commandes;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import bdd.*;
//import beans.Utilisateur;

/**
* @author: hugo labbé
*/
public class CommandeUtil implements Commande {
  private final String next;

  public CommandeUtil(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  /*DBS db = DBS.getInstance();
	  List<Utilisateur> util = db.getDB_UTILISATEUR().getUtilisateurs();
	  req.setAttribute("util", util);*/
      return next;
  }
}
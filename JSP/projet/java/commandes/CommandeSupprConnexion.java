

package commandes;

import java.text.*;
import java.util.Date;
import java.sql.Timestamp;
import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.Connexion;

/**
* @author: hugo labbé
*/
public class CommandeSupprConnexion implements Commande {
  private final String next;

  public CommandeSupprConnexion(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  Integer idu = Integer.parseInt(req.getParameter("idu"));
	  Integer ids = Integer.parseInt(req.getParameter("ids"));
	  
	  String datec = req.getParameter("datec");
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss"); 
	  Date date = dateFormat.parse(datec); 
	  long time = date.getTime(); 
	  Timestamp temps = new Timestamp(time);
	  
	  DB_CONNEXION db = DBS.getInstance().getDB_CONNEXION();
	  db.deleteConnexion(idu, ids, temps);

      return next;
  }

}

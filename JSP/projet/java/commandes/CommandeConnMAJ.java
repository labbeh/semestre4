package commandes;

import java.text.*;
import java.util.Date;
import java.sql.Timestamp;
import javax.servlet.*;
import javax.servlet.http.*;
import bdd.*;
import beans.*;

// Cette cde recupere update un pdt:
public class CommandeConnMAJ implements Commande {
  private final String next;

  public CommandeConnMAJ(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();

	  DB_CONNEXION db_conn = db.getDB_CONNEXION();

	  int idu = Integer.parseInt(req.getParameter("idu"));
	  int ids = Integer.parseInt(req.getParameter("ids"));
	  String datec = req.getParameter("datec");
	  
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
	  Date date = dateFormat.parse(datec); 
	  long time = date.getTime(); 
	  Timestamp temps = new Timestamp(time);

	  Connexion conn = db_conn.getConnexion(idu, ids, temps);

	  req.setAttribute("connexion", conn);

       return next;
  }

}

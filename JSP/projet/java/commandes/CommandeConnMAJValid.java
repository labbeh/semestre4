package commandes;

import javax.servlet.*;
import java.util.Date;
import javax.servlet.http.*;
import bdd.*;
import beans.*;
import java.text.*;
import java.sql.Timestamp;
// Cette cde recupere update un pdt:

public class CommandeConnMAJValid implements Commande {
  private final String next;

  public CommandeConnMAJValid(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
	  DBS db = DBS.getInstance();
	  
	  DB_CONNEXION db_conn = db.getDB_CONNEXION();
	  int idu = Integer.parseInt(req.getParameter("idu"));
	  int ids = Integer.parseInt(req.getParameter("ids"));
	  String datec = req.getParameter("datec");
	  
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss"); 
	  Date date = dateFormat.parse(datec); 
	  long time = date.getTime(); 
	  Timestamp temps = new Timestamp(time);
	  
	  Connexion conn = db_conn.getConnexion(idu, ids, temps);
	  
	  String login =req.getParameter("login");
	  int duree = Integer.parseInt(req.getParameter("duree"));
	  
	  conn.setLogin(login);
	  conn.setDuree(duree);
	  
	  db_conn.updateConnexion(conn);
	  req.setAttribute("conn", conn);
          return next;
  }

}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ControleSession extends HttpServlet {
    
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		
		//on recupere la session courante s'il y en a une
		HttpSession session = req.getSession(false);

		if(session == null)
			req.setAttribute("msgErr", "aucune session en cours");

		//on recupere le droit dans la session (s'il existe)
		Integer droit = (Integer) req.getAttribute("droit");

		//si le droit n'existe pas dans la session, 
		if(droit == null){
			// 1. on ajoute un attribut "erreur" dans la requete avec
			//    comme valeur "true" pour indiquer que la servlet
			//    a detecte une anomalie
			req.setAttribute("erreur", true);

			// 2. on renvoie vers login.jsp avec un include dynamique
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, res);
		}
		else{
			//sinon (le droit existe dans la session) on ajoute dans la requete un attribut
			//"erreur" avec comme valeur "false" pour indiquer que la session courante est valide
			//ce qui signifie que la servlet validation a auparavant valide la connexion.
			req.setAttribute("erreur", false);

			RequestDispatcher dispatcher = req.getRequestDispatcher("suite.jsp");
			dispatcher.forward(req, res);
			//res.sendRedirect("suite.jsp");
		}
	} //fin méthode service

	
} //fin de la classe
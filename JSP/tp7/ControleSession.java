import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.lang.reflect.Constructor;
import pac.Droits;

public class ControleSession extends HttpServlet {
	
	//cette méthode doit réaliser un "forward" vers la JSP 
	//dont le nom est passé en dernier paramètre
	private void forwardJSP(HttpServletRequest req, HttpServletResponse res,String pageJSP) throws ServletException, IOException {
		//on indique d'abord au controleur qu'il y a eu forward
		req.setAttribute("forwardOK",true);
		//On fait le forward : A COMPLETER
		RequestDispatcher dispatcher = req.getRequestDispatcher(pageJSP);
		dispatcher.forward(req, res);
	}

	//cette méthode permet de récupérer le droit 
	//de la personne connectée dans la session
	//NB : il peut être "null" si personne n'est connecté
	private Integer getDroitSession(HttpServletRequest req) {
		Integer droitUtil = 0;
		droitUtil = (Integer)req.getAttribute("droitSession");
		return droitUtil;
	}

	//cette méthode renvoie vrai si le droit d'accès de la commande devant
	//être prise en charge par le controleur (attribut droitCmd dans la requête)
	//correspond au droit ADMIN (voir constante dans Droits.java)
	private boolean evaluerDroitCommande(HttpServletRequest req) {
		boolean droitCommande = false;
		droitCommande = getDroitSession(req) != null && getDroitSession(req).equals(DROIT_UTIL_ADMIN);

		return droitCommande;
	}

	//Le contenu de la méthode service ne doit pas être modifié.
	//Lorsque les 3 méthodes précédentes seront complétées, il faudra
	//juste enlever les commentaires /* ... */ de la méthode service.
	public void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException { 
		
		/* COMMENTAIRES A ENLEVER QUAND LES 3 METHODES PRECEDENTES SONT COMPLETEES

		Integer droitUtil = getDroitSession(req);
		
		if ( droitUtil == null) {
			//personne n'est connecté
			//on renvoie vers la page de login			
			forwardJSP(req,res,"/login.jsp");
		}
		else {
			//il y a quelqu'un de connecté.
			//il vaut vérifier que son droit d'accès 
			//est compatible avec celui de la commande demandée
			boolean droitAdmin = evaluerDroitCommande(req);
			if (droitAdmin) {
				//C'est une commande à usage restreint (admin)
				if (droitUtil != Droits.DROIT_UTIL_ADMIN) {
					//l'utilisateur n'a pas le droit admin
					//on renvoie vers la page d'erreur droit
					forwardJSP(req,res,Controleur.JSP_ERREUR_DROIT);
				}
			} //fin if droitCmd = ADMIN
		} //fin else droitUtil != null

		*/

	} //fin méthode service

	
} //fin de la classe


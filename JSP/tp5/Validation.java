import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Validation extends HttpServlet {
    
    @Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		
		//on recupere le nom et le mot de passe dans la requete recue
		String nom = req.getParameter("nom");
 	  	String pass = req.getParameter("pass");

	  	String cible=""; //nom de la JSP qui va etre activee ensuite

	  	if(nom.length()==0){
			//aucun nom n'a ete saisi
			//on stocker le message d'erreur dans la requete
	  		req.setAttribute("msgErr", "Saisir nom et pass");

			//on memorise que l'on va ensuite activer login.jsp (voir fin de la mÃ©thode)
			cible = "login.jsp";
	  	}
	  	else {
			//Au moins un nom a ete saisi
			//on teste les deux couples login/mot de passe possibles

		  	if(nom.equalsIgnoreCase("user") && pass.equals("userpwd")){
				//on cree une nouvelle session
				HttpSession session = req.getSession(true);		  	

				//on stocke dans la session un paramÃ¨tre droit = 1 (mode consultation)
				session.setAttribute("droit", 1);

				//on memorise que l'on va ensuite activer suite.jsp
				cible = "suite.jsp";
		  	}
		  	else{
				if(nom.equalsIgnoreCase("admin") && pass.equals("adminpwd")){
					//idem que dans le cas precedent mais avec droit = 2 (mode administration)
					HttpSession session = req.getSession(true);
					session.setAttribute("droit", 2);
					cible ="suite.jsp";

 		        }
				else {
					//le couple login/mot de passe n'est pas bon
					//on stocke le message d'erreur dans la requete
					req.setAttribute("msgErr", "Erreur: nom et pass invalide");

					//on indique que l'on va ensuite activer login.jsp
					cible = "login.jsp";

				}
			} 
	  	}

		//On delegue le controle Ã  la JSP dont le nom est dans cible
		RequestDispatcher rd1 = req.getRequestDispatcher(cible); 
		rd1.forward(req, res); 
		
	} //fin mÃ©thode service

	
} //fin de la classe


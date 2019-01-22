import annexe.Personne;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ServletPersonne extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		//création d'un objet instance de la classe Personne
		Personne personne = new Personne("GARY", "Jean", 56);

		//affichage dans la page HTML générée du contenu de l'objet
		out.println("<HTML>"); 
        out.println("<HEAD><TITLE>Hello </TITLE></HEAD>"); 
        out.println("<BODY>");

		out.println("<p>");

		out.println(personne.getNom() +" "+ personne.getPrenom() +" "+ personne.getAge());

		out.println("</p>");

		out.println("</BODY></HTML>"); 
    }
}
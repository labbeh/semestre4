import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloClass extends HttpServlet {

	public void doGet( HttpServletRequest  req , HttpServletResponse  res ) throws ServletException, IOException {

		res.setContentType("text/html"); 

		PrintWriter  out  = res.getWriter();

		out.println("<form method=\"get\" action=\"bonjour\">");

		out.println("Un premier mot: <input type=\"text\" name=\"mot\"/> <br>");
		out.println("Un autre mot?<input type=\"text\" name=\"mot\"/> <br>");
		out.println("Un 3ème mot: <input type=\"text\" name=\"mot\" /> <br>");
		out.println("autre truc pas mot: <input type=\"text\" name=\"autre\" /> <br>");

		out.println("<input type=\"submit\"/>");
		out.println("</form>");

		String[] mots = req.getParameterValues("mot");

		if(mots != null){
			for(int i=0; i<mots.length; i++){
				out.println(mots[i]);
				out.println("<hr/>");
			}
		}
		else out.println("pas de mots <br />");

		String autre = req.getParameter("autre");

		if(autre != null && !autre.equals(""))
			out.println("<p style=\"color: red\"> pas dans mot:  " +autre+ "</p> " );
	}
}
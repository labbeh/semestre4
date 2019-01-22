import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CompteurClass extends HttpServlet {
    int compteur = 0;

    static int compteurGlobal = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

		compteur++;
		out.println("compteur global: " +(++compteurGlobal));
        out.println("compteur= "+ compteur);
    }
}
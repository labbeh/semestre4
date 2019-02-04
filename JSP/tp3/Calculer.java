import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Calculer extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    int op1, op2, result;
    String operateur;
   
    out.println("<html>");
    out.println("<body>");

    operateur = req.getParameter("op");

    if(operateur == null) {
      erreur("ERREUR: choisissez le type d'opération à effectuer", out);
      return;
    }

    try {
      op1 = Integer.parseInt(req.getParameter("operande1"));
      op2 = Integer.parseInt(req.getParameter("operande2"));
    }
    catch(NumberFormatException evt){
      erreur("ERREUR: vérifiez les opérandes", out);
      return;
    }


    if     (operateur.equals("Addition"      )) {result = op1 + op2; operateur = " + ";}
    else if(operateur.equals("Soustraction"  )) {result = op1 - op2; operateur = " - ";}
    else if(operateur.equals("Multiplication")) {result = op1 * op2; operateur = " * ";}
    else {
      if(op2 == 0) {
        erreur("ERREUR: Vous essayez de diviser par 0 !", out);
        return;
      }
      operateur = " div ";
      result = op1 / op2;
    }
    out.println("<p>" + op1 + operateur + op2 + " = " + result + "</p>");

    out.println("</body>");
    out.println("</html>");
  }

  private static void erreur(String msg, PrintWriter out) {
    out.println("<p>" +msg+ "</p>");
    out.println("</body>");
    out.println("</html>");
  }
}
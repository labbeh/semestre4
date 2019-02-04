import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Controle extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		int nbInstruments = 0;

		try {
			nbInstruments = Integer.parseInt(req.getParameter("nbr"));
		}
		catch(Exception evt){
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.include(req, res);
			return;
		}

		if(nbInstruments == 0) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/dommage.jsp");
			dispatcher.forward(req, res);
		}

		else if(nbInstruments > 0) {
			req.setAttribute("nbInstr",nbInstruments);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/saisieInst.jsp");
			dispatcher.forward(req, res);
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("form.html");
	}
}
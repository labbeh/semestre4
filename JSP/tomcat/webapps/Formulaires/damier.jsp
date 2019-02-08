<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Enumeration" %>

<html>
	<head>
		<title>Damier</title>
	</head>

	<body>

		<%
		int nbLig = -1;
		int nbCol = -1;

		String noir="black";
		String blanc="white";
		String couleur="";

		out.println("<form method=\"get\" action=\"damier.jsp\">");

		out.println("Nombre de ligne  : <input type=\"text\" name=\"nbLig\"/> <br>");
		out.println("Nombre de colonne: <input type=\"text\" name=\"nbCol\"/> <br>");

		out.println("<input type=\"submit\"/>");
		out.println("</form>");

		Enumeration<String> en = request.getParameterNames();

			while (en.hasMoreElements()) {
				String name = en.nextElement();
				String values[] = request.getParameterValues(name);

				if (values != null) {
					
					try {
						if(name.equals("nbLig"))
							nbLig = Integer.parseInt(values[0]);
					
						else if(name.equals("nbCol"))
							nbCol = Integer.parseInt(values[0]);

					}
					catch(Exception evt){out.println("<p> Erreur de paramètres </p>");}
				}
			}

		if(nbLig != -1 && nbCol != -1)
		{
			

			out.println("<h3>Damier de "+nbLig+" lignes par "+nbCol+" colonnes</h3><p>");
			out.println("<table border='1'>");

			for (int ligne=1;ligne<=nbLig;ligne++) {
				out.println("<tr>");
				if ((ligne%2)==0) couleur=noir; else couleur=blanc;
				for (int colonne=1;colonne<=nbCol;colonne++) {
					out.println("<td bgcolor='"+couleur+"' width='30px' height='30px'>&nbsp;</td>");
					if (couleur.equals(blanc)) couleur=noir; else couleur=blanc;
				}
				out.println("</tr>");
			}
			out.println("</table>");
		
		}
		%>

	</body>
</html>

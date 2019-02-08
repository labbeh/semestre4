<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<title>Damier</title>
		<link rel="stylesheet" type="text/css" href="styleDamier.css">
	</head>
	
	<body>
		<%
		 String[] nomsVilles =  { 
		 						  "Marseille", "Lyon", "Grenoble", "Nantes", "Strasbourg",
		 						  "Montpellier", "Bordeaux","Paris", "Lille", "Rennes",
		 						  "Angers", "Reims", "Le Havre", "Toulouse", "Saint-Etienne", "Toulon",
		 						  "Dijon", "Brest", "Nimes", "Nice"
		 						};

		 Arrays.sort(nomsVilles);

		%>
		<h1>Damier de 10 lignes par 10 colonnes</h1>

		<table style="border: 1px solid red">
			<%
				String type = new String();

				for(int cptLig=0; cptLig<10; cptLig++){
					out.println("<tr style=\"border: 1px solid black\">");

					if(cptLig % 2 == 0) type = "blanc" ;
					else 				type = "noir";

					for (int cptCol=0; cptCol<10; cptCol++){
						

						out.println("<td style=\"border: 1px solid red\" " +"class= " +type+ ">" + "</td>");

						if(type.equals("blanc")) type = "noir" ;
						else 					 type = "blanc";
					}

					out.println("</tr>");
				}				
			%>
		</table>
	</body>
</html>
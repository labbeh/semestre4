<%@ page import="java.util.*" %>
<%@ page import="annexe.Personne" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<title>Personne</title>
	</head>
	
	<body>
		<%!

		public int somme(int a, int b){
			return a+b;
		}

		int x = 22;
		int y = 10;

		%>

		<% 
		System.out.println("appli 1 lancée");
		Date d = new Date();
		out.println(" Nous sommes le "+ d);
		%>

		<%
		out.println("<br />");
		out.println(x + " + " + y + " = " +somme(x,y));

		x++;
		y++;

		out.println("<br />");

		Personne p1 = new Personne("Paccard", "Patrick", 20);
		Personne p2 = new Personne("Deville", "Francis", 45);
		Personne p3 = new Personne("Rialto", "Clémence", 62);

		Personne[] tabPers = new Personne[3];
		tabPers[0] = p1;
		tabPers[1] = p2;
		tabPers[2] = p3;

		%>

		<%= p1 %>
		<br />
		<br />

		<table style="border: 1px solid red">
			<%
				for(Personne pers: tabPers){
					out.println("<tr style=\"border: 1px solid red\">");

					out.println("<td style=\"border: 1px solid red\">" +pers.getNom   ()+ "</td>");
					out.println("<td style=\"border: 1px solid red\">" +pers.getPrenom()+ "</td>");
					out.println("<td style=\"border: 1px solid red\">" +pers.getAge   ()+ "</td>");

					out.println("</tr>");
				}
			%>
		</table>
	</body>
</html>
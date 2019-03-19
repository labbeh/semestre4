<%-- ======================================
		utilisateurs.jsp
========================================= --%>

<%@ page import="java.util.*,beans.*" %>

<% String titre = "LISTE DES CONNEXIONS"; %>

<%@include file="ihm/miseEnPageSUC1.jsp" %>


<%	// ==============  CORPS =================================================
	List<Connexion> connexions = (List<Connexion>)request.getAttribute("connexions");
	String coul="lignePaire";

	out.println("<table>");
	out.println("<tr class=\"enteteTableau\"> <th>idu</th> <th>ids</th> <th>datec</th> <th>login</th> <th>duree</th> </tr>");

	for (Connexion c : connexions) {
		coul=(coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";
	    out.println("<tr class=\""+coul+"\">");

		String href="controleur?cmd=connexion&idu="+c.getIdu();

		out.println("<td><a href="+href+">"+c.getIdu()+"</a></td>");
		out.println("<td>"+c.getIds()+"</td>");
		out.println("<td>"+c.getDatecString()+"</td>");
		out.println("<td>"+c.getLogin()+"</td>");
		out.println("<td>"+c.getDuree()+"</td>");

		out.println("</tr>");
	}

	out.println("</table></center>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPageSUC2.jsp" %>





<%-- ======================================
		utilisateurs.jsp
========================================= --%>

<%@ page import="java.util.*,beans.*" %>

<% String titre = "LISTE DES SERVEURS"; %>

<%@include file="ihm/miseEnPageSUC1.jsp" %>


<%	// ==============  CORPS =================================================
	List<Serveur> serveurs = (List<Serveur>)request.getAttribute("serveurs");
	String coul="lignePaire";

	out.println("<table>");
	out.println("<tr class=\"enteteTableau\"><th>ids</th><th>nom</th><th>os</th></tr>");

	for (Serveur s : serveurs) {
		coul=(coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";
	    out.println("<tr class=\""+coul+"\">");

		String href="controleur?cmd=serveur&ids="+s.getIds();

		out.println("<td><a href="+href+">"+s.getIds()+"</a></td>");
		out.println("<td>"+s.getNom()+"</td>");
		out.println("<td>"+s.getOs()+"</td>");
		out.println("</tr>");
	}

	out.println("</table></center>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPageSUC2.jsp" %>





<%-- ======================================
		clients.jsp
========================================= --%>

<%@ page import="java.util.*,pac.*" %>

<% String titre = "LISTE DES CLIENTS"; %>

<%@include file="ihm/miseEnPagePAC1.jsp" %>


<%	// ==============  CORPS =================================================
	List<Client> clients = (List<Client>)request.getAttribute("clients");
	String coul="lignePaire";
	out.println("<table>");
	out.println("<tr class=\"enteteTableau\"><th>num</th><th>nom</th><th>adresse</th></tr>");
	for (Client c : clients) {
		coul=(coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";
	        out.println("<tr class=\""+coul+"\">");
		String href="controleur?cmd=achats&ncli="+c.getNcli();
		out.println("<td><a href="+href+">"+c.getNcli()+"</a></td>");
		out.println("<td>"+c.getNom()+"</td>");
		out.println("<td>"+c.getAdr()+"</td>");
		out.println("</tr>");
	}
	out.println("</table></center>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPagePAC2.jsp" %>





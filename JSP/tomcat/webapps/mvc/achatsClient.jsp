<%-- ======================================
		achatsClient.jsp              
========================================= --%>

<%@ page import="java.util.*,pac.*" %>

<% Client client = (Client)request.getAttribute("client"); %>
<% String titre="LISTE DES ACHATS du client "+client.getNom(); %>

<%@include file="ihm/miseEnPagePAC1.jsp" %>

<%	// ==============  CORPS =================================================
	List<AchatCP> achats = (List<AchatCP>)request.getAttribute("achats");
	if(achats==null || achats.size()==0) out.println("pas d'achat");
	String coul="lignePaire";
	out.println("<table>");
	out.println("<tr class=\"enteteTableau\"><th>np</th><th>lib</th><th>qa</th></tr>");
	for (AchatCP a : achats) {
		coul=(coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";
	        out.println("<tr class=\""+coul+"\">");
		out.println("<td>"+a.getNp()+"</td>");
		out.println("<td>"+a.getLib()+"</td>");
		out.println("<td>"+a.getQa()+"</td>");
		out.println("</tr>");
	}
	out.println("</table>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPagePAC2.jsp" %>




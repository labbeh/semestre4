<%-- ======================================
		produitMAJValid.jsp
========================================= --%>
<%@ page import="java.util.*,pac.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<% String titre="AJOUT D'UN PRODUIT"; %>

<%@include file="ihm/miseEnPagePAC1.jsp" %>

<%	// ==============  CORPS =================================================
	Produit p = (Produit)request.getAttribute("produit");
	out.println("<h3>Produit ajouté</h3>");
	out.println("<table>");
	out.println("	<tr class=\"enteteTableau\"><th>np</th><th>lib</th><th>coul</th><th>qs</th></tr>");
	out.println("	<tr class=\"lignePaire\">");
	out.println("	<td>"+p.getNp()+"</td>");
	out.println("	<td>"+p.getLib()+"</td>");
	out.println("	<td>"+p.getCouleur()+"</td>");
	out.println("	<td>"+p.getQs()+"</td>");
	out.println("	</tr>");
	out.println("</table>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPagePAC2.jsp" %>



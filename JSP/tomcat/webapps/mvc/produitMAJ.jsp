<%-- ======================================
		produitMAJ.jsp
========================================= --%>
<%@ page import="java.util.*,pac.*" %>

<% String titre="MISE A JOUR PRODUIT"; %>

<%@include file="ihm/miseEnPagePAC1.jsp" %>

<%	// ==============  CORPS =================================================
	Produit p = (Produit)request.getAttribute("produit");
	out.println("<center><h3>mise a jour QS</h3></center>");
	out.println("<center><table>");
	out.println("	<tr class=\"enteteTableau\"><th>np</th><th>lib</th><th>coul</th><th>qs</th></tr>");
	out.println("	<form method=get action=controleur>");
	//out.println("	<form method=get action=echo.jsp>");
	out.println("	<tr class =\"lignePaire\">");
	out.println("	<td>"+p.getNp()+"</td>");
	out.println("	<td>"+p.getLib()+"</td>");
	out.println("	<td>"+p.getCouleur()+"</td>");
	out.println("	<td><input name=qs value="+p.getQs()+"></td>");
	out.println("	</tr>");
	out.println("	<tr>");
	out.println("	<td colspan=4><input type=submit value=valider></td>");
	out.println("	</tr>");
	out.println("	<input type=hidden name=np value="+p.getNp()+">");
	out.println("	<input type=hidden name=cmd value=produitMAJValid>");
	out.println("	</form>");
	out.println("</table></center>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPagePAC2.jsp" %>



<%-- ======================================
		produitMAJ.jsp
========================================= --%>
<%@ page import="java.util.*,beans.*" %>

<% String titre="MISE A JOUR UTILISATEUR"; %>

<%@include file="ihm/miseEnPageSUC1.jsp" %>

<%	// ==============  CORPS =================================================
	Utilisateur u = (Utilisateur)request.getAttribute("utilisateur");

	out.println("<center><h3>mise a jour QS</h3></center>");
	out.println("<center><table>");

	out.println("	<tr class=\"enteteTableau\"><th>idu</th><th>nom</th><th>role</th></tr>");
	out.println("	<form method=get action=controleur>");
	//out.println("	<form method=get action=echo.jsp>");
	out.println("	<tr class =\"lignePaire\">");
	out.println("	<td>"+u.getIdu()+"</td>");
	out.println("	<td>"+u.getNom()+"</td>");
	out.println("	<td>"+u.getRole()+"</td>");
	out.println("	<td><input name=nom value="+u.getNom()+"></td>");
	out.println("	<td><input name=nom value="+u.getRole()+"></td>");
	out.println("	</tr>");
	out.println("	<tr>");
	out.println("	<td colspan=4><input type=submit value=valider></td>");
	out.println("	</tr>");

	out.println("	<input type=hidden name=np value="+u.getIdu()+">");
	out.println("	<input type=hidden name=cmd value=utilMAJValid>");

	out.println("	</form>");

	out.println("</table></center>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPageSUC2.jsp" %>



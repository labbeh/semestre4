<%-- ======================================
		servMAJ.jsp
========================================= --%>
<%@ page import="java.util.*,beans.*" %>

<% String titre="MISE A JOUR SERVEUR"; %>

<%@include file="ihm/miseEnPageSUC1.jsp" %>

<%	// ==============  CORPS =================================================
	Serveur s = (Serveur)request.getAttribute("serveur");

	out.println("<center><h3>mise a jour SERVEUR</h3></center>");
	out.println("<center><table>");

	out.println("	<tr class=\"enteteTableau\"><th>ids</th><th>nom</th><th>os</th></tr>");
	out.println("	<form method=get action=controleur>");
	//out.println("	<form method=get action=echo.jsp>");
	out.println("	<tr class =\"lignePaire\">");
	out.println("	<td>"+s.getIds()+"</td>");
	out.println("	<td>"+s.getNom()+"</td>");
	out.println("	<td>"+s.getOs()+"</td>");
	out.println("	<td><input name=nom value="+s.getNom()+"></td>");
	out.println("	<td><input name=os value="+s.getOs()+"></td>");
	out.println("	</tr>");
	out.println("	<tr>");
	out.println("	<td colspan=4><input type=submit value=valider></td>");
	out.println("	</tr>");

	out.println("	<input type=hidden name=np value="+s.getIds()+">");
	out.println("	<input type=hidden name=cmd value=utilMAJValid>");

	out.println("	</form>");

	out.println("</table></center>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPageSUC2.jsp" %>



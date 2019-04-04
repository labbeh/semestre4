<%-- ======================================
		connMAJ.jsp
========================================= --%>
<%@ page import="java.util.*,beans.*" %>

<% String titre="MISE A JOUR CONNEXION"; %>

<%@include file="ihm/miseEnPageSUC1.jsp" %>

<%	// ==============  CORPS =================================================
	Connexion c = (Connexion)request.getAttribute("connexion");

	out.println("<center><h3>mise a jour CONNEXION</h3></center>");
	out.println("<center><table>");

	out.println("	<tr class=\"enteteTableau\"><th>idu</th><th>ids</th><th>datec</th> <th>login</th> <th>duree</th></tr>");
	out.println("	<form method=get action=controleur>");
	//out.println("	<form method=get action=echo.jsp>");
	out.println("	<tr class =\"lignePaire\">");
	out.println("	<td>"+c.getIdu()+"</td>");
	out.println("	<td>"+c.getIds()+"</td>");
	out.println("	<td>"+c.getDatec()+"</td>");
	out.println("	<td>"+c.getLogin()+"</td>");
	out.println("	<td>"+c.getDuree()+"</td>");
	out.println("	<td><input name=login value="+c.getLogin()+"></td>");
	out.println("	<td><input name=duree value="+c.getDuree()+"></td>");
	out.println("	</tr>");
	out.println("	<tr>");
	out.println("	<td colspan=4><input type=submit value=valider></td>");
	out.println("	</tr>");

	out.println("	<input type=hidden name=np value="+c.getIdu()+">");
	out.println("	<input type=hidden name=cmd value=connMAJValid>");

	out.println("	</form>");

	out.println("</table></center>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPageSUC2.jsp" %>



<%-- ======================================
		utilisateursMAJ.jsp
========================================= --%>
<%@ page import="java.util.*,beans.*" %>

<% String titre="LISTE DES UTILISATEURS"; %>

<%@include file="ihm/miseEnPageSUC1.jsp" %>

<%	// ==============  CORPS =================================================
	List<Utilisateur> utilisateurs = (List<Utilisateur>)request.getAttribute("utils");
	String coul="lignePaire";

	out.println("<table>");
	out.println("<tr class=\"enteteTableau\"><th>idu</th><th>nom</th><th>role</th></tr>");

	for (Utilisateur u : utilisateurs) {

		coul=(coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";
	    out.println("<tr class=\""+coul+"\">");

		String href="controleur?cmd=utilisateurMAJ&idu="+u.getIdu();

		out.println("<td><a href="+href+">"+u.getIdu()+"</a></td>");
		out.println("<td>"+u.getNom()+"</td>");
		out.println("<td>"+u.getRole()+"</td>");

		href = "controleur?cmd=supprUtilisateur&idu="+u.getIdu();

		out.println("<td><a href=\""+href+"\">supprimer</a></td>");
		out.println("</tr>");
	}
	out.println("</table>");
	// ==============  CORPS =================================================
%>

<%@include file="ihm/miseEnPageSUC2.jsp" %>




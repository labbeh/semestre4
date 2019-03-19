<%@ page import="java.util.*" %>
<%
	String message = "coucou";
	out.println("script1:" + message +"<br>");


	String idSession = session.getId();
	out.println("Id session:" + idSession + "<br>");

	long debut = session.getCreationTime();
	java.util.Date date_debut = new java.util.Date(debut);
	out.println("date de creation:" + date_debut + "<br>");

	int duree = session.getMaxInactiveInterval(); 
	int mn = duree/60;
	out.println("duree:" + mn + "mn<br>");

	session.setAttribute("message",message);

	Enumeration e = session.getAttributeNames();
	while (e.hasMoreElements()) {
		out.println(e.nextElement()+"<br>");
	}

	out.println("<a href=\"script2.jsp\"> script2</a>");
%>	

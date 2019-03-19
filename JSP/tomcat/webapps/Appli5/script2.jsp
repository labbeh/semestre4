<%
	String idSession = session.getId();
	out.println("Id session:" + idSession + "<br>");

	String message="";
	message=(String)session.getAttribute("message");
	out.println("script2:" + message);

%>	

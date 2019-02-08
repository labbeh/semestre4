<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Enumeration" %>
<html>
	<head>
		<title>Bonjour</title>
	</head>
	
	<body>
		

		<%
			out.println(request.getQueryString());
			out.println("<hr/>");
			out.println("Request Parameters:<br>\n");

			Enumeration<String> en = request.getParameterNames();
			while (en.hasMoreElements()) {
				   String name = en.nextElement();
				   String values[] = request.getParameterValues(name);
				   if (values != null) {
					    for (int i = 0; i < values.length; i++) {
					           out.println(name + "[" + i + "]= " + values[i]+"<br/>\n");
					    } 
				   }
				   else out.println("c'est null'");
			}
			System.out.println("Enum: " +en.getClass().getName());
		%>
	</body>
</html>

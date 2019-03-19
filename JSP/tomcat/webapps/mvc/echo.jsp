<%@ page import="java.util.Enumeration" %>

<%
        out.println(request.getQueryString());
        out.println("<hr>");
        out.println("Request Parameters:<br>\n");

        Enumeration en = request.getParameterNames();
        	while (en.hasMoreElements()) {
        	       String name = (String) en.nextElement();
               	       String values[] = request.getParameterValues(name);
               	       if (values != null) {
               	            for (int i = 0; i < values.length; i++) {
               	                   out.println(name + "[" + i + "]= " + values[i]+"<br>\n");
               	            } 
               	       }
               	}
%>


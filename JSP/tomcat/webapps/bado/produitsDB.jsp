<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="pac.*" %>

<%
	out.println("<table border width=200 bgcolor=\"#ffffee\">");
	out.println("<th>np</th><th>lib</th><th>qs</th>");
	DB dataBase = DB.getInstance();
	
	List<Produit> produits = dataBase.getProduits();
	
	for(Produit p : produits){
		out.println("<tr>");
		
		int np = p.getNp();
		String lib = p.getLib();
		int qs = p.getQs();
		
		out.println("<td>"+np+"</td><td>"+lib+"</td><td>"+qs+"</td>");
		out.println("<tr>");
	}
	
	out.println("</table>");
%>

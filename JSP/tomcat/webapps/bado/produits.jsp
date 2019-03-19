<%@ page import="java.sql.*" %>

<%
	Class.forName ("org.postgresql.Driver");
	Connection cnx = DriverManager.getConnection ("jdbc:postgresql://woody/lh150094","lh150094" , "phppasswd"); // A MODIFIER
	Statement st = cnx.createStatement();
	ResultSet rs = st.executeQuery("SELECT np,lib,qs FROM produit order by np");

	out.println("<table border width=200 bgcolor=\"#ffffee\">");
	out.println("<th>np</th><th>lib</th><th>qs</th>");
	while(rs.next()){
		out.println("<tr>");
		int np = rs.getInt("np");
		String lib = rs.getString("lib");
		int qs = rs.getInt("qs");
		out.println("<td>"+np+"</td><td>"+lib+"</td><td>"+qs+"</td>");
		out.println("<tr>");
	    }
	out.println("</table>");
	rs.close();
	cnx.close();
%>

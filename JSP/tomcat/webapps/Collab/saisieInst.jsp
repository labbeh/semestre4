<%@ page contentType="text/html; charset=UTF-8" %>

<html>
	<head>
		<title>Sasisie des instruments</title>
	</head>

	<body>
		<h2>Saisie des instruments joués</h2>
		<p>
			<%
				int nbInstr = (Integer)request.getAttribute("nbInstr");
				
				for (int i=0; i<nbInstr; i++) {
					out.println("instrument " +(i+1) +": ");
					out.println("<input type =\"texte\" name=\"instrument\" />");
					out.println("<br />");
				}
			%>
		</p>

		<p>
			<input type="submit" name="lancerControle" value="Valider">
		</p>

	</body>
</html>

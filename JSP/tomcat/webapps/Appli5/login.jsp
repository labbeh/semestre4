<html>
	<head>
		<link rel="stylesheet" type="text/css" href="style.css">
		<title>login</title>
	</head>

	<body>

		<form method="get" action="valider">
			<table>
				<tr>
					<td>nom:</td>
					<td><input type="texte" name="nom" /></td>
				</tr>

				<tr>
					<td>pass:</td>
					<td><input type="texte" name="pass"></td>
				</tr>

				<tr>
					<td><input type="submit" name="lancerSession" value="Envoyer"></td>
				</tr>
			</table>

			<p id="msgErr">
				<%
					String msgErreur = (String)request.getAttribute("msgErr");

					if(msgErreur != null) out.println(msgErreur);
					//else out.println("null");
				%>
			</p>

		</form>
	</body>
</html>

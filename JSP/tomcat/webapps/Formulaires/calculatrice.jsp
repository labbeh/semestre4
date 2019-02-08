<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Enumeration" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="style.css">
		<title>Calculatrice</title>
	</head>

	<body>

		<table>
			<tr>
				<td>Opérande 1</td>
				<td>Opérande 2</td>
			</tr>

			<form method="get" action="calculer">
				<tr>
					
					<td> <input type="texte" name="operande1" /> </td>
					<td> <input type="texte" name="operande2" /> </td>
				</tr>

				<tr>
					<td colspan="2">
						Choisissez un opérateur: <br />

						Addition: <input type="radio" name="op" value="Addition"> <br />
						Soustraction: <input type="radio" name="op" value="Soustraction"> <br />
						Multiplication: <input type="radio" name="op" value="Multiplication"> <br />
						Division: <input type="radio" name="op" value="Division"> <br />
					</td>
				</tr>

				<tr>
					<td colspan="2">
							<input type="submit" name="lancerCalcul" value="Calculer">
					</td>
				</tr>
			</form>
		</table>
	</body>
</html>

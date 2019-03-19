<%-- ======================================
		erreurDroit.jsp
========================================= --%>

<!--
	JSP permettant d'afficher un message d'erreur suite &agrave; une
	tentative d'accès à une page sans en avoir les droits (en tapant l'URL par exemple)	
-->

<% String titre = "ERREUR"; %>
<%@include file="../ihm/miseEnPagePAC1.jsp" %>

VOUS N'AVEZ PAS LES DROITS POUR ACCEDER A CETTE FONCTIONNALITE !

<%@include file="../ihm/miseEnPagePAC2.jsp" %>





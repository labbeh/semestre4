<%@ page import="bdd.Droits" %>

<%
	/* Ce booléen controle l'affichage des infos de debuggage en haut de page */
	boolean DEBUG = true;

	// on vérifie que la JSP a été activée via le controleur
	Boolean controleurOK = (Boolean)request.getAttribute("controleurOK");
	if (controleurOK==null) {
	  //pour empêcher que suite au forward vers erreurDroit.jsp (qui inclue
	  //cette JSP) controleurOK soit à nouveau à null.
	  request.setAttribute("controleurOK",true);
%>
	  <jsp:forward page="/controleur?cmd=erreurDroit" />
	  <!--<jsp:forward page="/erreurs/erreurDroit.jsp" />-->			
<%			
	}	
	
	boolean afficheMenuAdmin = false; //si ce booléen vaut vrai, le sous-menu dédié à l'administration sera affiché

	Integer droitSession = (Integer)session.getAttribute("droitSession");
	if(/*droitSession != null &&*/ droitSession == 1) afficheMenuAdmin = true;

	/* A COMPLETER
	 * En utilisant la session (attribut droit), mettre à jour 
	 * la variable afficheMenuAdmin pour que le sous-menu dédié
	 * à l'administration ne soit affiché que si l'utilisateur connecté
	 * (s'il y en a un) a le droit admin.
	 * NB : s'il n'y a pas de droit stocké dans la session, on doit
	 * être renvoyé vers la page de connexion.
	 */
	 /*if(afficheMenuAdmin){
	 	
	}*/
	//out.println("droit: " +droitSession); 	
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Bienvenue</title>
<link rel="stylesheet" type="text/css" href="ihm/site.css">
</head>
<body>

<div class="haut">
     <div class="hautGauche">
     	  <img src="ihm/imagesWA3.png" alt="logo webapp"/>
     </div>
     <div class="hautCentre">
     	  Gestion de la base Serveur-Utilisateur-Connexion

<%
	if (DEBUG) {
		String nomCmd = request.getParameter("cmd");
		String classeCmd = (String)request.getAttribute("classeCmd");
		String jsp = (String)request.getAttribute("jsp");
%>	
		<table class="erreur">
		<tr>
		<th> nom Commande </th>
		<th> nom Classe de commande </th>
		<th> nom JSP associée </th>
		</tr>
		<tr>
		<td> <%= nomCmd %> </td>
		<td> <%= classeCmd %> </td>
		<td> <%= jsp %> </td>
		</tr>
		</table>
<%
	}
%>

     </div>
</div>

<div class="milieu">
     <div class="menu">
	  <a href="controleur?cmd=deconnect"> D&eacute;connexion </a>
	  <br/>
	  <br/>
	  <hr/>
     	  Consultation
	  <hr/>
	  <ul>
	    <li><a href="controleur?cmd=utils">Utilisateurs</a></li>
	    <li><a href="controleur?cmd=serveurs">Serveurs</a></li>
	    <li><a href="controleur?cmd=connexions">Connexions</a></li>
	  </ul>

	  <%
		if (afficheMenuAdmin) {
	  %>
		  <br/>
		  <hr/>
		  Administration
		  <hr/>
		  <ul>
		    <li><a href="controleur?cmd=utilsMAJ"> Mise &agrave; jour des utilisateurs </a></li>
		    <li><a href="controleur?cmd=serveursMAJ"> Mise &agrave; jour des serveurs </a></li>
		    <li><a href="controleur?cmd=connexionsMAJ"> Mise &agrave; jour des connexions </a></li>
		  </ul>
	  <%
		}
	  %>
	  
     </div>
     <div class="contenu">
       <div id="titre">

	<%= titre %>
       </div>


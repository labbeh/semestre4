<%@ page import="pac.Droits" %>
<%@ page contentType="text/html; charset=UTF-8" %>

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
		<jsp:forward page="/erreurs/erreurDroit.jsp" />

<%		
	}	
	
	boolean afficheMenuAdmin = true; //si ce booléen vaut vrai, le sous-menu dédié à l'administration sera affiché
	
	/* A COMPLETER
	 * En utilisant la session (attribut droit), mettre à jour la variable afficheMenuAdmin
	 * pour que le sous-menu dédié à l'administration ne soit affiché que si l'utilisateur connecté
	 * (s'il y en a un) a le droit admin.
	 * NB : s'il n'y a pas de droit stocké dans la session, on doit
	 * être renvoyé vers la page de connexion.
	 */ 	
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
     	  Gestion de la base Produit-Achat-Client

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
     </div> <!-- fin hautcentre -->
</div> <!-- fin haut -->

<div class="milieu">
     <div class="menu">
	  <a href="controleur?cmd=deconnect"> D&eacute;connexion </a>
	  <br/>
	  <br/>
	  <hr/>
     	  Consultation
	  <hr/>
	  <ul>
	    <li><a href="controleur?cmd=produits">Produits</a></li>
	    <li><a href="controleur?cmd=clients">Clients</a></li>
	    <li><a href="">...</a></li>
	  </ul>
	  <%
		if (afficheMenuAdmin) {
	  %>
	  <br/>
	  <hr/>
	  Administration
	  <hr/>
	  <ul>
	    <li><a href="controleur?cmd=produitsMAJ"> Mise &agrave; jour des produits </a></li>
	    <li><a href="controleur?cmd=ajoutProduit"> Ajouter un produit </a></li>
	    <li><a href=""> </a></li>
	  </ul>
	  <%
		}
	  %>
	  
     </div>
     <div class="contenu">
       <div id="titre">
	 <%= titre %>
       </div>

<%--	suite.jsp		--%>
<%
//on fait appel a la servlet qui va controler la session
%>
<jsp:include page="/controle" />

<%
	//on recupere dans la requete l'attribut "erreur" stocke par la
	//servlet controleSession
	Boolean b = (Boolean)request.getAttribute("erreur");
	out.println("ok" +b);
	
	//si l'attribut erreur existe et vaut false (ce qui indique que la session est valide) alors
	if(b != null && b == false){
		//on affiche l'identifiant de session
		out.println("ID session: " +request.getSession().getId());

		//on recupere le droit dans la session
		int droit = (Integer)request.getAttribute("droit");
		
		//on affiche le message de bienvenue avec le bon mode
		if(droit == 1) out.println("Vous êtes connecté en mode consultation");
		else		   out.println("Vous êtes connecté en tant qu'administrateur");
	
		//on genere un lien vers le scripts fin.jsp
		out.println("mettre liens vers fin.jsp");
	}

%>	



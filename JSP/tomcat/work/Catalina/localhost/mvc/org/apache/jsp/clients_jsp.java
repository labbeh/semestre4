/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-03-14 13:54:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import pac.*;
import pac.Droits;

public final class clients_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/ihm/miseEnPagePAC1.jsp", Long.valueOf(1552571515000L));
    _jspx_dependants.put("/ihm/miseEnPagePAC2.jsp", Long.valueOf(1551695010000L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
 String titre = "LISTE DES CLIENTS"; 
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

	/* Ce booléen controle l'affichage des infos de debuggage en haut de page */
	boolean DEBUG = true;

	// on vérifie que la JSP a été activée via le controleur
	Boolean controleurOK = (Boolean)request.getAttribute("controleurOK");
	if (controleurOK==null) {
		//pour empêcher que suite au forward vers erreurDroit.jsp (qui inclue
		//cette JSP) controleurOK soit à nouveau à null.
		request.setAttribute("controleurOK",true);

      out.write('\n');
      out.write('	');
      out.write('	');
      if (true) {
        _jspx_page_context.forward("/erreurs/erreurDroit.jsp");
        return;
      }
      out.write('\n');
      out.write('\n');
		
	}	
	
	boolean afficheMenuAdmin = true; //si ce booléen vaut vrai, le sous-menu dédié à l'administration sera affiché
	
	/* A COMPLETER
	 * En utilisant la session (attribut droit), mettre à jour la variable afficheMenuAdmin
	 * pour que le sous-menu dédié à l'administration ne soit affiché que si l'utilisateur connecté
	 * (s'il y en a un) a le droit admin.
	 * NB : s'il n'y a pas de droit stocké dans la session, on doit
	 * être renvoyé vers la page de connexion.
	 */ 	

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-type\" content=\"text/html;charset=UTF-8\">\n");
      out.write("<title>Bienvenue</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"ihm/site.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"haut\">\n");
      out.write("     <div class=\"hautGauche\">\n");
      out.write("     \t  <img src=\"ihm/imagesWA3.png\" alt=\"logo webapp\"/>\n");
      out.write("     </div>\n");
      out.write("     <div class=\"hautCentre\">\n");
      out.write("     \t  Gestion de la base Produit-Achat-Client\n");
      out.write("\n");

	if (DEBUG) {
		String nomCmd = request.getParameter("cmd");
		String classeCmd = (String)request.getAttribute("classeCmd");
		String jsp = (String)request.getAttribute("jsp");

      out.write("\t\n");
      out.write("\t\t<table class=\"erreur\">\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t<th> nom Commande </th>\n");
      out.write("\t\t<th> nom Classe de commande </th>\n");
      out.write("\t\t<th> nom JSP associée </th>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t<td> ");
      out.print( nomCmd );
      out.write(" </td>\n");
      out.write("\t\t<td> ");
      out.print( classeCmd );
      out.write(" </td>\n");
      out.write("\t\t<td> ");
      out.print( jsp );
      out.write(" </td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");

	}

      out.write("\n");
      out.write("     </div> <!-- fin hautcentre -->\n");
      out.write("</div> <!-- fin haut -->\n");
      out.write("\n");
      out.write("<div class=\"milieu\">\n");
      out.write("     <div class=\"menu\">\n");
      out.write("\t  <a href=\"controleur?cmd=deconnect\"> D&eacute;connexion </a>\n");
      out.write("\t  <br/>\n");
      out.write("\t  <br/>\n");
      out.write("\t  <hr/>\n");
      out.write("     \t  Consultation\n");
      out.write("\t  <hr/>\n");
      out.write("\t  <ul>\n");
      out.write("\t    <li><a href=\"controleur?cmd=produits\">Produits</a></li>\n");
      out.write("\t    <li><a href=\"controleur?cmd=clients\">Clients</a></li>\n");
      out.write("\t    <li><a href=\"\">...</a></li>\n");
      out.write("\t  </ul>\n");
      out.write("\t  ");

		if (afficheMenuAdmin) {
	  
      out.write("\n");
      out.write("\t  <br/>\n");
      out.write("\t  <hr/>\n");
      out.write("\t  Administration\n");
      out.write("\t  <hr/>\n");
      out.write("\t  <ul>\n");
      out.write("\t    <li><a href=\"controleur?cmd=produitsMAJ\"> Mise &agrave; jour des produits </a></li>\n");
      out.write("\t    <li><a href=\"controleur?cmd=ajoutProduit\"> Ajouter un produit </a></li>\n");
      out.write("\t    <li><a href=\"\"> </a></li>\n");
      out.write("\t  </ul>\n");
      out.write("\t  ");

		}
	  
      out.write("\n");
      out.write("\t  \n");
      out.write("     </div>\n");
      out.write("     <div class=\"contenu\">\n");
      out.write("       <div id=\"titre\">\n");
      out.write("\t ");
      out.print( titre );
      out.write("\n");
      out.write("       </div>\n");
      out.write('\n');
      out.write('\n');
      out.write('\n');
	// ==============  CORPS =================================================
	List<Client> clients = (List<Client>)request.getAttribute("clients");
	String coul="lignePaire";
	out.println("<table>");
	out.println("<tr class=\"enteteTableau\"><th>num</th><th>nom</th><th>adresse</th></tr>");
	for (Client c : clients) {
		coul=(coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";
	        out.println("<tr class=\""+coul+"\">");
		String href="controleur?cmd=achats&ncli="+c.getNcli();
		out.println("<td><a href="+href+">"+c.getNcli()+"</a></td>");
		out.println("<td>"+c.getNom()+"</td>");
		out.println("<td>"+c.getAdr()+"</td>");
		out.println("</tr>");
	}
	out.println("</table></center>");
	// ==============  CORPS =================================================

      out.write('\n');
      out.write('\n');
      out.write("\t</div>\n");
      out.write("\t</div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

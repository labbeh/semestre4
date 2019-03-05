import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.lang.reflect.Constructor;
import pac.*;

public class Controleur extends HttpServlet {
	//hashmap permettant d'associer à chaque nom de commande la classe de commande associée
    private Map<String,Commande> map;

    //hashmap permettant d'associer à chaque nom de commande son droit d'accès (all ou admin)
    private Map<String,String> mapDroits;

    private static final String JSP_ERREUR_INIT = "/erreurs/erreurInitControleur.jsp";
    private static final String JSP_ERREUR_CMD = "/erreurs/erreurCommande.jsp";
    private static final String JSP_EXCEPTION_CMD = "/erreurs/exceptionCommande.jsp";
    public static final String JSP_ERREUR_DROIT = "/erreurs/erreurDroit.jsp";

    private static final String SERVLET_CONTROLE_SESSION = "/controleSession";

    //Nom de la commande de login (IMPOSE DANS CETTE VERSION)
    public static final String CMD_LOGIN = "login";

	/*
	 * code de l'erreur éventuellement générée pendant
           l'initialisation de la hashmap (méthode init)
	   0 : pas d'erreur
	   -1 : classe de commande inexistante
	   -2 : constructeur de la classe de commande inexistant
	   -3 : erreur de création d'une instance de la classe de commande
	   -4 : un paramètre de l'application est mal initialisé (valeur mal structurée)
	   -7 : on essaie d'insérer deux fois le même nom de commande dans la HashMap
	   -8 : utilisation d'un droit d'accès inexistant pour une commande
	 */
	private int erreur;
	private String messageErreur; 
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		
		//on vérifie si la méthode init a déclenché une erreur
		if (erreur !=0) {
		   	req.setAttribute("code", erreur);
			req.setAttribute("mess", messageErreur);
			RequestDispatcher rd1 = req.getRequestDispatcher(JSP_ERREUR_INIT); 
			rd1.forward(req, res);
			return;
		}

		//on indique que l'on est bien passé par le controleur
		req.setAttribute("controleurOK",true);

		String next=null;
		//on récupère le nom de la commande à activer dans la requête reçue                               
        	String cmd = req.getParameter("cmd");      
        	if (cmd==null) { cmd="accueil"; }
		else {
			if (!map.containsKey(cmd)) {
				RequestDispatcher rd1 = req.getRequestDispatcher(JSP_ERREUR_CMD); 
				rd1.forward(req, res); 
				return;
			}
		}

		//on vérifie que la commande peut bien être exécutée car il y a un utilisateur connecté
		//on vérifie que la commande demandée a bien un droit compatible avec l'utilisateur connecté
		//on ne le fait que si la commande demandée n'est pas la commande de login
		//Si l'un des deux points précédents pose problème, la servlet incluse 
		//va renvoyer vers la page de login avec un forward et donc la suite du controleur 
		//ne sera pas exécutée.
		//NB : il faut transmettre à la servlet de controle de session le droit associé à la commande
		if (! cmd.equals(CMD_LOGIN)) {
			String droitCmd = mapDroits.get(cmd); //on récupère le droit associé à la commande
			req.setAttribute("droitCmd",droitCmd); //on le transmet dans la requête
			//on délègue le controle à la servlet de controle de session 
			RequestDispatcher rdSession = req.getRequestDispatcher(SERVLET_CONTROLE_SESSION);
			rdSession.include(req,res);
		} 

		//on teste si la servlet de controle de session a fait un forward
		Boolean forwardOK = (Boolean)req.getAttribute("forwardOK");
		if (forwardOK!=null) { return; }

		//NB : si on exécute cette partie, c'est que le controle de session a été validé
		//on récupère l'objet de type Commande associé au nom de commande précédent
        	Commande cde = map.get(cmd);                 
		try {
			//on exécute la méthode execute de l'objet "commande" sélectionné
			//cette méthode renvoie le nom de la JSP qui devra être activée ensuite
        		next = cde.execute(req);
		} catch(Exception ex){
		  req.setAttribute("exception",ex.getMessage());
		  req.setAttribute("classeException",ex.getClass().getSimpleName());
		  req.setAttribute("classe",cde.getClass().getName());
		  next = JSP_EXCEPTION_CMD;
		}
		//Pour le debuggage éventuel, on passe dans la requête 
		//le nom de la classe de commande et le nom de la JSP (vue)
		req.setAttribute("classeCmd",cde.getClass().getName());
		req.setAttribute("jsp",next);		

		//on active la vue ie la page JSP cible
		RequestDispatcher rd = req.getRequestDispatcher(next); 
		rd.forward(req, res); 

	} //fin méthode service

	//Cette méthode est exécutée une seule fois lors de la création
	//de la servlet par tomcat. Ceci arrive lors de la première invocation
	//de la servlet
	@Override
	public void init(ServletConfig config) throws ServletException {

		//une hashmap est utilisée pour mémoriser les triplets :
		//<nom commande, commande à utiliser, page cible>
		//"commande à utiliser" correspond à un objet instance d'une classe
		//de commande dont la méthode "execute" devra être exécutée par le controleur
		//Suite à cette exécution, la JSP appelée "page cible" devra être activée par tomcat.
		//Pour cela, le nom de cette page va être mémorisée dans l'objet commande activé pour
		//être renvoyé par la méthode execute. 
		map = new HashMap<String,Commande>();
		mapDroits = new HashMap<String,String>();
		erreur = 0;

		//Enumeration<String> listeParametres = config.getServletContext().getInitParameterNames();
		Enumeration listeParametres = config.getServletContext().getInitParameterNames();

	    	while (listeParametres.hasMoreElements()) {
		    String nomParamCommande = ((String)listeParametres.nextElement());
			//chaque couple(classe,jsp) est stocké dans les paramètres d'initialisation sous la forme
			//d'un paramètre dont le nom est cmd-nom, avec nom : le nom de la commande
			if (!nomParamCommande.startsWith("cmd-")) {
			    continue;
			    // Si ce n'est pas une déclaration de commande, on ignore
			}
		    String ligne = config.getServletContext().getInitParameter(nomParamCommande);

			//la valeur du paramètre est une chaine de caractères où les éléments
			//du couple sont séparés par des points-virgule
        		String [] ligneDecomposee = ligne.split(";");
			if (ligneDecomposee.length != 3) {
				erreur = -4;
				messageErreur = "Dans le fichier web.xml, un param&egrave;tre est mal initialis&eacute; (";
				messageErreur += ligne + ")";
				return;
			}
			//on enlève les éventuels blancs "en trop" pour chacune des infos à récupérer
        		String nomCommande = nomParamCommande.substring(4).trim();
        		String nomClasseCommande = ligneDecomposee[0].trim();
        		String pageSuivante = ligneDecomposee[1].trim();
			String droit = ligneDecomposee[2].trim();

			//On vérifie que le droit est bien soit "all" soit "admin"
			if (!(droit.equals(Droits.DROIT_PAGE_ALL) || droit.equals(Droits.DROIT_PAGE_ADMIN))) {
				erreur = -8;
				messageErreur = "Utilisation dans le fichier web.xml d'un droit inexistant pour la commande  "+ nomCommande;
				return;				
			}

			//on demande à la machine virtuelle Java de charger la classe de l'objet commande
			//qui devra être activée
			Class<?> classeCommande = null;
			Constructor<?> consCommande = null;
			Commande commande = null;

			try {
			    classeCommande = Class.forName(nomClasseCommande);
			}
			catch (ClassNotFoundException ex) {
			      erreur = -1;
			      messageErreur = "Utilisation dans le fichier web.xml d'une classe de Commande inexistante (";
			      messageErreur += nomClasseCommande + ")";
			      return;
			}
			//on récupère dynamiquement le constructeur de cette classe
			//"String.class" signifie que le constructeur que l'on recherche prend un
			//élément de type String en paramètre (le nom de la page JSP cible)
			try {
        			consCommande = classeCommande.getConstructor(String.class);
			}
			catch (NoSuchMethodException ex) {
			      erreur = -2;
			      messageErreur = "Constructeur d'une classe de Commande inexistant";
			      return;
			}
			//on crée donc une objet instance de la classe de Commande concernée.
			//on passe en paramètre au constructeur le nom de la page JSP cible ie.
			//qui devra être activée par tomcat à la fin de l'exécution de la 
			//méthode execute de l'objet commande activé.
			try {
        			commande = ((Commande) consCommande.newInstance(pageSuivante));
			}
			catch (Exception ex) {
			      erreur = -3;
			      messageErreur = "Erreur pendant la cr&eacute;ation d'une instance d'une classe de Commande <br/>";
			      messageErreur += "Il est possible qu'une classe de commande utilisée dans le fichier web.xml";
			      messageErreur += " n'implante pas l'interface Commande.";
			      return;
			}
			//on ajoute le "triplet" dans le Hashmap
			if (map.containsKey(nomCommande)) {
				erreur = -7;
				messageErreur = "Un nom de commande est utilis&eacute; 2 fois dans le fichier web.xml (";
				messageErreur += nomCommande + ")";
				return;
			}
        		map.put(nomCommande,commande);
			mapDroits.put(nomCommande,droit);
	    } //fin du for                 
	} //fin méthode init

} //fin de la classe


package pac;

/*
 * Cette classe n'a pas vocation Ã  Ãªtre instanciÃ©e. Elle permet juste de recenser
 * les constantes liÃ©es  Ã  la connexion et Ã  la gestion des droits dans l'application.
 */
public abstract class Droits {

	//Identifiants de connexion pour le compte ADMIN
	public static final String LOGIN_ADMIN = "admin";
	public static final String MDP_ADMIN = "adminpwd";

	//Identifiants de connexion pour le compte USER
	public static final String LOGIN_USER = "user";
	public static final String MDP_USER = "userpwd";

		
	//Liste des valeurs possibles des droits associÃ©s aux pages dans le fichier web.xml
    	public static final String DROIT_PAGE_ALL = "all";
    	public static final String DROIT_PAGE_ADMIN = "admin";

	//Liste des valeurs possibles des droits associÃ©s Ã  l'utilisateur connectÃ©
	public static final int DROIT_UTIL_CONSULT = 1;
	public static final int DROIT_UTIL_ADMIN = 2;

	private Droits() { }
}

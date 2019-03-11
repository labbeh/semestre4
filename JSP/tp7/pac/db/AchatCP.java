package pac;
public class AchatCP extends Achat{
	String nom;
	String lib;
	public String getNom(){return nom;} 
	public String getLib(){return lib;} 
	
	public void setNom(String nom){this.nom=nom;} 
	public void setLib(String lib){this.lib=lib;} 
	
	public AchatCP(){} 
	public AchatCP(int ncli , int np,  int qa, String nom, String lib){
		super(ncli,np,qa);
		setNom(nom);setLib(lib);
	} 
	public String toString(){ return super.toString(); } 
}


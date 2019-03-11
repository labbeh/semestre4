package pac;
public class Client{
	int ncli; 
	String nom;     
	String adr; 
	public int getNcli(){return ncli;} 
	public String getNom(){return nom;} 
	public String getAdr(){return adr;} 
	public void setNcli(int ncli){this.ncli=ncli;} 
	public void setNom(String nom){this.nom=nom;} 
	public void setAdr(String adr){this.adr=adr;} 
	public Client(){} 
	public Client(int ncli , String nom,  String adr){ setNcli(ncli);setNom(nom);setAdr(adr); } 
	public String toString(){ return ncli+"," + nom +"," + adr; } 
}


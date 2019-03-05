package pac;
public class Produit{
	int	np; 
	String 	lib;     
	String 	couleur; 
	int 	qs; 

	public int 	getNp(){return np;} 
	public String 	getLib(){return lib;} 
	public String 	getCouleur(){return couleur;} 
	public int 	getQs(){return qs;} 

	public void setNp(int np){this.np=np;} 
	public void setLib(String lib){this.lib=lib;} 
	public void setCouleur(String couleur){this.couleur=couleur;} 
	public void setQs(int qs){this.qs=qs;} 

	public Produit(){} 
	public Produit(int np , String lib,  String couleur, int qs){ 
		setNp(np);setLib(lib);setCouleur(couleur);setQs(qs); 
	} 

	public String toString(){ return np+"," + lib +"," + couleur + ","+ qs; } 
}


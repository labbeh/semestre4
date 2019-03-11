package pac;
public class Achat{
	int ncli; 
	int np;     
	int qa;     
	public int getNcli(){return ncli;} 
	public int getNp(){return np;} 
	public int getQa(){return qa;} 
	public void setNcli(int ncli){this.ncli=ncli;} 
	public void setNp(int np){this.np=np;} 
	public void setQa(int qa){this.qa=qa;} 
	public Achat(){} 
	public Achat(int ncli , int np,  int qa){ setNcli(ncli);setNp(np);setQa(qa); } 
	public String toString(){ return ncli+"," + np +"," + qa; } 
}


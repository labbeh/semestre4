package annexe; 

/*
 * ATTENTION : les classes annexes doivent obligatoirement Ãªtre dÃ©clarÃ©es dans un package
 */

public class Personne {
    private String nom;
    private String prenom;
    int age;

    public Personne(String nom, String prenom, int age) {
	this.nom = nom;
	this.prenom = prenom;
	this.age = age;
    }

    public String getNom() {
	return nom;
    }
    
    public String getPrenom() {
	return prenom;
    }

    public int getAge() {
	return age;
    }

}

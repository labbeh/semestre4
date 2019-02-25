package philosophes.metier;

import philosophes.Controleur;

public class Philosophe extends Thread {
	/**
	 * Instance du controleur
	 * */
	private Controleur ctrl;
	
	/**
	 * Référence vers la première fourchette qui lui est associée
	 * */
	private Fourchette fourchette1;
    
    /**
	 * Référence vers la deuxième fourchette qui lui est associée
	 * */
	private Fourchette fourchette2;
	
	/**
	 * Nom du philosophe
	 * */
	private String nom;
	
	/**
	 * Construit un philosophe à partir de son nom
	 * @param nom nom du philosophe en String doit être unique
	 * */
	public Philosophe(String nom, Controleur ctrl){
		this.nom = nom;
		this.ctrl = ctrl;
	}

	@Override
	public void run() {
		super.run();
        //sleep();
        
		while(ctrl.resteAssiette()){
			System.out.println("Philosophe " +nom+ " réfléchi");
			reflechir();
			
			manger();
			
			System.out.println("Philosophe " +nom+ " dort");
			dormir();
		}
        ctrl.setEtat(nom, "A fini son cycle");
	}
	
	/**
	 * Permet au philosophe de dormir
	 * */
	private void dormir() {
        ctrl.setEtat(nom, "Dort...");
		sleep();
	}
	
	/**
	 * Permet au philosophe de manger, aura pour effet d'enelver une assiette de la pile
	 * */
	private void manger() {
		Assiette a = ctrl.prendreAssiete();
        
        if(a == null){
            System.out.println("plus d'assietes");
            return;
        }
        
        fourchette1.prendre(this);
        fourchette2.prendre(this);
        
		System.out.println(nom +" a pris l'assiete " +a.getNum());
        System.out.println("Philosophe " +nom+ " mange");
        ctrl.setEtat(nom, "mange avec f" +fourchette1.getNum() +" et f" +fourchette2.getNum());
		
		sleep(); // le temps de manger ...
		
        // on libère les sémaphores
        fourchette1.poser(this);
        fourchette2.poser(this);
        
		System.out.println(nom +" a fini de manger dans l'assiette " +a.getNum());
	}

	/**
	 * Permet au philosphe de réfléchir...
	 * */
	private void reflechir() {
        ctrl.setEtat(nom, "Réfléchi...");
		sleep();
	}
	
	/**
	 * Temporisation qui sere lancée pour dormir et réfléchir
	 * */
	private void sleep() {
		try{
			Thread.sleep(2000 +nom.length()*2);
		}
		catch(InterruptedException evt){
			evt.printStackTrace();
		}
	}

	/**
	 * Retourne le nom du philosophe
	 * @return nom du philosophe
	 * */
	public String getNom() {
		return nom;
	}
	
	public void setFourchette1(Fourchette fourchette) {
		this.fourchette1 = fourchette;
	}
    
    public void setFourchette2(Fourchette fourchette) {
		this.fourchette2 = fourchette;
	}

	@Override
	public String toString(){
		return this.nom;
	}
	
}

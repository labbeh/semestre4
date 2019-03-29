package philosophes;

import philosophes.exceptions.NombreIncorrectExcpetion;
import philosophes.ihm.IHMGui;
import philosophes.metier.Assiette;
import philosophes.metier.Fourchette;
import philosophes.metier.Philosophe;
import philosophes.metier.ensembles.EnsemblePhilosophe;
import philosophes.metier.ensembles.PileAssiette;

public class Controleur {
	/**
	 * Ensemble de Philosophe
	 * */
	private EnsemblePhilosophe philosophes;
	
	/**
	 * Pile d'Assiette
	 * */
	private PileAssiette assiettes;
	
	/**
	 * Ensemble de fourchettes
	 * */
	private Fourchette[] fourchettes;
    
    /**
     * Ihm gaphique
     */
    private IHMGui ihm;
	
	/**
	 * Constructeur du controleur MVC
	 * */
	public Controleur(){
		//this.ihm = new IHMGui(this);
	}
	
	/**
	 * Lance le scénario
	 * @param n nombre de Philosophe
	 * @param k nombre d'Assiette
	 * @param noms des Philosophe
	 * @throws NombreIncorrectExcpetion 
	 * */
	public void lancer( int n, int k, String[] noms ) throws NombreIncorrectExcpetion {
		// création de l'ensemble de philosophes
		this.philosophes = EnsemblePhilosophe.create(n);
		if(philosophes == null)
			throw new NombreIncorrectExcpetion("Erreur avec " +n+ " philosphes");
		
		for(String nom: noms) philosophes.add(new Philosophe(nom, this));
		
		// création de l'ensebmle d'assiettes
		this.assiettes = PileAssiette.create(philosophes, k);
		if(assiettes == null)
			throw new NombreIncorrectExcpetion("Erreur avec " +k+ " assietes");
		
		for(int i=0; i<k; i++) assiettes.push(new Assiette());
		
		// création des fourchettes et association à leurs philosphes
		this.fourchettes = new Fourchette[n];
		for(int i=0; i<fourchettes.length; i++) {
			fourchettes[i] = new Fourchette(this);
			
			
		}
        
        for(int i=0; i<philosophes.size(); i++){
            Philosophe temp = philosophes.get(i);
            
            temp.setFourchette1(fourchettes[i]);
            if(i+1 < fourchettes.length) temp.setFourchette2(fourchettes[i+1]);
            else                         temp.setFourchette2(fourchettes[0]);
        }
		
		// lancement des thread philosophes
		//animerPhilosophes();
        
        // création de la fenetre graphique
        lancerIhm();
	}
    
    /**
     * Permet de lancer les threads philosophes
     */
    public void animerPhilosophes(){
        for(Philosophe p: philosophes)
			p.start();
    }
    
    /**
     * Lance l'ihm graphique
     */
    public void lancerIhm(){
        java.awt.EventQueue.invokeLater(new ThreadIhm(this));
        
    }

    public void setEtat(String nom, String etat) {
        ihm.setEtat(nom, etat);
    }
    
    private class ThreadIhm implements Runnable{
        private Controleur ctrl;
        
        public ThreadIhm(Controleur ctrl){
            this.ctrl = ctrl;
        }

        @Override
        public void run() {
            ihm = new IHMGui(ctrl);
            ihm.setNbAssiettes(assiettes.size());
            ihm.setVisible(true);
        }
        
    }
	
	/**
	 * Permet de savoir si il reste des assiettes ou non dans la pile
	 * @return vrai si il reste des assiettes
	 * */
	public boolean resteAssiette(){
		return !assiettes.empty();
	}
	
	/**
	 * Permet à un Philosophe de prendre une Assiette
	 * @return la référence à l'Assiette qu'il a pris
	 * */
	public Assiette prendreAssiete() {
		Assiette a = assiettes.prendre();
        ihm.setNbAssiettes(assiettes.size());
        return a;
	}
    
    public int getNbPhilosophes(){
        return philosophes.size();
    }
    
    public int getNbAssiettes(){
        return assiettes.size();
    }
    
    public int getNbFourchettes(){
        return 0;
    }
    
    public Philosophe getPhilosophe(int index){
        return philosophes.get(index);
    }
	
	@Override
	public String toString(){
		return "Philosophes: " +philosophes+ "\n"
				+ "Assiettes: " +assiettes;
	}
}

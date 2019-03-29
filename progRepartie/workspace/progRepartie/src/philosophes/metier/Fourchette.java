package philosophes.metier;

import java.util.concurrent.Semaphore;
import philosophes.Controleur;

public class Fourchette {
    /**
     * Accès à l'instance du controleur
     */
    private Controleur ctrl;
	
	/**
	 * Semaphore pour que les 2 philosophes ne prennent pas la fourchette en même temps
	 * */
	private Semaphore semaphore;
	
	/**
	 * Compteur du nombre d'instance de Fourchette pour numéro unique
	 * */
	private static int nbInst = 0;
	
	/**
	 * Identifiant unique d'une instance
	 * */
	private int num;
	
	/**
	 * Construit une Fourchette qui sera partagée entre les philosophes à partir
	 * des références vers les deux philosophes qui la partageront
	 * @param ph1 premier philosophe ayant accès à la fourchette
	 * @param ph2 deuxième philosophe ayant accès à la fourchette
	 * */
	public Fourchette(Controleur ctrl) {
		this.num = ++nbInst;
		
        this.ctrl = ctrl;
		 semaphore = new Semaphore(1, true);
	}
	
	/**
	 * Permet à un philosphe de prendre la fourchette
	 * */
	public void prendre(Philosophe p) {
		try{
            ctrl.setEtat(p.getNom(), "attend accès fourchettes");
			semaphore.acquire();
            System.out.println(p +" a pris la fouchette " +num);
		}
		catch(InterruptedException evt){
			evt.printStackTrace();
		}
	}
	
	/**
	 * Libère la fourchette après utilisation
	 * */
	public void poser(Philosophe p) {
		semaphore.release();
        System.out.println(p +" a reposer la fourchette " +num);
	}
    
    public int getNum(){
        return num;
    }
	
	@Override
	public String toString(){
		return "f" +num;
	}

}

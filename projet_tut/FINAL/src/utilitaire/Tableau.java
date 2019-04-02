package utilitaire;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Tableau {
	
	public static int[][] creerCopie ( int[][] tabOrig ) {
		
		int nbLignes   = tabOrig.length;
		int nbColonnes = tabOrig[0].length;
		int[][] tabCopie = new int[nbLignes][nbColonnes];
		
		for ( int lig=0; lig<nbLignes; lig++ ) {
			for ( int col=0; col<nbColonnes; col++ ) {
				tabCopie[lig][col] = new Integer( tabOrig[lig][col] );
			}
		}
		
		return tabCopie;
	}
	
	public static int[] getColonne ( int[][] tab, int ind ) {
		
		// Récupérer colonne
		ArrayList<Integer> alColonne = new ArrayList<Integer>();
		for ( int lig=0; lig<tab.length; lig++ ) {
			alColonne.add(tab[lig][ind]);
		}
		
		// Convertir valeurs en int
		int[] colonne = new int[alColonne.size()];
		for (int i=0; i < colonne.length; i++) {
			colonne[i] = alColonne.get(i).intValue();
		}
		
		return colonne;
	}
	
	public static int getValeurMax ( int[] ligne ) {
		
		int valMax = 0;
		
		for ( int col=0; col<ligne.length; col++ ) {
			
			int val = ligne[col];
			
			if ( val > valMax )
				valMax = val;
			
		}
		
		return valMax;
	}
	
	public static int getValeurMin ( int[] ligne ) {
		
		int valMin = Integer.MAX_VALUE;
		
		for ( int col=0; col<ligne.length; col++ ) {
			
			int val = ligne[col];
			
			if ( val < valMin )
				valMin = val;
			
		}
		
		return valMin;
	}
	
	/*public static String toString ( ArrayList<ArrayList<String>> tabInfini ) {
		ArrayList<String> ligneInfini = tabInfini.get(0);
		int tailleX    = tabInfini.size();
		int tailleY    = ligneInfini.size();
		String[][] tab = new String[tailleX][tailleY];
		
		for ( int lig=0; lig<tailleX; lig++ ) {
			for ( int col=0; col<tailleY; col++ )
				tab[lig][col] = tabInfini.get(lig).get(col);
			
		}
		
		return toString(tab);
	}*/
	
	public static String toString ( ArrayList<ArrayList<Integer>> tabInfini ) {
		ArrayList<Integer> ligneInfini = tabInfini.get(0);
		int tailleX    = tabInfini.size();
		int tailleY    = ligneInfini.size();
		String[][] tab = new String[tailleX][tailleY];
		
		for ( int lig=0; lig<tailleX; lig++ ) {
			for ( int col=0; col<tailleY; col++ )
				tab[lig][col] = tabInfini.get(lig).get(col).toString();
			
		}
		
		return toString(tab);
	}
	
	public static String toString ( char[][] tab ) {
		
		String[][] tabString = new String[tab.length][tab[0].length];
		
		for ( int lig=0; lig<tab.length; lig++ ) {
			
			for ( int col=0; col<tab[0].length; col++ )
				tabString[lig][col] = "" + tab[lig][col];
			
		}
		
		return Tableau.toString(tabString);
		
	}
	
	public static String toString ( int[] tab ) {	
		String[] tabString = new String[tab.length];
			
		for ( int col=0; col<tab.length; col++ )
			tabString[col] = "" + tab[col];
		
		return Tableau.toString(tabString);
	}
	
	public static String toString ( int[][] tab ) {
		
		String[][] tabString = new String[tab.length][tab[0].length];
		
		for ( int lig=0; lig<tab.length; lig++ ) {
			
			for ( int col=0; col<tab[0].length; col++ )
				tabString[lig][col] = "" + tab[lig][col];
			
		}
		
		return Tableau.toString(tabString);
		
	}
	
	/** Cette méthode organise une affichage du tableau.
	  * @param tab
	  *        Tableau de String
	**/
	public static String toString ( String[] tab ) {
		
		int longMax  = 0;
		String s     = "";
		String ligne = "";
		
		// Longueur max
		for ( int col=0; col<tab.length; col++ ) {
			
			String mot = tab[col];
			
			if ( longMax < mot.length() )
				longMax = mot.length();
			
		}
				
		// Construction ligne
		for ( int col=0; col<tab.length; col++ ) {
			ligne += "+";
			for ( int cpt=0; cpt<longMax+2; cpt++ ) {
			ligne += "-";
			}
			
		}
		ligne += "+\n";
		
		// Construction tableau
		s += ligne + "|";
		
		for ( int col=0; col<tab.length; col++ ) {	
			String mot = tab[col];
			s += String.format(" %"+longMax+"s |",mot);
		}
		s += "\n";
		s += ligne;
		return s;
	}
	
	/** Cette méthode organise une affichage du tableau.
	  * @param tab
	  *        Tableau de String
	**/
	public static String toString ( String[][] tab ) {
		
		int longMax  = 0;
		String s     = "";
		String ligne = "";
		
		// Longueur max
		for ( int lig=0; lig<tab.length; lig++ ) {
			for ( int col=0; col<tab[0].length; col++ ) {
				
				String mot = tab[lig][col];
				
				if ( longMax < mot.length() )
					longMax = mot.length();
				
			}
			
		}
		
		// Construction ligne
		for ( int col=0; col<tab[0].length; col++ ) {
			ligne += "+";
			for ( int cpt=0; cpt<longMax+2; cpt++ ) {
			ligne += "-";
			}
			
		}
		ligne += "+\n";
		
		// Construction tableau
		for ( int lig=0; lig<tab.length; lig++ ) {
			
			s += ligne + "|";
			
			for ( int col=0; col<tab[0].length; col++ ) {
				
				String mot = tab[lig][col];
				s += String.format(" %"+longMax+"s |",mot);
				
			}
			s += "\n";
			
		}
		
		s += ligne;
		
		return s;
		
	}
	
}
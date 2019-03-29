import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

/**
 * Représentation d'un plateau de jeu
 * @author amélie nioche hugo labbé, yann reibel, clément jeanne dit fouque, louis-pierre aubert
 * */
public class Plateau
{
	private Controleur             ctrl;
	private ArrayList<SpaceObject> alObject;
	
	public Plateau(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		this.alObject = new ArrayList<SpaceObject>();
		this.alObject.add(new Fusee(this.ctrl));
		
		this.generationPlanetes(5);
	}
	
	private void generationPlanetes(int nbPlanetes)
	{
		for(int i=0; i<nbPlanetes; i++)
			alObject.add(new Planete(ctrl));
	}
	
	public void paintPlanete( Graphics g )
	{
		for ( int i=1; i<this.alObject.size(); i++ )
			this.alObject.get(i).dessiner(g);
	}
	
	public void paintFusee( Graphics g )
	{
		this.alObject.get(0).dessiner(g);
	}
	
	private boolean verifIndice(int ind)
	{
		boolean estNull = this.alObject.isEmpty();
		
		if(! estNull )
			estNull = ( ind < 0 || ind >= this.alObject.size() );
		
		return estNull;
	}
	
	public Planete getPlanete(int ind)
	{
		if( verifIndice(ind) ) return null;
		
		if( this.alObject.get(ind) instanceof Planete )
			return (Planete) this.alObject.get(ind);
		
		return null;
	}
	
	public void entreEnCollision()
	{
		boolean collision = false;
		
		List<Coordonnees> list = this.alObject.get(0).getCoordsContour();
		
		if(! list.isEmpty())
			for(Coordonnees co : list)
				if(entreEnCollision(co))
					collision = true;
		
		if( collision ) this.ctrl.partiePerdu();
	}
	
	private boolean entreEnCollision(Coordonnees pos)
	{
		boolean bCollision = false;
		
		for( SpaceObject obj : this.alObject )
			if( ! obj.equals(this.alObject.get(0)) )
				if( ! bCollision && obj.appartient(pos) )
					return true;
		
		return false;
	}
	
	public void ajouterPlanete(Planete planete) { this.alObject.add(planete); }
	
	public int                    getNbPlanetes()  { return this.alObject.size();        }
	public ArrayList<SpaceObject> getSpaceObject() { return this.alObject;               }
	public Fusee                  getFusee()       { return (Fusee)this.alObject.get(0); }
}
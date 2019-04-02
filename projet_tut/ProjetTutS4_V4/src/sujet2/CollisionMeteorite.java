package sujet2;

public class CollisionMeteorite
{	
	public static void calc ( Meteorite b1, Meteorite b2 ) 
	{ 	
		// Dans cette méthode (de la classe meteorite) : 
		// This est une meteorite courante, et b2 une autre meteorite
		// les angles sont en radians
		// le parametre vitesse est la norme (grandeur) du vecteur vitesse

		// Quantité de mouvement et énergie cinétique avant la collision 
		// double qmx1 = b1.m * b1.vx + b2.m * b2.vx; 
		// double qmy1 = b1.m * b1.vy + b2.m * b2.vy; 
		// double ec1 = b1.m * (b1.vx*b1.vx + b1.vy*b1.vy) + b2.m * (b2.vx*b2.vx + b2.vy*b2.vy); 
		// Calcul de la base orthonormée (n,g)
		
		double pos1X = b1.getPos().getX();
		double pos1Y = b1.getPos().getY();
		double pos2X = b2.getPos().getX();
		double pos2Y = b2.getPos().getY();
		
		double dx = pos1X - pos2X;  
		double dy = pos1Y - pos2Y; 
		
		double r = Math.sqrt(dx*dx + dy*dy); 
		// n est perpendiculaire au plan de collision 
		double nx = (pos2X - pos1X) / r; 
		double ny = (pos2Y - pos1Y) / r; 
		// g est tangent au plan de collision 
		double gx = -ny; 
		double gy = nx; 
		// Transition des vitesses de la base (x,y) vers (n,g) 
		double b1vx = b1.getVecteur().getValeur()*Math.cos( b1.getRot() );
		double b1vy = b1.getVecteur().getValeur()*Math.sin( b1.getRot() );
		double b2vx = b2.getVecteur().getValeur()*Math.cos( b2.getRot() );
		double b2vy = b2.getVecteur().getValeur()*Math.sin( b2.getRot() );
		
		double v1n = nx*b1vx + ny*b1vy; 
		double v1g = gx*b1vx + gy*b1vy; 
		double v2n = nx*b2vx + ny*b2vy; 
		double v2g = gx*b2vx + gy*b2vy; 
		// Détermination des nouvelles vitesses dans (n,g) 
		double b1m = b1.getDimension().getWidth() * b1.getDimension().getWidth();
		double b2m = b2.getDimension().getWidth() * b2.getDimension().getWidth();
		
		double m = b1m + b2m; 
		double m12 = (b1m - b2m)/m; 
		double m22 = (b2m + b2m)/m; 
		double m11 = (b1m + b1m)/m; 
		double m21 = (b2m - b1m)/m; 
		double v1n2 = m12*v1n + m22*v2n; 
		double v1g2 = m12*v1g + m22*v2g; 
		double v2n2 = m11*v1n + m21*v2n; 
		double v2g2 = m11*v1g + m21*v2g; 
		
		// Modification des vitesses dans la base (x,y) 
		b1vx = nx*v1n2 + gx*v1g2; 
		b1vy = ny*v1n2 + gy*v1g2; 
		b2vx = nx*v2n2 + gx*v2g2; 
		b2vy = ny*v2n2 + gy*v2g2; 
		
		b1.getVecteur().setForceX( b1vx*b1vx );
		b1.getVecteur().setForceY( b1vy*b1vy );
		b2.getVecteur().setForceX( b2vx*b2vx );
		b2.getVecteur().setForceY( b2vy*b2vy );
		
		b1.setRot( Math.toDegrees( Math.atan(b1vy/b1vx) ) );
		if (b1vx<0)
			if (b1vy>0)
				b1.setRot( b1.getRot() + Math.PI );
			else
				b1.setRot( b1.getRot() - Math.PI );

		b2.setRot( Math.atan(b2vy/b2vx) );
		if (b2vx<0)
			if (b2vy>0)
				b2.setRot( b2.getRot() + Math.PI );
			else
				b2.setRot( b2.getRot() - Math.PI );

	}
	
}
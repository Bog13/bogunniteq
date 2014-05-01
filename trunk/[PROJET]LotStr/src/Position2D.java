import java.util.Vector;





/**
 *  Gère tout ce qui concerne les positions dans l'espace
 *	@author: BoG
*/


public class Position2D
{
	final public static Position2D ORIGINE=new Position2D(0,0);
	final public static Position2D FIN=new Position2D(Global.NB_CASE_HAUTEUR-1,Global.NB_CASE_LARGEUR-1);
	private int	m_l;
	private int	m_c;

	public Position2D()
	{
		m_l = 0;
		m_c = 0;

	}

	public Position2D(int l, int c)
	{
		m_l = l;
		m_c = c;
	}
	
	public static Position2D position(int i,int j)
	{
		return new Position2D(i,j);
	}
	
	public boolean equal(Position2D A)
	{
		
		return (A.m_l==m_l && A.m_c == m_c);
	}

	/**
	 * Permet d'initialiser une position relativement à une autre.
	 * Exemple: pour créer une position à coté d'une autre.
	 * @param pos
	 * @param x
	 * @param y
	 */
	public Position2D(Position2D pos, Position2D pos2)
	{
		m_l = pos.getL() + pos2.getL();
		m_c = pos.getC() + pos2.getC();
	}

	public Position2D(Position2D pos, int x, int y)
	{
		m_l = pos.getL() + x;
		m_c = pos.getC() + y;
	}

	public int getL()
	{
		return m_l;
	}
	
	public int getC()
	{
		return m_c;
	}
	
	public void setC(int c)
	{
		m_l=c;
	}
	
	public void setL(int l)
	{
		m_c=l;
	}
	
	public Position2D clone()
	{
		return new Position2D(m_l,m_c);
	}
	
	public static Position2D random()
	{
		return Position2D.position(Outil.hasard(0,Global.NB_CASE_HAUTEUR),Outil.hasard(0,Global.NB_CASE_LARGEUR));
	}
	
	public boolean estEntre(Position2D A, Position2D B )
	{
		Vector<Position2D> vect=Position2D.positionEntre(A,B);
		boolean existe=false;
		int i=0;
		
		while(i<vect.size() && !existe)
		{
			if( vect.get(i).equal(this) )
			{
				existe=true;
			}
			i++;
		}
		
		return existe;
	}
	
	/*Algorithme de bresenham
	 * http://raphaello.univ-fcomte.fr/IG/Algorithme/Algorithmique.htm
	 */
	public static Vector<Position2D> bresenham(int xi,int yi,int xf,int yf) 
	{
		
		Vector<Position2D> res = new Vector<Position2D>();
		int dx,dy,i,xinc,yinc,cumul,x,y ;
		x = xi ;
		y = yi ;
		dx = xf - xi ;
		dy = yf - yi ;
		xinc = ( dx > 0 ) ? 1 : -1 ;
		yinc = ( dy > 0 ) ? 1 : -1 ;
		dx = Math.abs(dx) ;
		dy = Math.abs(dy) ;
		 
		 res.add(new Position2D(x,y)) ;
		  
		 if ( dx > dy )
		 {
			 cumul = dx / 2 ;
			 
		     for ( i = 1 ; i <= dx ; i++ )
		     {
		    	 x += xinc ;
		    	 cumul += dy ;
		      
		    	 if ( cumul >= dx )
		    	 {
		    		 cumul -= dx ;
		    		 y += yinc ;
		    	 }
		    	
		    	 res.add(new Position2D(x,y)) ; 
		     }
		 }
		 else
		 {
			 cumul = dy / 2 ;
		     for ( i = 1 ; i <= dy ; i++ )
		     {
		    	 y += yinc ;
		    	 cumul += dx ;
		      
		    	 if ( cumul >= dy )
		    	 {
		    		 cumul -= dy ;
		    		 x += xinc ;
		    	 }
		    	 
		    	 res.add(new Position2D(x,y));
		     }
		 }
		    	 
		  return res;
	}
		
	
	public static Vector<Position2D> positionEntre( Position2D pos1, Position2D pos2 )
	{
		return bresenham(pos1.m_l,pos1.m_c,pos2.m_l,pos2.m_c);
	}
	
	/*public static Vector<Position2D> positionEntre( Position2D pos1, Position2D pos2 )
	{
		
		Vector<Position2D> res = new Vector<Position2D>();
		Position2D pos=new Position2D(0,0);
		double a=0;
		
		if(pos2.m_c != pos1.m_c)
		{	
			a= (double)(pos2.m_l-pos1.m_l)/(double)( pos2.m_c-pos1.m_c);
		}

		double b= pos1.m_l - a*pos1.m_c;
		
	
		for(pos.m_c=pos1.m_c;pos.m_c<pos2.m_c;pos.m_c++)
		{
			pos.m_l= (int)(a*pos.m_c +b);
			res.add(new Position2D(pos.m_l,pos.m_c));
			
		}
		
		return res;
	}
	*/
	public boolean estPlusGrand(Position2D target)
	{
		return this.getDistanceTo(Position2D.ORIGINE) > target.getDistanceTo(Position2D.ORIGINE);
	}
	
	public static void swap(Position2D pos1,Position2D pos2)
	{
		Position2D tmp=pos1.clone();
		pos1=pos2.clone();
		pos2=tmp.clone();
		
		
	}

	/**
	 * 
	 * @param cible
	 * @return
	 */
	public boolean estEgal( Position2D cible )
	{
		if ( this.m_l == cible.m_l && this.m_c == cible.m_c ) return true;
		return false;
	}

	/**
	 * Permet de cacluler la distance entre ce point et un autre
	 * @param cible
	 * @return
	 */

	public float getDistanceTo( Position2D cible )
	{
		/*return (float) Math.sqrt(Math.pow(m_c - cible.m_c, 2)
				+ Math.pow(m_l - cible.m_l, 2));*/
		return Math.abs(cible.m_c - m_c)+2*Math.abs(cible.m_l-m_l);
	}
	
	public float getDistanceReelTo( Position2D cible )
	{
		return (float) Math.sqrt(Math.pow(m_l - cible.m_l, 2)
				+ Math.pow(m_c - cible.m_c, 2));
	}
	
	public String toString()
	{
		return "( "+m_l+", "+m_c+" )";
	}
	public Vector<Position2D> getVoisin()
	{
		Vector<Position2D> voisin= new Vector<Position2D>();
		Position2D haut=new Position2D(m_l-1,m_c);
		Position2D bas=new Position2D(m_l+1,m_c);
		Position2D gauche=new Position2D(m_l,m_c-1);
		Position2D droite=new Position2D(m_l,m_c+1);
		
		voisin.add(haut);
		voisin.add(bas);
		voisin.add(gauche);
		voisin.add(droite);
	
		return voisin;
	}
}
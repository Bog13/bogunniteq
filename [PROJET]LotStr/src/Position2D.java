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
	
	
	
	public static Vector<Position2D> positionEntre( Position2D pos1, Position2D pos2 )
	{
		
		Vector<Position2D> res = new Vector<Position2D>();
		Position2D pos=new Position2D(0,0);
		
		double a= (double)(pos2.m_c-pos1.m_c )/(double)( pos2.m_l-pos1.m_l);
			
		double b= pos1.m_c - a*pos1.m_l;
			
		for(pos.m_l=pos1.m_l+1;pos.m_l<=pos2.m_l-1;pos.m_l++)
		{
				
			pos.m_c=(int)(a*pos.m_l + b );
			res.add(new Position2D(pos.m_l,pos.m_c));
		}
		
		return res;
	}
	
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
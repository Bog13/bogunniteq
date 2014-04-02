public abstract class Perso
{
	protected float m_vie;
	protected String m_nom;
	protected Position2D m_position;
	protected char m_rendu;
	protected Monde m_monde;
	
	public void init(Monde monde)
	{
		m_monde=monde;
		m_nom="";
		m_position = new Position2D();
		m_vie=100;
		m_rendu='C';
	}
	
	public Perso(Monde monde)
	{
		init(monde);
	}
	
	public Perso(String nom,Monde monde)
	{
		init(monde);
		m_monde=monde;
		m_nom=nom;
	}
	
	/**
	 * Afficher un personnage � l'�cran (CONSOLE)
	 */
	public void afficher()
	{
		System.out.print(m_rendu);
	}
	
	public String getNom()
	{
		return m_nom;
	}
	
	public Position2D getPosition()
	{
		return m_position;
	}
	
	abstract public boolean jouer();
	
	public void move(int x,int y)
	{
		int newX=m_position.getC()+x;
		int newY=m_position.getL()+y;
		Position2D pos=new Position2D(newX,newY);
		if(m_monde.estAccessible(pos))
		{
			m_position=pos;
		}
	}
	
}
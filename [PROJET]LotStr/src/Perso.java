public abstract class Perso
{
	protected int m_vie;
	protected int m_atk;
	protected int m_def;
	
	protected String m_nom;
	protected Position2D m_position;
	protected char m_look;
	protected Monde m_monde;
	private boolean m_vivant;
	
	
	
	public int getVie()
	{
		return m_vie;
	}
	
	public void kill()
	{
		m_vie=0;
		m_vivant=false;
	}
	
	public void setVie(int modif)
	{
		m_vie = modif;
	}
	
	public boolean estMort()
	{
		return (m_vie<=0);
	}
	
	public boolean estVivant()
	{
		return !(estMort());
	}
	
	public void takeDamage(int degat)
	{
		if(m_vie>degat)m_vie-=degat;
		else m_vie=0;
	}
	
	public void update()
	{
		if(estMort())m_vivant=false;
		else m_vivant=true;
		
	}
	
	public int getAtk()
	{
		return m_atk;
	}
	
	public int getDef()
	{
		return m_def;
	}
	
	public void init(Monde monde)
	{
		m_monde=monde;
		m_nom="";
		m_position = new Position2D();
		m_vie=100;
		m_look='A';
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
	 * Afficher un personnage à l'écran (CONSOLE)
	 */
	public void afficher()
	{
		System.out.print(m_look);
	}
	
	public String getNom()
	{
		return m_nom;
	}
	
	public char getLook() {return m_look;}
	
	public Position2D getPosition()
	{
		return m_position;
	}
	
	public void setPosition(Position2D pos)
	{
		m_position=pos;
	}
	
	abstract public boolean jouer();

	public void move(int x,int y)
	{
		int newX=m_position.getL()+x;
		int newY=m_position.getC()+y;
		Position2D pos=new Position2D(newX,newY);
		
		if(m_monde.estAccessible(pos) && estVivant())
		{
			m_position=pos;
		}
	}
	
}
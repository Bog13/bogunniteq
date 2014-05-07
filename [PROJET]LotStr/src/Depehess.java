public class Depehess extends Pnj
{
	public Depehess (Monde monde)
	{
		super(monde);
		m_vie = 1;
		m_look = 'h';
		m_nom = "Depehess";
		m_atk = 20;
		m_def = 2;
		
	}
	
	public Depehess (Monde monde,Position2D pos)
	{
		super(monde);
		m_position=pos;
	}
	
	public void seBallader()
	{
		int x=Outil.hasard(-2,2);
		int y=Outil.hasard(-2,2);
	
		move(x,y);
	}
	
	public boolean jouer()
	{
		//seBallader();
		return true;
	}

	
	
	
	
}

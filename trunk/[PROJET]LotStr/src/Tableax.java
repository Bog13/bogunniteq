public class Tableax extends Pnj
{
	public Tableax (Monde monde)
	{
		super(monde);
		m_vie = 500;
		m_look = 'l';
		m_nom = "Tableax";
		m_atk = 50;
		m_def = 2;
		
	}
	
	public Tableax (Monde monde,Position2D pos)
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

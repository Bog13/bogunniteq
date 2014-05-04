
public class Pnj extends Perso 
{
	public Pnj (Monde monde)
	{
		super(monde);
		m_vie = 18;
		m_look = 'W';
		m_nom = "un monstre";
		m_atk = 3;
		m_def = 1;
		
	}
	
	public Pnj (Monde monde,Position2D pos)
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

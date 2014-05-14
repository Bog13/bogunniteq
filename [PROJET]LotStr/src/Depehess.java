public class Depehess extends Pnj
{
	public Depehess (Monde monde)
	{
		super(monde);
		m_vie = Global.DEPEHESS_VIE;
		m_look = 'h';
		m_nom = "Depehess";
		m_atk = Global.DEPEHESS_ATK;;
		m_def = Global.DEPEHESS_DEF;;
		
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
		super.jouer();
		return true;
	}

	
	
	
	
}

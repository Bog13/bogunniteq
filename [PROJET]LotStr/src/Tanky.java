public class Tanky extends Pnj
{
	public Tanky (Monde monde)
	{
		super(monde);
		m_vie = Global.TANKY_VIE;
		m_look = 'W';
		m_nom = "Tanky";
		m_atk = Global.TANKY_ATK;
		m_def = Global.TANKY_DEF;
		
	}
	
	public Tanky (Monde monde,Position2D pos)
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

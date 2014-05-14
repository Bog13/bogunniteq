public class Tableax extends Pnj
{
	public Tableax (Monde monde)
	{
		super(monde);
		m_vie = Global.TABLEAX_VIE;;
		m_look = 'l';
		m_nom = "Tableax";
		m_atk = Global.TABLEAX_ATK;
		m_def = Global.TABLEAX_DEF;
		
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
		super.jouer();
		return true;
	}

	
	public void loot()
	{
		m_monde.getCase(getPosition()).mettreItem(new Darkness());
		m_monde.placerSuperTableax();
	}
	
	
}

public class SuperTableax extends Pnj
{
	public SuperTableax (Monde monde)
	{
		super(monde);
		m_vie = Global.SUPER_TABLEAX_VIE;
		m_look = 'f';
		m_nom = "Super Tableax";
		m_atk = Global.SUPER_TABLEAX_ATK;
		m_def = Global.SUPER_TABLEAX_DEF;
		
	}
	
	public SuperTableax (Monde monde,Position2D pos)
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
		seBallader();
		return true;
	}

	public void placerAnneau()
	{
		Position2D pos=null;
		
		if( m_monde.estAccessible(m_position.getVoisin().get(0)) )
		{
			pos=m_position.getVoisin().get(0);
		}
		else if( m_monde.estAccessible(m_position.getVoisin().get(1)) )
		{
			pos=m_position.getVoisin().get(1);
		}
		else if( m_monde.estAccessible(m_position.getVoisin().get(2)) )
		{
			pos=m_position.getVoisin().get(2);
		}
		else if( m_monde.estAccessible(m_position.getVoisin().get(3)) )
		{
			pos=m_position.getVoisin().get(3);
		}
		else pos=m_monde.positionLibre();
			
		m_monde.placerAnneau(pos);
		m_monde.initPosition();
	}
	
	
	public void loot()
	{
		m_monde.getCase(getPosition()).mettreItem(new Darkness());
		placerAnneau();
	}
	
	
}

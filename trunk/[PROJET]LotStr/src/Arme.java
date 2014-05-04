
public abstract class Arme extends Item
{
	protected int m_degat;
	
	public Arme()
	{
		super('a');
		m_look='a';
		
		initVar();
	}
	
	public boolean estEquipe()
	{
		if(m_owner != null)
		{
			if(m_owner.getArme() == this) return true;
		}
		return false;
	}
	
	public void initVar()
	{
		m_nom="Arme de base";
		m_description="Arme prototype";
		m_degat=0;
		m_poid=0;
	}
	
	public int getAtk()
	{
		return m_degat;
	}
	
	public Arme(char look)
	{
		super(look);
		initVar();
	}
	
	public void use()
	{
		m_owner.setArme(this);
	}
	
		//m_owner.setVie(m_owner.getVie()+m_puissance);
	
	
	
	
	
	
	
	
	
}

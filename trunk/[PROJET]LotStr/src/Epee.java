

public class Epee extends Arme
{
	protected int m_degat;
	
	public Epee()
	{
		super('a');
		m_look='e';
		
		initVar();
	}
	
	public void initVar()
	{
		m_nom="Ep�e";
		m_description="Ep�e de base.";
		m_degat=5;
		m_poid=10;
	}
	
	public int getAtk()
	{
		return m_degat;
	}
	
	public Epee(char look)
	{
		super(look);
		initVar();
	}
	
	public  void use()
	{
		super.use();
	}
	
		//m_owner.setVie(m_owner.getVie()+m_puissance);
	
	
	
	
	
	
	
	
	
}

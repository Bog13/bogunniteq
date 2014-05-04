

public class Sabre extends Arme
{
	protected int m_degat;
	
	public Sabre()
	{
		super('a');
		m_look='s';
		
		initVar();
	}
	
	public void initVar()
	{
		m_nom="Sabre";
		m_description="Sabre royal.";
		m_degat=5;
		m_poid=10;
	}
	
	public int getAtk()
	{
		return m_degat;
	}
	
	public Sabre(char look)
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

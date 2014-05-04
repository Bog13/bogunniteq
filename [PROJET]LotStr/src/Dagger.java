

public class Dagger extends Arme
{
	protected int m_degat;
	
	public Dagger()
	{
		super('q');
		m_look='r';
		
		initVar();
	}
	
	public void initVar()
	{
		m_nom="Dague";
		m_description="Petite lame.";
		m_degat=5;
		m_poid=10;
	}
	
	public int getAtk()
	{
		return m_degat;
	}
	
	public Dagger(char look)
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

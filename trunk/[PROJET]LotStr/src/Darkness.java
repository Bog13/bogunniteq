

public class Darkness extends Arme
{
	protected int m_degat;
	
	public Darkness()
	{
		super('a');
		m_look='q';
		
		initVar();
	}
	
	public void initVar()
	{
		m_nom="Darkness";
		m_degat=5;
		m_poid=10;
	}
	
	public static String describe()
	{
		return "Epée épique ultime.";
	}
	
	public int getAtk()
	{
		return m_degat;
	}
	
	public Darkness(char look)
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

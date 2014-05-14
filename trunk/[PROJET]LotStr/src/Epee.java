

public class Epee extends Arme
{
	protected int m_degat;
	
	public Epee()
	{
		super('a');
		m_look='e';
		
		initVar();
	}
	
	public static String describe()
	{
		return "Epée de base.";
	}
	
	public void initVar()
	{
		m_nom="Epée";
		
		m_degat=Global.EPEE_DEGAT;
		m_poid=Global.EPEE_POIDS;;
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

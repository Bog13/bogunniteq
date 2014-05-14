

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
		m_degat=Global.DAGGER_DEGAT;
		m_poid=Global.DAGGER_POIDS;;
	}
	
	public static String describe()
	{
		return "Petite Lame";
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

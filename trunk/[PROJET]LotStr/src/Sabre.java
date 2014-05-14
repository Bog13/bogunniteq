

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
		m_degat=Global.SABRE_DEGAT;
		m_poid=Global.SABRE_POIDS;;
	}
	
	public static String describe()
	{
		return "Un sabre.";
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

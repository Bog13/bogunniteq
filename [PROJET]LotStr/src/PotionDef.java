
public class PotionDef extends Potion
{
	private int m_puissance;
	
	public PotionDef()
	{
		super('p');
		m_puissance =Global.POTION_DEF;
		m_look='d';
		m_nom="Potion de Défense";
		initVar();
	}
	
	public static String describe()
	{
		return "Une potion de defense";
	}
	
	public void initVar()
	{
		super.initVar();
	}
	
	public PotionDef(char look)
	{
		super(look);
		initVar();
	}
	
	public void use()
	{
		m_owner.setDef(m_owner.getDef()+m_puissance);
	}
	
	
	
	
	
	
	
	
}

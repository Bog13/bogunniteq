
public class PotionVie extends Potion
{
	private int m_puissance;
	
	public PotionVie()
	{
		super('p');
		m_puissance =Global.POTION_VIE;
		m_look='v';
		m_nom="Potion de Santé";
		initVar();
	}
	
	public static String describe()
	{
		return "Une potion de vie";
	}
	
	public void initVar()
	{
		super.initVar();
	}
	
	public PotionVie(char look)
	{
		super(look);
		initVar();
	}
	
	public void use()
	{
		m_owner.setVie(m_owner.getVie()+m_puissance);
	}
	
	
	
	
	
	
	
	
}

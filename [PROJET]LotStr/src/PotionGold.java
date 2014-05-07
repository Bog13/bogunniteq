
public class PotionGold extends Potion
{
	private int m_puissance;
	
	public PotionGold()
	{
		super('p');
		m_puissance =Global.POTION_GOLD;
		m_look='g';
		m_nom="Potion d'Argent";
		initVar();
	}
	
	public static String describe()
	{
		return "Une potion d'argent";
	}
	
	public void initVar()
	{
		super.initVar();
	}
	
	public PotionGold(char look)
	{
		super(look);
		initVar();
	}
	
	public void use()
	{
		if(m_owner instanceof Joueur)
		{
			((Joueur) m_owner).earnGold(m_puissance);
		}
	}
	
	
	
	
	
	
	
	
}



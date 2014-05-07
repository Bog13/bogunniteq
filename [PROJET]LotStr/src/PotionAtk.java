
public class PotionAtk extends Potion
{
	private int m_puissance;
	
	public PotionAtk()
	{
		super('p');
		m_puissance =Global.POTION_ATK;
		m_look='a';
		m_nom="Potion d'Attaque";
		
		initVar();
	}
	
	public static String describe()
	{
		return "Une potion d'attaque";
	}
	
	public void initVar()
	{
		super.initVar();
	}
	
	public PotionAtk(char look)
	{
		super(look);
		initVar();
	}
	
	public void use()
	{
		m_owner.setAtk(m_owner.getAtk()+m_puissance);
	}
	
	
	
	
	
	
	
	
}



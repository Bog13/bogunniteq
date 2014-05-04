
public class PotionGold extends Potion
{
	private int m_puissance;
	
	public PotionGold(int p)
	{
		super('p');
		m_puissance =p;
		m_look='g';
		m_nom="Potion d'Argent";
		m_description="Une potion d'argent";
		initVar();
	}
	
	public void initVar()
	{
		
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



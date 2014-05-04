
public class PotionAtk extends Potion
{
	private int m_puissance;
	
	public PotionAtk(int p)
	{
		super('p');
		m_puissance =p;
		m_look='a';
		m_nom="Potion d'Attaque";
		m_description="Une potion d'attaque";
		initVar();
	}
	
	public void initVar()
	{
		
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



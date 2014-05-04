
public class PotionVie extends Potion
{
	private int m_puissance;
	
	public PotionVie(int p)
	{
		super('p');
		m_puissance =p;
		m_look='v';
		m_nom="Potion de Santé";
		m_description="Une potion de vie";
		initVar();
	}
	
	public void initVar()
	{
		
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

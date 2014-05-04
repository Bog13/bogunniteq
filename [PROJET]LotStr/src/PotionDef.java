
public class PotionDef extends Potion
{
	private int m_puissance;
	
	public PotionDef(int p)
	{
		super('p');
		m_puissance =p;
		m_look='d';
		m_nom="Potion de Défense";
		m_description="Une potion de défense";
		initVar();
	}
	
	public void initVar()
	{
		
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

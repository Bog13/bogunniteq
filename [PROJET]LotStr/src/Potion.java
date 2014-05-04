
public abstract class Potion extends Item
{
	private int m_puissance;
	
	public Potion(int p)
	{
		super('p');
		m_puissance =p;
		m_look='p';
		m_nom="Potion de Santé n°";
		m_description="Une potion aux effets inconnus";
		initVar();
	}
	
	public void initVar()
	{
		m_puissance=2;
	}
	
	public Potion(char look)
	{
		super(look);
		initVar();
	}
	
	public abstract void use();
	
		//m_owner.setVie(m_owner.getVie()+m_puissance);
	
	
	
	
	
	
	
	
	
}

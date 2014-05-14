
public abstract class Potion extends Item
{
	private int m_puissance;
	
	public Potion(int p)
	{
		super('p');
		m_puissance =p;
		m_look='p';
		m_nom="Potion de Santé n°";
	
		initVar();
	}
	
	public int getPuissance() {return m_puissance;}
	
	public void initVar()
	{
		m_puissance=2;
		m_poid=Global.POTION_POIDS;
	}
	
	public static String describe()
	{
		return "Potion.";
	}
	
	public Potion(char look)
	{
		super(look);
		initVar();
	}
	
	public abstract void use();
	
		//m_owner.setVie(m_owner.getVie()+m_puissance);
	
	
	
	
	
	
	
	
	
}

/*
	auteur: BoG
	date: 7/3/14
*/

public class Case
{
	protected char m_id;
	protected Position2D m_position;
	protected Item m_item;
	protected boolean m_estVisible;
	protected boolean m_estObstacle;
	protected boolean m_estLumineux;
	
	
	public Case(char id)
	{
		m_id=id;
		m_position= new Position2D();
		this.initPropriete();

	}
	
	/**
	 * affiche une case dans la console
	 */
	public void afficher()
	{
		System.out.print(m_id);
	}
	
	public void initPropriete()
	{
		m_estVisible=false;
		switch(m_id)
		{
			case '0':///SOL
				m_estObstacle=false;
				m_estLumineux=false;
				break;
				
			case 'M':///MUR
				m_estObstacle=true;
				m_estLumineux=false;
				break;
				
			case 'T':///TORCHE
				m_estObstacle=false;
				m_estLumineux=false;//mais peut être activée !
				break;
			
		}
	}
	
	/**
	 * 
	 */
	public String toString()
	{
		return m_id+"";
	}
	
	/**
	 * 
	 * @return ID de la Case
	 */
	public char getId()
	{
		return m_id;
	}
	
	public boolean estObstacle()
	{
		return m_estObstacle;
	}
	
	public boolean estLumineux()
	{
		return m_estLumineux;
	}
	
	public boolean estVisible()
	{
		return m_estVisible;
	}
	
	public void setVisible(boolean b)
	{
		m_estVisible=b;
	}
	
	public void setId(char id) {m_id=id;}
	
}
/*
	auteur: BoG
	date: 7/3/14
*/

public class Case
{
	protected char m_id;
	protected char m_look;
	protected Position2D m_position;
	protected Item m_item;
	protected boolean m_estVisible;
	protected boolean m_estObstacle;
	protected boolean m_estLumineux;
	
	
	public Case(char id)
	{
		Init(id);
		this.initPropriete(id);

	}
	
	public void Init(char id)
	{
		m_id=id;
		m_look='@';
		m_position= new Position2D();
	}
	
	public Case(char id,char look)
	{
		
		Init(id);
		m_look=look;
		this.initPropriete(id);

	}
	
	/**
	 * affiche une case dans la console
	 */
	public void afficher()
	{
		System.out.print(m_look);
	}
	
	public void change(Case c)
	{
		
		m_look=c.getLook();
		this.initPropriete(c.getId());
	}
	
	public void initPropriete(int id)
	{
		m_estVisible=false;
		switch(id)
		{
			case '0':///SOL
				m_estObstacle=false;
				m_estLumineux=false;
				m_look=' ';
				break;
				
			case 'M':///MUR
				m_estObstacle=true;
				m_estLumineux=false;
				m_look='X';
				break;
				
			case 'T':///TORCHE
				m_estObstacle=false;
				m_estLumineux=false;//mais peut être activée !
				m_look='t';
				break;
			
			case 'P':///PORTE
				m_estObstacle=false;
				m_estLumineux=false;
				m_look='P';
				break;
			default:
				m_estObstacle=false;
				m_estLumineux=false;
				m_look='@';
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
	public void setLook(char look) {m_look=look;}
	public char getLook() {return m_look;}

	public void setObstacle( boolean b )
	{
		m_estObstacle=b;
		
	}

	
	
}
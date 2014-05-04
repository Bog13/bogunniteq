
public class CombatGraphique implements Combat_TypeCombat
{
	private int m_touche=0;
	final int NONE=-1;
	
	public CombatGraphique()
	{
		m_touche=NONE;
	}
	
	public void setTouche(int t)
	{
		m_touche=t;
		
	}
	
	public void reinitTouche()
	{
		m_touche=NONE;
	}
	
	public int choixAction()
	{
		while(m_touche==NONE)
		{
			
		}
		
		return m_touche;
	}

}


public class Torche extends Activable
{

	public Torche(char id)
	{
		super(id);
	}
	
	/**
	 * Active "éternellement"
	 */
	public void activer()
	{
		m_estLumineux=true;
		m_on=true;
	}
	
	/**
	 * Active pour un temps "timer"
	 */
	public void activer(int timer)
	{
		m_estLumineux=true;
		m_on=true;
		m_timer=timer;
	}
	
	
	public void desactiver()
	{
		m_estLumineux=false;
		m_on=false;
		m_timer=-1;
	}
	
	public void update()
	{
		if(m_timer>-1)m_timer--;
		else{ desactiver(); }
		
		if(m_on)m_look='T';
		else m_look='t';
	}

}

/*
	auteur: BoG
	date: 7/3/14
 */

public abstract class Activable extends Case
{
	protected boolean	m_on;
	protected int		m_timer;


	public Activable(char id)
	{
		super(id);
		m_timer = -1;
		m_on = false;
	}
	
	
	
	

	public void activer()
	{
		m_on=true;
	}

	
	
	public void activer(int timer)
	{
		m_on=true;
		m_timer=timer;
	}
	
	
	public void desactiver()
	{
		m_on=false;
		m_timer=-1;
	}
	
	public void update()
	{
		if(m_timer>-1)m_timer--;
		else{ desactiver(); }
	}

	public boolean estActif()
	{
		return m_on;
	}
}
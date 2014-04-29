
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
		super.activer(Global.TORCHE_DUREE);
		m_estLumineux=true;
	}
	
	
	public void desactiver()
	{
		super.desactiver();
		m_estLumineux=false;
		
	}
	
	public void update()
	{
		super.update();
		
		if(m_on)m_look='T';
		else m_look='t';
	}

}

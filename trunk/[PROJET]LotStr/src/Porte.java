
public class Porte extends Activable
{

	public Porte(char id)
	{
		super(id);
	}
	
	public void activer(int timer)
	{
		super.activer(timer);

	}
	
	
	public void desactiver()
	{
		super.desactiver();
		
		
	}
	
	public void update()
	{
		super.update();
		
		if(m_on)m_look='/';
		else m_look='_';
	}
	
}

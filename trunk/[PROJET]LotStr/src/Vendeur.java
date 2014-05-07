
public class Vendeur extends Activable
{

	public Vendeur(char id)
	{
		super('b');
	}
	
	public void activer(int timer)
	{
		super.activer(timer);
		Ecran.PASSER_MODE_ACHAT();
	}
	
	
	public void desactiver()
	{
		super.desactiver();
		
		
	}
	
	public void update()
	{
		super.update();
		
		if(m_on)m_look='b';
		else m_look='B';
	}
	
}

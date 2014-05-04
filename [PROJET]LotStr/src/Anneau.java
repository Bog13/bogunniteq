import java.util.ArrayList;
public class Anneau extends Activable 
{
	private ArrayList<ObservateurMonde> m_obsList=new ArrayList<ObservateurMonde>();
	
	public Anneau(char id)
	{
		super(id);
		m_look='O';
	}

	
	public void activer()
	{
		super.activer();
		Global.ringFound=true;
	}
	
	public void update()
	{
		super.update();
		
		/*if(!m_on)
		{
			m_look='O';
		}
		else
		{
			Global.ringFound=true;
		}*/
	}
	
	


	
	

}

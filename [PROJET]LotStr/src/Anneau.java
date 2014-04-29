import java.util.ArrayList;
public class Anneau extends Activable 
{
	private ArrayList<Observateur> m_obsList=new ArrayList<Observateur>();
	
	public Anneau(char id)
	{
		super(id);
	
	}

	

	
	public void update()
	{
		super.update();
		if(!m_on)
		{
			m_look='O';
		}
		else
		{
			Global.ringFound=true;
		}
	}
	
	


	
	

}

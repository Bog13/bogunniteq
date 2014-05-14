import java.util.ArrayList;
import java.util.Vector;


public class Pnj extends Perso 
{
	public Pnj (Monde monde)
	{
		super(monde);
		m_vie = 18;
		m_look = 'W';
		m_nom = "un monstre";
		m_atk = 3;
		m_def = 1;
		
	}
	
	public Pnj (Monde monde,Position2D pos)
	{
		super(monde);
		m_position=pos;
	}
	
	public void seBallader()
	{
		int hsd=Outil.hasard(0,4);
		
		switch(hsd)
		{
			case 0:move(-1,0);
				break;
				
			case 1:move(1,0);
			break;
			
			case 2:move(0,-1);
			break;
			
			case 3:move(0,1);
			break;
		}
		
		
	}
	
	
	public boolean jouer()
	{
		
		//seBallader();
		

		return true;
	}
	
	public void loot()
	{
		m_monde.getCase(getPosition()).mettreItem(Outil.hasardItem());
	}

	
	
	
	
}


public class Pnj extends Perso 
{
	public Pnj (Monde monde)
	{
		super(monde);
		m_vie = 4;
		m_rendu = 'W';
		m_nom = "Tamèr";
		m_atk = 3;
		m_def = 1;
		
	}
	
	public Pnj (Monde monde, int id, Position2D pos)
	{
		super(monde);
		m_position = pos;
		switch (id)
		{
		case 0 :
			m_vie = 4;
			m_rendu = 'W';
			m_nom = "Tamèr";
			m_atk = 3;
			m_def = 1;
			break;
		case 1 :
			m_vie = 6;
			m_rendu = 'W';
			m_nom = "Blob";
			m_atk = 2;
			m_def = 2;
			break;
		case 3 :
			m_vie = 15;
			m_rendu = 'W';
			m_nom = "Sonrot";
			m_atk = 4;
			m_def = 3;
			break;
		}
		
		
	}

	public Pnj (Monde monde,Position2D pos)
	{
		super(monde);
		m_position=pos;
	}
	
	public boolean jouer()
	{
		System.out.println(m_nom + " a joué");
		return true;
	}
	
	
	
}

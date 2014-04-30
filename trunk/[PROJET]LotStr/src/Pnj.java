
public class Pnj extends Perso 
{
	public Pnj (Monde monde)
	{
		super(monde);
		m_vie = 4;
		m_rendu = 'W';
		m_nom = "???";
		m_atk = 3;
		m_def = 1;
		
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

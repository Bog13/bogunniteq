
public class MonstreBoss  extends Pnj
{
	public MonstreBoss (Monde monde, Position2D pos)
	{
		super(monde);
		String noms[]= {"Sonrau","Nabil Boussaid","Sanrouma","Gumlau"}  ;
		m_vie = 15;
		m_look = 'W';
		m_nom = noms[(int)(Math.random()*noms.length)];
		m_atk = 4;
		m_def = 3;
		
		m_position = pos;
		
	}

}

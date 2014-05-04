
public class MonstreAgr extends Pnj
{
	public MonstreAgr (Monde monde, Position2D pos)
	{
		super(monde);
		String noms[]= {"Grrr","Bérenger","Roar","blabla"}  ;
		m_vie = 4;
		m_look = 'W';
		m_nom = noms[(int)(Math.random()*noms.length)];
		m_atk = 3;
		m_def = 1;
		
		m_position = pos;
		
	}
}

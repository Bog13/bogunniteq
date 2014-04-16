
public class MonstreDef extends Pnj
{
	public MonstreDef (Monde monde, Position2D pos)
	{
		super(monde);
		String noms[]= {"Blop","Blob","Grota","Tamère"}  ;
		m_vie = 6;
		m_rendu = 'W';
		m_nom = noms[(int)(Math.random()*noms.length)];
		m_atk = 2;
		m_def = 2;
		
		m_position = pos;
		
		
	}

}

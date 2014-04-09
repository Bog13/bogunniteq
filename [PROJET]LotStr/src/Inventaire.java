import java.util.Vector;

public class Inventaire
{
	private Vector<Item> m_content;
	
	public Inventaire()
	{
		m_content= new Vector<Item>();
	}
	
	public void add(Item it)
	{	
		m_content.add(it);
	}
}

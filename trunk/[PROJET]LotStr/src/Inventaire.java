import java.util.ArrayList;

public class Inventaire
{
	private ArrayList<Item> m_content;
	
	public Inventaire()
	{
		m_content= new ArrayList<Item>();
	}
	
	public void add(Item it)
	{	
		m_content.add(it);
	}
	
	public ArrayList<Item> lister()
	{
		
		ArrayList<Item> al=new ArrayList<Item>();
		
		for(Item item: m_content)
		{
		
			al.add(item);
		}
		
		return al;
	}
	
	public ArrayList<String> listerNom()
	{
		ArrayList<String> al=new ArrayList<String>();
		
		for(Item item: m_content)
		{
			al.add(item.getNom());
		}
		
		return al;
	}
}

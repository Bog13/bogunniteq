import java.util.ArrayList;

public class Inventaire
{
	private ArrayList<Item> m_content;
	private Perso m_owner;
	
	public Inventaire(Perso owner)
	{
		m_owner=owner;
		m_content= new ArrayList<Item>();
	}
	
	
	public void add(Item it)
	{	
		m_content.add(it);
		it.setOwner(m_owner);
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
	
	public int getQuantite()
	{
		return m_content.size();
	}
	
	public void use(Item it)
	{
		it.use();
		m_content.remove(it);		
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

import java.util.ArrayList;

public class Inventaire
{
	private ArrayList<Item> m_content;
	private Perso m_owner;
	private int m_poid;
	private boolean m_estPlein;
	private boolean m_estVide;
	
	public Inventaire(Perso owner)
	{
		m_estPlein=false;
		m_estVide=true;
		m_poid=0;
		m_owner=owner;
		m_content= new ArrayList<Item>();
	}
	
	public int getPoid() {return m_poid;}
	
	public boolean estVide() {return m_estVide;}
	public boolean estPlein() {return m_estPlein;}
	
	public void add(Item it)
	{	
		if(it.getPoid()+m_poid <= Global.MAX_INVENTAIRE)
		{
			m_estVide=false;
			m_content.add(it);
			it.setOwner(m_owner);
			m_poid+=it.getPoid();
		}
		
		if(m_poid==Global.MAX_INVENTAIRE)m_estPlein=true;
		
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
		if(!(it instanceof Arme))
		{
			it.use();
			m_poid-=it.getPoid();
			m_content.remove(it);
			if(m_content.size()==0)m_estVide=true;
			m_estPlein=false;
		}
		else
		{
			it.use();
		}
		
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

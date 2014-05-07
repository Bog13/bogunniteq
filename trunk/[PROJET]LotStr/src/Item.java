/*
	auteur: BoG
	date: 7/3/14
*/

public class Item 
{
	protected char m_look;
	protected Perso m_owner;
	protected String m_nom;
	protected int m_poid;
	
	public Item()
	{
		init('i');
	}
	
	public Item(char look)
	{
		init(look);
	}
	
	public void use() {};
	
	public void init(char look)
	{
		m_poid=0;
		m_nom="item";
		m_look=look;
		m_owner=null;
	}
	
	public int getPoid()
	{
		return m_poid;
	}
	
	public String getNom()
	{
		return m_nom;
	}
	
	
	public static String describe()
	{
		return "";
	}
	
	public void ramasser(Perso perso)
	{
		m_owner=perso;
	}
	
	public char rendu()
	{
		return m_look;
	}
	
	public char getLook()
	{
		return m_look;
	}

	public void setLook( char m_look )
	{
		this.m_look = m_look;
	}

	public Perso getOwner()
	{
		return m_owner;
	}

	public void setOwner( Perso m_owner )
	{
		this.m_owner = m_owner;
	}
}
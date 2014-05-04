
public class Contenance
{
	private Item m_item;
	private int m_quantite;
	
	public Contenance(Item it, int nb)
	{
		m_item=it;
		m_quantite=nb;
	}
	
	public void addOnce()
	{
		m_quantite++;
	}
	
	
}

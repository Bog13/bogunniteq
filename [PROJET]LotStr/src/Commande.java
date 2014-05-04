
public class Commande
{
	private Joueur m_joueur;
	
	public Commande(Joueur joueur)
	{
		Global.FENETRE.setCommande(this);
		m_joueur=joueur;
	}
	
	public void moveUp()
	{
		m_joueur.move(-1,0);
	}
	public void moveDown()
	{
		m_joueur.move(1,0);
	}
	public void moveLeft()
	{
		m_joueur.move(0,-1);
	}
	public void moveRight()
	{
		m_joueur.move(0,1);
	}
	public void prendre()
	{
		m_joueur.prendreItemCase();
	}
	
}

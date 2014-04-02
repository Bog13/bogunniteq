/*
	auteur: BoG
	date: 7/3/14
 */

public abstract class Activable extends Case
{
	protected boolean	m_on;
	protected int		m_timer;

	public Activable(char id)
	{
		super(id);
		m_timer = -1;
		m_on = false;
	}

	public abstract void activer();

	public abstract void activer( int timer );

	public abstract void desactiver();

	public abstract void update();

	public boolean estActif()
	{
		return m_on;
	}
}
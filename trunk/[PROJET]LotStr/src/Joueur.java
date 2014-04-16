import java.util.Scanner;
public class Joueur extends Perso
{
	private int m_or;
	private Inventaire m_inventaire;
	
	public void init()
	{
		m_inventaire=new Inventaire();
		m_or=0;
		m_vie = Global.VIE_JOUEUR ;
		m_rendu='C';
		m_nom = Global.NOM_JOUEUR;
		m_atk = Global.ATK_JOUEUR;
		m_def = Global.DEF_JOUEUR;
	}
	public Joueur(Monde monde)
	{
		super(monde);
		init();
	}
	
	public void prendreItem(Item it)
	{
		if( it instanceof PieceOr)
		{
			m_or++;
		}
		else
		{
			m_inventaire.add(it);
		}
		
	}
	
	public Case getCase()
	{
		return m_monde.getCase(m_position);
	}
	
	/**
	 * prend un item present sur la case courante 
	 * si case vide, retourne false
	 * @return
	 */
	public boolean prendreItemCase()
	{
		if(!getCase().hasItem() )
		{
			return false;
		}

		prendreItem( getCase().getItem());
		
		getCase().retirerItem();
		return true;
	}
	
	public Joueur(Monde monde,Position2D pos)
	{
		super(monde);
		m_position=pos;
		init();
	}
	
	public Joueur(String nom,Monde monde)
	{
		super(nom,monde);
		init();
	}
	
	/**
	 * Retourne False si le jeu ne doit pas continuer, True pour passer au tour suivant
	 */
	public boolean jouer()
	{
		Scanner sc=new Scanner(System.in);
		String saisie = new String();

		boolean saisieCorrect=false;
		
		do
		{
			saisie=sc.next();
			
			saisieCorrect=  (saisie.compareTo("p")==0) ||
							(saisie.compareTo("8")==0) ||
							(saisie.compareTo("5")==0) ||
							(saisie.compareTo("4")==0) ||
							(saisie.compareTo("6")==0) ||
							(saisie.compareTo("exit")==0) ;
		}
		while (!saisieCorrect);
		
		if(saisie.compareTo("exit")==0)return false;
		if(saisie.compareTo("8")==0  ) {move(-1,0);}
		if(saisie.compareTo("5")==0 ) {move(1,0);}
		if(saisie.compareTo("4")==0 ) {move(0,-1);}
		if(saisie.compareTo("6")==0 ) {move(0,1);}
		if(saisie.compareTo("p")==0) {prendreItemCase();}
		
		
		
		return true;
	}
	
	public boolean jouerGraphique(String saisie)
	{
		/*while(!Global.FENETRE.getPanel().existeEvenementClavier())
		{
			System.out.print('.');
		}*/
		
		if(saisie.compareTo("exit")==0)return false;
		if(saisie.compareTo("8")==0 && m_monde.estAccessible(new Position2D(m_position,-1,0)) ) {move(-1,0);}
		if(saisie.compareTo("5")==0 && m_monde.estAccessible(new Position2D(m_position,1,0))) {move(1,0);}
		if(saisie.compareTo("4")==0 && m_monde.estAccessible(new Position2D(m_position,0,-1))) {move(0,-1);}
		if(saisie.compareTo("6")==0 && m_monde.estAccessible(new Position2D(m_position,0,1))) {move(0,1);}
		
		return true;
	}
	public Inventaire getInventaire()
	{
		return m_inventaire;
	}
	public void setInventaire( Inventaire m_inventaire )
	{
		this.m_inventaire = m_inventaire;
	}
	
	public void afficheHud()
	{
		System.out.println("[ Or: "+m_or+"]");
	}
	
	
}
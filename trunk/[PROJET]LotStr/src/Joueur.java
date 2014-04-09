import java.util.Scanner;
public class Joueur extends Perso
{
	public void init()
	{
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
		do
		{
			saisie=sc.next();
		}
		while ( (saisie.compareTo("exit")!=0) && (saisie.compareTo("8")!=0) && (saisie.compareTo("5")!=0) && (saisie.compareTo("4")!=0) && (saisie.compareTo("6")!=0) );
		
		if(saisie.compareTo("exit")==0)return false;
		if(saisie.compareTo("8")==0  ) {move(-1,0);}
		if(saisie.compareTo("5")==0 ) {move(1,0);}
		if(saisie.compareTo("4")==0 ) {move(0,-1);}
		if(saisie.compareTo("6")==0 ) {move(0,1);}
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
	
	
}
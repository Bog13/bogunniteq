import java.util.Scanner;
public class Joueur extends Perso
{
	public Joueur(Monde monde)
	{
		super(monde);
	}
	
	public Joueur(Monde monde,Position2D pos)
	{
		super(monde);
		m_position=pos;
	}
	
	public Joueur(String nom,Monde monde)
	{
		super(nom,monde);
	}
	
	/**
	 * Retourne False si le jeu ne doit pas continuer, True pour passer au tour suivant
	 */
	public boolean jouer()
	{
		Scanner sc=new Scanner(System.in);
		String saisie=sc.next();
		
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
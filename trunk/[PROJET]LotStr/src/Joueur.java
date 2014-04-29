import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JPanel;
public class Joueur extends Perso 
{
	private int m_or;
	private Inventaire m_inventaire;
	private String m_saisie=new String();
	
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
	
	public boolean jouerGraphique()
	{
	
		int key;
		
		do
		{
			key=Global.FENETRE.getKey();
		}
		while(key==-1);
		
		if(key==27 ) return false;
		else if(key==Global.KEY_HAUT) {move(-1,0);}
		else if(key==Global.KEY_BAS) {move(1,0);}
		else if(key==Global.KEY_GAUCHE) {move(0,-1);}
		else if(key==Global.KEY_DROITE) {move(0,1);}
		else if(key==80) {prendreItemCase();}
		
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
		System.out.println("[ Or: "+m_or+"]" + " [Vie: "+ m_vie+"]");
	}
	
	public String getKey()
	{
		String m_saisie=new String();
		
		switch(Global.FENETRE.getKey())
		{
			case 38://haut
				m_saisie="8";
				break;
			case 40://bas
				m_saisie="5";
				break;
			case 37://gauche
				m_saisie="4";
				break;
			case 39://droite
				m_saisie="6";
				break;
			case 27://haut
				m_saisie="exit";
				break;
				
			default:
				m_saisie="none";
				break;
		}
		return m_saisie;
	}
		
	

	
	
}
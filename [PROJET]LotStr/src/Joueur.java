import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
public class Joueur extends Perso implements ObservableJoueur
{
	private int m_or;
	private Inventaire m_inventaire;
	private String m_saisie=new String();
	private Joueur_TypeJeu m_typeJeu;
	private ArrayList<ObservateurJoueur> al_obs=null;

	
	public void initTypeJeu()
	{
		if(Global.MODE_GRAPHIQUE)
		{
			m_typeJeu= new JeuGraphique();
		}
		else
		{
			m_typeJeu= new JeuConsole(this);
		}
	}
	
	public void init()
	{
		m_arme=null;
		al_obs= new ArrayList<ObservateurJoueur>();
				
		initTypeJeu();
		m_inventaire=new Inventaire(this);
		m_or=0;
		m_vie = Global.VIE_JOUEUR ;
		m_look='C';
		m_nom = Global.NOM_JOUEUR;
		m_atk = Global.ATK_JOUEUR;
		m_def = Global.DEF_JOUEUR;
	}
	public Joueur(Monde monde)
	{
		super(monde);
		init();
		
	}
	
	public void earnGold(int or) {m_or+=or;}
	public void loseGold(int or) {m_or-=or;}
	public int getGold() {return m_or;}
	public void prendreItem(Item it)
	{
		if( it instanceof PieceOr)
		{
			m_or+=Global.VALEUR_OR;
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
		if(!getCase().hasItem())
		{
			return false;
		}
		
		if(getCase().getItem().getPoid() + m_inventaire.getPoid() > Global.MAX_INVENTAIRE)
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
		boolean jeuBool=m_typeJeu.jouer();	
		return jeuBool;
	
	}
	
	public void update()
	{
		super.update();
		if(m_arme!=null)m_atk=Global.ATK_JOUEUR+m_arme.getAtk();
		updateObs();
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
		if(!Global.MODE_GRAPHIQUE) 
		{
			
		}
		else
		{
			
			System.out.println("[ Or: "+m_or+"]" + " [Vie: "+ m_vie+"]");
		}
		
	}


	public void addObs( ObservateurJoueur obs )
	{
		al_obs.add(obs);
	}

	
	public void delObs()
	{
		al_obs= new ArrayList<ObservateurJoueur>();
		
	}


	public void updateObs()
	{
		for(ObservateurJoueur obs: al_obs)
		{
			obs.update(m_inventaire,m_vie,m_atk,m_def,m_or);
		}
		
	}

	
	
	
		
	

	
	
}
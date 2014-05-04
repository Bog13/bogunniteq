import java.util.ArrayList;
import java.util.Scanner;


public class Combat implements ObservableCombat
{
	private Perso m_agresseur;
	private Perso m_victime;
	private Perso m_gagnant;
	private boolean m_continuer;
	private String m_message;
	
	private ArrayList<ObservateurCombat> al_obs;
	private Combat_TypeCombat m_typeCombat;
	
	public Combat(Perso agresseur, Perso victime)
	{
		al_obs= new ArrayList<ObservateurCombat>();
		this.addObs((ObservateurCombat) Ecran.COMBAT);
		Global.MODE_COMBAT=true;
		m_message=("Un combat commence ! "+agresseur.getNom()+" attaque "+victime.getNom());
		m_continuer = true;
		m_agresseur = agresseur;
		m_victime = victime;
		m_gagnant=null;
		
		if(Global.MODE_GRAPHIQUE)m_typeCombat=new CombatGraphique();
		else m_typeCombat=new CombatConsole();
	}
	
	public void pause()
	{
		try
		{
			Thread.sleep(2000);
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}
	
	public Perso fight()
	{
		final int ATTAQUER=1;
		final int FUIR=2;
		
		while (m_continuer == true && m_gagnant==null)
		{
			update();
			switch(choixAction())
			{
				case ATTAQUER:
					attaquer();
					
					break;
					
				case FUIR:
					fuir();
					break;
			}
		
		}
		
		if(m_gagnant != null)m_message="Victoire de "+m_gagnant.getNom()+" !";
		else m_message="Pas de gagnant...";
		
		update();
		pause();
		Ecran.PASSER_MODE_JEU();
		return m_gagnant;
	}
	
	public void afficherStat()
	{
		System.out.println("Vous , "+m_agresseur.getNom()+"êtes face à un terrible monstre : "+m_victime.getNom()+" !!");
		System.out.println("Stats de "+m_agresseur.getNom()+" : \n\n\tAttaque : "+m_agresseur.getAtk()+"\n\tDefense : "+m_agresseur.getDef()+"\n\tVie : "+m_agresseur.getVie()+"\n");
		System.out.println("Stats de "+m_victime.getNom()+" : \n\n\tAttaque : "+m_victime.getAtk()+"\n\tDefense : "+m_victime.getDef()+"\n\tVie : "+m_victime.getVie()+"\n");
	}
	
	public int choixAction()
	{
		
		return m_typeCombat.choixAction();
		
	}

	public CombatGraphique getCombatGraphique()
	{
		return (CombatGraphique) m_typeCombat;
	}


	public void attaquer()
	{
		m_message=m_agresseur.getNom()+"attaque "+m_victime.getNom()+" !";
		
		if 	((m_agresseur.getAtk() - m_victime.getDef()) > 0)
		{
			Outil.debugMsg("CAS 1");
			m_victime.takeDamage( m_agresseur.getAtk() - m_victime.getDef() );	
		}
		else 
		{
			m_victime.takeDamage(1);
		}
		
		if(m_agresseur.estMort())m_gagnant=m_victime;
		else if(m_victime.estMort())m_gagnant=m_agresseur;
		
	
	}

	public void fuir() 
	{
		Outil.debugMsg("FUITE");
		m_continuer = false;
	}

	
	public void addObs( ObservateurCombat obs )
	{
		al_obs.add(obs);
	}

	
	public void delObs()
	{
		al_obs= new ArrayList<ObservateurCombat>();
		
	}

	public void update()
	{
		if(Global.MODE_GRAPHIQUE)
		{
			updateObs();
		}
		else
		{
			afficherStat();
		}
	}
	
	public void updateObs()
	{
		for(ObservateurCombat obs: al_obs)
		{
			obs.updateCombat(m_message,m_agresseur,m_victime);
		}
		
		
	}
	
}

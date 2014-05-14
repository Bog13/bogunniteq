/*
	auteur: BoG
	date: 7/3/14
*/

import java.util.Vector;

public class Jeu
{
	private boolean m_alive;
	private Vector<Perso> m_perso;
	private Monde m_monde;
	private Joueur m_joueur;
	private int m_tour;
	private boolean m_gagne;
	
	public Jeu(Monde monde)
	{
		m_tour=0;
		m_monde=monde;
		
		this.m_alive=true;
		m_gagne=false;
	}
	
	public void jouer()
	{
		int numPerso=0;
		
		intro();
		
		while(m_alive==true)
		{
			
			numPerso=m_tour% m_monde.getNbPerso();
			
			///UPDATE
			this.update();
			
			
	
			///AFFICHAGE
			if(!Global.MODE_GRAPHIQUE)
			{
				if (numPerso ==0 && m_alive)
				{
					this.afficher();
					System.out.println("TOUR "+m_tour+": "+ m_monde.getPerso(numPerso).getNom() )  ;
				}
			}
			///JOUEURS
			//m_alive= m_monde.getPerso(numPerso).jouer();
			
			for(int i=1;i<Global.NB_PNJ;i++)
			{
				m_monde.getPerso(i).jouer();
			}
			m_monde.getJoueur().jouer();
			m_tour++;
	
		}
	}
	
	public void intro()
	{
		System.out.println("Lord Of The String");
	}
	
	public void win()
	{
		m_gagne=true;
		if(Global.MODE_GRAPHIQUE)
		{
			Global.FENETRE.changePanel(Ecran.WIN);
		}
		else
		{
			System.out.println("Bravo tu as gagné !");
		}
	}
	
	public void loose()
	{
		m_gagne=false;
		if(Global.MODE_GRAPHIQUE)
		{
			Global.FENETRE.changePanel(new Ecran.MenuPanel());
		}
		else
		{
			System.out.println("Tu as perdu...");
		}
	}
	
	
	public void update()
	{
		
		m_monde.update();
		
		if(Global.ringFound)
		{
			m_alive=false;
			win();
		}
	}
	
	public void afficher()
	{
		m_monde.afficher();
	}
	
	public void quitter()
	{
		m_alive=false;
	}
	
	
	
}
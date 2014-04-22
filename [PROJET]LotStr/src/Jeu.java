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
	private int m_tour;
	
	public Jeu(Monde monde)
	{
		m_tour=0;
		m_monde=monde;
		
		this.m_alive=true;
	}
	
	public void jouer()
	{
		int numPerso=0;
		
		while(m_alive==true)
		{
			
			numPerso=m_tour% m_monde.getNbPerso();
			
			///UPDATE
			this.update();
			
			
	
			///AFFICHAGE
			if (numPerso ==0)
			{
				this.afficher();
				System.out.println("TOUR "+m_tour+": "+ m_monde.getPerso(numPerso).getNom() )  ;
			}
			
			///JOUEURS
			if(Global.MODE_GRAPHIQUE==true) {m_alive= m_monde.getPerso(numPerso).jouerGraphique();}
			else { m_alive= m_monde.getPerso(numPerso).jouer();}
	
			
		}
	}
	
	public void update()
	{
		m_tour++;
		m_monde.update();
		if(Global.MODE_GRAPHIQUE==true) {Global.FENETRE.repaint();}
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
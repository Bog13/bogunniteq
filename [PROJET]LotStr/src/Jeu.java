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
		int numJoueur=0;
		
		while(m_alive==true)
		{
			
			numJoueur=m_tour% m_monde.getNbPerso();
			
			///UPDATE
			this.update();
			
			///AFFICHAGE
			this.afficher();
			
			
			
			///JOUEURS
			System.out.println("TOUR "+m_tour+": "+ m_monde.getPerso(numJoueur).getNom() )  ;
			m_alive= m_monde.getPerso(numJoueur).jouer();
			
			
			
		}
	}
	
	public void update()
	{
		m_tour++;
		m_monde.update();
	}
	
	public void afficher()
	{
		if(Global.MODE_GRAPHIQUE)m_monde.afficher();
		else m_monde.afficher();
	}
	
	public void quitter()
	{
		m_alive=false;
	}
	
	
	
}
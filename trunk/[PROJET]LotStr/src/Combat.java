import java.util.Scanner;


public class Combat 
{
	private Perso m_agresseur;
	private Perso m_victime;
	private boolean m_continuer;
	
	
	
	public Combat(Perso agresseur, Perso victime)
	{
		m_continuer = true;
		
		m_agresseur = agresseur;
		m_victime = victime;
		while (m_continuer == true)
		{
			action(choixAction(),m_agresseur,m_victime);
		}
	}

	public int choixAction()
	{
		int res = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Vous , "+m_agresseur.getNom()+"êtes face à un terrible monstre : "+m_victime.getNom()+" !!");
		System.out.println("Stats de "+m_agresseur.getNom()+" : \n\n\tAttaque : "+m_agresseur.getAtk()+"\n\tDefense : "+m_agresseur.getDef()+"\n\tVie : "+m_agresseur.getVie()+"\n");
		System.out.println("Stats de "+m_victime.getNom()+" : \n\n\tAttaque : "+m_victime.getAtk()+"\n\tDefense : "+m_victime.getDef()+"\n\tVie : "+m_victime.getVie()+"\n");
		
		do
		{
			System.out.println("Que voulez-vous faire ?\n\n\t\t\t1 : Attaquer\n\n\t\t\t2 ; fuir\n");
			res = sc.nextInt();
		}
		while (res!=1 || res!=2);
		
		return res;
		
	}

	public void action(int a, Perso m_agresseur, Perso m_victime )
	{
		switch (a) 
		{
		case 1 :
			attaque(m_agresseur , m_victime );
			break;
		case 2 :
			fuir();
			break;
		}
	}

	public static void attaque(Perso m_agresseur, Perso m_victime)
	{
		if 	((m_agresseur.getAtk() - m_victime.getDef()) > 0)
		{
			m_victime.setVie( m_victime.getVie() - (m_agresseur.getAtk() - m_victime.getDef()) );	
		}
		else m_victime.setVie(m_victime.getVie() -1);
	}

	public void fuir() 
	{
		m_continuer = false;
	}
	
}

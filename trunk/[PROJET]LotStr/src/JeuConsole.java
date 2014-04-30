import java.util.Scanner;


public class JeuConsole implements Joueur_TypeJeu
{

	private Joueur m_joueur;
	
	public JeuConsole(Joueur joueur)
	{
		m_joueur=joueur;
	}
	
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
		if(saisie.compareTo("8")==0  ) {m_joueur.move(-1,0);}
		if(saisie.compareTo("5")==0 ) {m_joueur.move(1,0);}
		if(saisie.compareTo("4")==0 ) {m_joueur.move(0,-1);}
		if(saisie.compareTo("6")==0 ) {m_joueur.move(0,1);}
		if(saisie.compareTo("p")==0) {m_joueur.prendreItemCase();}
		
		
		
		return true;
	}

}

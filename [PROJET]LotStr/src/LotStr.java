/**
 *  @param: Classe principale
*	@author: BoG,QG
*	@date: 7/3/14
*	
*/

/****************************/
/**LORD OF THE stRINGs !**/
/****************************/
//par GUILLIEN Quentin et OSSETE GOMBE Berenger


import java.util.Scanner;





public class LotStr
{
	
	public static void main (String[] args) 
	{
		
		Monde monde=new Monde();
		monde.charger("test.txt");
		
		Commande commande = new Commande(monde.getJoueur());
		
		Global.FENETRE.changePanel(new Ecran.JeuPanel(monde));
		

		
		monde.addObs( (ObservateurMonde) Global.FENETRE.getPanel());
		monde.getJoueur().addObs((ObservateurJoueur) Global.FENETRE.getPanel());
		
		Jeu jeu = new Jeu(monde);
		jeu.jouer();
	
		
		
	
	}
	
}
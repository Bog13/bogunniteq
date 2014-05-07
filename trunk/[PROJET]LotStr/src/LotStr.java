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
		Jeu jeu = new Jeu(monde);
		
		monde.charger("test.txt");
		Global.MONDE=monde;
		
		Commande commande = new Commande(monde.getJoueur());
		
		
		Global.FENETRE.changePanel(Ecran.MENU);
	
		while(!Global.MODE_JEU && Global.MODE_GRAPHIQUE) {}//on attends le menu
		jeu.jouer();
		
		
		
		
		
	
	}
	
}
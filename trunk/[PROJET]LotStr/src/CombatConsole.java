import java.util.Scanner;


public class CombatConsole implements Combat_TypeCombat
{

	public int choixAction()
	{
		int res = 0;
		Scanner sc = new Scanner(System.in);
		
		do
		{
			System.out.println("Que voulez-vous faire ?\n\n\t\t\t1 : Attaquer\n\n\t\t\t2 : fuir\n");
			res = sc.nextInt();
		}
		while (res !=1 && res!= 2);
		
		
		return res;
		
	}

}


public class Combat 
{
	public Combat()
	{
		
	}

	public void attaque(Perso attaquant, Perso victime)
	{
		if 	((attaquant.getAtk() - victime.getDef()) > 0)
		{
			victime.setVie( victime.getVie() - (attaquant.getAtk() - victime.getDef()) );	
		}
		else victime.setVie(victime.getVie() -1);
	}

	
}

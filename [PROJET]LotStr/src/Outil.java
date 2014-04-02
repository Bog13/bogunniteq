import java.io.*;

/**
 * Ensemble de fonctions basiques
 * @author B�renger
 *
 */
public class Outil
{
	/**
	 * Sauvegarde un fichier contenant un String
	 * @param nom
	 * @param contenu
	 * @author B�renger OSSETE GOMBE
	 */
	@SuppressWarnings({ "null", "unused", "resource" })
	public static boolean sauvegarderFichier(String nom,String contenu)
	{
		FileWriter fw=null;
		
		try
		{
			fw=new FileWriter(nom);
			fw.write(contenu,0,contenu.length());
		}
		catch(IOException e)
		{
			e.getStackTrace();
			return false;
		}
		
		try
		{
			if(fw!=null)
			{
				fw.close();
				return true;
			}
		}catch(IOException e){e.getStackTrace();return false;}
		
		return true;
	}
	
	public static String ouvrirFichier(String nom)
	{
		
		String contenu="";
		String ligne="";
		
		try
		{
			InputStream input_stream= new FileInputStream(nom);
			InputStreamReader reader= new InputStreamReader(input_stream);
			BufferedReader br=new BufferedReader(reader);
			
			while((ligne=br.readLine()) != null)
			{
				contenu+=ligne+'\n';
			}
			
			
		}
		catch(IOException e)
		{
			e.getStackTrace();
			
		}
		
		return contenu;
	}
	
	
}
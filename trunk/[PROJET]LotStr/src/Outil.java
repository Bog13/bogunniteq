import java.awt.Image;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * Ensemble de fonctions basiques
 * @author Bérenger
 *
 */
public class Outil
{
	/**
	 * Sauvegarde un fichier contenant un String
	 * @param nom
	 * @param contenu
	 * @author Bérenger OSSETE GOMBE
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
	
	public static int hasard(int a,int b)
	{
		return ((int)(Math.random()*(b-a) +a ));
	}
	
	public static Image loadImage(String path)
	{
		Image img;
		try
		{
			img = ImageIO.read(new File(path));
			return img;
		}
		catch ( IOException e )
		{
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void debugMsg(String msg)
	{
		System.out.println("[DEBUG] "+msg+" !");
	}
	
	
}

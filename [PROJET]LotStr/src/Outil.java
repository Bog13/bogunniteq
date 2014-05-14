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
	
	public static Item hasardItem()
	{
		int hasard=hasard(0,100);
		Item it=null;
		int proba=0;
		
		if(hasard>=0 && hasard <10)
		{
			it=new PotionVie();
		}
		else if(hasard>=10 && hasard <20)
		{
			it=new PotionDef();
		}
		else if(hasard>=20 && hasard <30)
		{
			it=new PotionAtk();
		}
		else if(hasard>=30 && hasard <40)
		{
			it=new PotionGold();
		}
		else if(hasard>=40 && hasard <50)
		{
			it=new Dagger();
		}
		else if(hasard>=50 && hasard <60)
		{
			it=new Epee();
		}
		else if(hasard>=60 && hasard <70)
		{
			it=new Sabre();
		}
		else if(hasard>=70 && hasard <100)
		{
			it=new PieceOr();
		}
		
		return it;
	}
	
	public static Image loadImage(String path)
	{
		Image img;
		try
		{
			img = ImageIO.read(new File(Global.IMG_PATH+path));
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

	public static Image getPersoFace(int m_vie)
	{
		Image persoFace;
		
		if(m_vie==0)
		{
			persoFace=Outil.loadImage("persoFaceDead.png");
		}
		else if(m_vie>=0 && m_vie<(int)(0.25*Global.VIE_JOUEUR))
		{
			persoFace=Outil.loadImage("persoFace3.png");
		}
		else if(m_vie>=(int)(0.25*Global.VIE_JOUEUR) && m_vie<(int)(0.5*Global.VIE_JOUEUR))
		{
			persoFace=Outil.loadImage("persoFace2.png");
		}
		else if(m_vie>=(int)(0.5*Global.VIE_JOUEUR) && m_vie<(int)(0.75*Global.VIE_JOUEUR))
		{
			persoFace=Outil.loadImage("persoFace1.png");
		}
		else if(m_vie>=(int)(0.75*Global.VIE_JOUEUR))
		{
			persoFace=Outil.loadImage("persoFace.png");
		}
		else persoFace=Outil.loadImage("black.png");
		
		return persoFace;
	}
	
	public static Image getMonstreFace(String nom)
	{
		Image monstreFace;
		
		if(nom.equals("Depehess"))
		{
			monstreFace=Outil.loadImage("depehess_max.png");
		}
		else if(nom.equals("Tanky"))
		{
			monstreFace=Outil.loadImage("tanky_max.png");
		}
		else if(nom.equals("Tableax"))
		{
			monstreFace=Outil.loadImage("tableax_max.png");
		}
		else if(nom.equals("Super Tableax"))
		{
			monstreFace=Outil.loadImage("tableax_max.png");
		}
		else monstreFace=Outil.loadImage("black.png");
		
		return monstreFace;
	}
	
	
}

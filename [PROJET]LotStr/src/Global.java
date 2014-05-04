/*
	auteur: BoG
	date: 7/3/14
*/
import java.io.FileWriter;
import java.io.IOException;

public class Global
{
	final public static boolean MODE_GRAPHIQUE=true;
	final public static int ECRAN_LARGEUR=1024;
	final public static int ECRAN_HAUTEUR=576;
	final public static int CASE_LARGEUR=14;
	final public static int CASE_HAUTEUR=19;
	/*final public static int NB_CASE_LARGEUR=ECRAN_LARGEUR/CASE_LARGEUR;
	final public static int NB_CASE_HAUTEUR=ECRAN_HAUTEUR/CASE_HAUTEUR;*/
	
	final public static int NB_CASE_LARGEUR=40;//40
	final public static int NB_CASE_HAUTEUR=30;//30
	
	
	final public static Ecran.Fenetre FENETRE = new Ecran.Fenetre();
	final public static int NB_PNJS = 10;
	final public static int TORCHE_RAYON=16;
	final public static int TORCHE_DUREE=NB_CASE_LARGEUR*2;
	final public static int JOUEUR_RAYON=4;
	
	final public static int VIE_JOUEUR = 10;
	final public static String NOM_JOUEUR = "Zdow-Wah";
	final public static int ATK_JOUEUR = 5;
	final public static int DEF_JOUEUR = 2;
	final public static String IMG_PATH="img/";

	final public static int NB_PIECE = 10;
	
	final public static int KEY_HAUT=0;
	final public static int KEY_BAS=1;
	final public static int KEY_GAUCHE=2;
	final public static int KEY_DROITE=3;
	final public static int KEY_PRENDRE=4;
	final public static int KEY_NONE=-1;
	
	final public static int VALEUR_OR=10;
	
	public static boolean ringFound=false;
	public static boolean keyPressed=false;
	
	public static Monde MONDE=null;
	
	public static boolean MODE_JEU=false;
	public static boolean MODE_COMBAT=false;
	
}
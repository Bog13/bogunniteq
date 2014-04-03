/*
	auteur: BoG
	date: 7/3/14
*/
import java.io.FileWriter;
import java.io.IOException;

public class Global
{
	final public static boolean MODE_GRAPHIQUE=false;
	final public static int ECRAN_LARGEUR=1024;
	final public static int ECRAN_HAUTEUR=576;
	final public static int CASE_LARGEUR=14;
	final public static int CASE_HAUTEUR=19;
	/*final public static int NB_CASE_LARGEUR=ECRAN_LARGEUR/CASE_LARGEUR;
	final public static int NB_CASE_HAUTEUR=ECRAN_HAUTEUR/CASE_HAUTEUR;*/
	
	final public static int NB_CASE_LARGEUR=40;//40
	final public static int NB_CASE_HAUTEUR=30;//30
	
	final public static Ecran.Fenetre FENETRE = new Ecran.Fenetre(new Ecran.MenuPanel());
	final public static int TORCHE_RAYON=4;
	final public static int TORCHE_DUREE=10;
	final public static int JOUEUR_RAYON=3;
	final public static float TORCHE_SEUIL=0.7f;
	
	
}
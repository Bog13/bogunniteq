/*
	auteur: BoG
	date: 7/3/14
*/
import java.io.FileWriter;
import java.io.IOException;

public class Global
{
	final public static boolean MODE_GRAPHIQUE=true;

	final public static int CASE_LARGEUR=14;
	final public static int CASE_HAUTEUR=19;
	
	final public static int NB_CASE_LARGEUR=40;//40
	final public static int NB_CASE_HAUTEUR=30;//30
	final public static int ECRAN_LARGEUR=1024;
	final public static int ECRAN_HAUTEUR= CASE_HAUTEUR*(NB_CASE_HAUTEUR+2);//576
	
	final public static Ecran.Fenetre FENETRE = new Ecran.Fenetre();
	
	final public static int JOUEUR_RAYON=4;
	final public static int TORCHE_RAYON=16;
	final public static int TORCHE_DUREE=NB_CASE_LARGEUR*2;
	
	
	final public static String IMG_PATH="img/";
	
	final public static int KEY_HAUT=0;
	final public static int KEY_BAS=1;
	final public static int KEY_GAUCHE=2;
	final public static int KEY_DROITE=3;
	final public static int KEY_PRENDRE=4;
	final public static int KEY_NONE=-1;
	
	
	/**
	 * VARIABLES
	 */
	
	//ARGENT
	final public static int VALEUR_OR=10;
	final public static int NB_PIECE = 10;
	//
	
	//MONSTRE
	public static final int	DEPEHESS_VIE	= 50;
	public static final int	DEPEHESS_ATK	= 1;
	public static final int	DEPEHESS_DEF	= 1;
	public static final int	NB_DEPEHESS	= 5;
	
	public static final int	TABLEAX_VIE	= 50;
	public static final int	TABLEAX_ATK	= 1;
	public static final int	TABLEAX_DEF	= 1;
	public static final int	NB_TABLEAX = 1;
	
	public static final int	SUPER_TABLEAX_VIE	= 50;
	public static final int	SUPER_TABLEAX_ATK	= 1;
	public static final int	SUPER_TABLEAX_DEF	= 1;
	public static final int	NB_SUPER_TABLEAX = 1;
	
	public static final int	TANKY_VIE	= 50;
	public static final int	TANKY_ATK	= 100000;
	public static final int	TANKY_DEF	= 1;
	public static final int	NB_TANKY	= 5;
	
	public static final int	NB_PNJ	= NB_TANKY+NB_DEPEHESS+NB_TABLEAX+NB_SUPER_TABLEAX;
	//
	
	// JOUEUR
	final public static int VIE_JOUEUR = 50;
	final public static String NOM_JOUEUR = "Zdow-Wah";
	final public static int ATK_JOUEUR = 13;
	final public static int DEF_JOUEUR = 9;
	//
	
	//BULLSHIT
	public static final int	PROBA_POTION_VIE	= 10;
	public static final int	PROBA_POTION_DEF	= 10;
	public static final int	PROBA_POTION_ATK	= 10;
	public static final int	PROBA_EPEE	= 5;
	public static final int	PROBA_POTION_GOLD	= 10;
	public static final int	PROBA_DAGGER	= 10;
	public static final int	PROBA_SABRE	= 2;
	public static final int	PROBA_OR	= 43;
	//
	
	//INVENTAIRE-POIDS
	public static final int	MAX_INVENTAIRE	= 25;
	public static final int	POTION_POIDS	= 2;
	public static final int	DARKNESS_POIDS	= 12;
	public static final int	DAGGER_POIDS	= 4;
	public static final int	SABRE_POIDS	= 5;
	public static final int	EPEE_POIDS	= 7;
	//
	
	//POTIONS
	public static final int	POTION_VIE	= 25;
	final public static int	NB_POTION_VIE= 5;
	public static final int	POTION_VIE_PRIX	= 25;
	
	public static final int	POTION_DEF	= 12;
	public static final int	NB_POTION_DEF	= 4;
	public static final int	POTION_DEF_PRIX	= 13;
	
	public static final int	POTION_ATK	= 13;
	public static final int	NB_POTION_ATK	= 5;
	public static final int	POTION_ATK_PRIX	= 20;
	
	public static final int	POTION_GOLD	= 100;
	public static final int	NB_POTION_GOLD	= 1;
	public static final int	POTION_GOLD_PRIX= 75;
	//
	
	//ARMES
	public static final int	DAGGER_DEGAT	= 5;
	public static final int	DAGGER_PRIX	= 40;
	public static final int	NB_DAGGER	= 9;
	
	public static final int	DARKNESS_DEGAT	= 20;
	public static final int	NB_DARKNESS	= 0;
	
	public static final int	EPEE_DEGAT	= 10;
	public static final int	EPEE_PRIX	= 50;
	public static final int	NB_EPEE	= 5;
	
	public static final int	SABRE_DEGAT	= 15;
	public static final int	SABRE_PRIX	= 70;
	public static final int	NB_SABRE	= 1;
	//
	
	
	
	
	
	
	
	
	
	
	public static boolean ringFound=false;
	public static boolean keyPressed=false;
	
	public static Monde MONDE=null;
	
	public static boolean MODE_JEU=false;
	public static boolean MODE_COMBAT=false;
	public static boolean MODE_ACHAT=false;
	
}
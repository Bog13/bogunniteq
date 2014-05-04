import java.awt.event.*;import java.awt.*;import javax.swing.*;import java.io.*;import javax.imageio.ImageIO;public class Ecran{		/**	*	JPanel permettant l'affichage de l'image dont le nom est pass� en param�tre.	*/	public static class ImagePanel extends JPanel	{		File m_file;		private int m_x;		private int m_y;		private int m_h;		private int m_w;				public ImagePanel(String nom)		{			m_file= new File(Global.IMG_PATH+nom);			m_x=0;			m_y=0;			m_w=Global.ECRAN_LARGEUR;			m_h=Global.ECRAN_HAUTEUR;		}				public ImagePanel(String nom,int x,int y,int w,int h)		{			m_file= new File(nom);			m_x=x;			m_y=y;			m_w=w;			m_h=h;		}				public void paintComponent(Graphics g)		{			try			{				Image img = ImageIO.read(m_file);				g.drawImage(img,0,0,m_w,m_h,this);							}			catch (IOException e )			{				e.printStackTrace();			}		}	}		public static class TextPanel extends JPanel	{		private String m_text;		private int m_size;				public TextPanel(String txt,int size)		{			m_size=size;			m_text=txt;		}				public void paintComponent(Graphics g)		{			Font font= new Font("Courrier",Font.BOLD,m_size);			g.setFont(font);			g.drawString(m_text,(int)((Global.ECRAN_LARGEUR -this.getSize().getWidth())/2),Global.ECRAN_HAUTEUR/12);					}	}		/**	*	Panel principale du ***MENU***	*/    public static class MenuPanel extends JPanel implements ActionListener	{    	private JPanel m_top;		private JPanel m_bottom;				private JButton m_nouvellePartie;		private JButton m_quitter;					public MenuPanel()		{						build();			m_nouvellePartie= new JButton("Nouvelle Partie/Charger Partie");			m_nouvellePartie.addActionListener(this);			m_top.add(m_nouvellePartie);						m_quitter= new JButton("Quitter");			m_quitter.addActionListener(this);			m_top.add(m_quitter);		}				public void build()		{			this.setLayout(new BorderLayout());						m_top=new JPanel();			m_top.setBackground(new Color(100,100,150));			this.add(m_top,BorderLayout.SOUTH);						m_bottom= new ImagePanel("fond.png");			m_bottom.setSize(new Dimension(1024,578));			this.add(m_bottom,BorderLayout.CENTER);		}				///* Gestion des evenements *///		public void actionPerformed(ActionEvent ae)		{			if(ae.getSource() == m_quitter)			{				quit();											}			else if(ae.getSource() == m_nouvellePartie)			{				//Global.FENETRE.changePanel(new ImagePanel("fond.png"));				PASSER_MODE_JEU();			}		}			}        public static void chargerJeu()    {    	Global.FENETRE.changePanel(new JeuPanel(Global.MONDE));    }        /**         *          * @author B�renger         *         */        public static class NouvellePartiePanel extends JPanel implements ActionListener        {        	JPanel j_north;        	JPanel j_center;        	JPanel j_south;        	JPanel j_east;        	        	JButton b_slot1;        	JButton b_slot2;        	JButton b_slot3;        	        	JButton b_continuer;        	JButton b_supprimer;        	JButton b_retour;        	        	        	        	public NouvellePartiePanel()        	{        		        		this.setLayout(new BorderLayout());        		buildPanel();        		buildButton();        		        	}        	        	public void buildButton()        	{        		b_slot1= new JButton("SLOT 1");        		j_center.add(b_slot1);        		b_slot1.addActionListener(this);        		        		b_slot2= new JButton("SLOT 2");        		j_center.add(b_slot2);        		b_slot2.addActionListener(this);        		        		b_slot3= new JButton("SLOT 3");        		j_center.add(b_slot3);        		b_slot3.addActionListener(this);        		        		b_continuer= new JButton("CONTINUER");        		j_south.add(b_continuer);        		b_continuer.addActionListener(this);        		        		b_supprimer= new JButton("SUPPRIMER");        		j_south.add(b_supprimer);        		b_supprimer.addActionListener(this);        		        		b_retour= new JButton("<--");        		j_south.add(b_retour);        		b_retour.addActionListener(this);        	}        	        	public void buildPanel()        	{        		j_north=new TextPanel("NOUVELLE PARTIE/CHARGER PARTIE",25);        		j_north.setBackground(Color.red);        		j_north.setPreferredSize(new Dimension(100,50));        		        		j_north.setLayout(new GridLayout(1,1));        		this.add(j_north,BorderLayout.NORTH);        		        		j_south=new JPanel();        		j_south.setBackground(Color.blue);        		this.add(j_south,BorderLayout.SOUTH);        		        		j_center=new JPanel();        		j_center.setBackground(Color.green);        		this.add(j_center,BorderLayout.CENTER);        		        		j_east=new JPanel();        		j_east.setBackground(Color.ORANGE);        		this.add(j_east,BorderLayout.EAST);        	}        	        	        	        	// Evenement        	public void actionPerformed(ActionEvent ae)        	{        		if(ae.getSource()==b_continuer)        		{	        			PASSER_MODE_JEU();        		}        	}        }                                public static void quit()        {        	Global.FENETRE.dispose();        	System.exit(0);        }            /**     *          * @author B�renger     *     */    public static class JeuPanel extends JPanel implements ObservateurMonde,ObservateurJoueur    {    	private JPanel j_zoneAffichage;    	private JPanel j_zoneInventaire;    	private Monde m_monde;            	    	public JeuPanel(Monde monde)    	{    		m_monde=monde;    		    		m_monde.addObs(this);    		m_monde.getJoueur().addObs(this);    		    		this.setLayout(new BorderLayout());    		buildPanel();    	}    	    	public void itemUp()    	{    		((EcranInventairePanel) j_zoneInventaire).itemUp();    	}    	    	public void itemUse()    	{    		((EcranInventairePanel) j_zoneInventaire).itemUse();    	}    	    	public void itemDown()    	{    		((EcranInventairePanel) j_zoneInventaire).itemDown();    	}    	    	public void buildPanel()    	{    		    		/*j_zoneAffichage=new EcranJeuPanel();    		j_zoneAffichage.setPreferredSize(new Dimension(Global.ECRAN_HAUTEUR,0));    		this.add(j_zoneAffichage,BorderLayout.WEST);    		    		j_zoneInventaire=new EcranInventairePanel();    		j_zoneInventaire.setPreferredSize(new Dimension(Global.ECRAN_LARGEUR,Global.ECRAN_HAUTEUR));    		this.add(j_zoneInventaire,BorderLayout.EAST);*/    		    		j_zoneAffichage=new EcranJeuPanel();    		j_zoneAffichage.setPreferredSize(new Dimension(Global.ECRAN_HAUTEUR,Global.ECRAN_HAUTEUR));    		this.add(j_zoneAffichage,BorderLayout.WEST);    		    		j_zoneInventaire=new EcranInventairePanel();    		j_zoneInventaire.setPreferredSize(new Dimension(Global.ECRAN_LARGEUR - Global.ECRAN_HAUTEUR,Global.ECRAN_HAUTEUR));    		this.add(j_zoneInventaire,BorderLayout.EAST);    	}    	    	    	    	public JPanel getAffichagePanel()    	{    		return j_zoneAffichage;    	}				public void update( char[][] monde )		{			((EcranJeuPanel) j_zoneAffichage).updateEcran(monde);								}		public void update( Inventaire inventaire, int vie,int atk,int def, int or )		{			((EcranInventairePanel) j_zoneInventaire).update(inventaire, vie,atk,def,or);		}    	    	    	    	    	    }            public static class EcranJeuPanel extends JPanel     {    	private char[][] m_tabMonde;    	private Image[] m_tabImage;    	    	public EcranJeuPanel()    	{    		    		//this.setLayout(new GridLayout(18,18));    		this.setBackground(Color.ORANGE);    		    		m_tabMonde=new char[Global.NB_CASE_HAUTEUR][Global.NB_CASE_LARGEUR];    		initTabMonde();    		initTabImage();    		buildPanel();    	}    	    	public void initTabImage()    	{    		final int nbImage=19;    		m_tabImage=new Image[nbImage];    		String nom="";    		    		for(int i=0;i<nbImage;i++)    		{    			switch(i)    			{    				case 0://sol    					nom="wall01.png";    					break;    					    				case 1://mur    					nom="wall_H.png";    					break;    					    				case 2://mur    					nom="wall_V.png";    					break;    					    				case 3://mur    					nom="wall00.png";    					break;    					    				case 4://mur    					nom="wall_LC.png";    					break;    					    				case 5://mur    					nom="wall_RC.png";    					break;    					    				case 6://or    					nom="gold.png";    					break;    					    				case 7://Anneau    					nom="ring.png";    					break;    				    				case 8://porte     					nom="doorOpen.png";    					break;    				case 9://porte     					nom="doorClose.png";    					break;    					    				case 10://torche    					nom="torcheOn.png";    					break;    				case 11://torche    					nom="torcheOff.png";    					break;    					    				case 12:    					nom="black.png";    					break;    				case 13:    					nom="perso.png";    					break;    				case 14:    					nom="monstre.png";    					break;    				case 15:    					nom="potionVie.png";    					break;    				case 16:    					nom="potionDef.png";    					break;    				case 17:    					nom="potionAtk.png";    					break;    				case 18:    					nom="potionGold.png";    					break;    			}    			    			m_tabImage[i]=getImage(nom);    		}    		    		    	}    	    	public void initTabMonde()    	{    		for(int i=0;i<Global.NB_CASE_HAUTEUR;i++)    		{    			for(int j=0;j<Global.NB_CASE_LARGEUR;j++)    			{    				m_tabMonde[i][j]='0';    			}    		}    	}    	    	public void buildPanel()    	{    		this.setFocusable(true);    		this.requestFocus();    	}    	        	    	public Image getImage(String nom)    	{    		Image img=null;    		try			{				img = ImageIO.read( new File(Global.IMG_PATH+nom) );			}			catch (IOException e)			{				e.printStackTrace();			}    		return img;    	}    	    	public Image imageCase(int i,int j)    	{    		int indice;    		Image image = null;    					switch(m_tabMonde[i][j])			{				case ' '://sol					indice=0;					break;									case '-'://mur					indice=1;					break;									case '|'://mur					indice=2;					break;									case '+'://mur					indice=3;					break;									case '<'://mur					indice=4;					break;									case '>'://mur					indice=5;					break;									case '$'://or					indice=6;					break;									case 'O'://Anneau					indice=7;					break;								case '/'://porte 					indice=8;					break;				case '_'://porte 					indice=9;					break;									case 'T'://torche					indice=10;					break;				case 't'://torche					indice=11;					break;				case 'v'://potion vie					indice=15;					break;				case 'd'://potion def					indice=16;					break;				case 'a'://potion atk					indice=17;					break;				case 'g'://potion gold					indice=18;					break;									default:					indice=12;					break;			}						if(  m_tabMonde[i][j]=='C'  )			{				indice=13;				image=m_tabImage[indice];			}			else if (m_tabMonde[i][j]=='W' )			{				indice=14;				image=m_tabImage[indice];			}			else			{																if(m_tabMonde[i][j]!=':')				{					image=m_tabImage[indice];														}				else				{					image=getImage("nolight.png");				}							}						return image;					}			    	    	    	public void paintComponent(Graphics g)    	{    		g.setColor(Color.gray);    		g.fillRect(0,0,Global.ECRAN_HAUTEUR,Global.ECRAN_HAUTEUR);         		for(int i=0;i<Global.NB_CASE_HAUTEUR;i++)    		{    			for(int j=0;j<Global.NB_CASE_LARGEUR;j++)    			{    				g.drawImage(imageCase(i,j),j*Global.CASE_LARGEUR,i*Global.CASE_HAUTEUR,Global.CASE_LARGEUR,Global.CASE_HAUTEUR,this);    			}    		}    		    		    		    		    		    	}						public void updateEcran( char[][] monde )		{	    	for(int i=0;i<Global.NB_CASE_HAUTEUR;i++)	    	{	    		for(int j=0;j<Global.NB_CASE_LARGEUR;j++)	    		{	    			m_tabMonde[i][j]=monde[i][j];	    		}	    	}	    		    	this.repaint();		}												    	    }            public static class EcranInventairePanel extends JPanel     {    	private int m_vie;    	private int m_def;    	private int m_atk;    	private int m_or;    	private Inventaire m_inventaire;    	private int m_numItem;    	private int m_largeurInventaire;    	private Color m_firstColor;    	private Color m_secondColor;    	private Image m_persoFace;    	private Font m_font;    	    	    	public EcranInventairePanel()    	{    		initColor();    		initVar();    		buildPanel();    	}    	    	public void itemUp()    	{    		if(m_numItem<m_inventaire.getQuantite()-1)m_numItem++;    		else m_numItem=0;    	}    	    	    	public void itemDown()    	{    		if(m_numItem<=0) m_numItem=m_inventaire.getQuantite()-1;    		else m_numItem--;    	}    	    	public void itemUse()    	{    		if(m_inventaire.getQuantite()>0)    		{    			m_inventaire.use(m_inventaire.lister().get(m_numItem));    			m_numItem=0;    		}    	}    	    	public void initImage()    	{    		m_persoFace=Outil.loadImage("persoFace.png");    	}    	    	public void initVar()    	{    		m_largeurInventaire=Global.ECRAN_LARGEUR - Global.ECRAN_HAUTEUR;    		m_numItem=0;    	}    	    	public void initColor()    	{    		m_firstColor=new Color(10,100,10);    		m_secondColor=new Color(10,50,10);    		m_font=new Font("Arial.ttf",0,20);    	}    	    	public void paintComponent(Graphics g)    	{    		int offset=0;    		    		//fond    		g.setColor(m_firstColor);    		g.fillRect(0,0,m_largeurInventaire,Global.ECRAN_HAUTEUR);    						//cadre    		g.setColor(m_secondColor);    		g.fillRect(0,0,Global.ECRAN_HAUTEUR/3,Global.ECRAN_HAUTEUR/3);    		g.drawRect(0,0,m_largeurInventaire,Global.ECRAN_HAUTEUR/3);    		g.drawImage(m_persoFace,0,0,this);			    		//stat    		g.setFont(m_font);			    		g.setColor(new Color(150,10,10));//vie    		g.drawString("PV "+m_vie,210,m_font.getSize());    		    		offset=2;    		g.setColor(new Color(150,150,10));//or    		g.drawString("PO "+m_or,210,m_font.getSize()*offset);    		    		offset=3;    		g.setColor(new Color(10,150,10));//atk    		g.drawString("ATK "+m_atk,210,m_font.getSize()*offset);    		    		offset=4;    		g.setColor(new Color(10,10,150));//def    		g.drawString("END "+m_def,210,m_font.getSize()*offset);    		    		//inventaire    		g.setColor(new Color(m_firstColor.getRed()-10,m_firstColor.getGreen()-10,m_firstColor.getBlue()-10));    		g.fillRect(0,Global.ECRAN_HAUTEUR/3+m_font.getSize(),Global.ECRAN_HAUTEUR/3 , (m_inventaire.getQuantite())*m_font.getSize());    	    		g.setColor(Color.black);    		String str;    		offset=2;//+    		for(Item it: m_inventaire.lister())    		{    			if(m_inventaire.lister().indexOf(it)==m_numItem)g.setColor(Color.red);    			else g.setColor(Color.black);    			    			str=it.getNom();    			g.drawString(str,0,Global.ECRAN_HAUTEUR/3+m_font.getSize()*offset);    			offset+=1;    		}    	}    	    	    	public void buildPanel()    	{    		this.setLayout(new BorderLayout());    		this.setBackground(Color.ORANGE);    	}    	    	public void updatePersoFace()    	{    		if(m_vie>=0 && m_vie<20)    		{    			m_persoFace=Outil.loadImage("persoFace3.png");    		}    		else if(m_vie>=20 && m_vie<40)    		{    			m_persoFace=Outil.loadImage("persoFace2.png");    		}    		else if(m_vie>=40 && m_vie<60)    		{    			m_persoFace=Outil.loadImage("persoFace.png");    		}    	}    					public void update(Inventaire inventaire, int vie,int atk,int def, int or )		{			m_vie=vie;			m_or=or;			m_atk=atk;			m_def=def;			m_inventaire=inventaire;			updatePersoFace();			this.repaint();		}    	    	    }            public static class CombatPanel extends JPanel implements ObservateurCombat    {        	private JPanel p_combat;    	private JPanel p_menu;    	private CombatGraphique m_cg;    	    	    	public CombatPanel()    	{    		m_cg=null;    		this.setLayout(new BorderLayout());    		buildPanel();    		    	}    	    	    	public void setCombatGraphique(CombatGraphique cg)    	{    		m_cg=cg;    		((MenuCombatPanel) p_menu).setCombatGraphique(cg);    	}    	    	     	    	public void buildPanel()    	{    		    		p_combat= new EcranCombatPanel();    		p_combat.setPreferredSize(new Dimension(Global.ECRAN_LARGEUR,2*Global.ECRAN_HAUTEUR/3));    		this.add(p_combat,BorderLayout.NORTH);    		    		p_menu= new MenuCombatPanel(m_cg);    		p_menu.setPreferredSize(new Dimension(Global.ECRAN_LARGEUR,Global.ECRAN_HAUTEUR/3));    		this.add(p_menu,BorderLayout.SOUTH);    	}				public void updateCombat( String msg, Perso agresseur, Perso victime )		{			((EcranCombatPanel) p_combat).updateEcran(msg,agresseur,victime);			this.repaint();					}    	    	    	    }        public static class MenuCombatPanel extends JPanel implements ActionListener    {        	private Color m_firstColor;    	private Color m_secondColor;    	private Font m_font;    	private CombatGraphique m_cg;    	    	    	    	private JButton b_attaquer;    	private JButton b_fuir;    	    	public MenuCombatPanel(CombatGraphique cg)    	{    		m_cg= cg;    		buildPanel();    	}    	    	    	    	public void initButton()    	{    		b_attaquer = new JButton("ATTAQUER");    		b_attaquer.addActionListener(this);    		this.add(b_attaquer);    		    		b_fuir = new JButton("FUIR");    		b_fuir.addActionListener(this);    		this.add(b_fuir);    		    	}    	    	public void setCombatGraphique(CombatGraphique cg)    	{    		m_cg=cg;        	}    	public void buildPanel()    	{    		this.setLayout(new GridLayout(2,5));    		this.setBackground(Color.ORANGE);    		    		initButton();    	}				public void actionPerformed( ActionEvent ae )		{			if(ae.getSource()==b_attaquer)			{				m_cg.setTouche(1);			}						if(ae.getSource()==b_fuir)			{				m_cg.setTouche(2);			}					}	    	    }        public static class EcranCombatPanel extends JPanel    {        	private Color m_firstColor;    	private Color m_secondColor;    	private String m_message;    	private Font m_font;    	private int m_hauteurMsgBox;    	    	private int m_vie1;    	private int m_vie2;    	private String m_nom1;    	private String m_nom2;    	private int m_atk1;    	private int m_atk2;    	private int m_def1;    	private int m_def2;    	    	    	    	private JButton b_attaquer;    	    	public EcranCombatPanel()    	{    		initColor();    		initVar();    		initButton();    		buildPanel();    	}    	    	public void initButton()    	{    		b_attaquer = new JButton("ATTAQUER");    		this.add(b_attaquer);    	}    	    	public void initVar()    	{    		m_hauteurMsgBox=Global.ECRAN_HAUTEUR/8;    		m_message="";    		    		m_vie1=11;			m_vie2=21;			m_nom1="???";			m_nom2="???";			m_atk1=12;			m_atk2=22;			m_def1=13;			m_def2=23;			     	}    	    	public void initColor()    	{    		m_firstColor=new Color(255,0,0);    		m_secondColor=new Color(255,255,0);    		m_font=new Font("Arial.ttf",0,20);    		    		m_firstColor=Color.black;    		m_secondColor=Color.white;    	}    	    	public void paintComponent(Graphics g)    	{    		int offset=0;    	    		g.setFont(m_font);    		//fond 1    		g.setColor(m_firstColor);    		g.fillRect(0,0,Global.ECRAN_LARGEUR/2,Global.ECRAN_HAUTEUR);    		    		//fond 2    		g.setColor(m_secondColor);    		g.fillRect(Global.ECRAN_LARGEUR/2,0,Global.ECRAN_LARGEUR,Global.ECRAN_HAUTEUR);    			    		/*PLAYER 1*/    		g.setColor(m_secondColor);    		offset=1;    		g.drawString(m_nom1,0,m_font.getSize());    		    		//vie    		offset=2;    		g.drawString("vie",0,offset*m_font.getSize());    		g.drawString(m_vie1+"",Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		g.drawLine(0,offset*m_font.getSize(),Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		    		//attaque    		offset=3;    		g.drawString("attaque",0,offset*m_font.getSize());    		g.drawString(m_atk1+"",Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		g.drawLine(0,offset*m_font.getSize(),Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		    		//defense    		offset=4;    		g.drawString("defense",0,offset*m_font.getSize());    		g.drawString(m_def1+"",Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		g.drawLine(0,offset*m_font.getSize(),Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		    		/*PLAYER 2*/    		g.setColor(m_firstColor);    		    		offset=1;    		g.drawString(m_nom2,Global.ECRAN_LARGEUR/2,m_font.getSize());    		    		//vie    		offset=2;    		g.drawString("vie",Global.ECRAN_LARGEUR/2,offset*m_font.getSize());    		g.drawString(m_vie2+"",Global.ECRAN_LARGEUR/2+Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		g.drawLine(Global.ECRAN_LARGEUR/2,offset*m_font.getSize(),Global.ECRAN_LARGEUR/2+Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		    		//attaque    		offset=3;    		g.drawString("attaque",Global.ECRAN_LARGEUR/2,offset*m_font.getSize());    		g.drawString(m_atk2+"",Global.ECRAN_LARGEUR/2+Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		g.drawLine(Global.ECRAN_LARGEUR/2,offset*m_font.getSize(),Global.ECRAN_LARGEUR/2+Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		    		//defense    		offset=4;    		g.drawString("defense",Global.ECRAN_LARGEUR/2,offset*m_font.getSize());    		g.drawString(m_def2+"",Global.ECRAN_LARGEUR/2+Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		g.drawLine(Global.ECRAN_LARGEUR/2,offset*m_font.getSize(),Global.ECRAN_LARGEUR/2+Global.ECRAN_LARGEUR/4,offset*m_font.getSize());    		    		//msgBox    		g.setColor(Color.GRAY);    		g.fillRect(0,2*Global.ECRAN_HAUTEUR/3-m_hauteurMsgBox,Global.ECRAN_LARGEUR,2*Global.ECRAN_HAUTEUR/3);    		g.setColor(Color.black);    		g.drawString(m_message,0,2*Global.ECRAN_HAUTEUR/3-m_hauteurMsgBox/2);    		    	}    	    	    	public void buildPanel()    	{    		this.setLayout(new BorderLayout());    		this.setBackground(Color.ORANGE);    	}				public void updateEcran( String msg, Perso agresseur, Perso victime )		{								m_vie1=agresseur.getVie();			m_vie2=victime.getVie();			m_nom1=agresseur.getNom();			m_nom2=victime.getNom();			m_atk1=agresseur.getAtk();			m_atk2=victime.getAtk();			m_def1=agresseur.getDef();			m_def2=victime.getDef();						m_message=msg;						this.repaint();					}    	    	    	    }                   public static JPanel MENU=new MenuPanel();    public static JPanel JEU=new JeuPanel(Global.MONDE);    public static JPanel NOUVELLE_PARTIE = new NouvellePartiePanel();    public static JPanel WIN = new WinPanel();    public static JPanel COMBAT=new CombatPanel();        public static void PASSER_MODE_COMBAT(Combat combat)    {    	Global.MODE_COMBAT=true;    	Global.MODE_JEU=false;    	((Ecran.CombatPanel) Ecran.COMBAT).setCombatGraphique(combat.getCombatGraphique());    	Global.FENETRE.changePanel(Ecran.COMBAT);    }        public static void PASSER_MODE_JEU()    {    	Global.MODE_JEU=true;    	Global.MODE_COMBAT=false;    	Global.FENETRE.changePanel(Ecran.JEU);    }    	/**	*	Panel principale de ***ECRAN***	*/	public static class Fenetre extends JFrame implements KeyListener	{		private JPanel m_panel;		private Commande m_commande;				public Fenetre()		{						addKeyListener(this);			build();			//changePanel(new MenuPanel());		}								public void setCommande(Commande commande)		{			m_commande=commande;		}						public void build()		{						this.setSize(Global.ECRAN_LARGEUR,Global.ECRAN_HAUTEUR);			this.setTitle("Test");			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			this.setResizable(false);			this.setLocationRelativeTo(null);			this.setVisible(true);											requestFocus();					}						        public void changePanel(JPanel panel)        {	    	m_panel=panel;			this.setContentPane(m_panel);	    	this.validate();	    	this.requestFocus();        }                    public JPanel getPanel()        {        	return m_panel;        }				public void keyPressed( KeyEvent e )		{			Global.keyPressed=true;						if(Global.MODE_JEU)			{				if(e.getKeyCode() == e.VK_UP)				{					m_commande.moveUp();				}				else if(e.getKeyCode() == e.VK_DOWN)				{					m_commande.moveDown();				}				else if(e.getKeyCode() == e.VK_LEFT)				{					m_commande.moveLeft();				}				else if(e.getKeyCode() == e.VK_RIGHT)				{					m_commande.moveRight();				}				else if(e.getKeyCode() == e.VK_SPACE)				{					m_commande.prendre();				}				else if(e.getKeyCode() == e.VK_SHIFT)				{					((JeuPanel) JEU).itemDown();				}				else if(e.getKeyCode() == e.VK_CONTROL)				{					((JeuPanel) JEU).itemUp();				}				else if(e.getKeyCode() == e.VK_ENTER)				{					((JeuPanel) JEU).itemUse();				}											}						if(e.getKeyCode() == e.VK_ESCAPE)			{				quit();			}						Global.keyPressed=false;					}			@Override		public void keyReleased( KeyEvent e )		{			Global.keyPressed=false;		}		@Override		public void keyTyped( KeyEvent e )		{			// TODO Auto-generated method stub					}	}			 public static class WinPanel extends JPanel implements ActionListener		{	    	private JPanel m_top;			private JPanel m_bottom;						private JButton m_quitter;								public WinPanel()			{								build();								m_quitter= new JButton("Quitter");				m_quitter.addActionListener(this);				m_top.add(m_quitter);			}						public void build()			{				this.setLayout(new BorderLayout());								m_top=new JPanel();				m_top.setBackground(new Color(100,100,150));				this.add(m_top,BorderLayout.SOUTH);								m_bottom= new ImagePanel("fondWin.png");				m_bottom.setSize(new Dimension(1024,578));				this.add(m_bottom,BorderLayout.CENTER);			}						///* Gestion des evenements *///			public void actionPerformed(ActionEvent ae)			{				if(ae.getSource() == m_quitter)				{					quit();				}				}					}		/**	*	***MAIN***	*/	/*public static void main (String[] args)    {		                }*/				}
import java.awt.event.*;import java.awt.*;import javax.swing.*;import java.io.*;import javax.imageio.ImageIO;public class Ecran{		/**	*	JPanel permettant l'affichage de l'image dont le nom est pass� en param�tre.	*/	public static class ImagePanel extends JPanel	{		File m_file;		private int m_x;		private int m_y;		private int m_h;		private int m_w;				public ImagePanel(String nom)		{			m_file= new File(nom);			m_x=0;			m_y=0;			m_w=Global.ECRAN_LARGEUR;			m_h=Global.ECRAN_HAUTEUR;		}				public ImagePanel(String nom,int x,int y,int w,int h)		{			m_file= new File(nom);			m_x=x;			m_y=y;			m_w=w;			m_h=h;		}				public void paintComponent(Graphics g)		{			try			{				Image img = ImageIO.read(m_file);				g.drawImage(img,0,0,m_w,m_h,this);							}			catch (IOException e )			{				e.printStackTrace();			}		}	}		public static class TextPanel extends JPanel	{		private String m_text;		private int m_size;				public TextPanel(String txt,int size)		{			m_size=size;			m_text=txt;		}				public void paintComponent(Graphics g)		{			Font font= new Font("Courrier",Font.BOLD,m_size);			g.setFont(font);			g.drawString(m_text,(int)((Global.ECRAN_LARGEUR -this.getSize().getWidth())/2),Global.ECRAN_HAUTEUR/12);					}	}		/**	*	Panel principale du ***MENU***	*/        public static class MenuPanel extends JPanel implements ActionListener	{		JPanel m_top;		JPanel m_bottom;				JButton m_nouvellePartie;		JButton m_quitter;				public MenuPanel()		{			build();			m_nouvellePartie= new JButton("Nouvelle Partie/Charger Partie");			m_nouvellePartie.addActionListener(this);			m_top.add(m_nouvellePartie);						m_quitter= new JButton("Quitter");			m_quitter.addActionListener(this);			m_top.add(m_quitter);		}				public void build()		{			this.setLayout(new BorderLayout());						m_top=new JPanel();			m_top.setBackground(new Color(100,100,150));			this.add(m_top,BorderLayout.SOUTH);						m_bottom= new ImagePanel("fond.png");			m_bottom.setSize(new Dimension(1024,578));			this.add(m_bottom,BorderLayout.CENTER);		}				///* Gestion des evenements *///		public void actionPerformed(ActionEvent ae)		{			if(ae.getSource() == m_quitter)			{				Global.FENETRE.dispose();			}			else if(ae.getSource() == m_nouvellePartie)			{				//Global.FENETRE.changePanel(new ImagePanel("fond.png"));				Global.FENETRE.changePanel(new NouvellePartiePanel());			}		}			}        /**         *          * @author B�renger         *         */        public static class NouvellePartiePanel extends JPanel implements ActionListener        {        	JPanel j_north;        	JPanel j_center;        	JPanel j_south;        	JPanel j_east;        	        	JButton b_slot1;        	JButton b_slot2;        	JButton b_slot3;        	        	JButton b_continuer;        	JButton b_supprimer;        	JButton b_retour;        	        	public NouvellePartiePanel()        	{        		this.setLayout(new BorderLayout());        		buildPanel();        		buildButton();        		        	}        	        	public void buildButton()        	{        		b_slot1= new JButton("SLOT 1");        		j_center.add(b_slot1);        		b_slot1.addActionListener(this);        		        		b_slot2= new JButton("SLOT 2");        		j_center.add(b_slot2);        		b_slot2.addActionListener(this);        		        		b_slot3= new JButton("SLOT 3");        		j_center.add(b_slot3);        		b_slot3.addActionListener(this);        		        		b_continuer= new JButton("CONTINUER");        		j_south.add(b_continuer);        		b_continuer.addActionListener(this);        		        		b_supprimer= new JButton("SUPPRIMER");        		j_south.add(b_supprimer);        		b_supprimer.addActionListener(this);        		        		b_retour= new JButton("<--");        		j_south.add(b_retour);        		b_retour.addActionListener(this);        	}        	        	public void buildPanel()        	{        		j_north=new TextPanel("NOUVELLE PARTIE/CHARGER PARTIE",25);        		j_north.setBackground(Color.red);        		j_north.setPreferredSize(new Dimension(100,50));        		        		j_north.setLayout(new GridLayout(1,1));        		this.add(j_north,BorderLayout.NORTH);        		        		j_south=new JPanel();        		j_south.setBackground(Color.blue);        		this.add(j_south,BorderLayout.SOUTH);        		        		j_center=new JPanel();        		j_center.setBackground(Color.green);        		this.add(j_center,BorderLayout.CENTER);        		        		j_east=new JPanel();        		j_east.setBackground(Color.ORANGE);        		this.add(j_east,BorderLayout.EAST);        	}        	        	        	        	// Evenement        	public void actionPerformed(ActionEvent ae)        	{        		        	}        }            /**     *          * @author B�renger     *     */    public static class JeuPanel extends JPanel     {    	private JPanel j_zoneAffichage;    	private JPanel j_zoneInventaire;    	private boolean m_evenementClavier;    	private Monde m_monde;        	    	public JeuPanel(Monde monde,Commande commande)    	{        		m_monde=monde;    		this.setLayout(new BorderLayout());    		buildPanel();    	}    	    	public void buildPanel()    	{    		    		j_zoneAffichage=new EcranJeuPanel();    		j_zoneAffichage.setPreferredSize(new Dimension(Global.ECRAN_HAUTEUR,0));    		this.add(j_zoneAffichage,BorderLayout.WEST);    		    		j_zoneInventaire=new EcranInventairePanel();    		j_zoneInventaire.setPreferredSize(new Dimension(Global.ECRAN_LARGEUR,Global.ECRAN_HAUTEUR));    		this.add(j_zoneInventaire,BorderLayout.EAST);    	}    	    	public void afficher()    	{    		j_zoneAffichage.repaint();    		//((JeuPanel) j_zoneInventaire).repaint();    	}    	    	public JPanel getAffichagePanel()    	{    		return j_zoneAffichage;    	}    	    	    	    }            public static class EcranJeuPanel extends JPanel implements Observateur    {    	private char[][] m_tabMonde;    	    	public EcranJeuPanel()    	{    		    		//this.setLayout(new GridLayout(18,18));    		this.setBackground(Color.ORANGE);    		    		m_tabMonde=new char[Global.NB_CASE_HAUTEUR][Global.NB_CASE_LARGEUR];    		initTabMonde();    		    		buildPanel();    	}    	    	public void initTabMonde()    	{    		for(int i=0;i<Global.NB_CASE_HAUTEUR;i++)    		{    			for(int j=0;j<Global.NB_CASE_LARGEUR;j++)    			{    				m_tabMonde[i][j]='0';    			}    		}    	}    	    	public void buildPanel()    	{    		this.setFocusable(true);    		this.requestFocus();    	}    	        	    	public Image getImage(String nom)    	{    		Image img=null;    		try			{				img = ImageIO.read( new File(nom) );			}			catch (IOException e)			{				e.printStackTrace();			}    		return img;    	}    	    	public Image imageCase(int i,int j)    	{    		String nom;    		Image image = null;    					switch(m_tabMonde[i][j])			{				case ' '://sol					nom="wall01.png";					break;									case '-'://mur					nom="wall_H.png";					break;									case '|'://mur					nom="wall_V.png";					break;									case '+'://mur					nom="wall00.png";					break;									case '<'://mur					nom="wall_LC.png";					break;									case '>'://mur					nom="wall_RC.png";					break;									case '$'://or					nom="gold.png";					break;									case 'O'://Anneau					nom="ring.png";					break;								case '/'://porte 					nom="doorOpen.png";					break;				case '_'://porte 					nom="doorClose.png";					break;									case 'T'://torche					nom="torcheOn.png";					break;				case 't'://torche					nom="torcheOff.png";					break;									default:					nom="black.png";					break;			}						if(  m_tabMonde[i][j]=='C'  )			{				//m_monde.getPersoPosition(new Position2D(i,j)).afficher();				image=getImage("perso.png");			}			else			{																if(m_tabMonde[i][j]!=':')				{					image=getImage(nom);														}				else				{					image=getImage("nolight.png");				}							}						return image;					}			    	    	    	public void paintComponent(Graphics g)    	{    		g.setColor(Color.gray);    		g.fillRect(0,0,Global.ECRAN_HAUTEUR,Global.ECRAN_HAUTEUR);         		for(int i=0;i<Global.NB_CASE_HAUTEUR;i++)    		{    			for(int j=0;j<Global.NB_CASE_LARGEUR;j++)    			{    				g.drawImage(imageCase(i,j),j*Global.CASE_LARGEUR,i*Global.CASE_HAUTEUR,Global.CASE_LARGEUR,Global.CASE_HAUTEUR,this);    			}    		}    		    		    		    		    		    	}						public void update( char[][] monde )		{			    	for(int i=0;i<Global.NB_CASE_HAUTEUR;i++)	    	{	    		for(int j=0;j<Global.NB_CASE_LARGEUR;j++)	    		{	    			m_tabMonde[i][j]=monde[i][j];	    		}	    	}	    		    	this.repaint();	    						}												    	    }            public static class EcranInventairePanel extends JPanel    {    	    	public EcranInventairePanel()    	{    		this.setLayout(new BorderLayout());    		this.setBackground(Color.blue);    		buildPanel();    	}    	    	public void afficher()    	{    	}    	    	public void buildPanel()    	{    		    	}    	    	    }    	/**	*	Panel principale de ***ECRAN***	*/	public static class Fenetre extends JFrame implements KeyListener	{		JPanel m_panel;		Commande m_commande;				public Fenetre()		{				addKeyListener(this);			build();			changePanel(new MenuPanel());		}				public void setCommande(Commande commande)		{			m_commande=commande;		}						public void build()		{						this.setSize(Global.ECRAN_LARGEUR,Global.ECRAN_HAUTEUR);			this.setTitle("Test");			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			this.setResizable(false);			this.setLocationRelativeTo(null);			this.setVisible(true);													requestFocus();					}				        public void changePanel(JPanel panel)        {	    	m_panel=panel;			this.setContentPane(m_panel);	    	this.validate();        }                    public JPanel getPanel()        {        	return m_panel;        }				public void keyPressed( KeyEvent e )		{			Global.keyPressed=true;						if(e.getKeyCode() == e.VK_UP)			{				m_commande.moveUp();			}			else if(e.getKeyCode() == e.VK_DOWN)			{				m_commande.moveDown();			}			else if(e.getKeyCode() == e.VK_LEFT)			{				m_commande.moveLeft();			}			else if(e.getKeyCode() == e.VK_RIGHT)			{				m_commande.moveRight();			}						Global.keyPressed=false;					}			@Override		public void keyReleased( KeyEvent e )		{			Global.keyPressed=false;		}		@Override		public void keyTyped( KeyEvent e )		{			// TODO Auto-generated method stub					}	}			/**	*	***MAIN***	*/	/*public static void main (String[] args)    {		                }*/				}
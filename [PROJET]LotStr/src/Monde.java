import java.util.ArrayList;
import java.util.Vector;





/*
 auteur: BoG
 date: 7/3/14
 */

public class Monde implements ObservableMonde
{
	private Case[][]			m_monde;
	private Joueur				m_joueur;
	private Vector<Perso>		m_population;
	private Vector<Position2D>	m_positionLumineux;
	private Vector<Position2D> m_positionActivable;
	private ArrayList<ObservateurMonde> m_alObs;

	public Monde() 
	{
		m_alObs=new ArrayList<ObservateurMonde>();
		m_monde = new Case[Global.NB_CASE_HAUTEUR][Global.NB_CASE_LARGEUR];

		m_joueur = new Joueur(this, new Position2D(0, 0));
		m_population = new Vector<Perso>();
		m_population.add(m_joueur);

		m_positionLumineux = new Vector<Position2D>();
		m_positionActivable = new Vector<Position2D>();
		
		initMonde();
		this.initPosition();
		initMur();

	}

	public Case[][] getMonde()
	{
		return m_monde;
	}

	public Joueur getJoueur()
	{
		return m_joueur;
	}

	// /////////////////////////////////
	// /********** INIT **********///
	// ////////////////////////////////
	
	/**
	 * Initialise le monde dans un etat de base;
	 */
	public void initMonde()
	{

		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				m_monde[i][j] = new Case('0');
			}
		}
		
	
	}
	
	public void placerPnjs()
	{
		for(int i=1;i<=Global.NB_PNJS;i++)
		{
			// � modifier de sorte que diff�rents types de monstres soient g�n�r�s
			//m_population.add(new MonstreAgr(this,posHasard()));
		}
	}
	
	public Position2D posHasard()
	{
		Position2D pos =null;
		
		do
		{
			pos = new Position2D((int)(Math.random()*Global.NB_CASE_HAUTEUR),(int)(Math.random()*Global.NB_CASE_LARGEUR));
		}
		while (getCase(pos).getId() != '0');
		
		return pos;
	}
	
	public void Init()
	{
		m_monde[3][2] = new Anneau('A');
		
		initPosition();
		initMur();
		
		getCase(Position2D.position(2,2)).mettreItem( new PieceOr('$') ); ////DEBUG////
		getCase(Position2D.position(2,3)).mettreItem( new PieceOr('$') ); ////DEBUG////
		getCase(Position2D.position(2,4)).mettreItem( new PieceOr('$') ); ////DEBUG////
		
		placerPnjs();
		getCase(Position2D.position(2,15)).mettreItem( new PieceOr('$') ); ////DEBUG////
		
		

	}
	
	public boolean estVoisinEgal (Position2D parcours,int i,char c)
	{
		return estDansMonde(parcours.getVoisin().get(i)) && getCase(parcours.getVoisin().get(i)).getId()==c;
	}
	
	public boolean estVoisinMP(Position2D pos, int i)
	{
		return estVoisinEgal(pos,i,'P') || estVoisinEgal(pos,i,'M');
	}
	
	public int nbVoisinMP(Position2D pos)
	{
		return nbVoisin(pos,'M')+nbVoisin(pos,'P');
	}
	
	public void initMur()
	{
		Position2D parcours=null;
		Case pcase=null;
		
		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				parcours = new Position2D(i, j);
				pcase=getCase(parcours);
				
				if(pcase.getId()=='M')
				{
					switch( nbVoisinMP(parcours) )
					{
						case 0://bloc seul
							pcase.setLook('X');
							break;
							
						case 1://extremite
							
							if( estVoisinMP(parcours,1) )
							{
								pcase.setLook('-');//haut
							}
							else if( estVoisinMP(parcours,0) )
							{
								pcase.setLook('-');//bas
							}
							else if( estVoisinMP(parcours,3) )
							{
								pcase.setLook('|');//gauche
							}
							else if( estVoisinMP(parcours,2))
							{
								pcase.setLook('|');//droite
							}
							else pcase.setLook('e');
							
							break;
							
						case 2://coin
							pcase.setLook('+');
							break;
							
						case 3://face
							if( estVoisinMP(parcours,0) && estVoisinMP(parcours,1))
							{
								pcase.setLook('|');//vertical
							}
							else pcase.setLook('-');//horizontal
							
							break;
							
						case 4://interieur
							//pcase.change(new Case('0'));
							pcase.setLook('0');
							//pcase.setId('0');
							break;
						default:
							pcase.setLook('X');
							break;
					}
				}
				
			}
		}
		
		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				if(getCase(Position2D.position(i,j)).getLook()=='0')
				{
					getCase(Position2D.position(i,j)).setId('0');
					getCase(Position2D.position(i,j)).setLook(' ');
					getCase(Position2D.position(i,j)).initPropriete('0');
				}
			}
		}
		
	}

	public void initPosition()
	{
		Position2D parcours=null;
		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				parcours = new Position2D(i, j);
				
				if ( this.getCase(parcours) instanceof Torche
						|| this.getCase(parcours).estLumineux() )
				{
					m_positionLumineux.add(parcours);
				}
				
				if(this.getCase(parcours) instanceof Activable)
				{
					m_positionActivable.add(parcours);
				}
				
			}
		}
	}

	// /////////////////////////////////
	// /********** OUTILS **********///
	// ////////////////////////////////
	
	/**
	 * Nombre de voisin d'une position quelconque
	 * @param pos
	 * @return
	 */
	public int nbVoisin(Position2D pos)
	{
		int nb=0;
		Vector<Position2D> voisin=pos.getVoisin();
		
		for(int i=0;i<voisin.size();i++)
		{
			if( estDansMonde(voisin.get(i)) )
			{
				nb++;
			}
		}
		return nb;
	}
	
	/**
	 * Nombre de case d'id type autour de pos
	 * @param pos
	 * @return
	 */
	public int nbVoisin(Position2D pos,char type)
	{
		int nb=0;
		Vector<Position2D> voisin=pos.getVoisin();
		
		for(int i=0;i<voisin.size();i++)
		{
			if( estDansMonde(voisin.get(i)) && getCase(voisin.get(i)).getId()==type )
			{
				nb++;
			}
		}
		return nb;
	}
	
	/**
	 * Indique si il existe un perso qui est aux coordon�es pos 
	 * @param pos
	 * @return
	 */

	public boolean existePersoPosition( Position2D pos )
	{
		boolean resultat = false;
		int i = 0;

		while (i < m_population.size() && !resultat)
		{
			if ( m_population.get(i).getPosition().estEgal(pos) )
			{
				resultat = true;
			}

			i++;
		}

		return resultat;
	}

	/**
	 * Retourne le perso � une position donn�e
	 * @param pos
	 * @return
	 */
	public Perso getPersoPosition( Position2D pos )
	{
		int indice = 0;
		int i = 0;
		boolean resultat = false;

		while (i < m_population.size() && !resultat)
		{
			if ( m_population.get(i).getPosition().estEgal(pos) )
			{
				indice = i;
				resultat = true;
			}

			i++;
		}

		return m_population.get(indice);
	}

	/**
	 * Permet de r�cuperer une case du monde suivant sa position.
	 * @return
	 */
	public Case getCase( Position2D pos )
	{
		return m_monde[pos.getL()][pos.getC()];
	}

	/**
	 * Indique si la case de position pos est un obstacle ou non.
	 * @param pos
	 * @return
	 */
	public boolean estObstacle( Position2D pos )
	{
		return getCase(pos).estObstacle();
	}

	/**
	 * Indique si une position est hors de m_monde
	 * @param pos
	 * @return
	 */
	public boolean estDansMonde( Position2D pos )
	{
		return(pos.getL() >= 0 && pos.getL() < Global.NB_CASE_HAUTEUR
				&& pos.getC() >= 0 && pos.getC() < Global.NB_CASE_LARGEUR);
	}

	/**
	 * Indique si un perso peut acceder � la case.
	 * @param pos
	 * @return
	 */
	public boolean estAccessible( Position2D pos )
	{
		return this.estDansMonde(pos) && !this.estObstacle(pos);
	}

	public Perso getPerso( int i )
	{
		return m_population.get(i);
	}

	public int getNbPerso()
	{
		return m_population.size();
	}
	
	public boolean sauvegarder( String nom )
	{
		String mondeStr = "";
		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				mondeStr += m_monde[i][j].toString();
			}

			/*
			 * String.format(%n) est ici utilis� car '\n' n'est pas reconnu par
			 * sauvegarderFichier mais il joue le m�me r�le ici.
			 */

			mondeStr += String.format("%n");

		}

		return Outil.sauvegarderFichier("test.txt", mondeStr);

	}

	/**
	 * Permet de charger un tableau de case dans monde
	 * @param nom
	 */
	public void charger( String nom )
	{
		String contenu = Outil.ouvrirFichier(nom);
		char actuel;

		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				actuel = contenu.charAt(j + i * (Global.NB_CASE_LARGEUR +1)); // Pourquoi
																	// +1
																				// ?
																				// :o

				switch ( actuel )
				{
					default:
						m_monde[i][j] = new Case(actuel);
						break;
						
					case 'T':
						m_monde[i][j] = new Torche(actuel);
						break;
					case 'P':
						m_monde[i][j] = new Porte(actuel);
						break;
				
				}
			}
		}

		Init();
	}

	// /////////////////////////////////
	// /********** UPDATE **********///
	// ////////////////////////////////

	/**
	 * Mise � jour du monde
	 */
	public void update()
	{
		updateActivationActivable();
		updateActivable();
		updateLumineux();
		updateSwitchOffLumineux();
		updateCombat();
		updateTest();
		updateVisibleAutour();
		updateObs();
		updatePrendreOr();
		m_joueur.update();
	}
	
	

	// /////////////////////////////////
	// /********** UPDATE **********///
	// ////////////////////////////////
	
	public void updateCombat()
	{
		//if () 
		
	}
	
	public void updatePrendreOr()
	{
		if( getCase (m_joueur.getPosition()).getLook()=='$' )
		{
			m_joueur.prendreItemCase();
		}
	}

	/**
	 * Indique s'il y a une torche autour de pos dans un rayon donn�.
	 * @param pos
	 * @param rayon
	 * @return
	 */
	public void updateVisibleAutour()
	{
		Position2D parcours;
		// joueur
		this.setCaseVisibleAutour(m_population.get(m_population.size() - 1)
				.getPosition(), Global.JOUEUR_RAYON);
	
		// torche
		for ( int i = 0; i < m_positionLumineux.size(); i++ )
		{
			parcours = m_positionLumineux.get(i);
			if ( this.getCase(parcours).estLumineux() )
			{
				this.setCaseVisibleAutour(parcours, Global.TORCHE_RAYON);
			}
	
		}
	}
	
	public void updateTest()
	{
		
	}

	public void setCaseVisibleAutour( Position2D pos, int rayon )
	{
		Position2D parcours;
		boolean estVisible=true;
		for ( int i = pos.getL() - rayon; i < pos.getL() + rayon + 1; i++ )
		{
			for ( int j = pos.getC() - rayon; j < pos.getC() + rayon + 1; j++ )
			{
				parcours = new Position2D(i, j);
				
				if ( this.estDansMonde(parcours) 
				&& pos.getDistanceTo(parcours) <=rayon
				)
				{
					
					/*DEBUG*/
					estVisible=true;
					
					
					if( murEntre(m_joueur.getPosition(),parcours))
					{
						estVisible=false;
					}else estVisible=true;
					
					
					
					/*FIN DEBUG*/
					
					this.getCase(parcours).setVisible(estVisible);
					
				}
				
	
			}
		}
	}

	
	
	public boolean estUnMur( Position2D pos)
	{
		return getCase(pos).getId()=='M';
	}
	
	public boolean estUnePorte( Position2D pos)
	{
		return getCase(pos).getId()=='P';
	}

	public boolean murEntre(Position2D A,Position2D B)
	{
		Vector<Position2D> vec=Position2D.positionEntre(A,B);
		vec.remove(vec.size()-1);
		for(Position2D pos: vec)
		{
			if(estDansMonde(pos) && (estUnMur(pos) || estUnePorte(pos)))
			{
				return true;
			}
		}
		return false;
	}

	
		
	
	public void updateActivationActivable()
	{
		for ( Position2D pos: m_positionActivable)
		{
			if ( this.existePersoPosition(pos) )
			{
				
				switch(getCase(pos).getId())
				{
					case 'P':
						((Activable) this.getCase(pos)).activer(1);
						break;
					
						
					default:
						((Activable) this.getCase(pos)).activer(3);
						break;
				}
				
			}
		}
			
	}
	
	

	public void updateActivationTorche()
	{

		for ( int i = 0; i < m_positionLumineux.size(); i++ )
		{
			if ( this.existePersoPosition(m_positionLumineux.get(i)) )
			{
				((Torche) this.getCase(m_positionLumineux.get(i)))
						.activer(Global.TORCHE_DUREE);
			}
		}
	}

	public void updateLumineux()
	{
		for (Position2D pos: m_positionLumineux)
		{
			if( !(getCase(pos) instanceof Activable) )
			{
				( (Torche) this.getCase(pos) ).update();
			}
		}
	}
	
	public void updateActivable()
	{
		for ( int i = 0; i < m_positionActivable.size(); i++ )
		{
			((Activable) this.getCase(m_positionActivable.get(i))).update();
		}
	}

	public void updateSwitchOffLumineux()
	{
		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				this.getCase(new Position2D(i, j)).setVisible(false);
				
			}
		}
	}

	// /////////////////////////////////
	// /**********AFFICHAGE**********///
	// ////////////////////////////////

	/**
	 * Permet d'afficher le monde dans la console;
	 */

	public void afficher()
	{
		
		char rendu;
		Position2D parcours=null;
		
		m_joueur.afficheHud();
		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				
				rendu= getCase(Position2D.position(i,j)).rendu();
				parcours=new Position2D(i,j);
				
				if ( existePersoPosition(parcours) )
				{
					getPersoPosition(parcours).afficher();
				}
				/*else if(parcours.estEntre(Position2D.position(0,0),Position2D.position(15,20)))
				{
					System.out.print('X');
				}else System.out.print(' '); DEBUG: POUR TESTER LES LIGNES
				*/
				else
				{

					this.updateVisibleAutour();
					if ( this.getCase(parcours).estVisible() )
					{
						System.out.print(rendu);

					}
					else
					{
						//System.out.print(rendu);
						System.out.print(':');
					}

				}
				

			}
			System.out.print('\n');
		}
		
	
	}

	
	public void addObs( ObservateurMonde obs )
	{
		m_alObs.add(obs);
		
	}

	
	public void delObs()
	{
		m_alObs=new ArrayList<ObservateurMonde>();
		
	}

	public char[][] makeCharTab()
	{
		char[][] charTab=new char[Global.NB_CASE_HAUTEUR][Global.NB_CASE_LARGEUR];
		
		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				
				if(m_monde[i][j].estVisible())
				{
					if(existePersoPosition(Position2D.position(i,j)))
					{
						charTab[i][j]='C';
					}
					else
					{
						charTab[i][j]=m_monde[i][j].getLook();
					}
				}
				else
				{
					charTab[i][j]='@';
				}
				
			}
		}
		
		return charTab;
	}
	
	public void updateObs()
	{
		
		for(ObservateurMonde obs: m_alObs)
		{
			obs.update(makeCharTab());
		}
		
	}
	
	//System.out.println('a');
}
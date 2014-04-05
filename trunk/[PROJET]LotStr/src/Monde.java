import java.util.Vector;





/*
 auteur: BoG
 date: 7/3/14
 */

public class Monde
{
	private Case[][]			m_monde;
	private Joueur				m_joueur;
	private Vector<Perso>		m_population;
	private Vector<Position2D>	m_positionLumineux;

	public Monde()
	{

		m_monde = new Case[Global.ECRAN_HAUTEUR][Global.ECRAN_LARGEUR];

		m_joueur = new Joueur(this, new Position2D(0, 0));
		m_population = new Vector<Perso>();
		m_population.add(m_joueur);

		m_positionLumineux = new Vector<Position2D>();

		initMonde();
		this.initPositionLumineux();
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
				//m_monde[i][j] = new Case('0',Position2D.position(i,j));
				m_monde[i][j] = new Case('0');
			}
		}
		

	}
	
	public void Init()
	{
		this.initPositionLumineux();
		initMur();
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
							pcase.setLook('1');
							break;
							
						case 1://extremite
							
							if( estVoisinMP(parcours,1) )
							{
								pcase.setLook('^');//haut
							}
							else if( estVoisinMP(parcours,0) )
							{
								pcase.setLook('v');//bas
							}
							else if( estVoisinMP(parcours,3) )
							{
								pcase.setLook('<');//gauche
							}
							else if( estVoisinMP(parcours,2))
							{
								pcase.setLook('>');//droite
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
							pcase.change(new Case('0'));
							break;
						default:
							pcase.setLook('X');
							break;
					}
				}
				
			}
		}
		
	}

	public void initPositionLumineux()
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
	 * Indique si il existe un perso qui est aux coordonées pos 
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
	 * Retourne le perso à une position donnée
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
	 * Permet de récuperer une case du monde suivant sa position.
	 * @return
	 */
	public Case getCase( Position2D pos )
	{
		return m_monde[pos.getC()][pos.getL()];
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
		return(pos.getC() >= 0 && pos.getC() < Global.NB_CASE_HAUTEUR
				&& pos.getL() >= 0 && pos.getL() < Global.NB_CASE_LARGEUR);
	}

	/**
	 * Indique si un perso peut acceder à la case.
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
			 * String.format(%n) est ici utilisé car '\n' n'est pas reconnu par
			 * sauvegarderFichier mais il joue le même rôle ici.
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
				actuel = contenu.charAt(j + i * (Global.NB_CASE_LARGEUR + 1)); // Pourquoi
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
				}
			}
		}

		Init();
	}

	// /////////////////////////////////
	// /********** UPDATE **********///
	// ////////////////////////////////

	/**
	 * Mise à jour du monde
	 */
	public void update()
	{
		updateActivationTorche();
		updateTorche();
		updateLumiere();
	}
	
	

	// /////////////////////////////////
	// /********** UPDATE **********///
	// ////////////////////////////////
	
	/**
	 * Indique s'il y a une torche autour de pos dans un rayon donné.
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

	public void setCaseVisibleAutour( Position2D pos, int rayon )
	{
		Position2D parcours;
		boolean estVisible=true;
		for ( int i = pos.getC() - rayon; i < pos.getC() + rayon + 1; i++ )
		{
			for ( int j = pos.getL() - rayon; j < pos.getL() + rayon + 1; j++ )
			{
				parcours = new Position2D(i, j);
				
				if ( this.estDansMonde(parcours) 
				/*&& Math.floor(pos.getDistanceTo(parcours)+ Global.TORCHE_SEUIL) < rayon */
				&& pos.getDistanceTo(parcours) <=rayon
				
				)
				{
					estVisible=!(murEntre(pos,parcours) || murEntre(parcours,pos));
					
					this.getCase(parcours).setVisible(estVisible);
					
				}
				
	
			}
		}
	}

	
	
	public boolean murEntre(Position2D A,Position2D B)
	{
		Vector<Position2D> vec=Position2D.positionEntre(A,B);
		
		int i=0;
		boolean existeMur=false;
		
		while(i<vec.size() && !existeMur)
		{
			if(getCase(vec.get(i)).getId()=='M')
			{
				existeMur=true;
			}
			
			i++;
		}
		
		return existeMur;
	}

	public void updateActivationTorche()
	{

		for ( int i = 0; i < m_positionLumineux.size(); i++ )
		{
			if ( this.existePersoPosition(m_positionLumineux.get(i)) )
			{
				System.out.println("OK");
				((Torche) this.getCase(m_positionLumineux.get(i)))
						.activer(Global.TORCHE_DUREE);
			}
		}
	}

	public void updateTorche()
	{
		for ( int i = 0; i < m_positionLumineux.size(); i++ )
		{
			((Torche) this.getCase(m_positionLumineux.get(i))).update();
		}
	}

	public void updateLumiere()
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
		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				
				rendu= getCase(Position2D.position(i,j)).getLook();
				parcours=new Position2D(i,j);
				
				if ( existePersoPosition(parcours) )
				{
					getPersoPosition(parcours).afficher();
				}
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
						System.out.print('"');
					}

				}

			}
			System.out.print('\n');
		}
		
		
		
		
		

	}
	
	//System.out.println('a');
}
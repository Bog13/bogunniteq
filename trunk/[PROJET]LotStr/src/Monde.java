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

	public void initPositionLumineux()
	{
		Position2D parcours;
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

		this.initPositionLumineux();
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
				&& Math.floor(pos.getDistanceTo(parcours)+ Global.TORCHE_SEUIL) < rayon 
				
				)
				{
					estVisible=!(murEntre(pos,parcours) || murEntre(pos,m_joueur.getPosition()));
					
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
		for ( int i = 0; i < Global.NB_CASE_HAUTEUR; i++ )
		{
			for ( int j = 0; j < Global.NB_CASE_LARGEUR; j++ )
			{
				// MONDE CASES
				switch ( m_monde[i][j].getId() )
				{
				case '0':// sol
					rendu = ' ';
					break;

				case 'M':// mur
					rendu = 'X';
					break;

				case 'P':// porte
					rendu = '_';
					break;

				case 'T':// torche
					if ( ((Activable) m_monde[i][j]).estActif() ) rendu = 'T';
					else rendu = 't';
					break;

				default:
					rendu = '@';
					break;
				}
			
				
				
				
				if ( existePersoPosition(new Position2D(i, j)) )
				{
					getPersoPosition(new Position2D(i, j)).afficher();
				}
				else
				{

					this.updateVisibleAutour();
					if ( this.getCase(new Position2D(i, j)).estVisible() )
					{
						System.out.print(rendu);

					}
					else
					{
						System.out.print('?');
					}

				}

			}
			System.out.print('\n');
		}
		
		
		
		
		

	}

}
/**
 *  
 */
enum EtatControleur {Fermee, EnOuverture, PorteOuverte, EnFermeture, EnAttente, Urgence};


/**
 * Classe qui contrôle une porte
 * @author Loïc
 * @version 1.0
 */
class ControleurDePorte
{
	/**
	 *	Constructeur
	 *  @param Porte
	 */
	public ControleurDePorte(Porte porte)
	{
		this.etatPrecedant_ = null;
		this.etatCourant_ = EtatControleur.Fermee;
		this.porte_ = porte;
	}
	
	/**
	 *  getter
	 *  @return EtatControlleur
	 */
	public EtatControleur getEtatCourant() { return this.etatCourant_; }

	/**
	 *  setter
	 *  @param EtatControleur
	 */
	public void setEtatCourant(EtatControleur etat) { this.etatCourant_ = etat; }
	
	/**
	 *  getter
	 *  @return EtatControlleur
	 */
	public EtatControleur setEtatPrecedant() { return this.etatPrecedant_; }	

	/**
	 *  setter
	 *  @param EtatControleur
	 */
	public void setEtatPrecedant(EtatControleur etat) { this.etatPrecedant_ = etat; }

	/**
	 *  getter
	 *  @return Porte
	 */
	public Porte getPorte() { return this.porte_; }

	/**
	 *  setter
	 *  @param Porte
	 */
	public void setPorte(Porte porte) { this.porte_ = porte; }
	
	/**
	 *  
	 */
	public void enregristreContact()
	{
		
	}
	
	/**
	 *  
	 */
	public void ouvre() throws Exception
	{
		if(this.etatCourant_.equals(EtatControleur.Fermee) ||
				this.etatCourant_.equals(EtatControleur.EnAttente))
		{
			this.etatPrecedant_ = this.etatCourant_;
			this.etatCourant_ = EtatControleur.EnOuverture;
			this.porte_.ouvre();
		}
		else if(this.etatCourant_.equals(EtatControleur.EnOuverture))
		{
			this.etatPrecedant_ = this.etatCourant_;
			this.etatCourant_ = EtatControleur.EnAttente;
			this.porte_.pause();
		}
		else throw new Exception("error");
	}
	
	/**
	 *  
	 */
	public void ferme() throws Exception
	{
		if(this.etatCourant_.equals(EtatControleur.EnFermeture))
		{
			this.etatPrecedant_ = this.etatCourant_;
			this.etatCourant_ = EtatControleur.EnAttente;
			this.porte_.pause();
		}
		else if(this.etatCourant_.equals(EtatControleur.EnAttente) || 
				this.etatCourant_.equals(EtatControleur.PorteOuverte))
		{
			this.etatPrecedant_ = this.etatCourant_;
			this.etatCourant_ = EtatControleur.EnFermeture;
			this.porte_.ferme();
		}
		else throw new Exception("error");
	}
	
	/**
	 *  
	 */
	public void urgence() throws Exception
	{
		if(this.etatCourant_.equals(EtatControleur.EnOuverture) || 
				this.etatCourant_.equals(EtatControleur.EnAttente) || 
				this.etatCourant_.equals(EtatControleur.EnFermeture))
		{
			this.etatPrecedant_ = this.etatCourant_;
			this.etatCourant_ = EtatControleur.Urgence;
			this.porte_.bloque();
		}
		else throw new Exception("error");	
	}

	/**
	 *  
	 */
	public void contact() throws Exception
	{
		if(this.etatCourant_.equals(EtatControleur.EnFermeture))
		{
			this.etatPrecedant_ = this.etatCourant_;
			this.etatCourant_ = EtatControleur.Fermee;
			this.porte_.fermee();
		}
		else if(this.etatCourant_.equals(EtatControleur.EnOuverture))
		{
			this.etatPrecedant_ = this.etatCourant_;
			this.etatCourant_ = EtatControleur.PorteOuverte;
			this.porte_.ouverte();
		}
		else throw new Exception("error");		
	}
	
	/**
	 *  
	 */
	public void repriseCle() throws Exception
	{
		EtatControleur temp;
		
		if(this.etatCourant_.equals(EtatControleur.Urgence))
		{
			temp = etatPrecedant_;
			this.etatPrecedant_ = this.etatCourant_;
			this.etatCourant_ = temp;
			this.porte_.reprend();	
		}
		else throw new Exception("error");			
	}
	
	
	private EtatControleur etatCourant_;
	private EtatControleur etatPrecedant_; // dans le cas d'une urgence, permet de reprendre à l'état précédant
	private Porte porte_;
	
}

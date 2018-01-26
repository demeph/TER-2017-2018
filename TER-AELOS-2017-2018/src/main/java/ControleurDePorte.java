import lejos.hardware.lcd.LCD;

/**
 *  
 */
enum EtatControleur {Fermee, EnOuverture, PorteOuverte, EnFermeture, EnAttente, Urgence};


/**
 * Classe qui contr�le une porte
 * @author Lo�c
 * @version 1.0
 */
class ControleurDePorte
{
	private EtatControleur _etatCourant;
	private EtatControleur _etatPrecedant; // dans le cas d'une urgence, permet de reprendre � l'�tat pr�c�dant
	private Porte _porte;
	
	
	ControleurDePorte(Porte porte)
	{
		this._etatPrecedant = null;
		this._etatCourant = EtatControleur.Fermee;
		this._porte = porte;
	}
	
	EtatControleur getEtatCourant() { return this._etatCourant; }

	void setEtatCourant(EtatControleur etat) { this._etatCourant = etat; }
	
	EtatControleur setEtatPrecedant() { return this._etatPrecedant; }	

	void setEtatPrecedant(EtatControleur etat) { this._etatPrecedant = etat; }

	Porte getPorte() { return this._porte; }

	void setPorte(Porte porte) { this._porte = porte; }
	
	/**
	 *  
	 */
	void enregristreContact()
	{
		
	}
	
	/**
	 * Demande � la porte de s'ouvrir et change l'�tat du controleur 
	 */
	void ouvre() throws Exception
	{
		if(this._etatCourant.equals(EtatControleur.Fermee) ||
				this._etatCourant.equals(EtatControleur.EnAttente))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EtatControleur.EnOuverture;
			this._porte.ouvre();
		}
		else if(this._etatCourant.equals(EtatControleur.EnOuverture))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EtatControleur.EnAttente;
			this._porte.pause();
		}
		else throw new Exception("error");
	}
	
	/**
	 *  Demande � la porte de se fermer et change l'�tat du controleur
	 */
	public void ferme() throws Exception
	{
		if(this._etatCourant.equals(EtatControleur.EnFermeture))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EtatControleur.EnAttente;
			this._porte.pause();
		}
		else if(this._etatCourant.equals(EtatControleur.EnAttente) || 
				this._etatCourant.equals(EtatControleur.PorteOuverte))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EtatControleur.EnFermeture;
			this._porte.ferme();
		}
		else throw new Exception("La porte est ferme");
	}
	
	/**
	 *  Arr�te le mouvement de la porte : eteint le moteur car c'est un cas d'urgence
	 */
	public void urgence() throws Exception
	{
		if(this._etatCourant.equals(EtatControleur.EnOuverture) || 
				this._etatCourant.equals(EtatControleur.EnAttente) || 
				this._etatCourant.equals(EtatControleur.EnFermeture))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EtatControleur.Urgence;
			this._porte.bloque();
		}
		else throw new Exception("error");	
	}

	/**
	 *  
	 */
	public void contact() throws Exception
	{
		if(this._etatCourant.equals(EtatControleur.EnFermeture))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EtatControleur.Fermee;
			this._porte.fermee();
		}
		else if(this._etatCourant.equals(EtatControleur.EnOuverture))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EtatControleur.PorteOuverte;
			this._porte.ouverte();
		}
		else throw new Exception("urgence echoue");		
	}
	
	/**
	 *  Reprend l'action qui a �t� stopp�e 
	 */
	void repriseCle() throws Exception
	{
		EtatControleur temp;
		
		if(this._etatCourant.equals(EtatControleur.Urgence))
		{
			temp = _etatPrecedant;
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = temp;
			this._porte.reprend();	
		}
		else throw new Exception("reprise echoue");			
	}
	
}

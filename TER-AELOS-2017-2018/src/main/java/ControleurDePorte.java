import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3TouchSensor;

/**
 *  
 */
enum EtatControleur {Fermee, EnOuverture, PorteOuverte, EnFermeture, EnAttente, Urgence};


/**
 * Classe qui contrôle une porte
 * @author Deme, Loic, Clément
 * @version 1.0
 */
class ControleurDePorte
{
	private EtatControleur _etatCourant;
	private EtatControleur _etatPrecedant; // dans le cas d'une urgence, permet de reprendre à l'état précédant
	private Porte _porte;
	private Capteur _po;
	private Capteur _pf;
	
	
	ControleurDePorte(Porte porte,EV3TouchSensor touchOuvert,EV3TouchSensor touchFerme)
	{
		this._etatPrecedant = null;
		this._etatCourant = EtatControleur.Fermee;
		this._porte = porte;
		
		//initialisation des capteur
		this._po = new Capteur(capteurType.capteurPourOuverture,touchOuvert);
		this._po.set_ctrl(this);
		this._pf = new Capteur(capteurType.capteurPourFermeture,touchFerme);
		this._pf.set_ctrl(this);
	}
	
	/**
	 *  
	 */
	void enregristreContact(Capteur capteur)
	{
		// historise l'activation des capteurs. A confirmer
		try {
			this.contact(capteur);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * Demande à la porte de s'ouvrir et change l'état du controleur 
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
		else throw new Exception("Pas dans etat ferme ou en ouverture");
	}
	
	/**
	 *  Demande à la porte de se fermer et change l'état du controleur
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
	 *  Arrête le mouvement de la porte : eteint le moteur car c'est un cas d'urgence
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
		else throw new Exception("pas etat urgence");	
	}
	
	
	private void captAction(capteurType cp) {
		if (this._po.get_typeCapteur().equals(cp)) {
			this._etatCourant = EtatControleur.PorteOuverte;
			this._porte.ouverte();
		} else if (this._pf.get_typeCapteur().equals(cp)) {
			this._etatCourant = EtatControleur.Fermee;
			this._porte.fermee();
		}else {
			try {
				this.urgence();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LCD.drawString(e.getMessage(), 0, 0);
			}
		}
	}
	

	/**
	 *  
	 */
	public void contact(Capteur capteur) throws Exception
	{
		if(this._etatCourant.equals(EtatControleur.EnFermeture))
		{
			if (capteur.get_typeCapteur().equals(capteurType.capteurPourFermeture)) {
				this._etatPrecedant = this._etatCourant;
				this.captAction(capteur.get_typeCapteur());
			}
		}
		else if(this._etatCourant.equals(EtatControleur.EnOuverture))
		{
			if (capteur.get_typeCapteur().equals(capteurType.capteurPourOuverture)) {
				this._etatPrecedant = this._etatCourant;
				this.captAction(capteur.get_typeCapteur());
			}
		}
		else throw new Exception("urgence echoue");		
	}
	
	/**
	 *  Reprend l'action qui a été stoppée 
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
	
	
	public Capteur get_po() {
		return _po;
	}

	public void set_po(Capteur _po) {
		this._po = _po;
	}

	public Capteur get_pf() {
		return _pf;
	}

	public void set_pf(Capteur _pf) {
		this._pf = _pf;
	}

	EtatControleur getEtatCourant() { return this._etatCourant; }

	void setEtatCourant(EtatControleur etat) { this._etatCourant = etat; }
	
	EtatControleur setEtatPrecedant() { return this._etatPrecedant; }	

	void setEtatPrecedant(EtatControleur etat) { this._etatPrecedant = etat; }

	Porte getPorte() { return this._porte; }

	void setPorte(Porte porte) { this._porte = porte; }
}

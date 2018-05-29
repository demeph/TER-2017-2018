package fr;

import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3TouchSensor;

enum lesCommandes {
	ouvrirLaPorte,
	fermerLaPorte
}

/**
 * Classe qui contrôle une porte
 * @author Deme, Loic, Clément
 * @version 1.0
 */
public class ControleurDePorte
{
	private EnumEtatControleur _etatCourant;
	private EnumEtatControleur _etatPrecedant; // dans le cas d'une urgence, permet de reprendre à l'état précédant
	private Porte _porte;
	private Capteur _po;
	private Capteur _pf;
	
	
	public ControleurDePorte(Porte porte,EV3TouchSensor touchOuvert,EV3TouchSensor touchFerme,int nb1,int nb2)
	{
		this._etatPrecedant = null;
		this._etatCourant = EnumEtatControleur.Fermee;
		this._porte = porte;
		
		//initialisation des capteurs 
		this._po = new Capteur(EnumCapteurType.capteurPourOuverture,touchOuvert,nb1);
		this._po.set_ctrl(this);
		this._pf = new Capteur(EnumCapteurType.capteurPourFermeture,touchFerme,nb2);
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
	public void ouvre() throws Exception
	{
		if(this._etatCourant.equals(EnumEtatControleur.Fermee) ||
				this._etatCourant.equals(EnumEtatControleur.EnAttente))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EnumEtatControleur.EnOuverture;
			this._porte.ouvre();
		}
		else if(this._etatCourant.equals(EnumEtatControleur.EnOuverture))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EnumEtatControleur.EnAttente;
			this._porte.pause();
		}
		else throw new Exception("Pas dans etat ferme ou en ouverture");
	}
	
	/**
	 *  Demande à la porte de se fermer et change l'état du controleur
	 */
	public void ferme() throws Exception
	{
		if(this._etatCourant.equals(EnumEtatControleur.EnFermeture))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EnumEtatControleur.EnAttente;
			this._porte.pause();
		}
		else if(this._etatCourant.equals(EnumEtatControleur.EnAttente) || 
				this._etatCourant.equals(EnumEtatControleur.PorteOuverte))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EnumEtatControleur.EnFermeture;
			this._porte.ferme();
		}
		else throw new Exception("La porte est ferme");
	}
	
	
	/**
	 *  Arrête le mouvement de la porte : eteint le moteur car c'est un cas d'urgence
	 */
	public void urgence() throws Exception
	{
		if(this._etatCourant.equals(EnumEtatControleur.EnOuverture) || 
				this._etatCourant.equals(EnumEtatControleur.EnAttente) || 
				this._etatCourant.equals(EnumEtatControleur.EnFermeture))
		{
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = EnumEtatControleur.Urgence;
			this._porte.bloque();
		}
		else throw new Exception("pas etat urgence");	
	}
	
	
	public void captAction(EnumCapteurType cp) {
		if (this._po.get_typeCapteur().equals(cp)) {
			this._etatCourant = EnumEtatControleur.PorteOuverte;
			this._porte.ouverte();
		} else if (this._pf.get_typeCapteur().equals(cp)) {
			this._etatCourant = EnumEtatControleur.Fermee;
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
		if(this._etatCourant.equals(EnumEtatControleur.EnFermeture))
		{
			if (capteur.get_typeCapteur().equals(EnumCapteurType.capteurPourFermeture)) {
				this._etatPrecedant = this._etatCourant;
				this.captAction(capteur.get_typeCapteur());
			}
		}
		else if(this._etatCourant.equals(EnumEtatControleur.EnOuverture))
		{
			if (capteur.get_typeCapteur().equals(EnumCapteurType.capteurPourOuverture)) {
				this._etatPrecedant = this._etatCourant;
				this.captAction(capteur.get_typeCapteur());
			}
		}
		else throw new Exception("urgence echoue");		
	}
	
	/**
	 *  Reprend l'action qui a été stoppée 
	 */
	public void repriseCle() throws Exception
	{
		EnumEtatControleur temp;
		
		if(this._etatCourant.equals(EnumEtatControleur.Urgence))
		{
			temp = _etatPrecedant;
			this._etatPrecedant = this._etatCourant;
			this._etatCourant = temp;
			this._porte.reprend();	
		}
		else throw new Exception("reprise echoue");			
	}
	
	
	public void traiterDemandeFermeture() {
		//String msg ="Rien a Faire";
		try {
			if (this._etatCourant.equals(EnumEtatControleur.EnFermeture)) {
				this._etatCourant = EnumEtatControleur.EnAttente;
				this._porte.pause();
			//	msg = "La fermeture de la porte est en attente";
			} else if (this._etatCourant.equals(EnumEtatControleur.Urgence)){
				//msg = "Action non autorise, la porte est bloque";
			} else if (this._etatCourant.equals(EnumEtatControleur.PorteOuverte)) {
				ferme();
				//msg = "La porte est en fermeture";
			} else if (this._etatCourant.equals(EnumEtatControleur.EnAttente)) {
				ferme();
				//msg = "La porte reprend la fermeture";
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void traiterDemandeOuverture() {
		//String msg ="Rien a Faire";
		try {			
			if (this._etatCourant.equals(EnumEtatControleur.EnOuverture)) {
				_etatCourant = EnumEtatControleur.EnAttente;
				this._porte.pause();
			//	msg = "L'ouverture de la porte est en attente";
			} else if (this._etatCourant.equals(EnumEtatControleur.Urgence)){
				//msg = "Action non autorise, la porte est bloque";
			} else if (this._etatCourant.equals(EnumEtatControleur.Fermee)) {
			//	msg = "La porte est en ouverture";
				ouvre();			
			} else if (this._etatCourant.equals(EnumEtatControleur.EnAttente)) {
				//msg = "La porte reprend l'ouverture";
				ouvre();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
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

	public EnumEtatControleur get_etatCourant() { return this._etatCourant; }

	public EnumEtatControleur get_etatPrecedant() {
		return _etatPrecedant;
	}
	
	void set_etatCourant(EnumEtatControleur etat) { this._etatCourant = etat; }
	
	public EnumEtatControleur set_etatPrecedant() { return this._etatPrecedant; }	

	void set_etatPrecedant(EnumEtatControleur etat) { this._etatPrecedant = etat; }

	public Porte getPorte() { return this._porte; }

	void setPorte(Porte porte) { this._porte = porte; }
}

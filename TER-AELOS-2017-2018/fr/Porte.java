package fr;


/**
 * 
 * @author Deme, Loic, Clément
 *
 */
public class Porte {
	private EnumEtatPorte _etatCourant;
	private EnumEtatPorte _etatPrecedent;
	private Moteur _mt;
	private ControleurDePorte _ctrl;
	
	
	public Porte(Moteur mt/*,ControleurDePorte ctrl*/) {
		this._etatCourant = EnumEtatPorte.PorteFermee;
		this._etatPrecedent = this._etatCourant;
		this._mt = mt; //ex:  new MoteurSimple(this)
		//this.ctrl = ctrl;
	}
	
	public EnumEtatPorte getEtatPrecedent() {
		return _etatPrecedent;
	}

	public void setEtatPrecedent(EnumEtatPorte etatPrecedent) {
		this._etatPrecedent = etatPrecedent;
	}

	ControleurDePorte getCtrl() {
		return _ctrl;
	}

	void setCtrl(ControleurDePorte ctrl) {
		this._ctrl = ctrl;
	}

	public EnumEtatPorte getEtatCourant() {
		return _etatCourant;
	}

	public void setEtatCourant(EnumEtatPorte etat) {
		this._etatCourant = etat;
	}
	
	public Moteur getMt() {
		return _mt;
	}
	

	void setMt(Moteur mt) {
		this._mt = mt;
	}
	
	
	/**
	 * Ouvre la porte : change l'état et appelle le moteur
	 */
	public void ouvre() {
		if (this._etatCourant != EnumEtatPorte.PorteOuverte) {
			this._etatCourant = EnumEtatPorte.enOuverture;
			this._etatPrecedent = this._etatCourant;
			//moteur tire
			_mt.tirer();	
		}
	}
	
	/**
	 * Ferme la porte : passe l'état à "EnFermeture" et appelle le moteur
	 */
	public void ferme() {
		if (this._etatCourant != EnumEtatPorte.PorteFermee) {
			this._etatCourant = EnumEtatPorte.enFermeture;
			this._etatPrecedent = this._etatCourant;
			//moteur pousse
			_mt.pousser();
		}
	}
	
	/**
	 * Stop le mouvement de la porte : passe l'état à "PorteArreté" et appelle le moteur
	 */
	public void pause() {
		if (this._etatCourant != EnumEtatPorte.PorteArrete) {
			this._etatCourant = EnumEtatPorte.PorteArrete;
			this._etatPrecedent = this._etatCourant;
			_mt.stop();
		}
	}
	
	/**
	 * Passe l'état à "PorteFerme" et appelle le moteur 
	 */
	public void fermee() {
		this._etatCourant = EnumEtatPorte.PorteFermee;
		this._etatPrecedent = this._etatCourant;
		//moteur arrete
		_mt.stop();
	}
	
	/**
	 * Passe l'état à "PorteOuverte" et appelle le moteur
	 */
	public void ouverte() {
		this._etatCourant = EnumEtatPorte.PorteOuverte;
		this._etatPrecedent = this._etatCourant;
		//moteur stop
		_mt.stop();
	}
	
	/**
	 * Bloque la porte : passe l'état à "Bloquée" et stop le moteur
	 */
	public void bloque() {
		this._etatPrecedent = this._etatCourant;
		this._etatCourant = EnumEtatPorte.PorteBloquee;
		//moteur coupe
		_mt.stop();
	}
	
	/**
	 * Reprend l'activité du moteur suivant l'état où il s'était arrêté
	 * avant le bloquage
	 */
	public void reprend() {
		this._etatCourant = this._etatPrecedent;
		if (this._etatPrecedent == EnumEtatPorte.enFermeture) {
			//moteur pousse
			_mt.pousser();
		} else if (this._etatPrecedent == EnumEtatPorte.enOuverture) {
			//moteur tire
			_mt.tirer();
		} else if (this._etatPrecedent == EnumEtatPorte.PorteArrete) {
			_mt.stop();
		}
	}

}

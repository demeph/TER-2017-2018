enum etatPorte {
	PorteOuverte,
	PorteFermee,
	PorteBloquee,
	enOuverture,
	enFermeture,
	PorteArrete,
};

/**
 * 
 * @author Demna
 *
 */
class Porte {
	private etatPorte _etatCourant;
	private etatPorte _etatPrecedent;
	private Moteur _mt;
	private ControleurDePorte _ctrl;
	
	
	Porte(Moteur mt/*,ControleurDePorte ctrl*/) {
		this._etatCourant = etatPorte.PorteFermee;
		this._etatPrecedent = this._etatCourant;
		this._mt = mt; //ex:  new MoteurSimple(this)
		//this.ctrl = ctrl;
	}
	
	etatPorte getEtatPrecedent() {
		return _etatPrecedent;
	}

	void setEtatPrecedent(etatPorte etatPrecedent) {
		this._etatPrecedent = etatPrecedent;
	}

	ControleurDePorte getCtrl() {
		return _ctrl;
	}

	void setCtrl(ControleurDePorte ctrl) {
		this._ctrl = ctrl;
	}

	etatPorte getEtatCourant() {
		return _etatCourant;
	}

	void setEtatCourant(etatPorte etatCourant) {
		this._etatCourant = etatCourant;
	}
	
	Moteur getMt() {
		return _mt;
	}
	

	void setMt(Moteur mt) {
		this._mt = mt;
	}
	
	
	/**
	 * Ouvre la porte : change l'état et appelle le moteur
	 */
	void ouvre() {
		if (this._etatCourant != etatPorte.PorteOuverte) {
			this._etatCourant = etatPorte.enOuverture;
			this._etatPrecedent = this._etatCourant;
			//moteur tire
			_mt.tirer();	
		}
	}
	
	/**
	 * Ferme la porte : passe l'état à "EnFermeture" et appelle le moteur
	 */
	void ferme() {
		if (this._etatCourant != etatPorte.PorteFermee) {
			this._etatCourant = etatPorte.enFermeture;
			this._etatPrecedent = this._etatCourant;
			//moteur pousse
			_mt.pousser();
		}
	}
	
	/**
	 * Stop le mouvement de la porte : passe l'état à "PorteArreté" et appelle le moteur
	 */
	void pause() {
		if (this._etatCourant != etatPorte.PorteArrete) {
			this._etatCourant = etatPorte.PorteArrete;
			this._etatPrecedent = this._etatCourant;
			_mt.stop();
		}
	}
	
	/**
	 * Passe l'état à "PorteFerme" et appelle le moteur 
	 */
	void fermee() {
		this._etatCourant = etatPorte.PorteFermee;
		this._etatPrecedent = this._etatCourant;
		//moteur arrete
		_mt.stop();
	}
	
	/**
	 * Passe l'état à "PorteOuverte" et appelle le moteur
	 */
	void ouverte() {
		this._etatCourant = etatPorte.PorteOuverte;
		this._etatPrecedent = this._etatCourant;
		//moteur stop
		_mt.stop();
	}
	
	/**
	 * Bloque la porte : passe l'état à "Bloquée" et stop le moteur
	 */
	void bloque() {
		this._etatPrecedent = this._etatCourant;
		this._etatCourant = etatPorte.PorteBloquee;
		//moteur coupe
		_mt.stop();
	}
	
	/**
	 * Reprend l'activité du moteur suivant l'état où il s'était arrêté
	 * avant le bloquage
	 */
	void reprend() {
		this._etatCourant = this._etatPrecedent;
		if (this._etatPrecedent == etatPorte.enFermeture) {
			//moteur pousse
			_mt.pousser();
		} else if (this._etatPrecedent == etatPorte.enOuverture) {
			//moteur tire
			_mt.tirer();
		} else if (this._etatPrecedent == etatPorte.PorteArrete) {
			_mt.stop();
		}
	}
		
}

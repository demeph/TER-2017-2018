enum etatPorte {
	PorteOuverte,
	PorteFermee,
	PorteBloquee,
	enOuverture,
	enFermeture,
	PorteArrete,
};


public class Porte {
	private etatPorte etatCourant;
	private etatPorte etatPrecedent;
	private Moteur mt;
	private ControleurDePorte ctrl;
	
	
	public Porte(Moteur mt/*,ControleurDePorte ctrl*/) {
		this.etatCourant = etatPorte.PorteFermee;
		this.etatPrecedent = this.etatCourant;
		this.mt = mt; //ex:  new MoteurSimple(this)
		//this.ctrl = ctrl;
	}
	
	/**
	 * @return the etatPrecedent
	 */
	public etatPorte getEtatPrecedent() {
		return etatPrecedent;
	}

	/**
	 * @param etatPrecedent the etatPrecedent to set
	 */
	public void setEtatPrecedent(etatPorte etatPrecedent) {
		this.etatPrecedent = etatPrecedent;
	}

	/**
	 * @return the ctrl
	 */
	public ControleurDePorte getCtrl() {
		return ctrl;
	}

	/**
	 * @param ctrl the ctrl to set
	 */
	public void setCtrl(ControleurDePorte ctrl) {
		this.ctrl = ctrl;
	}

	/**
	 * @return the etatCourant
	 */
	public etatPorte getEtatCourant() {
		return etatCourant;
	}
	/**
	 * @param etatCourant the etatCourant to set
	 */
	public void setEtatCourant(etatPorte etatCourant) {
		this.etatCourant = etatCourant;
	}
	/**
	 * @return the mt
	 */
	public Moteur getMt() {
		return mt;
	}
	/**
	 * @param mt the mt to set
	 */
	public void setMt(Moteur mt) {
		this.mt = mt;
	}
	
	public void ouvre() {
		if (this.etatCourant != etatPorte.PorteOuverte) {
			this.etatCourant = etatPorte.enOuverture;
			this.etatPrecedent = this.etatCourant;
			//moteur tire
			mt.tirer();	
		}
	}
	
	public void ferme() {
		if (this.etatCourant != etatPorte.PorteFermee) {
			this.etatCourant = etatPorte.enFermeture;
			this.etatPrecedent = this.etatCourant;
			//moteur pousse
			mt.pousser();
		}
	}
	
	public void pause() {
		if (this.etatCourant != etatPorte.PorteArrete) {
			this.etatCourant = etatPorte.PorteArrete;
			this.etatPrecedent = this.etatCourant;
			mt.stop();
		}
	}
	public void fermee() {
		this.etatCourant = etatPorte.PorteFermee;
		this.etatPrecedent = this.etatCourant;
		//moteur arrete
		//mt.arreter();
	}
	
	public void ouverte() {
		this.etatCourant = etatPorte.PorteOuverte;
		this.etatPrecedent = this.etatCourant;
		//moteur arrete
		mt.arreter();
	}
	
	public void bloque() {
		this.etatPrecedent = this.etatCourant;
		this.etatCourant = etatPorte.PorteBloquee;
		//moteur stoppe
		mt.stop();
	}
	
	public void reprend() {
		this.etatCourant = this.etatPrecedent;
		if (this.etatPrecedent == etatPorte.enFermeture) {
			//moteur pousse
			mt.pousser();
		} else if (this.etatPrecedent == etatPorte.enOuverture) {
			//moteur tire
			mt.tirer();
		} else if (this.etatPrecedent == etatPorte.PorteArrete) {
			mt.stop();
		}
	}
		
}

enum EtatMoteur {
	Enpousee,
	Entiree,
	Arret,
};


/**
 * 
 * @author Deme, Loic, Clément
 *
 */
public abstract class Moteur {
	@SuppressWarnings("unused")
	private EtatMoteur _etat;
	
	void setEtat(EtatMoteur a) {
		this._etat = a;
	}
	
	Moteur() {
		this._etat = EtatMoteur.Arret;
	}

	EtatMoteur getEtatMoteur(){
		return this._etat;
	}
	
	/**
	 * Modélise un mouvement de poussée du moteur qui va se traduire par un .forward()
	 */
	abstract void pousser();
	
	/**
	 * Modélise un mouvement de poussée du moteur qui va se traduire par un .backward()
	 */
	abstract void tirer();
	
	/**
	 * Coupe le moteur .close()
	 */
	abstract void arreter();
	
	/**
	 * Met la vitesse du moteur à 0
	 */
	abstract void stop();


}

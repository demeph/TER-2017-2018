package fr.Domodoor;

/**
 * 
 * @author Deme, Loic, Clément
 *
 */
public abstract class Moteur {
	@SuppressWarnings("unused")
	private EnumEtatMoteur _etat;
	
	public void setEtat(EnumEtatMoteur a) {
		this._etat = a;
	}
	
	Moteur() {
		this._etat = EnumEtatMoteur.Arret;
	}

	public EnumEtatMoteur getEtatMoteur(){
		return this._etat;
	}
	
	/**
	 * Modélise un mouvement de poussée du moteur qui va se traduire par un .forward()
	 */
	public abstract void pousser();
	
	/**
	 * Modélise un mouvement de poussée du moteur qui va se traduire par un .backward()
	 */
	public abstract void tirer();
	
	/**
	 * Coupe le moteur .close()
	 */
	public abstract void arreter();
	
	/**
	 * Met la vitesse du moteur à 0
	 */
	abstract void stop();


}

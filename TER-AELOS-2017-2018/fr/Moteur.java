package fr;

import lejos.robotics.RegulatedMotor;

/**
 * 
 * @author Deme, Loic, Clément
 *
 */
public class Moteur {
	
	private EnumEtatMoteur _etatCourant;
	private EnumEtatMoteur _etatPrec;
	private RegulatedMotor _mA;
	private int _vitesse;
	
	public Moteur(RegulatedMotor mA,int vitesse) {
		this._mA = mA;
		this._vitesse = vitesse;
		this._mA.setSpeed(_vitesse);
		_etatCourant = EnumEtatMoteur.Arret;
		_etatPrec = _etatCourant;
	}
	

	public	void pousser() {
		this._mA.setSpeed(_vitesse);
		this._mA.backward();
		_etatPrec = _etatCourant;
		setEtatCourant(EnumEtatMoteur.Enpousee);
	}
	

	public void tirer() {
		this._mA.setSpeed(_vitesse);
		this._mA.forward();
		_etatPrec = _etatCourant;
		setEtatCourant(EnumEtatMoteur.Entiree);
	}
	

	public EnumEtatMoteur get_etatPrec() {
		return _etatPrec;
	}


	public void set_etatPrec(EnumEtatMoteur _etatPrec) {
		this._etatPrec = _etatPrec;
	}


	public void arreter() {
		_mA.stop();
		_etatPrec = _etatCourant;
		setEtatCourant(EnumEtatMoteur.Arret);
	}
	
	public void stop() {
		_mA.stop();
		_etatPrec = _etatCourant;
		setEtatCourant(EnumEtatMoteur.Arret);
	}
	
	
	public RegulatedMotor get_mA() {
		return _mA;
	}


	public void set_mA(RegulatedMotor _mA) {
		this._mA = _mA;
	}


	public void setEtatCourant(EnumEtatMoteur a) {
		this._etatCourant = a;
	}
	
	Moteur() {
		this._etatCourant = EnumEtatMoteur.Arret;
	}

	public EnumEtatMoteur getEtatCourant(){
		return this._etatCourant;
	}
}

package fr;

import lejos.robotics.RegulatedMotor;

/**
 * 
 * @author Deme, Loic, Clément
 *
 */
public class Moteur {
	
	private EnumEtatMoteur _etat;
	private RegulatedMotor _mA;
	private int _vitesse;
	
	public Moteur(RegulatedMotor mA,int vitesse) {
		this._mA = mA;
		this._vitesse = vitesse;
		this._mA.setSpeed(_vitesse);
	}
	

	public	void pousser() {
		_mA.backward();
		setEtat(EnumEtatMoteur.Enpousee);
	}
	

	public void tirer() {
		_mA.forward();
		setEtat(EnumEtatMoteur.Entiree);
	}
	

	public void arreter() {
		_mA.stop();
		setEtat(EnumEtatMoteur.Arret);
	}
	
	public void stop() {
		_mA.setSpeed(0);
		_mA.stop();
		setEtat(EnumEtatMoteur.Arret);
	}
	
	
	public void setEtat(EnumEtatMoteur a) {
		this._etat = a;
	}
	
	Moteur() {
		this._etat = EnumEtatMoteur.Arret;
	}

	public EnumEtatMoteur getEtatMoteur(){
		return this._etat;
	}
}

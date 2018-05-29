package fr;

import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3TouchSensor;

/**
 *
 * @author Deme, Loic, Clément
 *
 */
public class Capteur extends Thread{
	
	private ControleurDePorte _ctrl;
	private EnumCapteurType _typeCapteur;
	private EV3TouchSensor _touchSensor;
	private int _numCapt;

	Capteur(EnumCapteurType typeCaptTactile,EV3TouchSensor touchSensor,int numCpat){
			this._touchSensor = touchSensor;
			this._typeCapteur = typeCaptTactile;
			_numCapt = numCpat;
	}
	
	@Override
	public void run(){
		this.contact();
	}

	/**
	 * Verifie si le capteur tactile etait touche, si Vrai envoye l'information a ctrl pour interpretation
	 */
	void contact() {
		while (true) {
			
			int size = this._touchSensor.sampleSize();
			float[] sample = new float[size];
			this._touchSensor.fetchSample(sample,0);
			
			if (sample[0] == 1.0) {
				this._ctrl.enregristreContact(this);
			}
			
			//LCD.clear();
			if (_numCapt == 0) {
			
				
				LCD.drawString("Ctrl_1_Ouv: " + this._ctrl.getPorte().getMt().getEtatCourant().toString(),0,4);			
				LCD.setAutoRefresh(true);
			} else if (_numCapt == 1) {
				//LCD.clear();
				LCD.drawString("Ctrl_1_Fer: " +this._ctrl.getPorte().getMt().getEtatCourant().toString(),0,5);			
				LCD.setAutoRefresh(true);
			} else if (_numCapt == 2) {
				//LCD.clear();
				LCD.drawString("Ctrl_2_Ouv: " +this._ctrl.getPorte().getMt().getEtatCourant().toString(),0,6);			
				LCD.setAutoRefresh(false);
			} else if (_numCapt == 3) {
				//LCD.clear();
				LCD.drawString("Ctrl_2_Fer: " +this._ctrl.getPorte().getMt().getEtatCourant().toString(),0,7);			
				LCD.setAutoRefresh(false);
			}
			

		}
	}
	
	public ControleurDePorte getCtrl() {
		return _ctrl;
	}

	public void setCtrl(ControleurDePorte ctrl) {
		this._ctrl = ctrl;
	}
	
	public ControleurDePorte get_ctrl() {
		return _ctrl;
	}

	public void set_ctrl(ControleurDePorte _ctrl) {
		this._ctrl = _ctrl;
	}

	public EnumCapteurType get_typeCapteur() {
		return _typeCapteur;
	}

	public void set_typeCapteur(EnumCapteurType _typeCapteur) {
		this._typeCapteur = _typeCapteur;
	}

	public EV3TouchSensor get_touchSensor() {
		return _touchSensor;
	}

	public void set_touchSensor(EV3TouchSensor _touchSensor) {
		this._touchSensor = _touchSensor;
	}

}

package fr.Domodoor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
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

	Capteur(EnumCapteurType typeCaptTactile,EV3TouchSensor touchSensor){
			this._touchSensor = touchSensor;
			this._typeCapteur = typeCaptTactile;
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
			//LCD.clear();7
			System.out.println(this._ctrl.getPorte().getMt().getEtatMoteur().toString());
			//LCD.setAutoRefresh(false);
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

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;


enum capteurType {
	capteurPourOuverture,
	capteurPourFermeture,
};

/**
 *
 * @author Deme, Loic, Clément
 *
 */
public class Capteur extends Thread{
	
	private ControleurDePorte _ctrl;
	private capteurType _typeCapteur;
	private EV3TouchSensor _touchSensor;

	Capteur(capteurType typeCaptTactile){
		if (typeCaptTactile == capteurType.capteurPourFermeture) {
			this._touchSensor = new EV3TouchSensor(SensorPort.S1);
			this._typeCapteur = typeCaptTactile;
		}
		else if (typeCaptTactile == capteurType.capteurPourOuverture) {
			this._touchSensor = new EV3TouchSensor(SensorPort.S2);
			this._typeCapteur = typeCaptTactile;
		}
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
				this._ctrl.enregristreContact(this._typeCapteur);
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

	public capteurType get_typeCapteur() {
		return _typeCapteur;
	}

	public void set_typeCapteur(capteurType _typeCapteur) {
		this._typeCapteur = _typeCapteur;
	}

	public EV3TouchSensor get_touchSensor() {
		return _touchSensor;
	}

	public void set_touchSensor(EV3TouchSensor _touchSensor) {
		this._touchSensor = _touchSensor;
	}

}

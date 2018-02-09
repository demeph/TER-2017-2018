import lejos.robotics.RegulatedMotor;

/**
 * 
 * @author Deme, Loic, Clément
 *
 */
class MoteurSimple extends Moteur {
	private RegulatedMotor _mA;
	
	MoteurSimple(RegulatedMotor mA) {
		super();
		this._mA = mA;
	}
	
	@Override
	void pousser() {
		_mA.setSpeed(50);
		_mA.backward();
		setEtat(EtatMoteur.Enpousee);
	}
	
	@Override
	void tirer() {
		_mA.setSpeed(50);
		_mA.forward();
		setEtat(EtatMoteur.Entiree);
	}
	
	@Override
	void arreter() {
		_mA.close();
		setEtat(EtatMoteur.Arret);
	}
	
	@Override
	void stop() {
		_mA.setSpeed(0);
		_mA.stop();
		setEtat(EtatMoteur.Arret);
	}
}

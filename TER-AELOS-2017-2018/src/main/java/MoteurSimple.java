import lejos.robotics.RegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * 
 * @author Deme, Loic, Clément
 *
 */
class MoteurSimple extends Moteur {
	private RegulatedMotor _mA;
	
	MoteurSimple() {
		super();
		_mA = new EV3LargeRegulatedMotor(MotorPort.A);
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
	}
}

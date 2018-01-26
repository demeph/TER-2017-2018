import lejos.robotics.RegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;


class MoteurDouble extends Moteur {
	private RegulatedMotor _mA;
	private RegulatedMotor _mB;
	
	MoteurDouble() {
		super();
		_mA = new EV3LargeRegulatedMotor(MotorPort.A);
		_mB = new EV3LargeRegulatedMotor(MotorPort.B);
	}
	
	@Override
	void pousser() {
		_mA.forward();
		_mA.setSpeed(100);
		_mB.forward();
		_mB.setSpeed(100);
		setEtat(EtatMoteur.Enpousee);
	}
	
	@Override
	void tirer() {
		_mA.backward();
		_mA.setSpeed(100);
		_mB.backward();
		_mB.setSpeed(100);
		setEtat(EtatMoteur.Entiree);
	}
	
	@Override
	void arreter() {
		_mA.close();
		_mB.close();
		setEtat(EtatMoteur.Arret);
	}
	
	@Override
	void stop() {
		_mA.setSpeed(0);
		_mB.setSpeed(0);
	}
}

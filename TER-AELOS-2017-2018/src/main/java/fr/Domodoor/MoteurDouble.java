package fr.Domodoor;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * @deprecated
 * @author Deme, Loic, Clément
 *
 */
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
		_mA.setSpeed(50);
		_mA.backward();
		_mB.setSpeed(50);
		_mB.backward();
		setEtat(EnumEtatMoteur.Enpousee);
	}
	
	@Override
	void tirer() {
		_mA.setSpeed(50);
		_mA.forward();
		_mB.setSpeed(50);
		_mB.forward();
		setEtat(EnumEtatMoteur.Entiree);
	}
	
	@Override
	void arreter() {
		_mA.close();
		_mB.close();
		setEtat(EnumEtatMoteur.Arret);
	}
	
	@Override
	void stop() {
		_mA.setSpeed(0);
		_mB.setSpeed(0);
	}
}

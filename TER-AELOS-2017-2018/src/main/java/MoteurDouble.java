import lejos.robotics.RegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;


public class MoteurDouble extends Moteur {
	private RegulatedMotor mA;
	private RegulatedMotor mB;
	
	public MoteurDouble() {
		super();
		mA = new EV3LargeRegulatedMotor(MotorPort.A);
		mB = new EV3LargeRegulatedMotor(MotorPort.B);
	}
	
	public void pousser() {
		mA.forward();
		mA.setSpeed(100);
		mB.forward();
		mB.setSpeed(100);
		setEtat(EtatMoteur.Enpousee);
	}
	
	public void tirer() {
		mA.backward();
		mA.setSpeed(100);
		mB.backward();
		mB.setSpeed(100);
		setEtat(EtatMoteur.Entiree);
	}
	
	
	public void arreter() {
		mA.close();
		mB.close();
		setEtat(EtatMoteur.Arret);
	}
	
	public void stop() {
		mA.setSpeed(0);
		mB.setSpeed(0);
	}
}

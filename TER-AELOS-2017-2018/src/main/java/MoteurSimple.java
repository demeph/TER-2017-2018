import lejos.robotics.RegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;


public class MoteurSimple extends Moteur {
	private RegulatedMotor mA;
	
	public MoteurSimple() {
		super();
		mA = new EV3LargeRegulatedMotor(MotorPort.A);
	}
	
	public void pousser() {
		mA.forward();
		mA.setSpeed(100);
		setEtat(EtatMoteur.Enpousee);
	}
	
	public void tirer() {
		mA.backward();
		mA.setSpeed(100);
		setEtat(EtatMoteur.Entiree);
	}
	
	
	public void arreter() {
		mA.close();
		setEtat(EtatMoteur.Arret);
	}
	
	public void stop() {
		mA.setSpeed(0);
	}
}

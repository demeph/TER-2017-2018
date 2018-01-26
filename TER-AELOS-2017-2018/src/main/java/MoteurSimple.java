import lejos.robotics.RegulatedMotor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;


public class MoteurSimple extends Moteur {
	private RegulatedMotor mA;
	
	public MoteurSimple() {
		super();
		mA = new EV3LargeRegulatedMotor(MotorPort.A);
	}
	
	public void pousser() {
		mA.rotate(45);
		mA.setSpeed(10);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setEtat(EtatMoteur.Enpousee);
	}
	
	public void tirer() {
		mA.forward();
		mA.setSpeed(100);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

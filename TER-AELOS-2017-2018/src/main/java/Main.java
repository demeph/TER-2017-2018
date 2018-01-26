import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EV3 ev3 = (EV3) BrickFinder.getDefault();
		Moteur mt = new MoteurSimple();
		Porte pt = new Porte(mt);
		ControleurDePorte cp = new ControleurDePorte(pt);
		
		try {
			LCD.drawString("ici", 1, 1);
			LCD.setAutoRefresh(false);
			cp.ouvre();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LCD.drawString(e.getMessage(), 0, 0);;
		}
	}

}

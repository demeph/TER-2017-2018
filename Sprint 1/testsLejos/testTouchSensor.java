import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;

public class testTouchSensor {

	@SuppressWarnings({ "resource", "resource", "resource" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EV3 ev3 = (EV3) BrickFinder.getDefault();
		EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
		
		RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.A);
		
		while(true) {
			int size = touchSensor.sampleSize();
			float[] sample = new float[size];
			touchSensor.fetchSample(sample,0);
			LCD.drawString("TS: " + sample[0], 0, 0);
			LCD.setAutoRefresh(false);
			if (sample[0] == 1.0) {
				mA.setSpeed(300);
				mA.forward();
			}
			else {
				mA.backward();
				mA.setSpeed(100);
			}
			
		}
		

	}

}

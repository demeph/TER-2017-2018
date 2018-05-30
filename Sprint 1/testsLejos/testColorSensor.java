import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;

public class testColorSensor {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EV3 ev3 = (EV3) BrickFinder.getDefault();
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
		TextLCD lcd = ev3.getTextLCD();
		Keys keys = ev3.getKeys();

		SensorMode color = colorSensor.getRGBMode();
		float[] colorSample = new float[color.sampleSize()];
		lcd.drawInt(colorSample.length, 0, 2);
		int key;
		long startTime = System.currentTimeMillis();
		long duration;

		while (true) {
			duration = System.currentTimeMillis() - startTime;
			color.fetchSample(colorSample, 0);
			lcd.drawString("" + colorSample[0], 0, 3);
			lcd.drawString("" + colorSample[1], 0, 4);
			lcd.drawString("" + colorSample[2], 0, 5);
			
		}

	}

}

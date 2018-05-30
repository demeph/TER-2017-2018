import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class testGiroscope {

	//Robot Configuration
	private static EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S1);

	//Configuration
	private static int HALF_SECOND = 500;

	public static void main(String[] args) {

		final SampleProvider sp = gyroSensor.getAngleMode();
		int value = 0;

        //Control loop
        while(true) {

        	float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            value = (int)sample[0];
            LCD.setAutoRefresh(false);
			LCD.drawString("Gyro angle: " + value,1,2);

            Delay.msDelay(HALF_SECOND);
        }

		//System.out.println(Battery.getInstance().getVoltage());

	}

}
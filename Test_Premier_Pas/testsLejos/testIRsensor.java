	import lejos.hardware.BrickFinder;
	import lejos.hardware.ev3.EV3;
	import lejos.hardware.lcd.LCD;
	import lejos.hardware.motor.*;
	import lejos.hardware.port.MotorPort;
	import lejos.hardware.port.SensorPort;
	import lejos.hardware.sensor.EV3IRSensor;
	import lejos.hardware.sensor.SensorMode;
	import lejos.robotics.RegulatedMotor;

	public class testIRsensor {
	
		private static EV3IRSensor irSensor;
	
		@SuppressWarnings({ "unused", "resource" })
		public static void main(String[] args) {
			EV3 ev3 = (EV3) BrickFinder.getDefault();
			
			irSensor = new EV3IRSensor(SensorPort.S1);
			
			RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.A);
			RegulatedMotor mB = new EV3LargeRegulatedMotor(MotorPort.B);
					
			mA.forward();
			mA.setSpeed(100);
			mB.forward();
			mB.setSpeed(100);
			SensorMode sp = irSensor.getDistanceMode();
			while (true) {
				
				float [] sample = new float[sp.sampleSize()];
				sp.fetchSample(sample, 0);
				float distance = sample[0];
				            
				if (distance < 40){
					String message = "dist : "+distance;
					LCD.clear();
					LCD.drawString(message,4,4);
				    LCD.refresh();
				    mA.setSpeed(30);
				    mB.setSpeed(30);
				}else {
					mA.setSpeed(100);
					mB.setSpeed(100);
					String message = "dist : "+distance;
					LCD.clear();
					LCD.drawString(message,4,4);
				    LCD.refresh();
				}
			}
	//		
			
		}
	
	}

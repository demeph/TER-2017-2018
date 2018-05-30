package fr;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;


public class MainCommunicationBluetooth {

	public static void main(String[] args) {
		

		@SuppressWarnings("unused")
		EV3 ev3 = (EV3) BrickFinder.getDefault();
		
		
		EV3TouchSensor portOuverte1 =  new EV3TouchSensor(SensorPort.S2);
		EV3TouchSensor portFermet1 =  new EV3TouchSensor(SensorPort.S1);

		EV3TouchSensor portOuverte2 =  new EV3TouchSensor(SensorPort.S4);
		EV3TouchSensor portFermet2 =  new EV3TouchSensor(SensorPort.S3);			
		
		Moteur mt1 = new Moteur(new EV3LargeRegulatedMotor(MotorPort.A),30);
		Moteur mt2 = new Moteur(new EV3LargeRegulatedMotor(MotorPort.B),30);
		
		Porte pt1 = new Porte(mt1);
		Porte pt2 = new Porte(mt2);
		
		final ControleurDePorte cp1 = new ControleurDePorte(pt1,portOuverte1,portFermet1,0,1);
		
		final ControleurDePorte cp2 = new ControleurDePorte(pt2,portOuverte2,portFermet2,2,3);
		
		Telecommande2ctrl teleCommande = new Telecommande2ctrl(cp1,cp2);
		
		BluetoothSocket bts = new BluetoothSocket();
		bts.set_teleCommande(teleCommande);
		bts.start();
		
		cp1.get_pf().start();
		cp1.get_po().start();
		
		cp2.get_pf().start();
		cp2.get_po().start();
		
		Button.ESCAPE.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(Key k) {
				// TODO Auto-generated method stub
				try {
					System.exit(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void keyPressed(Key k) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button.ESCAPE.waitForPressAndRelease();

	}

}

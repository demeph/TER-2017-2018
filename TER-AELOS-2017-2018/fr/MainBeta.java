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

public class MainBeta {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EV3 ev3 = (EV3) BrickFinder.getDefault();
		
		EV3TouchSensor portOuverte1 =  new EV3TouchSensor(SensorPort.S2);
		EV3TouchSensor portFermet1 =  new EV3TouchSensor(SensorPort.S1);
		
		
		Moteur mt = new Moteur(new EV3LargeRegulatedMotor(MotorPort.A),20);
		Porte pt = new Porte(mt);
		final ControleurDePorte cp = new ControleurDePorte(pt,portOuverte1,portFermet1,0,1);
		
		cp.get_pf().start();
		cp.get_po().start();
		
		Button.UP.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(Key k) {
				// TODO Auto-generated method stub
				try {
					cp.ouvre();
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
				
		
		Button.DOWN.addKeyListener(new KeyListener() {
				
				@Override
				public void keyReleased(Key k) {
					// TODO Auto-generated method stub
					try {
						cp.ferme();
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
		
		Button.LEFT.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(Key k) {
				// TODO Auto-generated method stub
				try {
					cp.urgence();
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
		
		Button.RIGHT.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(Key k) {
				// TODO Auto-generated method stub
				try {
					cp.repriseCle();
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
		 
		
		Button.UP.waitForPressAndRelease();
		Button.DOWN.waitForPressAndRelease();
		Button.LEFT.waitForPressAndRelease();
		Button.RIGHT.waitForPressAndRelease();
		Button.ESCAPE.waitForPressAndRelease();
	
	}

}

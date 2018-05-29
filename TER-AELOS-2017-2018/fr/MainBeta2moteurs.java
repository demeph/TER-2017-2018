package fr;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class MainBeta2moteurs {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				EV3 ev3 = (EV3) BrickFinder.getDefault();
				
				
				EV3TouchSensor portOuverte1 =  new EV3TouchSensor(SensorPort.S2);
				EV3TouchSensor portFermet1 =  new EV3TouchSensor(SensorPort.S1);

				EV3TouchSensor portOuverte2 =  new EV3TouchSensor(SensorPort.S4);
				EV3TouchSensor portFermet2 =  new EV3TouchSensor(SensorPort.S3);
				
				
				Moteur mt1 = new Moteur(new EV3LargeRegulatedMotor(MotorPort.A),20);
				Moteur mt2 = new Moteur(new EV3LargeRegulatedMotor(MotorPort.B),20);
				Porte pt1 = new Porte(mt1);
				Porte pt2 = new Porte(mt2);
				final ControleurDePorte cp1 = new ControleurDePorte(pt1,portOuverte1,portFermet1,0,1);
				final ControleurDePorte cp2 = new ControleurDePorte(pt2,portOuverte2,portFermet2,2,3);
				
				cp1.get_pf().start();
				cp1.get_po().start();
				cp2.get_po().start();
				cp2.get_pf().start();
				
				Button.UP.addKeyListener(new KeyListener() {
					
					@Override
					public void keyReleased(Key k) {
						// TODO Auto-generated method stub
						try {
							cp1.ouvre();
							cp2.ouvre();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							LCD.drawString(e.getMessage().toString(), 0, 3);
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
								cp1.ferme();
								cp2.ferme();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								LCD.drawString(e.getMessage().toString(), 0, 3);
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
							cp1.urgence();
							cp2.urgence();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							LCD.drawString(e.getMessage().toString(), 0, 3);
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
							cp1.repriseCle();
							cp2.repriseCle();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							LCD.drawString(e.getMessage().toString(), 0, 3);
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
							LCD.drawString(e.getMessage().toString(), 0, 3);
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

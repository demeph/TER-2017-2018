package fr.Domodoor;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;


public class CommunicationWithPC {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
			EV3 ev3 = (EV3) BrickFinder.getDefault();
			
			
			EV3TouchSensor portOuverte1 =  new EV3TouchSensor(SensorPort.S2);
			EV3TouchSensor portFermet1 =  new EV3TouchSensor(SensorPort.S1);
	
			EV3TouchSensor portOuverte2 =  new EV3TouchSensor(SensorPort.S4);
			EV3TouchSensor portFermet2 =  new EV3TouchSensor(SensorPort.S3);
			
			
			Moteur mt1 = new MoteurSimple(new EV3LargeRegulatedMotor(MotorPort.A));
			Moteur mt2 = new MoteurSimple(new EV3LargeRegulatedMotor(MotorPort.B));
			Porte pt1 = new Porte(mt1);
			Porte pt2 = new Porte(mt2);
			final ControleurDePorte cp1 = new ControleurDePorte(pt1,portOuverte1,portFermet1);
			final ControleurDePorte cp2 = new ControleurDePorte(pt2,portOuverte2,portFermet2);
			
			
//			cp1.get_pf().start();
//			cp1.get_po().start();
//			
//			cp2.get_po().start();
//			cp2.get_pf().start();
			
			Telecommande2ctrl teleCommande = new Telecommande2ctrl(cp1,cp2);
			
			SocketThread st = new SocketThread();
			st.set_teleCommande(teleCommande);
			st.start();

	}
}

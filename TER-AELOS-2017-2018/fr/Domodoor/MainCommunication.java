package fr.Domodoor;
import java.io.*;
import java.net.*;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class MainCommunication {
	
	enum lesCommandes {
		versAvant,
		versArriere,
		arreterMoteur,
		fin
	}

	private static ServerSocket welcomeSocket = null;

	private static String capitalizedSentence;

	public static void main(String[] args) {
		
		  String clientSentence;
		  
	      
		try {
			welcomeSocket = new ServerSocket(5555);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        RegulatedMotor m = new EV3MediumRegulatedMotor(MotorPort.A);
	      while(true) {
	    	  try {
	    		  Socket connectionSocket = welcomeSocket.accept();
	            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	            clientSentence = inFromClient.readLine();
	            System.out.println("Received: " + clientSentence);
	            if (clientSentence.equals(lesCommandes.versAvant.toString())) {
	            	m.forward();
	            } else if (clientSentence.equals(lesCommandes.versArriere.toString())) {
	            	m.backward();
	            } else if (clientSentence.equals(lesCommandes.arreterMoteur.toString())){
	            	m.stop();
	            } else if (clientSentence.equals(lesCommandes.fin.toString()))
	            {
	            	m.close();
	            	break;
	            }
	            capitalizedSentence = clientSentence.toUpperCase() + '\n';
	            outToClient.writeBytes(capitalizedSentence);
		      }catch (Exception e) {
					System.out.println(e.getMessage()+":ev3");
				}

	          
	      }
	      
		
	}

}

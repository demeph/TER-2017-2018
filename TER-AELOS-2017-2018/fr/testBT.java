package fr;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lejos.hardware.Bluetooth;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;

public class testBT {
	
	private static DataOutputStream out; 
	private static DataInputStream in;
	private static BTConnection BTConnect;
	private static int commande;

	
	public static boolean estAppareilAutorise(String dataBluetooth) 
	{
		
		String bluetooth_code = "7C:C7:09:18:06:C4";
		
		String bluetooth_code1 = "A0:4C:5B:82:EC:16";
		
		if (dataBluetooth.equals(bluetooth_code)) 
		{
	        return true;
	        
		} 
		else  if (dataBluetooth.equals(bluetooth_code1))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public static boolean connect() throws InterruptedException, IOException
	{  
		System.out.println("En attente de connexion");
		BTConnector BTconnector = (BTConnector) Bluetooth.getNXTCommConnector();
		BTConnect = (BTConnection) BTconnector.waitForConnection(5000, NXTConnection.RAW);
		
		out = BTConnect.openDataOutputStream();
		in = BTConnect.openDataInputStream();
		
		// L'adresse de l'appareil qui veut connecter
		String bluetooth_code = in.readUTF();
		System.out.println(bluetooth_code);

		//connection.connectToJsonFile();
		
		return estAppareilAutorise(bluetooth_code);
	}
	
	private static void writeMessage(byte msg) throws InterruptedException {
		try {
            out.write(msg);
            out.flush();
            Thread.sleep(1000);
        } catch (IOException e) {
        	System.out.println("Impossible d'envoyer le message");
        }
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			try {
				if (!connect() ) {
					//Cas de l'appareil non autorise
					System.out.println("Appareil non autorise");
					writeMessage((byte)-1);
					Thread.sleep(50000);
					
				} else {
					// Cas Autorise
					
					System.out.println("Appareil Autoriser");
					
					writeMessage((byte)-2);
					boolean ecouteMessage= true;
					while(ecouteMessage) {
						try {
							commande = in.readByte();
							
							switch(commande) {	
								// Ouvrir la porte
								case 1:
									System.out.println("1");
							   		break;		   		
							    // Fermer la porte
							   case 2:
								   System.out.println("2");
								   break;
							   case 3 :
								   System.out.println("Urgence");
								   break;
							   case 4 :
								   ecouteMessage = false;
								   System.out.println("Fermer");
								   break;
							   default:
								   break;
							}
						} catch (IOException ioe) {
							System.out.println("111");
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("rest");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("120");
			}
	
		
			
			
			
		
		System.out.println("On attend");
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("134");;
		}
		
	}

}

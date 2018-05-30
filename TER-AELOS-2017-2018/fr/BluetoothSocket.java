package fr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import lejos.hardware.Bluetooth;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;

public class BluetoothSocket extends Thread {
	
	Telecommande2ctrl _teleCommande;
	

	private static DataOutputStream out; 
	private static DataInputStream in;
	private static BTConnection BTConnect;
	private static int commande;
	
	ArrayList<String> lesAppareils;	
	
	public BluetoothSocket() {
		super();
		lesAppareils = new ArrayList<>();
		lesAppareils.add("7C:C7:09:18:06:C4");
		lesAppareils.add("A0:4C:5B:82:EC:16");
	}


	public void run() {
		
		try {
			
			if ( !connect() ) {
					
				//Cas de l'appareil non autorise
				
				System.out.println("Appareil non autorise");
				writeMessage((byte)-1);
				Thread.sleep(50000);
					
			} else {
				
				// Cas Autorise
				
				System.out.println("Appareil : OK");
			
				boolean finConnexion = true;
				
				while(finConnexion) {
					
					try {
								
						commande = in.readByte();
							
						switch(commande) {	
			
							case 1:
							
								_teleCommande.demandeOuverture();
								break;		   		
							
							case 2:
								
								_teleCommande.demandeFermeture();
								break;
								
							default:
								break;
						}
					} catch (IOException ioe) {
						
						System.out.println("IO Exception readInt");
									
					}
				}
			}
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}	
			
		
		System.out.println("Fermeture de l'application");
		
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	public boolean connect() throws InterruptedException, IOException
	{  
		BTConnector BTconnector = (BTConnector) Bluetooth.getNXTCommConnector();
		
		BTConnect = (BTConnection) BTconnector.waitForConnection(100000, NXTConnection.RAW);
		
		//permet envoyer l'information a l'appareil connecter
		out = BTConnect.openDataOutputStream();
		
		//permet recevoir l'information a partir l'appareil connecter
		in = BTConnect.openDataInputStream();
		
		// L'adresse de l'appareil qui veut se connecter
		
		String macMobile = in.readUTF();
		
		boolean estAutorise = false;
		
		for (int i = 0; i < this.lesAppareils.size(); i++) {
			
			if (this.lesAppareils.get(i).equals(macMobile)) 
			{
				
				estAutorise = true;
				
			}
			
		}
		
		return estAutorise;
		
	}
	
	private  void writeMessage(byte msg) throws InterruptedException {
		try {
			
            out.write(msg);
            out.flush();
            Thread.sleep(1000);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}	
	
	
	public Telecommande2ctrl get_teleCommande() {
		return _teleCommande;
	}

	public void set_teleCommande(Telecommande2ctrl _teleCommande) {
		this._teleCommande = _teleCommande;
	}

}

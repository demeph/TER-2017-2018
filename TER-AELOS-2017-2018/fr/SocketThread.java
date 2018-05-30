package fr;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread extends Thread {
	
	Telecommande2ctrl _teleCommande;
	
	@SuppressWarnings("resource")
	@Override
	public void run() {
	 	String clientSentence, capitalizedSentence;  
	      
		ServerSocket welcomeSocket = null;
		
		try {
			
			welcomeSocket = new ServerSocket(5555);
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
			
		}

		while(true) {
			
			try {
				
				Socket connectionSocket = welcomeSocket.accept();
				
	            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	            
	            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	            
	            clientSentence = inFromClient.readLine();
	            
	            if (clientSentence.equals(lesCommandes.ouvrirLaPorte.toString())) {
	            	
	            	_teleCommande.demandeOuverture();
	            	
	            } else if (clientSentence.equals(lesCommandes.fermerLaPorte.toString())) {
	            	
	            	_teleCommande.demandeFermeture();
	            	
	            }            
	            
	            capitalizedSentence = _teleCommande.get_msgATransporte() + '\n';
	            outToClient.writeBytes(capitalizedSentence);
	            
		    }
			catch (Exception e) 
			{
				
		    	  System.out.println(e.getMessage()+":ev3");
		    	  
		    }
			
	     }
		
	}

	public Telecommande2ctrl get_teleCommande() {
		return _teleCommande;
	}

	public void set_teleCommande(Telecommande2ctrl _teleCommande) {
		this._teleCommande = _teleCommande;
	}
	
}

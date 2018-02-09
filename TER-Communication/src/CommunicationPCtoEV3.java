import java.io.*;
import java.net.*;
import java.util.Scanner;


public class CommunicationPCtoEV3 {
	
	enum lesCommandes {
		versAvant,
		versArriere,
		arreterMoteur,
		fin
	}
	
	public static void socketConnection(lesCommandes cmd) throws UnknownHostException, IOException {
	    String modifiedSentence;
		Socket clientSocket = new Socket("10.0.1.1", 5555);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outToServer.writeBytes(cmd.toString() + '\n');
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			  int choix = 0;
			  Scanner inFromUser = new Scanner(System.in);
			  
			  System.out.println("Choisissez l'action :");
			  System.out.println("1 - Mouvement vers l'avant du moteur ");
			  System.out.println("2 - Mouvement vers l'arriere du moteur ");
			  System.out.println("3 - Couper moteur ");
			  System.out.println("4 - Quitter");
		     
		      while (true) {
		    	  System.out.print("Votre choix ?");
		    	  choix = inFromUser.nextInt();
		    	  if (choix == 1) {
						System.out.println("cas 1");
						socketConnection(lesCommandes.versAvant);
		    	  } else  if (choix == 2 ){
		    		  socketConnection(lesCommandes.versArriere);					
		    	  } else  if (choix == 3) {
		    		  	socketConnection(lesCommandes.arreterMoteur);
		    	  } else if (choix == 4){
		    		  socketConnection(lesCommandes.fin);
		    		  System.exit(0);
		    	  }
		      }
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	

}

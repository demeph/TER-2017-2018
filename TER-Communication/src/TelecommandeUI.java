import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

enum lesCommandes {
	ouvrirLaPorte,
	fermerLaPorte
}

public class TelecommandeUI {
		
	
	public static void socketConnection(lesCommandes cmd) throws UnknownHostException, IOException {
	    String modifiedSentence;
		Socket clientSocket = new Socket("10.0.1.1", 5555);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outToServer.writeBytes(cmd.toString() + '\n');
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM Lejos: " + modifiedSentence);
		clientSocket.close();
	}

	public static void main(String[] args) {
		JFrame fenetre = new JFrame();
	      
		fenetre.setTitle("Controleur Porte");
		fenetre.setSize(250, 150);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		
		fenetre.setLayout(new GridLayout(3,1));
		
		final JLabel label = new  JLabel("Bonjour",SwingConstants.CENTER);	
		Button butOuverture = new Button("Ouverture de la porte");
		Button butFermeture = new Button("Fermeture de la porte");
		
		fenetre.getContentPane().add(label,BorderLayout.CENTER);
		fenetre.getContentPane().add(butOuverture);
		fenetre.getContentPane().add(butFermeture);
		
		butOuverture.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					socketConnection(lesCommandes.ouvrirLaPorte);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					label.setText(e1.getMessage());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					label.setText(e1.getMessage());
				}
			}
		});
		
		butFermeture.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					socketConnection(lesCommandes.fermerLaPorte);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					label.setText(e1.getMessage());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					label.setText(e1.getMessage()); 
				}
			}
		});
		
		
		
	}
}
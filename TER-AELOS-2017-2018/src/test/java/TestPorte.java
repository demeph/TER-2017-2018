//package TER-AELOS-2017-2018;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

enum etatPorte {
	PorteOuverte,
	PorteFermee,
	PorteBloquee,
	enOuverture,
	enFermeture,
	PorteArrete,
};

public class TestPorte{
	Moteur mt = new MoteurSimple(new EV3LargeRegulatedMotor(MotorPort.A));
	Porte p = new Porte(mt);

	@Test
	public void testPorteConstructeur(){
		assertEquals(p.getEtatCourant(), etatPorte.PorteFermee);
		assertEquals(p.getMt(), mt);
	}

	@Test
	public void testSetEtatCourant(){
		p.setEtatCourant(etatPorte.PorteOuverte);
		assertEquals(p.getEtatCourant, etatPorte.PorteOuverte);
	}

	@Test
	public void testSetEtatPrecendent(){
		p.setEtatCourant(etatPorte.PorteOuverte);
		assertEquals(p.getEtatPrecendent(), etatPorte.PorteFermee);
	}

	@Test
	public void testOuvre(){
		// pas besoin de changer l'état car la porte est fermée donc on peut l'ouvrir
		p.ouvre();
		assertEquals(p.getEtatCourant(), etatPorte.enOuverture);
	}

	@Test
	public void testFerme(){
		// besoin de changer l'état car la porte est fermée 
		p.setEtatCourant(etatPorte.PorteOuverte); //met en ouverture
		p.ferme();
		assertEquals(p.getEtatCourant(), etatPorte.enFermeture);
	}


	@Test
	public void testPause(){
		// pas besoin de changer l'état car la porte est fermée donc on peut la mettre en pause
		p.pause();
		assertEquals(p.getEtatCourant(), etatPorte.PorteArrete);
	}

	@Test
	public void testFermeeeeee(){
		//attention test sur fermee et non pas ferme
		// pas besoin de changer l'état car la porte est fermée donc on peut la mettre en pause
		p.fermee();
		assertEquals(p.getEtatCourant(), etatPorte.PorteFermee);
	}


	@Test
	public void testOuverte(){
		// pas besoin de changer l'état car la porte est fermée donc on peut la mettre en pause
		p.ouverte();
		assertEquals(p.getEtatCourant(), etatPorte.PorteOuverte);
	}

	@Test
	public void testBloque(){
		// pas besoin de changer l'état car la porte est fermée donc on peut la mettre en pause
		p.bloque();
		assertEquals(p.getEtatCourant(), etatPorte.PorteBloquee);
	}

	@Test
	public void testReprend(){
		p.setEtatCourant(etatPorte.PorteOuverte); // je le passe en ouvert
		// ensuite je test le reprend qui est censé reprendre en ouverte normalement
		p.reprend();
		assertEquals(p.getEtatCourant(), etatPorte.PorteOuverte);
	}	
}
package java.fr.unantes.cta;

//package TER-AELOS-2017-2018;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.Domodoor.*;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class TestPorte{
	Moteur mt = new MoteurSimple(new EV3LargeRegulatedMotor(MotorPort.A));
	Porte p = new Porte(mt);

	@Test
	public void testPorteConstructeur(){
		assertEquals(p.getEtatCourant(), EnumEtatPorte.PorteFermee);
		assertEquals(p.getMt(), mt);
	}

	@Test
	public void testSetEtatCourant(){
		p.setEtatCourant(EnumEtatPorte.PorteOuverte);
		assertEquals(p.getEtatCourant(), EnumEtatPorte.PorteOuverte);
	}

	@Test
	public void testSetEtatPrecendent(){
		p.setEtatCourant(EnumEtatPorte.PorteOuverte);
		assertEquals(p.getEtatPrecedent(), EnumEtatPorte.PorteFermee);
	}

	@Test
	public void testOuvre(){
		// pas besoin de changer l'état car la porte est fermée donc on peut l'ouvrir
		p.ouvre();
		assertEquals(p.getEtatCourant(), EnumEtatPorte.enOuverture);
	}

	@Test
	public void testFerme(){
		// besoin de changer l'état car la porte est fermée 
		p.setEtatCourant(EnumEtatPorte.PorteOuverte); //met en ouverture
		p.ferme();
		assertEquals(p.getEtatCourant(), EnumEtatPorte.enFermeture);
	}


	@Test
	public void testPause(){
		// pas besoin de changer l'état car la porte est fermée donc on peut la mettre en pause
		p.pause();
		assertEquals(p.getEtatCourant(), EnumEtatPorte.PorteArrete);
	}

	@Test
	public void testFermeeeeee(){
		//attention test sur fermee et non pas ferme
		// pas besoin de changer l'état car la porte est fermée donc on peut la mettre en pause
		p.fermee();
		assertEquals(p.getEtatCourant(), EnumEtatPorte.PorteFermee);
	}


	@Test
	public void testOuverte(){
		// pas besoin de changer l'état car la porte est fermée donc on peut la mettre en pause
		p.ouverte();
		assertEquals(p.getEtatCourant(), EnumEtatPorte.PorteOuverte);
	}

	@Test
	public void testBloque(){
		// pas besoin de changer l'état car la porte est fermée donc on peut la mettre en pause
		p.bloque();
		assertEquals(p.getEtatCourant(), EnumEtatPorte.PorteBloquee);
	}

	@Test
	public void testReprend(){
		p.setEtatCourant(EnumEtatPorte.PorteOuverte); // je le passe en ouvert
		// ensuite je test le reprend qui est censé reprendre en ouverte normalement
		p.reprend();
		assertEquals(p.getEtatCourant(), EnumEtatPorte.PorteOuverte);
	}	
}
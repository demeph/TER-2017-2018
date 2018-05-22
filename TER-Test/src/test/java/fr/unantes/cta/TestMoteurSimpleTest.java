package java.fr.unantes.cta;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static org.junit.Assert.*;
import org.junit.Test;

import fr.Domodoor.*;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;



public class TestMoteurSimpleTest {
	Moteur mt = new MoteurSimple(new EV3LargeRegulatedMotor(MotorPort.A));
	
	@Test 	
	public void testConstructeurMoteur(){
		assertEquals(mt.getEtatMoteur(),EnumEtatMoteur.Arret);
	}

	
	@Test
	public void testPousser() {
		fail("Not yet implemented");
	}

	@Test
	public void testTirer() {
		fail("Not yet implemented");
	}

	@Test
	public void testArreter() {
		fail("Not yet implemented");
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoteurSimple() {
		fail("Not yet implemented");
	}

}

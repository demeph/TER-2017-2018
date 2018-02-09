import static org.junit.Assert.*;

import org.junit.Test;


enum EtatMoteur {
	Enpousee,
	Entiree,
	Arret,
};


public class TestMoteurSimpleTest {
	Moteur mt = new MoteurSimple(new EV3LargeRegulatedMotor(MotorPort.A));
	
	@Test 	
	public void testConstructeurMoteur(){
		AssertEquals(mt.getEtatMoteur(),EtatMoteur.Arret);
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

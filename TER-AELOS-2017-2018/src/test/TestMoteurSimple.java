

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.Domodoor.*;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class TestMoteurSimple {
	Moteur mt = new MoteurSimple(new EV3LargeRegulatedMotor(MotorPort.A));

	@Test 	
	public void testConstructeurMoteur(){
		assertEquals(mt.getEtatMoteur(),EnumEtatMoteur.Arret);
	}

	@Test
	public void testPousser(){
		mt.pousser();
		assertEquals(mt.getEtatMoteur(),EnumEtatMoteur.Enpousee);
	}

	@Test
	public void testTirer(){
		mt.tirer();
		assertEquals(mt.getEtatMoteur(),EnumEtatMoteur.Entiree);
	}

	@Test
	public void testArreter(){
		//déjà l'arrêt par défaut donc je vais changer son état quand même
		mt.setEtat(EnumEtatMoteur.Entiree);
		mt.arreter();
		assertEquals(mt.getEtatMoteur(),EnumEtatMoteur.Arret);
	}
}
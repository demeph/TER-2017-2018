

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.Domodoor.*;

enum EtatMoteur {
	Enpousee,
	Entiree,
	Arret,
};

public class TestMoteurSimple {
	Moteur mt = new MoteurSimple(new EV3LargeRegulatedMotor(MotorPort.A));

	@Test 	
	public void testConstructeurMoteur(){
		AssertEquals(mt.getEtatMoteur(),EtatMoteur.Arret);
	}

	@Test
	public void testPousser(){
		mt.pousser();
		AssertEquals(mt.getEtatMoteur(),EtatMoteur.Enpousee);
	}

	@Test
	public void testTirer(){
		mt.tirer();
		AssertEquals(mt.getEtatMoteur(),EtatMoteur.Entiree);
	}

	@Test
	public void testArreter(){
		//déjà l'arrêt par défaut donc je vais changer son état quand même
		mt.setEtat(EtatMoteur.Entiree);
		mt.arreter();
		AssertEquals(mt.getEtatMoteur(),EtatMoteur.Arret);
	}
}
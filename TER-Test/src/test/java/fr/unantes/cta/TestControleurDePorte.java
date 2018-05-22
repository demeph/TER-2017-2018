package java.fr.unantes.cta;


import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.Domodoor.*;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;


/**
 * Classe qui tests la classe ControleurDePorte
 * @author Loïc
 * @version 1.0
 */
class TestControleurDePorte
{
	Moteur mt = new MoteurSimple(new EV3LargeRegulatedMotor(MotorPort.A));
	Porte pt = new Porte(mt);
	// pas nécéssaire de le mettre dans chaque méthode, on en a tjrs besoin
	ControleurDePorte cp = new ControleurDePorte(pt,new EV3TouchSensor(SensorPort.S1),new EV3TouchSensor(SensorPort.S3)); 
	
	/**
 	*  
 	*/
	@Before
	public void setUp() throws Exception 
	{
	
	}

	/**
 	*  
 	*/
	@After
	public void tearDown() throws Exception 
	{
	
	}

	/**
	 * @return 
 	*  
 	*/
	@Test
	public void testControleurDePorte()
	{
		assertEquals(cp.get_etatPrecedant(),null);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.Fermee);	
		assertEquals(cp.getPorte(),null);
		assertEquals(cp.get_pf(),null);
		assertEquals(cp.get_po(),null);

	}

	/**
 	*  
 	*/	
	@Test
	public void testEnregristreContact()
	{
		// rien à test ?
	}
	
	/**
 	*  
 	*/	
	@Test
	public void testOuvre_Fermee()
	{
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.Fermee);	
		try {
			cp.ouvre();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.Fermee);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnOuverture);

	}

	/**
 	*  
 	*/	
	@Test
	public void testOuvre_EnAttente()
	{
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnAttente);	
		try {
			cp.ouvre();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.EnAttente);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnOuverture);

	}

	/**
 	*  
 	*/
	@Test
	public void testOuvre_EnOuverture()
	{
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnOuverture);	
		try {
			cp.ouvre();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.EnOuverture);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnAttente);

	}

	/**
 	*  
 	*/
	@Test
	public void testFerme_EnFermeture()
	{
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnFermeture);	
		try {
			cp.ferme();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.EnAttente);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.PorteOuverte);
	
	}

	/**
 	*  
 	*/	
	@Test
	public void testFerme_EnAttente()
	{
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnAttente);	
		try {
			cp.ferme();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.EnAttente);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnFermeture);
	
	}

	/**
 	*  
 	*/
	@Test
	public void testFerme_PorteOuverte()
	{
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.PorteOuverte);	
		try {
			cp.ferme();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.PorteOuverte);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnFermeture);
	
	}

	/**
 	*  
 	*/
	@Test
	public void testUrgence_EnOuverture()
	{
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnOuverture);	
		try {
			cp.urgence();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.EnAttente);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.Urgence);
	
	}
	
	/**
 	*  
 	*/
	@Test
	public void testUrgence_EnAttente()
	{
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnAttente);	
		try {
			cp.urgence();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.EnAttente);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.Urgence);
	
	}

	/**
 	*  
 	*/
	@Test
	public void testUrgence_EnFermeture()
	{
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnFermeture);	
		try {
			cp.urgence();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.EnFermeture);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.Urgence);

	}

	/**
 	*  
 	*/
	@Test
	private void testCaptAction_po() 
	{
		assertEquals(cp.get_po().get_typeCapteur(),EnumCapteurType.capteurPourOuverture);
		cp.captAction(EnumCapteurType.capteurPourOuverture);
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.PorteOuverte);

	}

	/**
 	*  
 	*/
	@Test
	private void testCaptAction_pf() 
	{
		assertEquals(cp.get_po().get_typeCapteur(),EnumCapteurType.capteurPourFermeture);
		cp.captAction(EnumCapteurType.capteurPourOuverture);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.Fermee);

	}

	/**
 	*  
 	*/
	@Test
	private void testCaptAction_not_po_or_pf() 
	{
		assertEquals(cp.get_po().get_typeCapteur(),EnumCapteurType.capteurPourFermeture);		
		assertEquals(cp.get_po().get_typeCapteur(),EnumCapteurType.capteurPourOuverture);
		cp.captAction(EnumCapteurType.capteurPourOuverture);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.Urgence);

	}

	/**
 	*  
 	*/
	@Test 
	public void testContact_EnFermeture()
	{
		assertEquals(cp.get_po().get_typeCapteur(),EnumCapteurType.capteurPourFermeture);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnFermeture);	

	}
	
	/**
 	*  
 	*/	
	@Test 
	public void testContact_EnOuverture()
	{
		assertEquals(cp.get_po().get_typeCapteur(),EnumCapteurType.capteurPourOuverture);		
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnOuverture);	

	}

	/**
 	*  
 	*/
	@Test
	public void testRepriseCle()
	{
		assertEquals(cp.set_etatPrecedant(),EnumEtatControleur.EnOuverture);
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.Urgence);
		try {
			cp.repriseCle();
		} catch (InvocationTargetException e) {

    // Answer:
    e.getCause().printStackTrace();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cp.get_etatCourant(),EnumEtatControleur.EnOuverture);	
		assertEquals(cp.get_etatPrecedant(),EnumEtatControleur.Urgence);

	}

	// pas de tests pour les getters et setters

}
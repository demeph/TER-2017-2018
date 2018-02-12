
import static org.junit.Assert.*;

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
		assertThat(cp.get_etatPrecedant()).isEqualTo(null);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.Fermee);	
		assertThat(cp.getPorte()).isNotEqualTo(null);
		assertThat(cp.get_pf()).isNotEqualTo(null);
		assertThat(cp.get_po()).isNotEqualTo(null);

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
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.Fermee);	
		cp.ouvre();
		assertThat(cp.set_etatPrecedant()).isEqualTo(EnumEtatControleur.Fermee);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnOuverture);

	}

	/**
 	*  
 	*/	
	@Test
	public void testOuvre_EnAttente()
	{
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnAttente);	
		cp.ouvre();
		assertThat(cp.set_etatPrecedant()).isEqualTo(EnumEtatControleur.EnAttente);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnOuverture);

	}

	/**
 	*  
 	*/
	@Test
	public void testOuvre_EnOuverture()
	{
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnOuverture);	
		cp.ouvre();
		assertThat(cp.set_etatPrecedant()).isEqualTo(EnumEtatControleur.EnOuverture);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnAttente);

	}

	/**
 	*  
 	*/
	@Test
	public void testFerme_EnFermeture()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EnumEtatControleur.EnFermeture);	
		cp.ferme();
		assertThat(cp.setEtatPrecedant()).isEqualTo(EnumEtatControleur.EnAttente);
		assertThat(cp.getEtatCourant()).isEqualTo(EnumEtatControleur.PorteOuverte);
	
	}

	/**
 	*  
 	*/	
	@Test
	public void testFerme_EnAttente()
	{
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnAttente);	
		cp.ferme();
		assertThat(cp.set_etatPrecedant()).isEqualTo(EnumEtatControleur.EnAttente);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnFermeture);
	
	}

	/**
 	*  
 	*/
	@Test
	public void testFerme_PorteOuverte()
	{
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.PorteOuverte);	
		cp.ferme();
		assertThat(cp.set_etatPrecedant()).isEqualTo(EnumEtatControleur.PorteOuverte);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnFermeture);
	
	}

	/**
 	*  
 	*/
	@Test
	public void testUrgence_EnOuverture()
	{
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnOuverture);	
		cp.urgence();
		assertThat(cp.set_etatPrecedant()).isEqualTo(EnumEtatControleur.EnAttente);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.Urgence);
	
	}
	
	/**
 	*  
 	*/
	@Test
	public void testUrgence_EnAttente()
	{
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnAttente);	
		cp.urgence();
		assertThat(cp.set_etatPrecedant()).isEqualTo(EnumEtatControleur.EnAttente);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.Urgence);
	
	}

	/**
 	*  
 	*/
	@Test
	public void testUrgence_EnFermeture()
	{
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnFermeture);	
		cp.urgence();
		assertThat(cp.set_etatPrecedant()).isEqualTo(EnumEtatControleur.EnFermeture);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.Urgence);

	}

	/**
 	*  
 	*/
	@Test
	private void testCaptAction_po() 
	{
		assertThat(cp.get_po().get_typeCapteur().isEqualTo(capteurType.capteurPourOuverture));
		captAction(capteurType.capteurPourOuverture);
		assertThat(cp..setEtatPrecedant()).isEqualTo(EnumEtatControleur.PorteOuverte);

	}

	/**
 	*  
 	*/
	@Test
	private void testCaptAction_pf() 
	{
		assertThat(cp.get_po().get_typeCapteur().isEqualTo(capteurType.capteurPourFermeture));
		captAction(capteurType.capteurPourOuverture);
		assertThat(cp..getEtatCourant()).isEqualTo(EnumEtatControleur.Fermee);

	}

	/**
 	*  
 	*/
	@Test
	private void testCaptAction_not_po_or_pf() 
	{
		assertThat(cp.get_po().get_typeCapteur().isNotEqualTo(capteurType.capteurPourOuverture));		
		assertThat(cp.get_po().get_typeCapteur().isNotEqualTo(capteurType.capteurPourFermeture));
		captAction(capteurType.capteurPourOuverture);
		assertThat(cp..getEtatCourant()).isEqualTo(EnumEtatControleur.Urgence);

	}

	/**
 	*  
 	*/
	@Test 
	public void testContact_EnFermeture()
	{
		assertThat(cp.get_po().get_typeCapteur().isEqualTo(EnumCapteurType.capteurPourFermeture));
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnFermeture);	

	}
	
	/**
 	*  
 	*/	
	@Test 
	public void testContact_EnOuverture()
	{
		assertThat(cp.get_po().get_typeCapteur().isEqualTo(EnumCapteurType.capteurPourOuverture));		
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.EnOuverture);	

	}

	/**
 	*  
 	*/
	@Test
	public void testRepriseCle()
	{
		assertThat(cp.set_etatPrecedant()).isEqualTo(EnumEtatControleur.EnOuverture);
		assertThat(cp.get_etatCourant()).isEqualTo(EnumEtatControleur.Urgence);	
		cp.repriseCle();
		assertThat(cp.getEtatCourant()).isEqualTo(EnumEtatControleur.EnOuverture);	
		assertThat(cp.getEtatPrecedant()).isEqualTo(EnumEtatControleur.Urgence);

	}

	// pas de tests pour les getters et setters

}
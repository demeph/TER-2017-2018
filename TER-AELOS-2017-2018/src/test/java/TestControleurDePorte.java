
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 *  
 */
enum EtatControleur {Fermee, EnOuverture, PorteOuverte, EnFermeture, EnAttente, Urgence};

/**
 *  
 */
enum capteurType {capteurPourOuverture, capteurPourFermeture};

/**
 * Classe qui tests la classe ControleurDePorte
 * @author Loïc
 * @version 1.0
 */
class TestControleurDePorte
{
	Moteur mt = new MoteurSimple();
	Porte pt = new Porte(mt);
	ControleurDePorte cp = new ControleurDePorte(pt); // pas nécéssaire de le mettre dans chaque méthode, on en a tjrs besoin
	
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
 	*  
 	*/
	@test
	public testControleurDePorte()
	{
		assertThat(cp._etatPrecedent.getEtatPrecedant()).isEqualTo(null);
		assertThat(cp._etatCourant.getEtatCourant()).isEqualTo(EtatControleur.Fermee);	
		assertThat(cp._porte.getPorte()).isNotEqualTo(null);
		assertThat(cp._pf.get_pf()).isNotEqualTo(null);
		assertThat(cp._po.get_po()).isNotEqualTo(null);

	}

	/**
 	*  
 	*/	
	@test
	public void testEnregristreContact()
	{
		// rien à test ?
	}
	
	/**
 	*  
 	*/	
	@test
	public void testOuvre_Fermee()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.Fermee);	
		cp.ouvre();
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.Fermee);
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnOuverture);

	}

	/**
 	*  
 	*/	
	@test
	public void testOuvre_EnAttente()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnAttente);	
		cp.ouvre();
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.EnAttente);
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnOuverture);

	}

	/**
 	*  
 	*/
	@test
	public void testOuvre_EnOuverture()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnOuverture);	
		cp.ouvre();
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.EnOuverture);
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnAttente);

	}

	/**
 	*  
 	*/
	@test
	public void testFerme_EnFermeture()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnFermeture);	
		cp.ferme();
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.EnAttente);
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.PorteOuverte);
	
	}

	/**
 	*  
 	*/	
	@test
	public void testFerme_EnAttente()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnAttente);	
		cp.ferme();
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.EnAttente);
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnFermeture);
	
	}

	/**
 	*  
 	*/
	@test
	public void testFerme_PorteOuverte()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.PorteOuverte);	
		cp.ferme();
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.PorteOuverte);
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnFermeture);
	
	}

	/**
 	*  
 	*/
	@test
	public void testUrgence_EnOuverture()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnOuverture);	
		cp.urgence();
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.EnAttente);
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.Urgence);
	
	}
	
	/**
 	*  
 	*/
	@test
	public void testUrgence_EnAttente()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnAttente);	
		cp.urgence();
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.EnAttente);
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.Urgence);
	
	}

	/**
 	*  
 	*/
	@test
	public void testUrgence_EnFermeture()
	{
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnFermeture);	
		cp.urgence();
		assertThat(cp..getEtatPrecedant()).isEqualTo(EtatControleur.EnFermeture);
		assertThat(cp..getEtatCourant()).isEqualTo(EtatControleur.Urgence);

	}

	/**
 	*  
 	*/
	@test
	private void testCaptAction_po() 
	{
		assertThat(cp.get_po().get_typeCapteur().isEqualTo(capteurType.capteurPourOuverture));
		captAction(capteurType.capteurPourOuverture);
		assertThat(cp..getEtatPrecedant()).isEqualTo(EtatControleur.PorteOuverte);

	}

	/**
 	*  
 	*/
	@test
	private void testCaptAction_pf() 
	{
		assertThat(cp.get_po().get_typeCapteur().isEqualTo(capteurType.capteurPourFermeture));
		captAction(capteurType.capteurPourOuverture);
		assertThat(cp..getEtatCourant()).isEqualTo(EtatControleur.Fermee);

	}

	/**
 	*  
 	*/
	@test
	private void testCaptAction_not_po_or_pf() 
	{
		assertThat(cp.get_po().get_typeCapteur().isNotEqualTo(capteurType.capteurPourOuverture));		
		assertThat(cp.get_po().get_typeCapteur().isNotEqualTo(capteurType.capteurPourFermeture));
		captAction(capteurType.capteurPourOuverture);
		assertThat(cp..getEtatCourant()).isEqualTo(EtatControleur.Urgence);

	}

	/**
 	*  
 	*/
	@test 
	public void testContact_EnFermeture()
	{
		assertThat(cp.get_po().get_typeCapteur().isEqualTo(capteurType.capteurPourFermeture));
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnFermeture);	

	}
	
	/**
 	*  
 	*/	
	@test 
	public void testContact_EnOuverture()
	{
		assertThat(cp.get_po().get_typeCapteur().isEqualTo(capteurType.capteurPourOuverture));		
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnOuverture);	

	}

	/**
 	*  
 	*/
	@Test
	public void testRepriseCle()
	{
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.EnOuverture);
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.Urgence);	
		cp.repriseCle();
		assertThat(cp.getEtatCourant()).isEqualTo(EtatControleur.EnOuverture);	
		assertThat(cp.getEtatPrecedant()).isEqualTo(EtatControleur.Urgence);

	}

	// pas de tests pour les getters et setters

}
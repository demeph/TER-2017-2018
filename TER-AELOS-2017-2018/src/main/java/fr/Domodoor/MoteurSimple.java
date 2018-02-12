package fr.Domodoor;
import lejos.robotics.RegulatedMotor;

/**
 * 
 * @author Deme, Loic, Clément
 *
 */
public class MoteurSimple extends Moteur {
	private RegulatedMotor _mA;
	
	public MoteurSimple(RegulatedMotor mA) {
		super();
		this._mA = mA;
	}
	
	@Override
	public	void pousser() {
		_mA.setSpeed(50);
		_mA.backward();
		setEtat(EnumEtatMoteur.Enpousee);
	}
	
	@Override
	public void tirer() {
		_mA.setSpeed(50);
		_mA.forward();
		setEtat(EnumEtatMoteur.Entiree);
	}
	
	@Override
	public void arreter() {
		_mA.close();
		setEtat(EnumEtatMoteur.Arret);
	}
	
	@Override
	public void stop() {
		_mA.setSpeed(0);
		_mA.stop();
		setEtat(EnumEtatMoteur.Arret);
	}
}

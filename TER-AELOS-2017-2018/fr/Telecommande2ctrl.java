package fr;


public class Telecommande2ctrl {

	private ControleurDePorte _ctrl1;
	private ControleurDePorte _ctrl2;
	private String _msgATransporte;
	
	public Telecommande2ctrl(ControleurDePorte ctrl1, ControleurDePorte ctrl2) {
		this._ctrl1 = ctrl1;
		this._ctrl2 = ctrl2;
	
		this._ctrl1.get_pf().start();
		this._ctrl1.get_po().start();
		
		this._ctrl2.get_pf().start();
		this._ctrl2.get_po().start();
	}
	
	void demandeOuverture() {
		this._ctrl2.getPorte().getMt().get_mA().startSynchronization();
		String msg1 = this._ctrl1.traiterDemandeOuverture();
		String msg2 = this._ctrl2.traiterDemandeOuverture();
		this._ctrl2.getPorte().getMt().get_mA().endSynchronization();
		this._msgATransporte = msg1 +":" + msg2;						
	}
	
	void demandeFermeture() {
		this._ctrl2.getPorte().getMt().get_mA().startSynchronization();
		String msg1 = this._ctrl1.traiterDemandeFermeture();
		String msg2 = this._ctrl2.traiterDemandeFermeture();
		this._ctrl2.getPorte().getMt().get_mA().endSynchronization();
		this._msgATransporte = msg1 +":" + msg2;			
	}

	public ControleurDePorte get_ctrl1() {
		return _ctrl1;
	}

	public void set_ctrl1(ControleurDePorte _ctrl1) {
		this._ctrl1 = _ctrl1;
	}

	public ControleurDePorte get_ctrl2() {
		return _ctrl2;
	}

	public void set_ctrl2(ControleurDePorte _ctrl2) {
		this._ctrl2 = _ctrl2;
	}

	public String get_msgATransporte() {
		return _msgATransporte;
	}

	public void set_msgATransporte(String _msgATransporte) {
		this._msgATransporte = _msgATransporte;
	}
	
	
}
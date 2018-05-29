package fr;


public class Telecommande2ctrl {

	private ControleurDePorte _ctrl1;
	private ControleurDePorte _ctrl2;
	private String _msgATransporte;
	
	public Telecommande2ctrl(ControleurDePorte ctrl1, ControleurDePorte ctrl2) {
		this._ctrl1 = ctrl1;
		this._ctrl2 = ctrl2;
	
		
	}
	
	void demandeOuverture() {
		CtrlOpenThread ctrlOpen1 = new CtrlOpenThread();
		
		ctrlOpen1.set_ctrl(_ctrl1);
		
		CtrlOpenThread ctrlOpen2 = new CtrlOpenThread();
		
		ctrlOpen2.set_ctrl(_ctrl2);
		
		ctrlOpen1.start();
		ctrlOpen2.start();
		
		
	}
	
	void demandeFermeture() {
		
		CtrlCloseThread ctrlClose1 = new CtrlCloseThread();
		
		ctrlClose1.set_ctrl(_ctrl1);
		
		CtrlCloseThread ctrlClose2 = new CtrlCloseThread();
		
		ctrlClose2.set_ctrl(_ctrl2);
		
		ctrlClose1.start();
		ctrlClose2.start();
			
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

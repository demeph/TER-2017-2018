package fr;

public class CtrlOpenThread extends Thread {

	ControleurDePorte _ctrl;

	public ControleurDePorte get_ctrl() {
		return _ctrl;
	}

	public void set_ctrl(ControleurDePorte _ctrl) {
		this._ctrl = _ctrl;
	}
	
	public void run() {
		this._ctrl.traiterDemandeOuverture();
	}
	
}

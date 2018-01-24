enum EtatMoteur {
	Enpousee,
	Entiree,
	Arret,
};

public abstract class Moteur {
	private EtatMoteur etat;
	
	public void setEtat(EtatMoteur a) {
		etat = a;
	}
	
	public Moteur() {
		etat = EtatMoteur.Arret;
	}
	
	public abstract void pousser();
	public abstract void tirer();
	public abstract void arreter();
	public abstract void stop();
}

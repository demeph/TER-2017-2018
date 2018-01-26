import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EV3 ev3 = (EV3) BrickFinder.getDefault();
		Moteur mt = new MoteurSimple();
		Porte pt = new Porte(mt);
		ControleurDePorte cp = new ControleurDePorte(pt);
		
		
		while (true) {

			if (Button.DOWN.isDown()) {
				try {
					LCD.clear();
					LCD.drawString("j'ouvre", 1, 1);
					LCD.setAutoRefresh(false);
					cp.ouvre();			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LCD.drawString(e.getMessage(), 0, 0);
				}
			} else if (Button.UP.isDown()) {
				try {
					LCD.clear();
					LCD.drawString("je ferme", 1, 1);
					LCD.setAutoRefresh(false);
					cp.ferme();			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LCD.drawString(e.getMessage(), 0, 0);
				}
			} else if (Button.LEFT.isDown()) {
				try {
					LCD.clear();
					LCD.drawString("je suis en etat urgence", 1, 1);
					LCD.setAutoRefresh(false);
					cp.urgence();			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LCD.drawString(e.getMessage(), 0, 0);
				}
			} else if (Button.RIGHT.isDown()) {
				try {
					LCD.clear();
					LCD.drawString("je reprend", 1, 1);
					LCD.setAutoRefresh(false);
					cp.repriseCle();			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LCD.drawString(e.getMessage(), 0, 0);
				}
			}
		}		
	}

}

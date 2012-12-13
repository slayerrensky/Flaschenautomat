package Automat;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:51
 */
public class Laufband extends Aktor {

	private boolean gesperrt;
	private int lastState;

	public Laufband(int adresse) {
		super(adresse);
		gesperrt = false;
	}

	public void rueckwerts(){
		if(!gesperrt){
			this.HWaccess.write(adresse, -1);
			lastState = -1;
		}
	}

	public void stop(){
		this.HWaccess.write(adresse, 0);
	}

	public void vorwaerts(){
		if(!gesperrt){
			this.HWaccess.write(adresse, 1);
			lastState = 1;
		}
	}
	
	public void sperren(){
		gesperrt = true;
	}
	
	public void entsperren(){
		gesperrt = false;
	}
	
	public void ausschalten() {
			this.HWaccess.write(adresse, 0);
			lastState = 0;
	}

	public void einschalten() {
		if(!gesperrt){
			this.HWaccess.write(adresse, lastState);
		}
	}
}
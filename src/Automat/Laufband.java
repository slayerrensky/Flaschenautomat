package Automat;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:51
 */
public class Laufband extends Aktor {

	private boolean gesperrt;

	public Laufband(int adresse) {
		super(adresse);
		gesperrt = false;
	}

	public void rueckwerts(){
		if(!gesperrt){
			this.HWaccess.write(adresse, 0);
		}
	}

	public void stopp(){
		this.HWaccess.write(adresse, 0);
	}

	public void vorwaerts(){
		if(!gesperrt){
			this.HWaccess.write(adresse, 1);
		}
	}
	
	public void sperren(){
		gesperrt = true;
	}
	
	public void entsperren(){
		gesperrt = false;
	}
}
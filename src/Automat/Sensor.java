package Automat;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:52
 */
public class Sensor {

	protected int adresse;
	protected HWLayer HWaccess;

	// type = 0 == Leuchte; 1 == Troete
	public Sensor(int adresse) {
		this.adresse = adresse;
		HWaccess = HWLayer.getInstance();
	}

	public Boolean read() {
		Boolean tmp = false;
		HWaccess.read(adresse, tmp);
		return tmp;
	}

}
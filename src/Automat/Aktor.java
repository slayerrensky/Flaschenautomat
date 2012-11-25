package Automat;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:52
 */
public class Aktor {

	protected int adresse;
	protected HWLayer HWaccess;

	// type = 0 == Leuchte; 1 == Troete
	public Aktor(int adresse) {
		this.adresse = adresse;
		HWaccess = HWLayer.getInstance();
	}

	public void finalize() throws Throwable {

	}

	public void ausschalten() {
		HWaccess.write(adresse, false);
	}

	public void einschalten() {
		HWaccess.write(adresse, true);
	}

}
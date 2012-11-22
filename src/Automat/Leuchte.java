package Automat;

import Fassade.Fassade;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:52
 */
public class Leuchte {

	private int adresse;
	private Fassade DieFassade;

	// type = 0 == Leuchte; 1 == Troete
	public Leuchte(Fassade fassade, int adresse) {
		this.adresse = adresse;
		this.DieFassade = fassade;
	}

	public void finalize() throws Throwable {

	}

	public void ausschalten() {
		DieFassade.warnleuchteAUS();
	}

	public void einschalten() {
		DieFassade.warnleuchteAN();
	}

}
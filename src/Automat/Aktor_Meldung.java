package Automat;

import Fassade.Fassade;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:52
 */
public class Aktor_Meldung {

	private int adresse;
	private int type;
	private Fassade DieFassade;

	// type = 0 == Leuchte; 1 == Troete
	public Aktor_Meldung(Fassade fassade, int adresse, int type) {
		this.adresse = adresse;
		this.DieFassade = fassade;
		this.type = type;
	}

	public void finalize() throws Throwable {

	}

	public void ausschalten() {
		switch (type) {
		default:
		case 0:
			DieFassade.warnleuchteAUS();
			break;
		case 1:
			DieFassade.warnsignalAUS();
			break;
		}
	}

	public void einschalten() {
		switch (type) {
		default:
		case 0:
			DieFassade.warnleuchteAN();
			break;
		case 1:
			DieFassade.warnsignalAN();
			break;
		}
	}

}
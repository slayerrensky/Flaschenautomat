package Automat;

import Fassade.Fassade;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:51
 */
public class Display {

private int adresse;
private Fassade DieFassade;
		
	public Display(Fassade fassade, int adresse){
		this.DieFassade = fassade;
		this.adresse = adresse;
	}
	
	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param Text
	 */
	public void setText(String Text){
		DieFassade.displayAktualisieren(Text);
	}

}
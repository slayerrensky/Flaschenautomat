package Automat;

import Fassade.Fassade;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:52
 */
public class Leuchte extends Aktor{
	private int color_adresse;
	
	public Leuchte(int adresse, int color_adresse) {
		super(adresse);
		this.color_adresse = color_adresse;
	}
	
	public void setColor(int color){
		if(!(color<0 || color>3)){
			this.HWaccess.write(color_adresse, color);
		}
	}

}
package Automat;

/**
 * @author Dennis
 * @version 1.0
 * @created 03-Nov-2012 10:11:17
 */
public class Lichtschranke {

	private int adresse;

	public Lichtschranke(int adresse) {
		this.adresse = adresse;
	}

	public void finalize(){

	}

	public boolean getStatus(){
		return false;
	}

}
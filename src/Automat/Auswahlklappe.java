package Automat;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:45
 */
public class Auswahlklappe extends Aktor {

	public Auswahlklappe(int adresse) {
		super(adresse);
	}
	
	/**
	 * @param FlaschenType Flasche
	 * 
	 * @param o0
	 */
	public void stellen(FlaschenType type){
		
		switch (type) {
		default:
		case PET:
			HWaccess.write(adresse, false);
			break;
		case Mehrweg:
			HWaccess.write(adresse, true);
			break;
		}
	}



}
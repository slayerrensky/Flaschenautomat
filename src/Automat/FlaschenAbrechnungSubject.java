package Automat;

/*
 * Diese Klasse ist das Contretes Subjekt f�r Pfandflaschenberechnung
 *  
 */

public class FlaschenAbrechnungSubject extends Subjekt {

	protected String code;
	
	public String getSubjectState(){
		return code;
	}
	
	public void setSubjectState(String code){
		this.code = code;
	}
}

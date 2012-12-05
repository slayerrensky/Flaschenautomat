package Automat;

/*
 * Diese Klasse ist das Contretes Subjekt für Pfandflaschenberechnung
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

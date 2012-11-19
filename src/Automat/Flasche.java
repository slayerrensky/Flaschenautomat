package Automat;

import java.util.Currency;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:54
 */
public class Flasche {

	private FlaschenType type;
	private Currency guthaben;
	private int anzahl;
	private String scanncode;

	public Flasche(FlaschenType type, Currency guthaben, String scanncode){
		this.type = type;
		this.guthaben = guthaben;
		this.scanncode = scanncode;
		
	}

	public void finalize() throws Throwable {

	}
	
	
	
	public void AnzahlInc()
	{
		this.anzahl++;
	}
	
	public void AnzahlDec()
	{
		this.anzahl--;
	}
	
	public Currency getGuthaben(){
		return null;
	}

}
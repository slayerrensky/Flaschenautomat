package Automat;

import java.util.Currency;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:54
 */
public class Flasche {

	private FlaschenType Type;
	private Currency Guthaben;
	private int anzahl;
	private String scanncode;

	public Flasche(FlaschenType Type){

	}

	public void finalize() throws Throwable {

	}
	
	public void AnzahlInc()
	{}
	
	public void AnzahlDec()
	{}
	
	/**
	 * 
	 * @param Guthaben
	 * @param Type
	 */
	public Flasche(Currency Guthaben, FlaschenType Type){

	}

	public Currency getGuthaben(){
		return null;
	}

}
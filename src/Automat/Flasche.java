package Automat;

import java.math.BigDecimal;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:54
 */
public class Flasche {

	private FlaschenType type;
	private BigDecimal pfandwert;
	private int anzahl;
	private String scanncode;

	public Flasche(FlaschenType type, BigDecimal pfandwert, String scanncode){
		this.type = type;
		this.pfandwert = pfandwert;
		this.scanncode = scanncode;
		
	}

	public void finalize() throws Throwable {

	}
	
	public String getCode()
	{
		return scanncode;
	}
	
	public void AnzahlInc()
	{
		this.anzahl++;
	}
	
	public void AnzahlDec()
	{
		this.anzahl--;
	}
	
	public BigDecimal getPfandwert(){
		return pfandwert;
	}

	public int getAnzahl(){
		return anzahl;
	}
	
	public void reset()
	{
		this.anzahl=0;
	}
	
	public FlaschenType getType()
	{
		return type;
	}
	
	public String toString()
	{
		return "FlaschenType: " + type.toString() + ";Scancode: " + scanncode + ";Pfandwert: " + pfandwert.toString() + ";Anzahl: " + anzahl;  
	}
}
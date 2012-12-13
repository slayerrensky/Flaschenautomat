package Automat;

import Fassade.Fassade;

/**
 * Performante und thread-safe Implementierung des Singleton-Patterns
 */
public class HW {
	private static HW instance = new HW();

	/**
	 * Default-Konstruktor, der nicht au�erhalb dieser Klasse
	 * aufgerufen werden kann
	 */
	private HW() {
		// Do nothing!
	}

	/**
	 * Statische Methode, liefert die einzige Instanz dieser
	 * Klasse zur�ck
	 */
	public static HW getInstance() {
		return instance;
	}
	
	public Boolean readBool(int adresse){
		return false;		
	}
	
	public Integer readInt(int adresse){
		return 0;		
	}
	
	public String readStr(int adresse){
		return "";
	}
	
	public void write(int adresse, Boolean status){
	}
	
	public void write(int adresse, Integer number){
	}
	
	public void write(int adresse, String text){
	}
	
	public void setF(Fassade dieFassade){
	}
}
package Automat;

import java.util.ArrayList;
import Fassade.Fassade;

/**
 * Performante und thread-safe Implementierung des Singleton-Patterns
 */
public class HWSimulation {
	private static HWSimulation instance = new HWSimulation();
	private ArrayList<Comparable> mapping;
	private Fassade dieFassade;

	/**
	 * Default-Konstruktor, der nicht au�erhalb dieser Klasse
	 * aufgerufen werden kann
	 */
	private HWSimulation() {
		mapping = new ArrayList<Comparable>();
		mapping.add(new Boolean(false)); // 0 Troete
		mapping.add(new Boolean(true));  // 1 Leuchte
		mapping.add(new Integer(0)); 	 // 2 Leuchte -Farbe
		mapping.add(new Boolean(false)); // 3 AuswahlklappeEingangslichtschranke
		mapping.add(new Boolean(false)); // 4 Eingangslichtschranke
		mapping.add(new Boolean(false)); // 5 Justierlichtschranke
		mapping.add(new Boolean(false)); // 6 Ausgangslichtschranke
		mapping.add(new Boolean(false)); // 7 UebergabelichtschrankePET
		mapping.add(new Boolean(false)); // 8 UebergabelichtschrankeMehrweg
		mapping.add(new Integer(0)); // 9 LaufbandEingang
		mapping.add(new Integer(0)); // 10 LaufbandDrehen
		mapping.add(new Integer(0)); // 11 LaufbandAusgang
		mapping.add(new Boolean(false)); // 12 Auswahlklappe
		mapping.add(new Integer(0)); 	 // 13 Scanner
		mapping.add(new Boolean(false)); // 14 BonDrucker
		mapping.add(new Boolean(false)); // 15 Druckknopf
		mapping.add(new String("init")); // 16 Display
		mapping.add(new String("init")); // 17 Drucker
	}

	/**
	 * Statische Methode, liefert die einzige Instanz dieser
	 * Klasse zur�ck
	 */
	public static HWSimulation getInstance() {
		return instance;
	}
	
	public Boolean readBool(int adresse){
		Boolean status = (Boolean) mapping.get(adresse);
		dieFassade.simKonsolenText(0, "[read:"+adresse+"] "+status);
		return status;		
	}
	
	public Integer readInt(int adresse){
		Integer number = (Integer) mapping.get(adresse);
		dieFassade.simKonsolenText(0, "[read:"+adresse+"] "+number);
		return number;		
	}
	
	public String readStr(int adresse){
		String text = (String) mapping.get(adresse);
		dieFassade.simKonsolenText(0, "[read:"+adresse+"] \""+text+"\"");
		return text;
	}
	
	public void write(int adresse, Boolean status){
		mapping.set(adresse, status);
		dieFassade.aktuallisereHW(mapping);
		dieFassade.simKonsolenText(0, "[write:"+adresse+"] "+status);
	}
	
	public void write(int adresse, Integer number){
		mapping.set(adresse, number);
		dieFassade.aktuallisereHW(mapping);
		dieFassade.simKonsolenText(0, "[write:"+adresse+"] "+number);
	}
	
	public void write(int adresse, String text){
		mapping.set(adresse, text);
		dieFassade.aktuallisereHW(mapping);
		dieFassade.simKonsolenText(0, "[write:"+adresse+"] \""+text+"\"");
	}
	
	public void setF(Fassade dieFassade){
		this.dieFassade = dieFassade;
		this.dieFassade.aktuallisereHW(mapping);
	}
}
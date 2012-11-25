package Automat;

import java.util.ArrayList;
import Fassade.Fassade;

/**
 * Performante und thread-safe Implementierung des Singleton-Patterns
 */
public class HWLayer {
	private static HWLayer instance = new HWLayer();
	private ArrayList<Comparable> mapping;
	private Fassade dieFassade;

	/**
	 * Default-Konstruktor, der nicht außerhalb dieser Klasse
	 * aufgerufen werden kann
	 */
	private HWLayer() {
		mapping = new ArrayList<Comparable>();
		mapping.add(new Boolean(false)); // Troete
		mapping.add(new Boolean(true));  // Leuchte
		mapping.add(new Integer(0)); 	 // Leuchte -Farbe
		mapping.add(new Boolean(false)); // AuswahlklappeEingangslichtschranke
		mapping.add(new Boolean(false)); // Eingangslichtschranke
		mapping.add(new Boolean(false)); // Justierlichtschranke
		mapping.add(new Boolean(false)); // Ausgangslichtschranke
		mapping.add(new Boolean(false)); // UebergabelichtschrankePET
		mapping.add(new Boolean(false)); // UebergabelichtschrankeMehrweg
		mapping.add(new Integer(0)); // LaufbandEingang
		mapping.add(new Integer(0)); // LaufbandDrehen
		mapping.add(new Integer(0)); // LaufbandAusgang
		mapping.add(new Boolean(false)); // Auswahlklappe
		mapping.add(new Integer(0)); 	 // Scanner
		mapping.add(new Boolean(false)); // BonDrucker
		mapping.add(new Boolean(false)); // Druckknopf
		mapping.add(new String("init")); // Display
		mapping.add(new String("init")); // Drucker
	}

	/**
	 * Statische Methode, liefert die einzige Instanz dieser
	 * Klasse zurück
	 */
	public static HWLayer getInstance() {
		return instance;
	}
	
	public void read(int adresse, Boolean status){
		status = (Boolean) mapping.get(adresse);
		dieFassade.simKonsolenText(0, "HWLayer[read] an Adr "+adresse+" Wert: "+status);
	}
	
	// trick damit es call by referenc ist
	public void read(int adresse, Integer number){
		number = (Integer) mapping.get(adresse);
		dieFassade.simKonsolenText(0, "HWLayer[read] an Adr "+adresse+" Wert: "+number);
	}
	
	public void read(int adresse, String text){
		text = (String) mapping.get(adresse);
		dieFassade.simKonsolenText(0, "HWLayer[read] an Adr "+adresse+" Wert: "+text);
	}
	
	public void write(int adresse, Boolean status){
		mapping.set(adresse, status);
		dieFassade.aktuallisereHW(mapping);
		dieFassade.simKonsolenText(0, "HWLayer[write] an Adr "+adresse+" Wert: "+status);
	}
	
	public void write(int adresse, Integer number){
		mapping.set(adresse, number);
		dieFassade.aktuallisereHW(mapping);
		dieFassade.simKonsolenText(0, "HWLayer[write] an Adr "+adresse+" Wert: "+number);
	}
	
	public void write(int adresse, String text){
		mapping.set(adresse, text);
		dieFassade.aktuallisereHW(mapping);
		dieFassade.simKonsolenText(0, "HWLayer[write] an Adr "+adresse+" Wert: "+text);
	}
	
	public void setF(Fassade dieFassade){
		this.dieFassade = dieFassade;
		this.dieFassade.aktuallisereHW(mapping);
	}
}
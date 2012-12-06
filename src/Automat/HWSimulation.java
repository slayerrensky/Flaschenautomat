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
	
	//define adressing constants
	public final int Troete = 0;
	public final int Leuchte = 1;
	public final int LFarbe = 2;
	public final int AuswahlEingLS = 3;
	public final int EingLS = 4;
	public final int JustierLS = 5;
	public final int AusgangsLS = 6;
	public final int UebergabeLSPET = 7;
	public final int UebergabeLSMehrweg = 8;
	public final int LBEingang = 9;
	public final int LBDrehen = 10;
	public final int LBAusgang = 11;
	public final int Auswahlklappe = 12;
	public final int Scanner = 13;
	public final int BonDrucker = 14;
	public final int Druckknopf = 15;
	public final int Display = 16;
	public final int Drucker = 17;
	

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
	
	public void read(int adresse, Boolean status){
		status = (Boolean) mapping.get(adresse);
		dieFassade.simKonsolenText(0, "[read:"+adresse+"] "+status);
	}
	
	// trick damit es call by referenc ist
	public void read(int adresse, Integer number){
		number = (Integer) mapping.get(adresse);
		dieFassade.simKonsolenText(0, "[read:"+adresse+"] "+number);
	}
	
	public void read(int adresse, String text){
		text = (String) mapping.get(adresse);
		dieFassade.simKonsolenText(0, "[read:"+adresse+"] \""+text+"\"");
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
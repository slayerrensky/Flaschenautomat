package Test;

import Automat.FlaschenType;
import Automat.HWSimulation;
import Automat.Adressen;
import Fassade.Fassade;





public class SimulationFlasche extends Thread {
	
	private Fassade Fassade;
	private HWSimulation HW;
	private FlaschenType FType;
	
	enum Station {
		Ausgeworfen,
		Eingang,
		Positioniert,
		Auswahlklappe,
		Mehrwegbehaelter,
		PETbehaelter;
	}
	
	
	public SimulationFlasche(Fassade fassade,FlaschenType FTypeIn) {
		Fassade = fassade;
		HW = HWSimulation.getInstance();
		FType = FTypeIn;
		
	}
	
	
	
	public void run() {

		boolean posChanged=true; // wird gesetzt um kürzlichen Positionswechsel anzuzeigen 
		
		Station pos = Station.Eingang;
		
		
		Fassade.simKonsolenText(0, "Simulation einer Flasche des Typs: " + FType.toString());
		
		while(true){	
			
			try { Thread.sleep(100); } //warte um nicht alles zu überlasten
			catch(InterruptedException e){ System.out.println(e); }
			
			switch (pos) {
			
			/*
			 * Am Eingang
			 */
			case Eingang:
				
				if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()==0){
			
					//Position nicht verändert
				
					if(posChanged){ // Eigenschaften der Position setzen
						HW.write(Adressen.Eingangslichtschranke.ordinal(), true);
						Fassade.simKonsolenText(0, "Sim: Eingangslichtschranke durch Flasche aktiviert.");
						posChanged = false;
					}
				}
				else if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()>0){
					try { Thread.sleep(100); } //warte 0.1s bis die Flasche weggefahren ist
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.Eingangslichtschranke.ordinal(), false);
					pos=Station.Positioniert; //nächster Zustand: Flasche Positioniert
					posChanged = true;
				}
				else if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()<0){
					try { Thread.sleep(100); } //warte 0.1s bis die Flasche weggefahren ist
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.Eingangslichtschranke.ordinal(), false);
					pos=Station.Ausgeworfen; //nächster Zustand: Flasche Ausgeworfen
					posChanged = true;
				}
				break;
			
			/*
			 *  An der Postionierungslichtschranke
			 */
			case Positioniert: 
				if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()==0 &&
						HW.readInt(Adressen.LaufbandDrehen.ordinal()).intValue()==0){
					//Position nicht verändert
					
					if(posChanged){
						HW.write(Adressen.Justierlichtschranke.ordinal(), true);
						Fassade.simKonsolenText(0, "Sim: Justierlichtschranke durch Flasche aktiviert.");
						posChanged = false;
					}
				}
				else if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()>0){
					try { Thread.sleep(100); } //warte 0.1s bis die Flasche weggefahren ist
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.Justierlichtschranke.ordinal(), false);
					pos=Station.Auswahlklappe; //nächster Zustand: Flasche bei Auswahlklappe
					posChanged = true;
				}
				else if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()<0){
					try { Thread.sleep(100); } //warte 0.1s bis die Flasche weggefahren ist
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.Justierlichtschranke.ordinal(), false);
					pos=Station.Eingang; //nächster Zustand: Flasche am Eingang
					posChanged = true;
				}
				else if(HW.readInt(Adressen.LaufbandDrehen.ordinal()).intValue()!=0){
					try { Thread.sleep(100); } //warte 0.1s um ein Ergebnis auszugeben (random wäre besser)
					catch(InterruptedException e){ System.out.println(e); }
					
					switch(FType){
						case Mehrweg : 
							HW.write(Adressen.Scanner.ordinal(), 1); //code für Mehrweg
							Fassade.simKonsolenText(0, "Sim: Mehrweg Flaschencode");
							break;
						case PET :
							HW.write(Adressen.Scanner.ordinal(), 1000); //code für PET
							Fassade.simKonsolenText(0, "Sim: PET Flaschencode");
							break;
						case CodeNichtValide :
							HW.write(Adressen.Scanner.ordinal(), 2392728); //unbekannter Flaschencode
							Fassade.simKonsolenText(0, "Sim: Unbekannter Flaschencode");
							break;
						case CodeUnlesbar :
							HW.write(Adressen.Scanner.ordinal(), -1); //Flaschencode unlesbar
							Fassade.simKonsolenText(0, "Sim: Unlesbarer Flaschencode.");
							break;
						
						default :
							Fassade.simKonsolenText(0, "Systemfehler: Flaschentyp existiert nicht.");
							break;
					}
				}
				
				
				break;
			
			/*
			 * An der Auswahlklappe
			 */
			case Auswahlklappe:
				if(HW.readBool(Adressen.Auswahlklappe.ordinal()).booleanValue()){
					//für Mehrweg
					HW.write(Adressen.UebergabelichtschrankeMehrweg.ordinal(), true);
					try { Thread.sleep(100); } //warte 0.1s bis die Flasche weggefahren ist
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.UebergabelichtschrankeMehrweg.ordinal(), false);
					pos=Station.Mehrwegbehaelter; //nächster Zustand: Flasche bei Auswahlklappe
					posChanged = true;
				}
				else if(!HW.readBool(Adressen.Auswahlklappe.ordinal()).booleanValue()){
					//für PET
					HW.write(Adressen.UebergabelichtschrankePET.ordinal(), true);
					try { Thread.sleep(100); } //warte 0.1s bis die Flasche weggefahren ist
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.UebergabelichtschrankePET.ordinal(), false);
					pos=Station.PETbehaelter; //nächster Zustand: Flasche bei Auswahlklappe
					posChanged = true;
				}
				break;
				
				
			default:
				
				break;
			}
			
		}
	}
	
}

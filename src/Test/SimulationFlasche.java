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
		Eingang,
		Positioniert,
		Ausgang,
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
		
		MainLoop:
		while(true){	
			
			try { Thread.sleep(100); } //regelmäßig warten
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
						Fassade.simKonsolenText(0, "Sim: Flasche am Eingang.");
						Fassade.simKonsolenText(0, "Sim: Eingangslichtschranke aktiviert.");
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
					
					HW.write(Adressen.Eingangslichtschranke.ordinal(), true);
					
					while(HW.readBool(Adressen.Eingangslichtschranke.ordinal())){
					try { Thread.sleep(100); } //warte 0.1s bis die Flasche weggefahren ist
					catch(InterruptedException e){ System.out.println(e); }
					}
					//Fassade.simKonsolenText(0, "Sim: Simulation Beendet.");
					break MainLoop;
					
				}
				break;
			
			/*
			 *  An der Postionierungslichtschranke
			 */
			case Positioniert: 
				if(posChanged){
					HW.write(Adressen.Justierlichtschranke.ordinal(), true);
					Fassade.simKonsolenText(0, "Sim: Flasche bei der Positionierung.");
					Fassade.simKonsolenText(0, "Sim: Justierlichtschranke aktiviert.");
					posChanged = false;
					
					try { Thread.sleep(1000); } //warte damit das System reagieren kann
					catch(InterruptedException e){ System.out.println(e); }
				}
				else if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()==0 &&
						HW.readInt(Adressen.LaufbandDrehen.ordinal()).intValue()==0){
					//Position nicht verändert
					
				}
				else if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()>0){ //hier fehlerhaft
					try { Thread.sleep(100); } //warte 0.1s bis die Flasche weggefahren ist
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.Justierlichtschranke.ordinal(), false);
					pos=Station.Ausgang; //nächster Zustand: Flasche beim hinteren Ausgang
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
					try { Thread.sleep(100); } //warte 0.1s um ein Ergebnis auszugeben
					catch(InterruptedException e){ System.out.println(e); }
					
					Fassade.simKonsolenText(0, "Sim: Flasche wird zum Scannen gedreht.");
					
					switch(FType){
						case Mehrweg : 
							//HW.write(Adressen.Scanner.ordinal(), "1"); //code für Mehrweg
							Fassade.simKonsolenText(0, "Sim: Mehrweg Flaschencode");
							break;
						case PET :
							//HW.write(Adressen.Scanner.ordinal(), "1000"); //code für PET
							Fassade.simKonsolenText(0, "Sim: PET Flaschencode");
							break;
						case CodeNichtValide :
							//HW.write(Adressen.Scanner.ordinal(), "2392728"); //unbekannter Flaschencode
							Fassade.simKonsolenText(0, "Sim: Unbekannter Flaschencode");
							break;
						case CodeUnlesbar :
							//HW.write(Adressen.Scanner.ordinal(), "-1"); //Flaschencode unlesbar
							Fassade.simKonsolenText(0, "Sim: Unlesbarer Flaschencode.");
							break;
						
						default :
							Fassade.simKonsolenText(0, "Systemfehler: Flaschentyp existiert nicht.");
							break;
					}
				}
				
				
				break;
				
			/*
			 * Am hineren Ausgang der Scan-Einrichtung
			 */
			case Ausgang:
				
				HW.write(Adressen.Ausgangslichtschranke.ordinal(), true);
				
				if(posChanged){
					HW.write(Adressen.Ausgangslichtschranke.ordinal(), true);
					Fassade.simKonsolenText(0, "Sim: Flasche bei der Ausgangslichtschranke.");
					Fassade.simKonsolenText(0, "Sim: Ausgangslichtschranke aktiviert.");
					posChanged = false;
					
					try { Thread.sleep(1000); } //warte 1s damit das System reagieren kann
					catch(InterruptedException e){ System.out.println(e); }
				}
				else if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()==0){
					//Position nicht verändert
				}
				else if(HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue()>0){ //hier fehlerhaft
					try { Thread.sleep(100); } //warte 0.1s bis die Flasche weggefahren ist
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.Ausgangslichtschranke.ordinal(), false);
					pos=Station.Auswahlklappe; //nächster Zustand: Flasche bei Auswahlklappe
					posChanged = true;
				}
				
				break;
			
			/*
			 * An der Auswahlklappe
			 */
			case Auswahlklappe:
				
				if(posChanged){
					//aktiviere die Eingangslichtschranke der Auswahlklappe
					HW.write(Adressen.AuswahlklappeEingangslichtschranke.ordinal(), true);
					Fassade.simKonsolenText(0, "Sim: Flasche bei der Auswahlklappe.");
					Fassade.simKonsolenText(0, "Sim: AusgangswahlklappeEingangslichtschranke aktiviert.");
					posChanged = false;
					
					try { Thread.sleep(1000); } //warte 1s damit das System reagieren kann
					catch(InterruptedException e){ System.out.println(e); }
					HW.write(Adressen.AuswahlklappeEingangslichtschranke.ordinal(), false);
				}
				else if(HW.readBool(Adressen.Auswahlklappe.ordinal()).booleanValue()){
					//für Mehrweg
					HW.write(Adressen.UebergabelichtschrankeMehrweg.ordinal(), true);
					try { Thread.sleep(1000); } //Die Flasche ist eine Sekunde lang unter dem Sensor
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.UebergabelichtschrankeMehrweg.ordinal(), false);
					pos=Station.Mehrwegbehaelter; //nächster Zustand: Flasche bei Auswahlklappe
					posChanged = true;
				}
				else if(!HW.readBool(Adressen.Auswahlklappe.ordinal()).booleanValue()){
					//für PET
					HW.write(Adressen.UebergabelichtschrankePET.ordinal(), true);
					try { Thread.sleep(1000); } //Die Flasche ist eine Sekunde lang unter dem Sensor
					catch(InterruptedException e){ System.out.println(e); }
					
					HW.write(Adressen.UebergabelichtschrankePET.ordinal(), false);
					pos=Station.PETbehaelter; //nächster Zustand: Flasche bei Auswahlklappe
					posChanged = true;
				}
				break;
				
			case Mehrwegbehaelter:
				
				Fassade.simKonsolenText(0, "Sim: Die Flasche ist im Mehrwegbehälter gelandet.");
		
				break MainLoop;

			
			case PETbehaelter:
				
				Fassade.simKonsolenText(0, "Sim: Die Flasche ist im PETbehaelter gelandet.");
				
				break MainLoop;
				
			default:
				
				break;
			}
			
		}
	}
	
}

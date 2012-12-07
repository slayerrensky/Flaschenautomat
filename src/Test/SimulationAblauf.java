package Test;

import Automat.FlaschenType;
import Automat.HWSimulation;
import Fassade.Fassade;

public class SimulationAblauf {
	
	private Fassade Fassade;
	private HWSimulation HW;
	
	public SimulationAblauf(Fassade fassade) {
		Fassade = fassade;
		HW = HWSimulation.getInstance();
	}
	
	public void simBeginn(FlaschenType FType) {
		
		int tmp=0;
		
		
		Fassade.simKonsolenText(0, "Simulation einer Flasche des Typs: " + FType.toString());
		
		/* 
		 * Flasche wird eingelegt und aktiviert die vordere LS
		 */
		HW.write(HW.EingLS, true);
		Fassade.simKonsolenText(0, "Sim: Eingangslichschranke aktiviert durch Flasche einlegen.");
		try { Thread.sleep(1000); }
		catch(Exception e){ System.out.println(e); }
		HW.write(HW.EingLS, false);
		
		/*
		 * Das System sollte nun das vordere Laufband aktivieren
		 * Die Justierungslichtschranke wird aktiviert sobald
		 * die Flasche an der richtigen Position ist
		 */
		// hier warten auf aktivierung des Laufbandes einfügen
		tmp = HW.readInt(HW.Scanner).intValue();
		if(tmp > 0){ 
			
			HW.write(HW.JustierLS, true);
			Fassade.simKonsolenText(0, "Sim: Justierlichtschranke aktiviert weil vorderes Laufband. "
										+ "die Flasche richtig positioniert hat");
			HW.write(HW.JustierLS, false);
		}
		else if(tmp == 0){ Fassade.simKonsolenText(0, "Fehler: vorderes Laufband" +
				" aktiviert nicht beim Flasche einziehen."); }

		
		switch(FType){
			case Mehrweg : 
			break;
			case PET :
			break;
			case CodeNichtValide :
			break;
			case CodeUnlesbar :
				
				/*
				 *  LaufbandDrehen dreht die Flasche zum Scannen.
				 *  Die Simulation liefert einen Fehlercode vom Scanner,
				 *  da kein Flaschencode gefunden werden konnte
				 */
				tmp=0;
				tmp=HW.readInt(HW.LBDrehen).intValue();
				if( tmp != 0){
					Fassade.simKonsolenText(0, "Laufband dreht Flasche. Scanner liefert einen Fehlercode.");
					HW.write(HW.Scanner, new Integer(-1));
				}
				else { Fassade.simKonsolenText(0, "Fehler: dreh Laufband aktiviert nicht"); }
				
				
				/*
				 * da die Flasche nicht erkannt wurde muss sie wieder ausgeworfen werden.
				 */
				tmp=0;
				tmp=HW.readInt(HW.LBEingang).intValue();
				if( tmp < 0){
					Fassade.simKonsolenText(0, "Laufband wirft Flasche wieder aus.");
				}
				else if(tmp == 0){ Fassade.simKonsolenText(0, "Fehler: vorderes Laufband" +
						" aktiviert nicht beim Flasche Auswerfen."); }
				
				
				
			break;
			default :
				Fassade.simKonsolenText(0, "Systemfehler: Flaschentyp nicht erkannt.");
			break;
		}
		
		
	}
	
}

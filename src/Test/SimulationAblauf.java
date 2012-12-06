package Test;

import Automat.FlaschenType;
import Automat.HWSimulation;
import Fassade.Fassade;

public class SimulationAblauf {
	
	private Fassade DieFassade;
	private HWSimulation HWaccess;
	
	public SimulationAblauf(Fassade fassade) {
		DieFassade = fassade;
		this.HWaccess = HWSimulation.getInstance();
	}
	
	public void simBeginn(FlaschenType FType) {
		DieFassade.simKonsolenText(0, "Flasche mit dem Code " + FType.toString()
				+ " wurde eingelegt.");
		//HWaccess.
		
		switch(FType){
			case Mehrweg : 
			break;
			case PET :
			break;
			case CodeNichtValide :
			break;
			case CodeUnlesbar :
			break;
			default :
				DieFassade.simKonsolenText(0, "Systemfehler: Flaschentyp nicht erkannt.");
			break;
		}
		
		
	}
}

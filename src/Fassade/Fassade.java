package Fassade;
import java.awt.EventQueue;

import Automat.FlaschenType;
import GUI.SimulationGUI;

/**
 * @author Dennis
 * @version 1.0
 * @created 02-Nov-2012 9:30:04 AM
 */
public class Fassade {

	private SimulationGUI Gui; 
	
	public Fassade(){
		Gui = new SimulationGUI(this);
		Gui.frame.setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fassade DieFassade =  new Fassade();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void finalize() throws Throwable {

	}

	/**
	 * aktualisiertden Text des Displays, blockierend
	 * 
	 * @param Text
	 */
	public void aktualisiereDisplay(String Text){

	}

	/**
	 * Wenn der Bon Button in der GUI gedrückt wurde
	 */
	public void bonButtonGedrueckt(){
		// Ausgabe auf Gui.Monitoring
		// Automat.Ablieferung etwas tuhen
	}

	/**
	 * druckt, blockierend
	 * 
	 * @param Text
	 */
	public void drucken(String Text){

	}

	public void leuchteAN(){

	}

	public void leuchteAUS(){

	}

	/**
	 * 
	 * @param level
	 * @param Text
	 */
	public void logText(int level, String Text){
		Gui.MonitoringUpdate(Text);
	}

	/**
	 * 
	 * @param Type
	 */
	public void simFlascheEingelegt(FlaschenType Type){
		logText(0, "Flasche mit dem Code " + Type.toString() + " wurde eingelegt.");
	}

	public void troeteAN(){

	}

	public void troeteAUS(){

	}

}
package Fassade;
import java.awt.EventQueue;

import Automat.FlaschenType;
import GUI.HardwareGUI;
import GUI.SimulationGUI;

/**
 * @author Dennis
 * @version 1.0
 * @created 02-Nov-2012 9:30:04 AM
 */
public class Fassade {

	private SimulationGUI SIMGui;
	private HardwareGUI HWGui;
	
	public Fassade(){
		SIMGui = new SimulationGUI(this);
		HWGui = new HardwareGUI(this);
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
		HWGui.updateDisplay(Text);
		SIMGui.MonitoringUpdate("Display aktualisiert mit \""+Text+"\"");
	}

	/**
	 * Wenn der Bon Button in der GUI gedrückt wurde
	 */
	public void bonButtonGedrueckt(){
		// Ausgabe auf Gui.Monitoring
		// Automat.Ablieferung etwas tuhen
		SIMGui.MonitoringUpdate("Bon Button wurde gedrückt");
	}

	/**
	 * Druckt den Bon in die Gui, blockierend
	 * 
	 * @param Text
	 */
	public void bonAnzeigen(String Text){
		HWGui.drucken(Text);
		SIMGui.MonitoringUpdate("Bon gedruckt: \""+Text+"\"");
	}

	public void leuchteAN(){
		
		SIMGui.MonitoringUpdate("Leuchte wurd eingeschaltet");
	}

	public void leuchteAUS(){
		SIMGui.MonitoringUpdate("Leuchte wurd AUSgeschaltet");
	}

	/**
	 * 
	 * @param level
	 * @param Text
	 */
	public void monitoringText(int level, String Text){
		SIMGui.MonitoringUpdate(Text);
	}

	/**
	 * 
	 * @param Type
	 */
	public void simFlascheEingelegt(FlaschenType Type){
		monitoringText(0, "Flasche mit dem Code " + Type.toString() + " wurde eingelegt.");
	}

	public void troeteAN(){
		SIMGui.MonitoringUpdate("Tröte wurd eingeschaltet");
	}

	public void troeteAUS(){
		SIMGui.MonitoringUpdate("Tröte wurd AUSgeschaltet");
	}

}
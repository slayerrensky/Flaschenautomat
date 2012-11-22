package Fassade;

import java.awt.EventQueue;
import java.util.LinkedList;

import Automat.*;
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
	private Ablieferung FachklasseAblieferung;

	public Fassade() {
		SIMGui = new SimulationGUI(this);
		HWGui = new HardwareGUI(this);
		String FlaschenfilePathasString = "./flaschenfile.txt";
		FachklasseAblieferung = new Ablieferung(this,
				loadFlaschenFile(FlaschenfilePathasString),loadFlaschenFile(FlaschenfilePathasString));
	}

	public void finalize() throws Throwable {

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fassade DieFassade = new Fassade();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * aktualisiertden Text des Displays, blockierend
	 * 
	 * @param Text
	 */
	public void displayAktualisieren(String Text) {
		HWGui.updateDisplay(Text);
		SIMGui.MonitoringUpdate("Display aktualisiert mit \"" + Text + "\"");
	}

	/**
	 * Wenn der Bon Button in der GUI gedrückt wurde
	 */
	public void bonAnfordern() {
		// Ausgabe auf Gui.Monitoring
		// Automat.Ablieferung etwas tuhen
		SIMGui.MonitoringUpdate("Bon Button wurde gedrückt");
	}

	/**
	 * Druckt den Bon in die Gui, blockierend
	 * 
	 * @param Text
	 */
	public void bonAnzeigen(String Text) {
		HWGui.drucken(Text);
		SIMGui.MonitoringUpdate("Bon gedruckt: \"" + Text + "\"");
	}
	
	private LinkedList<Flasche> loadFlaschenFile(String Path)
	{
		
		return null;
	}
	
	public void warnleuchteAN() {

		SIMGui.MonitoringUpdate("Leuchte wurd eingeschaltet");
	}

	public void warnleuchteAUS() {
		SIMGui.MonitoringUpdate("Leuchte wurd AUSgeschaltet");
	}

	/**
	 * 
	 * @param level
	 * @param Text
	 */
	public void simKonsolenText(int level, String Text) {
		SIMGui.MonitoringUpdate(Text);
	}

	/**
	 * 
	 * @param Type
	 */
	public void simFlascheEinlegen(FlaschenType Type) {
		simKonsolenText(0, "Flasche mit dem Code " + Type.toString()
				+ " wurde eingelegt.");
	}

	public void warnsignalAN() {
		SIMGui.MonitoringUpdate("Tröte wurd eingeschaltet");
	}

	public void warnsignalAUS() {
		SIMGui.MonitoringUpdate("Tröte wurd AUSgeschaltet");
	}

}
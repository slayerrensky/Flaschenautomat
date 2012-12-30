package Fassade;

import java.awt.Color;
import java.awt.EventQueue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import Automat.*;
import GUI.HardwareGUI;
import GUI.SimulationGUI;
import Test.SimulationFlasche;

/**
 * @author Dennis
 * @version 1.0
 * @created 02-Nov-2012 9:30:04 AM
 */
public class Fassade {

	private SimulationGUI SIMGui;
	private HardwareGUI HWGui;
	private Ablieferung FachklasseAblieferung;
	private HWSimulation HWaccess;
	private SimulationFlasche SimAblauf;

	public Fassade() {
		SIMGui = new SimulationGUI(this);
		HWGui = new HardwareGUI(this);
		this.HWaccess = HWSimulation.getInstance();
		this.HWaccess.setF(this);
		String FlaschenfilePathasString = "./flaschenfile.txt";
		FachklasseAblieferung = new Ablieferung(this,
				loadFlaschenFile(FlaschenfilePathasString),loadFlaschenFile(FlaschenfilePathasString));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fassade DieFassade = new Fassade();
					DieFassade.FachklasseAblieferung.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void aktuallisereHW(ArrayList<Comparable> list){
		SIMGui.updateUI(list);
		HWGui.updateUI(list);
		Color c;
		
		switch ((Integer)list.get(Adressen.Leuchte_Frabe.ordinal())) {
		default:
		case 0: c = Color.red;			
			break;
		case 1: c = Color.orange;
			break;
		case 2: c = Color.green; 
			break;
		}
		
		HWGui.setLeuchte((Boolean)list.get(Adressen.Leuchte.ordinal()), c);
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
	 * Wenn der Bon Button in der GUI gedr�ckt wurde
	 */
	public void bonAnfordern() {
		// Ausgabe auf Gui.Monitoring
		// Automat.Ablieferung etwas tuhen
		SIMGui.MonitoringUpdate("Bon Button wurde gedr�ckt.");
		FachklasseAblieferung.BonButtonPresst = true;
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
		LinkedList<Flasche> flaschen = new LinkedList<Flasche>();
		flaschen.add(new Flasche(FlaschenType.PET,new BigDecimal(0.25),"00000"));
		flaschen.add(new Flasche(FlaschenType.Mehrweg,new BigDecimal(0.08),"00001"));
		return flaschen;
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
		HWaccess.write(Adressen.Scanner.ordinal(), Type);
		SimAblauf = new SimulationFlasche(this,Type);
		//SimAblauf.start();
	}

	public void warnsignalAN() {
		SIMGui.MonitoringUpdate("Tröte wurde eingeschaltet");
	}

	public void warnsignalAUS() {
		SIMGui.MonitoringUpdate("Tröte wurde AUSgeschaltet");
	}
	
	public void highlightEA(Adressen thisChanged){
		SIMGui.highlightEA(thisChanged);		
	}
}

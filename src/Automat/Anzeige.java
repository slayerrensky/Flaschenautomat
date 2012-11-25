package Automat;

import Fassade.Fassade;

/**
 * @author Rensky
 * @version 1.0
 * @created 18-Nov-2012 16:41:59
 */
public class Anzeige {

	private Leuchte m_Leuchte;
	private Aktor m_Troete;
	private Display m_Display;

	public Anzeige(Fassade fassade) {
		m_Leuchte = new Leuchte(Adressen.Leuchte.ordinal(), Adressen.Leuchte_Frabe.ordinal());
		m_Troete = new Aktor(Adressen.Troete.ordinal());
		m_Display = new Display(Adressen.Display.ordinal());
	}

	/**
	 * 
	 * @param Text
	 */
	public void Display_akt(String text) {
		m_Display.setText(text);
	}

	/**
	 * 
	 * @param errno
	 */
	public void FehlerMelden(int errno) {
		int pulseEin, pulseAus, wdh;
		SimpleThread leuchte, troete;

		switch (errno) {
		default:
		case 0:
			wdh = 5;
			pulseEin = 1000;
			pulseAus = 1000;
			break;
		case 1:
			wdh = 10;
			pulseEin = 1500;
			pulseAus = 1000;
			break;
		case 2:
			wdh = 5;
			pulseEin = 500;
			pulseAus = 500;
			break;
		}
		
		leuchte = new SimpleThread(m_Leuchte, wdh, pulseEin, pulseAus);
		troete = new SimpleThread(m_Troete, wdh, pulseEin, pulseAus);

		leuchte.start();
		troete.start();
	}

	public class SimpleThread extends Thread {

		private Aktor aktor;
		private int wdh, pulse_ein, pulse_aus;

		public SimpleThread(Aktor aktor, int wdh, int pulse_ein, 
				int pulse_aus) {
			this.aktor = aktor;
			this.wdh = wdh;
			this.pulse_ein = pulse_ein;
			this.pulse_aus = pulse_aus;
		}

		public void run() {
			for (int i = 0; i <= wdh; i++) {

				aktor.einschalten();
				try {
					sleep(pulse_ein);
				} catch (InterruptedException ie) {
					System.out
							.println("Fehler [aktor-Thread]: Aktor konnte nicht eingeschaltet werden.");
				}
				aktor.ausschalten();
				try {
					sleep(pulse_aus);
				} catch (InterruptedException ie) {
					System.out
							.println("Fehler [aktor-Thread]: Aktor konnte nicht ausgeschaltet werden.");
				}
			}
		}
	}

}
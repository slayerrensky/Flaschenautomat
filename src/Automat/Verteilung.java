package Automat;

public class Verteilung {
	
//Variablen

		private Auswahlklappe m_Auswahlklappe;
		
		private Laufband m_HinteresLaufband;
		
		private Sensor s_AuswahlklappeEingangsLichtschranke;
		private Sensor s_MehrwegBehaelterLichtschranke;
		private Sensor s_PetBehaelterLichtschranke;
		
		private ParallelWarteClass workerThread;
	
//Konstruktor
		
		public Verteilung(){

			m_Auswahlklappe = new Auswahlklappe(Adressen.Auswahlklappe.ordinal());
			m_HinteresLaufband = new Laufband(Adressen.LaufbandAusgang.ordinal());
			s_AuswahlklappeEingangsLichtschranke = new Sensor(Adressen.AuswahlklappeEingangslichtschranke.ordinal());
			s_MehrwegBehaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankeMehrweg.ordinal());
			s_PetBehaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankePET.ordinal());
			
			workerThread = new ParallelWarteClass(10000);
		}

//Methoden
		
		protected boolean Durchlauf_B(Sensor s){
			
			workerThread.run();
		
			while(!s.read() && workerThread.isAlive());

			if (!workerThread.isAlive()){
					
				return false;
			}
		
			if(workerThread.isAlive()){
				workerThread.interrupt();			
			}

			workerThread.run();

			while(s.read() && workerThread.isAlive());
				
			if (!workerThread.isAlive()){
					
				return false;
			}
			
			
			
			return true;
		}
		
		protected boolean Durchlauf_A (FlaschenType Flasche){
			
			m_HinteresLaufband.vorwaerts();
			m_Auswahlklappe.stellen(Flasche);
			Durchlauf_B(s_AuswahlklappeEingangsLichtschranke);
			
			return true;
		}
		
		public boolean Flasche_weiterleiten(FlaschenType Flasche){
			
			switch (Flasche) {
			
				case PET:
						
						Durchlauf_A(Flasche);
						Durchlauf_B(s_PetBehaelterLichtschranke);
						m_HinteresLaufband.stop();
						return true;
				default:		
					
				case Mehrweg:
						
						Durchlauf_A(Flasche);
						Durchlauf_B(s_MehrwegBehaelterLichtschranke);
						m_HinteresLaufband.stop();
						return true;
		
			}
		}
}

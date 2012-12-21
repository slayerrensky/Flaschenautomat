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
		
		public boolean Flasche_weiterleiten (FlaschenType Flasche){
			
			if( ! Durchlauf_A(Flasche)){
				return false;
			}
			
			switch (Flasche) {
			
				case PET:
							
					return Durchlauf_B(s_PetBehaelterLichtschranke);		
					
				case Mehrweg:
						
					return Durchlauf_B(s_MehrwegBehaelterLichtschranke);
				
				default:
					
					return false;
			}
		}
		
		protected boolean Durchlauf_A (FlaschenType Flasche){
			
			m_HinteresLaufband.vorwaerts();
			m_Auswahlklappe.stellen(Flasche);
	
			return Durchlauf_B(s_AuswahlklappeEingangsLichtschranke);
		}
		
		protected boolean Durchlauf_B (Sensor s){
			
			workerThread =  new ParallelWarteClass(10000);
			workerThread.start();
			while(!s.read() && workerThread.isAlive())
			{
				workerThread.wait(1000);
			}
			
			if (!workerThread.isAlive()){		
				return false;
			}
		
			if(workerThread.isAlive()){
				workerThread.interrupt();			
			}

			workerThread =  new ParallelWarteClass(10000);
			workerThread.start();
			while(s.read() && workerThread.isAlive())
			{
				workerThread.wait(1000);
			}
				
			if (!workerThread.isAlive()){
				return false;
			}

			if((s.equals(s_PetBehaelterLichtschranke)) || 
					(s.equals(s_MehrwegBehaelterLichtschranke))){
				m_HinteresLaufband.stop();
			}
				
			return true;
		}
}

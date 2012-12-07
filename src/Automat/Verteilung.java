package Automat;

public class Verteilung {
	
//Variablen

		private Auswahlklappe m_Auswahlklappe;
		
		private Laufband m_HinteresLaufband;
		
		private Sensor s_AuswahlklappeEingangsLichtschranke;
		private Sensor s_MehrwegBehaelterLichtschranke;
		private Sensor s_PetBehaelterLichtschranke;
	
//Konstruktor
		
		public Verteilung(){

			m_Auswahlklappe = new Auswahlklappe(Adressen.Auswahlklappe.ordinal());
			m_HinteresLaufband = new Laufband(Adressen.LaufbandAusgang.ordinal());
			s_AuswahlklappeEingangsLichtschranke = new Sensor(Adressen.AuswahlklappeEingangslichtschranke.ordinal());
			s_MehrwegBehaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankeMehrweg.ordinal());
			s_PetBehaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankePET.ordinal());
		}

//Methoden
		protected void wait (int Sek){
			
			//Thread.currentThread().sleep(1000);
		}
		
		public boolean Durchlauf(Sensor s){
			
			int i = 0;
			int grenze = 10;
			
			while(s.read() != true && i < grenze){
				
				i++;
				wait(1000);
				
			}
			
			if (i >= grenze){
				
				return false;
			}
			
			i = 0;
			
			while(s.read() !=false && i < grenze){
				
				i++;
				wait(1000);
			}	
			
			if (i >= grenze){
				
				return false;
			}
			
			return true;
		}
		
		public boolean Flasche_weiterleiten(FlaschenType Flasche){
			
			switch (Flasche) {
			
				case PET:
					
						m_HinteresLaufband.vorwaerts();
						m_Auswahlklappe.stellen(Flasche);
						
						getUebergabeLichtschrankePET();
						
						m_HinteresLaufband.stopp();
						
						return true;
				
				case Mehrweg:
					
						m_HinteresLaufband.vorwaerts();
						m_Auswahlklappe.stellen(Flasche);	
						
						getUebergabeLichtschrankeMehrweg();
						
						m_HinteresLaufband.stopp();
						
						return true;
	
				default:
						
						m_HinteresLaufband.rueckwerts();
						
						return false;
				
			}
		}
			

		public boolean getUebergabeLichtschrankeMehrweg(){
		
			return Durchlauf(s_MehrwegBehaelterLichtschranke);
			
		}
		
		public boolean getUebergabeLichtschrankePET(){
		
			return Durchlauf(s_PetBehaelterLichtschranke);
		}
		
}

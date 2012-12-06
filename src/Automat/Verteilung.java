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
		
		public void Flasche_weiterleiten(FlaschenType Flasche){
			
			
//			switch (Flasche) {
//			
//				case Flasche == FlaschenType.PET:
//					
//						m_HinteresLaufband.vorwaerts();
//						m_Auswahlklappe.stellen(Flasche.PET);
//						
//						while(s_PetBehaelterLichtschranke.read() != true){
//							//wait(1000);
//						}
//						while(s_PetBehaelterLichtschranke.read() !=false){
//							//Thread.currentThread().sleep(1000);
//						}
//						
//						m_HinteresLaufband.stopp();
//						
//						break;
//				
//				case Flasche == FlaschenType.Mehrweg:
//					
//						m_HinteresLaufband.vorwaerts();
//						m_Auswahlklappe.stellen(Flasche.Mehrweg);	
//						
//						while(s_MehrwegBehaelterLichtschranke.read() != true){
//							//Thread.currentThread().sleep(1000);
//						}
//						while(s_MehrwegBehaelterLichtschranke.read() !=false){
//							//Thread.currentThread().sleep(1000);
//						}	
//						
//						m_HinteresLaufband.stopp();
//						
//						break;
//	
//				default:
//						
//						m_HinteresLaufband.rueckwerts();
//						
//				break;
//			
//			}
			
				if(Flasche == FlaschenType.PET){
				
					m_HinteresLaufband.vorwaerts();
					m_Auswahlklappe.stellen(Flasche.PET);
					
					while(s_PetBehaelterLichtschranke.read() != true){
						//wait(1000);
					}
					while(s_PetBehaelterLichtschranke.read() !=false){
						//Thread.currentThread().sleep(1000);
					}
					
					m_HinteresLaufband.stopp();
				}
					
				if(Flasche == FlaschenType.Mehrweg){
						
					m_HinteresLaufband.vorwaerts();
					m_Auswahlklappe.stellen(Flasche.Mehrweg);
					
					while(s_MehrwegBehaelterLichtschranke.read() != true){
						//Thread.currentThread().sleep(1000);
					}
					while(s_MehrwegBehaelterLichtschranke.read() !=false){
						//Thread.currentThread().sleep(1000);
					}
					
					m_HinteresLaufband.stopp();
				}
		}
		
		public boolean getUebergabeLichtschrankeMehrweg(){
		
			return false;
		}
		
		public boolean getUebergabeLichtschrankePET(){
		
			return false;
		}
		
}

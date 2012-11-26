package Automat;

public class Sector_two {
	
//Variablen

		private Auswahlklappe m_Auswahlklappe;
		
		private Laufband m_HinteresLaufband;
		
		private Sensor s_AuswahlklappeEingangLichtschranke;
		private Sensor s_MehrwegBeaelterLichtschranke;
		private Sensor s_PetBeaelterLichtschranke;
	
//Konstruktor
		
		public Sector_two(){

			m_Auswahlklappe = new Auswahlklappe(Adressen.Auswahlklappe.ordinal());
			m_HinteresLaufband = new Laufband(Adressen.LaufbandAusgang.ordinal());
			s_AuswahlklappeEingangLichtschranke = new Sensor(Adressen.AuswahlklappeEingangslichtschranke.ordinal());
			s_MehrwegBeaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankeMehrweg.ordinal());
			s_PetBeaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankePET.ordinal());
		}

//Methoden
		
		public void Flasche_weiterleiten(){
			
		}
		
		public boolean getUebergabeichtschrankeMehrweg(){
		
			return false;
		}
		
		public boolean getUebergabeichtschrankePET(){
		
			return false;
		}
		
}

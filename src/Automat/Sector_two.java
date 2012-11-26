package Automat;

public class Sector_two {
	
//Variablen

		private Auswahlklappe m_Auswahlklappe;
		
		private Laufband m_HinteresLaufband;
		
		private Sensor s_AuswahlklappeEingangsLichtschranke;
		private Sensor s_MehrwegBehaelterLichtschranke;
		private Sensor s_PetBehaelterLichtschranke;
	
//Konstruktor
		
		public Sector_two(){

			m_Auswahlklappe = new Auswahlklappe(Adressen.Auswahlklappe.ordinal());
			m_HinteresLaufband = new Laufband(Adressen.LaufbandAusgang.ordinal());
			s_AuswahlklappeEingangsLichtschranke = new Sensor(Adressen.AuswahlklappeEingangslichtschranke.ordinal());
			s_MehrwegBehaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankeMehrweg.ordinal());
			s_PetBehaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankePET.ordinal());
		}

//Methoden
		
		public void Flasche_weiterleiten(){
			
		}
		
		public boolean getUebergabeLichtschrankeMehrweg(){
		
			return false;
		}
		
		public boolean getUebergabeLichtschrankePET(){
		
			return false;
		}
		
}

package Temp_Aufteilung;

//import Automat.Adressen;
//import Automat.Auswahlklappe;
//import Automat.Laufband;
//import Automat.Lichtschranke;

public class Sector_two {

		private Auswahlklappe m_Auswahlklappe;
		
		private Laufband m_HinteresLaufband;
		
		private Lichtschranke s_AuswahlklappeEingangLichtschranke;
		private Lichtschranke s_MehrwegBeaelterLichtschranke;
		private Lichtschranke s_PetBeaelterLichtschranke;
		
		public Sector_two(){

			m_Auswahlklappe = new Auswahlklappe(Adressen.Auswahlklappe.ordinal());
			m_HinteresLaufband = new Laufband(Adressen.LaufbandAusgang.ordinal());
			s_AuswahlklappeEingangLichtschranke = new Lichtschranke(Adressen.AuswahlklappeEingangslichtschranke.ordinal());
			s_MehrwegBeaelterLichtschranke = new Lichtschranke(Adressen.UebergabelichtschrankeMehrweg.ordinal());
			s_PetBeaelterLichtschranke = new Lichtschranke(Adressen.UebergabelichtschrankePET.ordinal());
		}
		
		public void Flasche_weiterleiten(){
			
		}
		
		public boolean getUebergabeichtschrankeMehrweg(){
		
			return false;
		}
		
		public boolean getUebergabeichtschrankePET(){
		
			return false;
		}
		
}

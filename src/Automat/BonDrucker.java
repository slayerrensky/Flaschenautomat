package Automat;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:42:49
 */
public class BonDrucker extends Aktor{
	
	public BonDrucker(int adresse){
		super(adresse);
	}

	/**
	 * 
	 * @param Liste
	 */
	public void BonDrucken(LinkedList<Flasche> ListofBottles){
		//string erstellen und an HWaccess.write(adresse,text); übergeben
		String str = "";
		BigDecimal gesamt = new BigDecimal(0);
		Date dnow= new Date();
		
		str += "Gruppe 3 GmbH" + "\n";
		str += "Straße 212" + "\n";
		str += "16321 Bernau" + "\n";
		str += "\n";
		str += "Datum: " + new StringBuilder(new SimpleDateFormat("dd.MM.yyyy").format( dnow ) ) + "\n";
		
		for(Flasche f : ListofBottles)
		{
			BigDecimal zwischensumme = new BigDecimal(0);
			zwischensumme = zwischensumme.add(f.getPfandwert().multiply(new BigDecimal(f.getAnzahl())));
						
			str += f.getType().toString() + " " + f.getAnzahl() + " x " + 
					NumberFormat.getCurrencyInstance().format(f.getPfandwert()) + "\n      = " + 
					NumberFormat.getCurrencyInstance().format(zwischensumme) + "\n";
			
			gesamt = gesamt.add(zwischensumme);
		}
		
		str += "Gesamt:     " + NumberFormat.getCurrencyInstance().format(gesamt) + "\n";
		
		HWaccess.write(adresse, str);
		
	}

}
package Automat;

import java.math.BigDecimal;
import java.util.LinkedList;

public class FlaschenZaehler extends Observer{

	protected Scanner subject;
	protected LinkedList<Flasche> abgeleiferteFlaschen;
	protected FlaschenType lastFlaschenType;
	protected int GesamtAnzahlFlaschen = 0; 

	
	public FlaschenZaehler(Scanner subject, LinkedList<Flasche> flaschen)
	{
		this.subject = subject;
		this.abgeleiferteFlaschen = flaschen;
	}
	
	@Override	
	public void update() {
		FlaschenType fType = subject.getSubjectState();
		
		if (abgeleiferteFlaschen != null)
		{	
			for (Flasche f : abgeleiferteFlaschen)
			{
				if (f.getType().compareTo(fType)==0)
				{
					f.AnzahlInc();
					GesamtAnzahlFlaschen++;
					lastFlaschenType = f.getType();
				}
			}
		}
	}
	
	public BigDecimal getGesamtGuthaben()
	{
		BigDecimal guthaben = new BigDecimal(0);
		for (Flasche f : abgeleiferteFlaschen)
		{
			guthaben = guthaben.add(f.getPfandwert().multiply(new BigDecimal(f.getAnzahl())));
		}
		return guthaben;
	}
	
	public LinkedList<Flasche> getFlaschenListe(){
		return abgeleiferteFlaschen;
	}
	
	public void reset()
	{
		for (Flasche f : abgeleiferteFlaschen)
		{
			f.reset();
		}
	}
	
	public FlaschenType getLastFlaschenType()
	{
		return lastFlaschenType;
	}
	
	public int getGesamtAnzahlFlaschen(){
		return GesamtAnzahlFlaschen;
	}
}

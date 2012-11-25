package Automat;

import java.math.BigDecimal;
import java.util.LinkedList;

public class FlaschenZaehler extends Observer{

	protected Scanner subject;
	protected LinkedList<Flasche> abgeleiferteFlaschen;

	
	public FlaschenZaehler(Scanner subject, LinkedList<Flasche> flaschen)
	{
		this.subject = subject;
		this.abgeleiferteFlaschen = flaschen;
	}
	
	@Override	
	public void update() {
		String flaschencode = subject.getSubjectState();
		
		for (Flasche f : abgeleiferteFlaschen)
		{
			if (f.getCode().compareTo(flaschencode)==0)
			{
				f.AnzahlInc();
			}
		}
	}
	
	public BigDecimal getGesamtGuthaben()
	{
		BigDecimal guthaben = new BigDecimal(0);
		for (Flasche f : abgeleiferteFlaschen)
		{
			guthaben.add(f.getPfandwert().multiply(new BigDecimal(f.getAnzahl())));
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
}

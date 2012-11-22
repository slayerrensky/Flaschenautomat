package Automat;

import java.util.LinkedList;

public abstract class ObserverSubjekt {
	
	protected LinkedList<Observer> observers = new LinkedList<Observer>();
	
	public void attach(Observer o)
	{
		if ( ! this.observers.contains(o));
		this.observers.add(o);
	}
	
	public void detach(Observer o)
	{
		this.observers.remove(o);
	}
	
	public void notifyObservers()
	{	
		for( Observer o : observers)
		{
			o.update();
		}
	}
	
}


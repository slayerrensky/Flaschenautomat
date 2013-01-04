package Automat;

import java.util.Random;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:57
 */
public class Scanner extends Subjekt{

	private int adresse;
	public FlaschenType lastCode ;
	private HWSimulation HWaccess;
	private ParallelWarteClass workerThread;
	
	public Scanner(int adresse, int timeoutMS){
		this.adresse = adresse;
		this.HWaccess = HWSimulation.getInstance();
		workerThread = new ParallelWarteClass(1000);
	}

	public FlaschenType getSubjectState(){
		return lastCode;
	}

	public boolean Scan(){
		ParallelWarteClass randomWaitThread = new ParallelWarteClass(new Random().nextInt(5000)+1000);
		
		workerThread.interrupt();
		workerThread = new ParallelWarteClass(10000);
		workerThread.start();
		randomWaitThread.start();
		
		//warten auf beendinugn der random wartezeit
		while(randomWaitThread.isAlive());
		
		while(( null == (lastCode = this.HWaccess.readFlaschenType(adresse))) && workerThread.isAlive());
		{
			
		}
		
		if (lastCode != FlaschenType.CodeUnlesbar)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public void stopScann(){
	
	}
	
}
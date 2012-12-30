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
	private int timeoutMS; 
	private int fehlversuche;
	
	public Scanner(int adresse, int timeoutMS){
		this.adresse = adresse;
		this.HWaccess = HWSimulation.getInstance();
		workerThread = new ParallelWarteClass(1000);
		this.timeoutMS = timeoutMS;
		fehlversuche = 3;
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
		
		//int[] passRef = new int[1];
		//HWaccess.read(this.adresse, passRef);
		//return passRef[1];
		/*int fehlversuche = new Random().nextInt(5)+1;
		while(fehlversuche>0){
			int wartezeit = new Random().nextInt(3000)+1000;
			workerThread.setTimeout(wartezeit);
			workerThread.start();
			while(workerThread.isAlive());
			if(workerThread.isAlive()){
				workerThread.interrupt();
			}// abbrechen falls er noch läuft
			fehlversuche--;
		}*/// das hier habe ich eingebaut um die eingelegte flasche besser zu simulieren, damit er die flasche auch mal dreht
		
		if(fehlversuche>0){
			fehlversuche--;
			return false;
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
	
	public void resetMissCounter(){
		fehlversuche = 3;
	}
}
package Automat;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:57
 */
public class Scanner extends Subjekt{

	private int adresse;
	private String lastCode = "00000";
	private HWSimulation HWaccess;
	private ParallelWarteClass workerThread;
	private int timeoutMS; 
	
	public Scanner(int adresse, int timeoutMS){
		this.adresse = adresse;
		this.HWaccess = HWSimulation.getInstance();
		workerThread = new ParallelWarteClass(1000);
		this.timeoutMS = timeoutMS;
	}

	public String getSubjectState(){
		return lastCode;
	}

	public boolean Scan(){
		
		int time = 0;
		while(( null == (lastCode = this.HWaccess.readStr(adresse))) && time <= timeoutMS);
		{
			workerThread.run();
			workerThread.isAlive();
			time += 1000;
			
		}
		
		//int[] passRef = new int[1];
		//HWaccess.read(this.adresse, passRef);
		//return passRef[1];
		
		
		
		if (lastCode != "")
		{
			this.notifyObservers();
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
package Automat;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:57
 */
public class Scanner extends Subjekt{

	private int adresse;
	private String lastCode;
	private HWSimulation HWaccess;
	private ParallelWarteClass workerThread;
	
	public Scanner(int adresse, int timeoutMS){
		this.adresse = adresse;
		this.HWaccess = HWSimulation.getInstance();
		workerThread = new ParallelWarteClass(timeoutMS);
	}

	public String getSubjectState(){
		return lastCode;
	}

	public boolean Scan(){
		workerThread.run();
		while(workerThread.isAlive());
		
		//int[] passRef = new int[1];
		//HWaccess.read(this.adresse, passRef);
		//return passRef[1];
		
		lastCode = this.HWaccess.readStr(adresse);
		
		if (lastCode != "")
		{
			this.notifyAll();
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
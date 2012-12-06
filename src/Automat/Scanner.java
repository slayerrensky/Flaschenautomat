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
	private SimpleThread workerThread;
	
	public Scanner(int adresse, int timeoutMS){
		this.adresse = adresse;
		this.HWaccess = HWSimulation.getInstance();
		workerThread = new SimpleThread(timeoutMS);
	}

	public String getSubjectState(){
		return lastCode;
	}

	public String Scan(){
		workerThread.run();
		while(workerThread.isAlive());
		
		//int[] passRef = new int[1];
		//HWaccess.read(this.adresse, passRef);
		//return passRef[1];
		
		lastCode = this.HWaccess.readStr(adresse);
		this.notifyAll();
		
		return lastCode;
	}

	public void stopScann(){
	
	}
	
	public class SimpleThread extends Thread {
		private int timeoutMS;
		
		public SimpleThread(int timeoutMS) {
			this.timeoutMS = timeoutMS;
		}
		
		public void run() {
			try {
				this.sleep(timeoutMS);
			} catch (InterruptedException e) {
				// gwollt ohne anweisung
			}			
		}
	}

}
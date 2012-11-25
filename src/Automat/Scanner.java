package Automat;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:57
 */
public class Scanner extends ObserverSubjekt{

	private int adresse;
	private int lastCode;
	private HWLayer HWaccess;
	private SimpleThread workerThread;
	
	public Scanner(int adresse, int timeoutMS){
		this.adresse = adresse;
		this.HWaccess = HWLayer.getInstance();
		workerThread = new SimpleThread(timeoutMS);
	}

	public int getLastScannCode(){
		return lastCode;
	}

	public int Scan(){
		workerThread.run();
		while(workerThread.isAlive());
		
		//int[] passRef = new int[1];
		//HWaccess.read(this.adresse, passRef);
		this.notifyAll();
		//return passRef[1];
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
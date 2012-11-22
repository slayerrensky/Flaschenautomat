package Automat;

import java.awt.event.ActionEvent;

/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:57
 */
public class Scanner {

	private int lastCode;
	private int adresse;
	private SimpleThread workerThread;
	
	public Scanner(int adresse){
		this.adresse = adresse;
		workerThread = new SimpleThread(adresse, lastCode);
	}

	public int getLastScannCode(){
		return lastCode;
	}

	public void startScann(int code){
		if( ! workerThread.isAlive()){
			workerThread.start();
		}
	}

	public void stopScann(){
	
	}
	
	public class SimpleThread extends Thread {
		
		private int adresse;
		private int lastCode;
		private boolean stop;
		
		public SimpleThread(int adresse, int lastCode) {
			this.adresse = adresse;
			this.adresse = lastCode;
			stop = false;
		}
		
		public void halt() {
	        stop = true;
	    }
		
		public void run() {
			// Hardware zugriff
			while(!stop){
				//report scanning
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					//report error while sleep
				}
			}
			
		}
	}

}
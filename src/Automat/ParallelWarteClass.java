package Automat;

public class ParallelWarteClass extends Thread{
		private int timeoutMS;
		
		public ParallelWarteClass(int timeoutMS) {
			this.timeoutMS = timeoutMS;
		}
		
		public void run() {
			try {
				Thread.sleep(timeoutMS);
			} catch (InterruptedException e) {
				// gwollt ohne anweisung
			}			
		}
}

package Automat;

public class ParallelWarteClass extends Thread {
	private int timeoutMS;

	public ParallelWarteClass(int timeoutMS) {
		this.timeoutMS = timeoutMS;
	}

	public void run() {
		try {
			ParallelWarteClass.sleep(timeoutMS);
		} catch (InterruptedException e) {
			interrupt();
		}
	}
	
	public void setTimeout(int timeoutMS){
		if(! this.isAlive())
		{
			this.timeoutMS = timeoutMS;
		}
	}
}

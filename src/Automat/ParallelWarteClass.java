package Automat;

public class ParallelWarteClass extends Thread {
	private int timeoutMS;

	public ParallelWarteClass(int timeoutMS) {
		this.timeoutMS = timeoutMS;
	}

	public void run() {
		try {
			Thread.sleep(timeoutMS);
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
	
	public void wait(int timeout)
	{
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			interrupt();
		}
	}
}

package GUI;

import java.awt.Color;

import javax.swing.JComponent;

public class highlighter extends Thread {
	private int timeoutMS;
	private JComponent tmp;

	public highlighter(int timeoutMS, JComponent tmp) {
		this.timeoutMS = timeoutMS;
		this.tmp = tmp;
	}

	public void run() {
		try {
			tmp.setBackground(Color.red);
			highlighter.sleep(timeoutMS);
			tmp.setBackground(Color.gray);
		} catch (InterruptedException e) {
			interrupt();
		}
	}
}

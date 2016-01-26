package animation;

import java.util.TimerTask;

import javax.swing.JFrame;

import gui.MainWindow;

public class Animation extends TimerTask {
	
	private MainWindow mw;
	private int p;
	
	public Animation(MainWindow mw) {
		this.mw = mw;
		this.p = -1;
	}
	
	@Override
	public void run() {
		mw.animate(p);
		p++;
	}

}

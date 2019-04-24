package frameOperate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class preTime implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SimpleDateFormat dtFmt = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’ HH:mm:ss");
		while (true) {
			mainFrame.refreshTime(dtFmt.format(new Date()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

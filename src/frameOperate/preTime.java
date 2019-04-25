package frameOperate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class preTime implements Runnable {
	@Override
	public void run() {
		SimpleDateFormat dtFmt = new SimpleDateFormat("yyyyÄêMMÔÂddÈÕ HH:mm:ss");
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

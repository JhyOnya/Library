package frameOperate;

import java.text.SimpleDateFormat;
import java.util.Date;

import timeOperate.getTime;

public class preTime implements Runnable {
	@Override
	public void run() {
		SimpleDateFormat dtFmt = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		int[] weekAndDay = getTime.time();
		String day = "";
		switch (weekAndDay[1]) {
		case 1:
			day = "一";
			break;
		case 2:
			day = "二";
			break;
		case 3:
			day = "三";
			break;
		case 4:
			day = "四";
			break;
		case 5:
			day = "五";
			break;
		case 6:
			day = "六";
			break;

		default:
			day = "日";
			break;
		}
		String weekDay0 = "今天是：第" + weekAndDay[0] + "周 周" + day + " " ;
		while (true) {
			String date = dtFmt.format(new Date());
			String weekDay =weekDay0+date.substring(date.lastIndexOf(" "));
			mainFrame.refreshTime(date, weekDay);
			msgChange.refresh();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

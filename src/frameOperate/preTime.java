package frameOperate;

import java.text.SimpleDateFormat;
import java.util.Date;

import timeOperate.getTime;

public class preTime implements Runnable {
	@Override
	public void run() {
		SimpleDateFormat dtFmt = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		int[] weekAndDay = getTime.time();
		String day = "";
		switch (weekAndDay[1]) {
		case 1:
			day = "һ";
			break;
		case 2:
			day = "��";
			break;
		case 3:
			day = "��";
			break;
		case 4:
			day = "��";
			break;
		case 5:
			day = "��";
			break;
		case 6:
			day = "��";
			break;

		default:
			day = "��";
			break;
		}
		String weekDay0 = "�����ǣ���" + weekAndDay[0] + "�� ��" + day + " " ;
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

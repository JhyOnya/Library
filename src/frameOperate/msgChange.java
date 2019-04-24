package frameOperate;

import java.awt.Image;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Message.Seat;

public class msgChange {

	static String imgDir = "E:\\Code\\Java\\Library\\src\\Message\\picUI\\";

	public static void init() {
		Seat.init();
		mainFrame.rooms = new String[Seat.roomNum];
		for (int i = 0; i < Seat.roomNum; i++) {
			mainFrame.rooms[i] = (i + 1) + "";
		}
	}

	public static void login() {
		mainFrame.loginBt.setBounds(410, 37, 86, 23);
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonLog.png");
		iconStart.setImage(iconStart.getImage().getScaledInstance(mainFrame.loginBt.getBounds().width,
				mainFrame.loginBt.getBounds().height, Image.SCALE_SMOOTH));
		mainFrame.loginBt.setIcon(iconStart);
		mainFrame.loginBt.setContentAreaFilled(false);
		mainFrame.loginBt.setEnabled(false);
		mainFrame.nameText.setEnabled(false);
		mainFrame.textPlease.setVisible(true);
		mainFrame.chooseClass.setEnabled(true);
		for (int i = 0; i < Seat.seatNum; i++) {
			mainFrame.seatBts[i].setEnabled(true);
		}
		refreshRoom(0);
	}

	public static void logout() {
		mainFrame.chooseClass.setSelectedIndex(0);
		mainFrame.loginBt.setBounds(410, 37, 86, 23);
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonLogin.png");
		iconStart.setImage(iconStart.getImage().getScaledInstance(mainFrame.loginBt.getBounds().width,
				mainFrame.loginBt.getBounds().height, Image.SCALE_SMOOTH));
		mainFrame.loginBt.setIcon(iconStart);
		mainFrame.loginBt.setContentAreaFilled(true);
		mainFrame.loginBt.setEnabled(true);
		mainFrame.nameText.setEnabled(true);
		mainFrame.textPlease.setVisible(false);
		mainFrame.chooseClass.setEnabled(false);
		for (int i = 0; i < Seat.seatNum; i++) {
			mainFrame.seatBts[i].setEnabled(false);
		}
	}

	public static void chooseSeat(int room, int seat, String name) {
		int r = room + 1;
		int s = seat + 1;
		getOrder(name + "，您的座位是：\n   教室" + r + ",座位" + s + "！");
		Seat.selectSeat(room, seat, name);
		msgChange.logout();
	}

	public static void getOrder(String qus) {
		JOptionPane.showMessageDialog(null, qus, "选座成功", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void refreshRoom(int room) {
		for (int i = 0; i < Seat.seatNum; i++) {
			mainFrame.seatBts[i].setEnabled(true);
		}
		Map<Integer, String> preSeats = Seat.getroomSeat(room);
		if (preSeats != null) {
			Iterator<Map.Entry<Integer, String>> entries = preSeats.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<Integer, String> entry = entries.next();
				mainFrame.seatBts[entry.getKey()].setEnabled(false);
			}
		}
	}

}

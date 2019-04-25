package frameOperate;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Message.Seat;

public class msgChange {
	static Boolean preOrChs = true;
	static Boolean isLog = false;
	static String imgDir = "E:\\Code\\Java\\Library\\src\\Message\\picUI\\";

	public static void init(String time) {
		Seat.init(time);
		mainFrame.rooms = Seat.rooms;
	}

	public static void login() {
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonLog.png");
		iconStart.setImage(iconStart.getImage().getScaledInstance(mainFrame.loginBt.getBounds().width,
				mainFrame.loginBt.getBounds().height, Image.SCALE_SMOOTH));
		mainFrame.loginBt.setIcon(iconStart);
		mainFrame.loginBt.setContentAreaFilled(false);
		mainFrame.loginBt.setEnabled(false);
		mainFrame.nameText.setEnabled(false);
		isLog=true;
		refresh();
	}

	public static void logout() {
		mainFrame.chooseRoom.setSelectedIndex(0);
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonLogin.png");
		iconStart.setImage(iconStart.getImage().getScaledInstance(mainFrame.loginBt.getBounds().width,
				mainFrame.loginBt.getBounds().height, Image.SCALE_SMOOTH));
		mainFrame.loginBt.setIcon(iconStart);
		mainFrame.loginBt.setContentAreaFilled(true);
		mainFrame.loginBt.setEnabled(true);
		mainFrame.nameText.setEnabled(true);
		mainFrame.nameText.setText("");
		isLog=false;
		refresh();
	}

	public static void chooseSeat(int room, int seat, String name) {
		if (name.isEmpty()) {
			getErrOrder("���¼����ѡ����");
			return;
		}
		int r = room + 1;
		int s = seat + 1;
		((JButton) mainFrame.panel.getComponent(seat)).setEnabled(false);
		((JButton) mainFrame.panel.getComponent(seat)).setText("ʹ���У�" + name);
		getOrder(name + "��������λ�ǣ�\n   ����" + r + ",��λ" + s + "��\n   ���Զ��˳�");

		Seat.selectSeat(room, seat, name);
		msgChange.logout();
	}

	public static void getOrder(String qus) {
		JOptionPane.showMessageDialog(null, qus, "ѡ���ɹ�", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void getErrOrder(String qus) {
		JOptionPane.showMessageDialog(null, qus, "����ʧ�ܣ�", JOptionPane.DEFAULT_OPTION);
	}

	public static void refresh() {
		int room = mainFrame.chooseRoom.getSelectedIndex();
		// ���õ���λ
		for (int i = 0; i < Seat.seatNum; i++) {
			((JButton) mainFrame.panel.getComponent(i)).setEnabled(preOrChs&&isLog);
			((JButton) mainFrame.panel.getComponent(i)).setText(i + 1 + "");
		}

		// ʹ������λ
		Map<Integer, String> preSeats = Seat.getroomSeat(room);
		if (preSeats != null) {
			Iterator<Map.Entry<Integer, String>> entries = preSeats.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<Integer, String> entry = entries.next();
				((JButton) mainFrame.panel.getComponent(entry.getKey())).setEnabled(false);
				((JButton) mainFrame.panel.getComponent(entry.getKey())).setText("ʹ���У�" + entry.getValue());
			}
		}

		// vip��λʱ�����
		Map<String, Integer> vipSeats = Seat.getVIPSeats(room);
		if (vipSeats != null) {
			Iterator<Map.Entry<String, Integer>> entries = vipSeats.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, Integer> entry = entries.next();
//				((JButton) mainFrame.panel.getComponent(entry.getValue())).setEnabled(false);
				String time = preOrChs ? mainFrame.preTime.getText() : mainFrame.chsTime.getText();
				String freshTime = Seat.getSeatTime(entry.getKey(), time);
				((JButton) mainFrame.panel.getComponent(entry.getValue())).setText("ʣ��ʱ�䣺" + freshTime);
			}
		}
	}

	// ͨ���������Ѱ�Ұ�ť
	public static int searchComponentByName(Container c, String name) {
		int chsBtn = 0;
		Component[] components = c.getComponents();
		for (; chsBtn < components.length; chsBtn++) {
			JButton preBtn = (JButton) components[chsBtn];
			if (preBtn.getText().equals(name)) {
				return chsBtn;
			}
		}
		return chsBtn;
	}
}

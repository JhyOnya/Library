package frameOperate;

import java.awt.Image;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Message.Seat;

public class msgChange
{

	static String imgDir = "E:\\Code\\Java\\Library\\src\\Message\\picUI\\";

	public static void init()
	{
		Seat.init();
		mainFrame.rooms = new String[Seat.roomNum];
		for (int i = 0; i < Seat.roomNum; i++)
		{
			mainFrame.rooms[i] = (i + 1) + "";
		}
	}

	public static void login()
	{
		mainFrame.loginBt.setBounds(410, 37, 86, 23);
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonLog.png");
		iconStart.setImage(iconStart.getImage().getScaledInstance(mainFrame.loginBt.getBounds().width,
				mainFrame.loginBt.getBounds().height, Image.SCALE_SMOOTH));
		mainFrame.loginBt.setIcon(iconStart);
		mainFrame.loginBt.setContentAreaFilled(false);
		mainFrame.loginBt.setEnabled(false);
		mainFrame.nameText.setEnabled(false);
		mainFrame.textPlease.setVisible(true);
		for (int i = 0; i < Seat.seatNum; i++)
			((JButton) mainFrame.panel.getComponent(i)).setEnabled(true);
		refreshRoom(0);
	}

	public static void logout()
	{
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
		for (int i = 0; i < Seat.seatNum; i++)
			((JButton) mainFrame.panel.getComponent(i)).setEnabled(false);
		mainFrame.nameText.setText("");
	}

	public static void chooseSeat(int room, int seat, String name)
	{
		if(name.isEmpty()) {
			getErrOrder("���¼����ѡ����");
			return;
		}
		int r = room + 1;
		int s = seat + 1;
		((JButton) mainFrame.panel.getComponent(seat)).setEnabled(false);
		((JButton) mainFrame.panel.getComponent(seat)).setText("ʹ���У�" + name);
		getOrder(name + "��������λ�ǣ�\n   ����" + r + ",��λ" + s + "��");

		Seat.selectSeat(room, seat, name);
		msgChange.logout();
	}

	public static void getOrder(String qus)
	{
		JOptionPane.showMessageDialog(null, qus, "ѡ���ɹ�", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void getErrOrder(String qus)
	{
		JOptionPane.showMessageDialog(null, qus, "����ʧ�ܣ�", JOptionPane.DEFAULT_OPTION);
	}

	public static void refreshRoom(int room)
	{
		for (int i = 0; i < Seat.seatNum; i++)
		{
			((JButton) mainFrame.panel.getComponent(i)).setEnabled(true);
			((JButton) mainFrame.panel.getComponent(i)).setText(i + 1 + "");
		}

		// ʹ����
		Map<Integer, String> preSeats = Seat.getroomSeat(room);
		if (preSeats != null)
		{
			Iterator<Map.Entry<Integer, String>> entries = preSeats.entrySet().iterator();
			while (entries.hasNext())
			{
				Map.Entry<Integer, String> entry = entries.next();
				((JButton) mainFrame.panel.getComponent(entry.getKey())).setEnabled(false);
				((JButton) mainFrame.panel.getComponent(entry.getKey())).setText("ʹ���У�" + entry.getValue());
			}
		}
		// vip��λʱ�����
		Map<String, Integer> vipSeats = Seat.getVIPSeats(room);
		if (vipSeats != null)
		{
			Iterator<Map.Entry<String, Integer>> entries = vipSeats.entrySet().iterator();
			while (entries.hasNext())
			{
				Map.Entry<String, Integer> entry = entries.next();
				((JButton) mainFrame.panel.getComponent(entry.getValue())).setEnabled(false);
				String freshTime = Seat.getSeatTime(entry.getKey());
				((JButton) mainFrame.panel.getComponent(entry.getValue())).setText("ʣ��ʱ�䣺" + freshTime);
			}
		}
	}

}

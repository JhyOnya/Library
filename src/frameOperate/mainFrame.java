package frameOperate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Message.Seat;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class mainFrame extends JFrame {

	private int x, y;
	static String[] rooms;
	static String imgDir = "E:\\Code\\Java\\Library\\src\\Message\\picUI\\";
	static JButton[]  seatBts;

	static mainFrame mframe;
	private JPanel contentPane;
	private static JTextField preTime;
	private JTextField textField;
	static JComboBox chooseClass;
	private JTextField textField_1;
	static JTextField nameText;
	private JButton btnNewButton;
	private JButton buttonG;
	private JButton buttonY;
	private JButton buttonR;
	static JButton loginBt;
	static JTextField textPlease;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mframe = new mainFrame();
					mframe.setUndecorated(true);// 去除边框
					mframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainFrame() {
		msgChange.init();
		this.setSize(677, 459);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = this.getSize();
		int fx = (int) screensize.getWidth() / 2 - (int) framesize.getWidth() / 2;
		int fy = (int) screensize.getHeight() / 2 - (int) framesize.getHeight() / 2;
		this.setLocation(fx, fy);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		chooseClass = new JComboBox(rooms);
		chooseClass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					msgChange.refreshRoom(Integer.parseInt(chooseClass.getSelectedItem() + "") - 1);
				}
			}
		});
		chooseClass.setEnabled(false);
		chooseClass.setBounds(598, 37, 41, 21);
		contentPane.add(chooseClass);

		nameText = new JTextField();
		nameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (nameText.getText().equals("请输入姓名进行登录！"))
					nameText.setText("");
			}
		});
		nameText.setBounds(237, 37, 163, 21);
		contentPane.add(nameText);
		nameText.setColumns(10);

		preTime = new JTextField();
		preTime.setBackground(new Color(204, 204, 255));
		preTime.setEditable(false);
		preTime.setColumns(10);
		preTime.setBorder(null);
		preTime.setBounds(10, 37, 170, 21);
		contentPane.add(preTime);

		textField = new JTextField();
		textField.setBackground(new Color(204, 204, 255));
		textField.setText("\u59D3\u540D\uFF1A");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(197, 37, 66, 21);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setBackground(new Color(204, 204, 255));
		textField_1.setText("\u9009\u62E9\u6559\u5BA4\uFF1A");
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBounds(541, 37, 66, 21);
		contentPane.add(textField_1);

		loginBt = new JButton();
		loginBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] stuMsg;
				if (nameText.getText().isEmpty() || nameText.getText().equals("请输入姓名进行登录！"))
					nameText.setText("请输入姓名进行登录！");
				else {
					stuMsg = Seat.isVIP(nameText.getText());
					if (stuMsg[0] == -1) {
						msgChange.login();
					} else {
						// vip，指定座位
						msgChange.chooseSeat(stuMsg[0], stuMsg[1], "尊敬的VIP用户 " + nameText.getText());
					}
				}
			}
		});
		loginBt.setBounds(410, 37, 86, 23);
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonLogin.png");
		iconStart.setImage(iconStart.getImage().getScaledInstance(loginBt.getBounds().width, loginBt.getBounds().height,
				Image.SCALE_SMOOTH));
		loginBt.setIcon(iconStart);
		loginBt.setContentAreaFilled(false);
		loginBt.setBorderPainted(false);
		contentPane.add(loginBt);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(10, 89, 641, 321);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnNewButton = new JButton("New button");
		btnNewButton.setEnabled(false);
		panel.add(btnNewButton);

		JButton buttonG = new JButton();
		buttonG.setBounds(getWidth() - 63, 0, 21, 21);
		ImageIcon iconG = new ImageIcon(imgDir + "buttonG.png");
		iconG.setImage(iconG.getImage().getScaledInstance(buttonG.getBounds().width, buttonG.getBounds().height,
				Image.SCALE_SMOOTH));
		buttonG.setIcon(iconG);
		contentPane.add(buttonG);

		JButton buttonY = new JButton();
		buttonY.setBounds(getWidth() - 42, 0, 21, 21);
		ImageIcon iconY = new ImageIcon(imgDir + "buttonY.png");
		iconY.setImage(iconY.getImage().getScaledInstance(buttonY.getBounds().width, buttonY.getBounds().height,
				Image.SCALE_SMOOTH));
		buttonY.setIcon(iconY);
		buttonY.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});
		buttonY.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int left = mframe.getLocation().x;
				int top = mframe.getLocation().y;
				mframe.setLocation(left + e.getX() - x, top + e.getY() - y);
			}
		});
		contentPane.add(buttonY);

		JButton buttonR = new JButton();
		buttonR.setBounds(getWidth() - 21, 0, 21, 21);
		ImageIcon iconR = new ImageIcon(imgDir + "buttonR.png");
		iconR.setImage(iconR.getImage().getScaledInstance(buttonR.getBounds().width, buttonR.getBounds().height,
				Image.SCALE_SMOOTH));
		buttonR.setIcon(iconR);
		buttonR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		contentPane.add(buttonR);

		textPlease = new JTextField();
		textPlease.setText("\u8BF7\u9009\u62E9\u5EA7\u4F4D\uFF01");
		textPlease.setEditable(false);
		textPlease.setColumns(10);
		textPlease.setBorder(null);
		textPlease.setBackground(new Color(204, 204, 255));
		textPlease.setBounds(291, 68, 76, 21);
		textPlease.setVisible(false);
		contentPane.add(textPlease);
		
		seatBts=new JButton[5];
		for (int i = 0; i < Seat.seatNum; i++) {
			seatBts[i]=new JButton(i + 1 + "");
			seatBts[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println((JButton) e.getSource());
					msgChange.chooseSeat(Integer.parseInt(chooseClass.getSelectedItem() + "") - 1,
							Integer.parseInt(((JButton) e.getSource()).getText()) - 1, nameText.getText());
				}
			});
			seatBts[i].setEnabled(false);
			panel.add(seatBts[i]);
		}
		new Thread(new preTime()).start();
	}

	public static void refreshTime(String str) {
		preTime.setText(str);
	}
}
package frameOperate;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
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
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.DropMode;

public class mainFrame extends JFrame {

	private int x, y;
	static String[] rooms;
	static String imgDir = System.getProperty("user.dir");

	static mainFrame mframe;
	private JPanel contentPane;
	static JTextField preTime;
	private JTextField textField;
	static JComboBox chooseRoom;
	private JTextField textField_1;
	static JTextField nameText;
	private JButton btnNewButton;
	private JButton buttonG;
	private JButton buttonY;
	private JButton buttonR;
	static JButton loginBt;
	static JTextField textMsg;
	static JPanel panel;
	static JTextField chsTime;
	private JButton chsBtn;

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
		rooms = Seat.rooms;
		imgDir = imgDir + "\\picUI\\";
		this.setSize(865, 626);
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

		preTime = new JTextField();
		new Thread(new preTime()).start();
		preTime.setText("2019\u5E7404\u670825\u65E5 10:31:28");
		preTime.setOpaque(false);
		preTime.setEditable(false);
		preTime.setColumns(10);
		preTime.setBorder(null);
		preTime.setBounds(82, 36, 148, 21);
		contentPane.add(preTime);
		chooseRoom = new JComboBox(rooms);
		chooseRoom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					msgChange.refresh();
				}
			}
		});
		chooseRoom.setBounds(725, 37, 86, 21);
		contentPane.add(chooseRoom);

		nameText = new JTextField(imgDir);
		nameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (nameText.getText().equals("请输入姓名进行登录！"))
					nameText.setText("");
			}
		});
		nameText.setBounds(354, 36, 181, 21);
		contentPane.add(nameText);
		nameText.setColumns(10);

		textField = new JTextField();
		textField.setOpaque(false);
		textField.setText("\u59D3\u540D\uFF1A");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(314, 36, 66, 21);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setOpaque(false);
		textField_1.setText("\u9009\u62E9\u6559\u5BA4\uFF1A");
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBounds(668, 37, 66, 21);
		contentPane.add(textField_1);

		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 106, 834, 471);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(6, 5, 13, 13));

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

		textMsg = new JTextField("请登陆后进行选座！");
		textMsg.setHorizontalAlignment(SwingConstants.CENTER);
		textMsg.setOpaque(false);
		textMsg.setEditable(false);
		textMsg.setColumns(10);
		textMsg.setBorder(null);
		textMsg.setBounds(339, 68, 203, 21);
		contentPane.add(textMsg);

		JRadioButton preTimeRBt = new JRadioButton("\u5F53\u524D\u65F6\u95F4");
		preTimeRBt.setSelected(true);
		preTimeRBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (preTimeRBt.isSelected()) {
					textMsg.setText("请选择座位！");
					chsBtn.setEnabled(false);
					msgChange.preOrChs = true;
					msgChange.refresh();
				}
			}
		});
		preTimeRBt.setOpaque(false);
		preTimeRBt.setBounds(10, 35, 76, 23);
		contentPane.add(preTimeRBt);

		JRadioButton chsTimeRBt = new JRadioButton("\u9009\u5B9A\u65F6\u95F4");
		chsTimeRBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chsTimeRBt.isSelected()) {
					textMsg.setText("仅能查看指定时间，禁止提前占座！");
					chsBtn.setEnabled(true);
					msgChange.preOrChs = false;
					msgChange.refresh();
				}
			}
		});
		chsTimeRBt.setOpaque(false);
		chsTimeRBt.setBounds(10, 66, 76, 23);
		contentPane.add(chsTimeRBt);

		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(preTimeRBt);
		sexGroup.add(chsTimeRBt);

		chsTime = new JTextField();
		chsTime.setText("2019\u5E7404\u670825\u65E5 10:31:28");
		chsTime.setOpaque(false);
		chsTime.setColumns(10);
		chsTime.setBorder(null);
		chsTime.setBounds(82, 67, 148, 21);
		contentPane.add(chsTime);

		chsBtn = new JButton("\u67E5\u8BE2");
		chsBtn.setEnabled(false);
		chsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msgChange.refresh();
			}
		});
		chsBtn.setBounds(236, 67, 66, 23);
		contentPane.add(chsBtn);

		for (int i = 0; i < Seat.seatNum; i++) {
			JButton seatBts = new JButton(i + 1 + "");
			ImageIcon iconSeat = new ImageIcon(imgDir + "buttonSeat.png");
			iconSeat.setImage(iconSeat.getImage().getScaledInstance(160, 70, Image.SCALE_DEFAULT));
			seatBts.setHorizontalTextPosition(JButton.CENTER);
			seatBts.setIcon(iconSeat);
			seatBts.setContentAreaFilled(false);
			seatBts.setBorderPainted(false);
			seatBts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String btnName = ((JButton) e.getSource()).getText();
					if (btnName.indexOf(".") != -1)
						btnName = btnName.substring(0, btnName.indexOf("."));
					int chsBtnNum = Integer.parseInt(btnName) - 1;
					msgChange.chooseSeat(chooseRoom.getSelectedIndex(), chsBtnNum, nameText.getText());
//					int chsBtnNum = msgChange.searchComponentByName(panel, ((JButton) e.getSource()).getText());
//					msgChange.chooseSeat(chooseRoom.getSelectedIndex(), chsBtnNum, nameText.getText());
				}
			});
			panel.add(seatBts);
		}

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
		loginBt.setBounds(545, 37, 86, 23);
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonLogin.png");
		iconStart.setImage(iconStart.getImage().getScaledInstance(loginBt.getBounds().width, loginBt.getBounds().height,
				Image.SCALE_SMOOTH));
		loginBt.setIcon(iconStart);
		loginBt.setContentAreaFilled(false);
		loginBt.setBorderPainted(false);
		contentPane.add(loginBt);
		msgChange.refresh();
	}

	public static void refreshTime(String str) {
		preTime.setText(str);
	}
}
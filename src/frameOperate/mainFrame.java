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
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class mainFrame extends JFrame {

	static String imgDir = "E:\\Code\\Java\\Library\\src\\Message\\picUI\\";
	String[] rooms;

	static mainFrame mframe;
	private JPanel contentPane;
	private static JTextField preTime;
	private JTextField textField;
	private JComboBox chooseClass;
	private JTextField textField_1;
	private JTextField nameText;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mframe = new mainFrame();
					mframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void init() {
		rooms = new String[Seat.rooms];
		for (int i = 0; i < Seat.rooms; i++) {
			rooms[i] = (i + 1) + "";
		}
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		init();
		this.setSize(677, 459);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = this.getSize();
		int fx = (int) screensize.getWidth() / 2 - (int) framesize.getWidth() / 2;
		int fy = (int) screensize.getHeight() / 2 - (int) framesize.getHeight() / 2;
		this.setLocation(fx, fy);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(216, 191, 216));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		chooseClass = new JComboBox(rooms);
		chooseClass.setEnabled(false);
		chooseClass.setBounds(598, 10, 41, 21);
		contentPane.add(chooseClass);

		nameText = new JTextField();
		nameText.setBounds(237, 10, 163, 21);
		contentPane.add(nameText);
		nameText.setColumns(10);

		preTime = new JTextField();
		preTime.setBackground(new Color(216, 191, 216));
		preTime.setEditable(false);
		preTime.setColumns(10);
		preTime.setBorder(null);
		preTime.setBounds(10, 10, 170, 21);
		contentPane.add(preTime);

		textField = new JTextField();
		textField.setBackground(new Color(216, 191, 216));
		textField.setText("\u59D3\u540D\uFF1A");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(197, 10, 66, 21);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setBackground(new Color(216, 191, 216));
		textField_1.setText("\u9009\u62E9\u6559\u5BA4\uFF1A");
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBounds(541, 10, 66, 21);
		contentPane.add(textField_1);

		JButton loginBt = new JButton();
		loginBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<Integer, Integer> stuMsg = Seat.isVIP(nameText.getName());
			}
		});
		loginBt.setBounds(410, 10, 86, 23);
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonLogin.png");
		iconStart.setImage(iconStart.getImage().getScaledInstance(loginBt.getBounds().width, loginBt.getBounds().height,
				Image.SCALE_SMOOTH));
		loginBt.setContentAreaFilled(false);
		loginBt.setBorderPainted(false);
		loginBt.setIcon(iconStart);
		contentPane.add(loginBt);
		new Thread(new preTime()).start();
	}

	public static void refreshTime(String str) {
		preTime.setText(str);
	}
}
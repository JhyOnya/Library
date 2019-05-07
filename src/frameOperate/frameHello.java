package frameOperate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.UIManager;

import excelOperate.readFile;

public class frameHello extends JFrame {
	int x, y;
	static frameHello hello;
	static String imgDir = System.getProperty("user.dir");
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					imgDir = imgDir + "\\picUI\\";
					hello = new frameHello();
					hello.setUndecorated(true);// 去除边框
					hello.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		Thread timeController = new Thread() {
			public void run() {
				try {
					Thread.sleep(3000); // 限制运行时间
					hello.dispose();

					mainFrame.mframe = new mainFrame();
					mainFrame.mframe.setUndecorated(true);// 去除边框
					mainFrame.mframe.setVisible(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timeController.start();
	}

	/**
	 * Create the frame.
	 */
	public frameHello() {
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(532, 263);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = this.getSize();
		int fx = (int) screensize.getWidth() / 2 - (int) framesize.getWidth() / 2;
		int fy = (int) screensize.getHeight() / 2 - (int) framesize.getHeight() / 2;
		this.setLocation(fx, fy);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		ImageIcon iconBg = new ImageIcon(imgDir + "picHello.jpg");
		iconBg.setImage(iconBg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));

		JEditorPane editorHello = new JEditorPane();
		editorHello.setEditable(false);
		editorHello.setEnabled(false);
		editorHello.setFont(new Font("华文隶书", Font.PLAIN, 50));
		editorHello.setOpaque(false);
		editorHello.setDisabledTextColor(UIManager.getColor("Button.light"));
		editorHello.setText("\u6B22\u8FCE\u4F7F\u7528\u56FE\u4E66\u9986\u7CFB\u7EDF\uFF01");
		editorHello.setBounds(22, 103, 532, 48);
		contentPane.add(editorHello);
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonHello.png");

		JLabel bgLabel = new JLabel(iconBg);
		bgLabel.setBounds(0, 0, getWidth(), getHeight()); // 设置位置
		contentPane.add(bgLabel);

	}
}

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

public class frameHello extends JFrame
{
	int x, y;
	static frameHello hello;
	static String imgDir = "E:\\_Practice\\JavaEE\\OJ\\src\\tea\\judge\\img\\";
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					hello = new frameHello();
					hello.setUndecorated(true);// 去除边框
					hello.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				catch (Throwable e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread timeController = new Thread()
		{
			public void run()
			{
				try
				{
					Thread.sleep(3000); // 限制运行时间
					hello.dispose();

//					mainFrame = new JudgeUIMain();
					mainUIFrame.mainFrame = new mainUIFrame();
					mainUIFrame.mainFrame.setUndecorated(true);// 去除边框
					mainUIFrame.mainFrame.setVisible(true);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		};
		timeController.start();
	}

	/**
	 * Create the frame.
	 */
	public frameHello()
	{
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

		ImageIcon iconBg = new ImageIcon(imgDir + "bground.jpg");
		iconBg.setImage(iconBg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));

		JEditorPane editorHello = new JEditorPane();
		editorHello.setEditable(false);
		editorHello.setEnabled(false);
		editorHello.setFont(new Font("华文隶书", Font.PLAIN, 50));
		editorHello.setOpaque(false);
		editorHello.setDisabledTextColor(Color.black);
		editorHello.setText("\u6B22\u8FCE\u4F7F\u7528\u7A0B\u5E8F\u8BC4\u5224\u7CFB\u7EDF\uFF01");
		editorHello.setBounds(0, 82, 556, 48);
		contentPane.add(editorHello);
		ImageIcon iconStart = new ImageIcon(imgDir + "buttonHello.png");

		JLabel bgLabel = new JLabel(iconBg);
		bgLabel.setBounds(0, 0, getWidth(), getHeight()); // 设置位置
		contentPane.add(bgLabel);

	}
}

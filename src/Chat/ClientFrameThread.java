package Chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Users.Login;
import Users.Users_Main;

public class ClientFrameThread extends JFrame implements ActionListener, KeyListener {
	Container c = getContentPane();
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	JTextArea ta = new JTextArea();
	JTextField tf = new JTextField(35);
	JButton btn = new JButton("����");
	private String uid = "";
	private Socket socket;
	private DataOutputStream out;

	public ClientFrameThread() {
		setTitle("ä��");
		setSize(470, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		btn.setIcon(new ImageIcon(new ImageIcon("resource/icon/send.png").getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH)));
		setLayout(new BorderLayout());

		pan1.setLayout(new FlowLayout());		
		pan1.setLayout(new BorderLayout());

		JScrollPane sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		tf.addKeyListener(this);
		btn.addActionListener(this);

		c.setFont(new Font("�����ٸ����", Font.BOLD, 12));

		btn.setContentAreaFilled(false);

		setIconImage(changedImg);

		pan2.setBackground(Color.WHITE);

		pan1.add(sp);
		pan2.add(tf);
		pan2.add(btn); 

		add(pan1, BorderLayout.CENTER);
		add(pan2, BorderLayout.SOUTH);
		
		this.addWindowListener(new WindowAdapter() {@Override
			public void windowClosing(WindowEvent arg0) {
			dispose();
			new Users_Main();
		}
		});

		setResizable(false); // âũ�� �Һ�
		setVisible(true);

		Start();

		tf.requestFocus();
	}

	private void Start() {
		String ip = "127.0.0.1";
		int port = Integer.parseInt("9999");
		uid = Login.id;

		// ���� �ʱ�ȭ �ϱ� 
		init(uid, ip, port);		
	}

	public static void main(String[] args) {
		new ClientFrameThread();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn) {
			sendMsg();
			tf.requestFocus();
		}
	}

	// ���� ��û�� ���� �ʱ�ȭ ���� 
	private void init(String uid, String ip, int port) {
		try {			
			// ������ �����Ͽ� ������ ��û�Ѵ�.
			socket = new Socket(ip, port);
			out = new DataOutputStream(socket.getOutputStream());
			System.out.println("������ ����Ǿ����ϴ�.");

			//������ �̸�����
			out.writeUTF(uid);

			// �����带 �̿��ؼ� �޽��� �ޱ� 
			Thread receiver = new Thread(new ClientReceiver(socket));
			receiver.start();

		} catch(ConnectException ce) {
			ce.printStackTrace();
		} catch(Exception e) {}
	}

	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;

		ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch(IOException e) {}
		}

		public void run() {
			while(in != null) {
				try {
					String msg = in.readUTF();
					ta.append(msg + "\n");
				} catch(IOException e) {}
			}
		} 
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			sendMsg();
		}
	}

	private void sendMsg() {
		try {
			// ���� �޽��� 
			out.writeUTF("["+uid+"] : "+ tf.getText());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		tf.setText("");
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
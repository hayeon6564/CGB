package Chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Users.Login;

public class ServerFrameThread extends JFrame implements ActionListener {
	Container c = getContentPane();
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	JPanel pan3 = new JPanel();
	JTextField tf = new JTextField("9999", 5);
	JButton btn1 = new JButton("���� ����");
	JButton btn2 = new JButton("�������� �ϱ�");
	JTextArea ta = new JTextArea();
	private ServerSocket serverSocket;
	private Socket socket;
	private HashMap<String, DataOutputStream> clients;

	public ServerFrameThread() {
		setTitle("���� ������");
		setSize(400, 500);
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		
		setLayout(new BorderLayout());
		
		// �߰� �ڵ� ���⿡		
		clients  = new HashMap<>();
		Collections.synchronizedMap(clients);//����ȭ ó��
		
		pan1.setLayout(new FlowLayout());		
		pan2.setLayout(new BorderLayout());
		pan3.setLayout(new FlowLayout());
		
		JLabel lbl = new JLabel("����� ������ PORT ��ȣ �Է� : ");
		
		JScrollPane sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		c.setFont(new Font("�����ٸ����", Font.BOLD, 12));
		
		btn1.setContentAreaFilled(false);
		btn2.setContentAreaFilled(false);
		
		setIconImage(changedImg);
		
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.WHITE);
		pan3.setBackground(Color.WHITE);
		
		pan1.add(lbl);
		pan1.add(tf);
		pan1.add(btn1);
		pan2.add(sp);
		pan3.add(btn2); 
		
		add(pan1, BorderLayout.NORTH);
		add(pan2, BorderLayout.CENTER);
		add(pan3, BorderLayout.SOUTH);
		
		setResizable(false); // âũ�� �Һ�
		setVisible(true);
	}

	public static void main(String[] args) {
		new ServerFrameThread();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn1){
			int portNum = Integer.parseInt(tf.getText());
			start(portNum);
		}else if(obj == btn2){
			System.exit(0);
		}
		
	}
	
	// ���� �����ϱ� 
	private void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			ta.append(port + "�� ������ ���۵Ǿ����ϴ�.\n");
			Thread t1 = new StartThread();
			t1.start();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
		
	
	class StartThread extends Thread{
		public void run(){
			while(true){
				try {
					socket = serverSocket.accept();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ta.append(Login.id + "���� �����Ͽ����ϴ�.\n");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		}
	}
	
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch(IOException e) {}
		}

		//������� Ŭ���̾�Ʈ�� �߰��� �� ���� �����
		public void run() {
			String name = Login.id;
			try {
				name = in.readUTF();
				sendToAll(name+"���� �����ϼ̽��ϴ�.");

				clients.put(name, out);
				ta.append("���� ���������� ���� " + clients.size() + " �Դϴ�.\n");
				
				while(in!=null) {
					sendToAll(in.readUTF());
				}
				
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				sendToAll(name+"���� �����̽��ϴ�.");
				clients.remove(name);
				ta.append("���� ���������� ���� "+ clients.size()+"�Դϴ�.");
			} 
		} 

		//Ŭ���̾�Ʈ�� �����͸� �Է��ϸ� ��� Ŭ���̾�Ʈ���� ������ ����
		private void sendToAll(String msg) {
			Iterator<String> it = clients.keySet().iterator();

			while(it.hasNext()) {
				try {
					DataOutputStream out = (DataOutputStream)clients.get(it.next());
					out.writeUTF(msg);
				} catch(IOException e){}
			} 
		} 
	} 
	
}

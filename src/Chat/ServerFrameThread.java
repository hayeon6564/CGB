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
	JButton btn1 = new JButton("서버 구동");
	JButton btn2 = new JButton("서버종료 하기");
	JTextArea ta = new JTextArea();
	private ServerSocket serverSocket;
	private Socket socket;
	private HashMap<String, DataOutputStream> clients;

	public ServerFrameThread() {
		setTitle("서버 프레임");
		setSize(400, 500);
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		
		setLayout(new BorderLayout());
		
		// 추가 코드 여기에		
		clients  = new HashMap<>();
		Collections.synchronizedMap(clients);//동기화 처리
		
		pan1.setLayout(new FlowLayout());		
		pan2.setLayout(new BorderLayout());
		pan3.setLayout(new FlowLayout());
		
		JLabel lbl = new JLabel("사용할 서버의 PORT 번호 입력 : ");
		
		JScrollPane sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		c.setFont(new Font("나눔바른고딕", Font.BOLD, 12));
		
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
		
		setResizable(false); // 창크기 불변
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
	
	// 서버 구동하기 
	private void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			ta.append(port + "번 서버가 시작되었습니다.\n");
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
				ta.append(Login.id + "에서 접속하였습니다.\n");
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

		//쓰레드는 클라이언트가 추가될 때 마다 생긴다
		public void run() {
			String name = Login.id;
			try {
				name = in.readUTF();
				sendToAll(name+"님이 접속하셨습니다.");

				clients.put(name, out);
				ta.append("현재 서버접속자 수는 " + clients.size() + " 입니다.\n");
				
				while(in!=null) {
					sendToAll(in.readUTF());
				}
				
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				sendToAll(name+"님이 나가셨습니다.");
				clients.remove(name);
				ta.append("현재 서버접속자 수는 "+ clients.size()+"입니다.");
			} 
		} 

		//클라이언트가 데이터를 입력하면 모든 클라이언트에게 데이터 전달
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

package Users;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import Setting.Bean;
import Setting.DBmgr;

public class Search extends JFrame implements ActionListener{
	Container c = getContentPane(); 
	
	JPanel pan1 = new JPanel(new GridLayout(1,2,10,10));
	JPanel pan2 = new JPanel(new GridLayout(2, 2, -90, 15));
	JPanel pan3 = new JPanel();

	//항상 떠있음
	JButton btn1 = new JButton("ID"); // pan1
	JButton btn2 = new JButton("PW");	// pan1
	JButton btn3 = new JButton("ID 찾기");	// pan3
	JButton btn4 = new JButton("닫기");	// pan3

	JLabel lbl1 = new JLabel("이름");	// pan2
	JTextField tf1 = new JTextField(15);	// pan2

	JLabel lbl2 = new JLabel("생년월일");	// pan2
	JTextField tf2 = new JTextField(15);	// pan2

	JLabel lbl4 = new JLabel("찾으실 버튼을 눌러주세요!"); // 첫 화면

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	ArrayList<Bean> list =new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Search() {
		setLayout(null);
		
		pan1.setBounds(55, 20, 165, 25);
		pan2.setBounds(21, 70, 200, 60);
		pan3.setBounds(45, 160, 170, 35);
		lbl4.setBounds(60, 70, 300, 50);
		
		btn1.setIcon(new ImageIcon(new ImageIcon("resource/icon/id.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn2.setIcon(new ImageIcon(new ImageIcon("resource/icon/pw.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

		pan1.add(btn1);	// ID
		pan1.add(btn2);	// PW
		
		pan2.add(lbl1);	// 이름
		pan2.add(tf1);	// 이름	
		pan2.add(lbl2);	// 생년월일
		pan2.add(tf2);	// 생년월일
	
		pan3.add(btn3);	// ID 찾기
		pan3.add(btn4);	// 닫기
		
		add(lbl4);
		add(pan1);
		add(pan3);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		
		// 버튼 영역 채우기
		btn1.setContentAreaFilled(false);
		btn2.setContentAreaFilled(false);
		btn3.setContentAreaFilled(false);
		btn4.setContentAreaFilled(false);

		c.setFont(new Font("나눔바른고딕", Font.BOLD, 12));
		
		// 배경 흰색
		c.setBackground(Color.WHITE);
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.WHITE);
		pan3.setBackground(Color.WHITE);
		
		setIconImage(changedImg);
		
		// 윈도우 이벤트 등록
		addWindowListener(new WindowAdapter() {
			@Override
			// 윈도우가 닫히려고 할때
			public void windowClosing(WindowEvent arg0) {
				new Login();	// 로그인창을 띄운다.
			}
			});
		
		setTitle("ID/PW 찾기");
		setSize(300,250);
		setLocationRelativeTo(null);	// 윈도우 창을 화면의 가운데 띄우는 역할
		setResizable(false); // 창크기 불변
		setVisible(true);
		
		pan1.requestFocus();
	}

	public static void main(String[] args) {
		//new Search();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// btn1(ID)가 눌렸을 때
		if(obj == btn1) {
			lbl4.setText("");
			lbl1.setText("이름");
			lbl2.setText("생년월일");
			btn3.setText("ID 찾기");
			tf1.setText("");
			tf2.setText("");

			add(pan2);
			// btn2(PW)가 눌렸을 때
		}else if(obj == btn2){
			lbl4.setText("");
			lbl1.setText("이름");
			lbl2.setText("ID");
			btn3.setText("PW 찾기");
			tf1.setText("");
			tf2.setText("");

			add(pan2);
			// btn3가 눌렸을 때
		}else if(obj == btn3) {
			// btn3의 내용과 "ID 찾기"가 같을 때
			if(btn3.getText().equals("ID 찾기")) {
				// tf1이 빈칸이거나 tf2가 빈칸일 때 메시지를 띄우고 tf1에 포커스를 맞춘다
				if(tf1.getText().equals("") || tf2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "메시지", JOptionPane.ERROR_MESSAGE);
					tf1.requestFocus();
					// tf1과 tf2가 빈칸이 아닐 때
				}else {
					// ulist에서 u_name와 tf1의 내용과 u_bd와 tf2의 내용이 같은 것을 뽑아오고 list에 저장한다
					DBmgr.sql = "select * from ulist where u_name = '" + tf1.getText() + "' and u_bd = '" + tf2.getText() + "'";
					list = mgr.user();
					try {
					bean = list.get(0);
					String id = bean.getUid();
					String name = bean.getUname();
					String bd = bean.getUbd();
					// id 첫번째부터 4번째까지 뽑아오고 나머지는 *로 알려주고 tf1과 tf2는 빈칸으로 만들어준다. 
					JOptionPane.showMessageDialog(null, name + "님의 ID는 " + id.substring(0, 4) + "*** 입니다.", "메시지", JOptionPane.WARNING_MESSAGE);
					tf1.setText("");
					tf2.setText("");
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "회원정보가 틀립니다.다시입력해주세요.", "메시지", JOptionPane.ERROR_MESSAGE);
						tf1.setText("");
						tf2.setText("");
						tf1.requestFocus();
					}
				}
				// btn3의 내용과 "PW 찾기"가 같을 때
			}else if(btn3.getText().equals("PW 찾기")) {
				// tf1이 빈칸이거나 tf2가 빈칸일 때 메시지를 띄우고 tf1에 포커스를 맞춘다 
				if(tf1.getText().equals("") || tf2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "메시지", JOptionPane.ERROR_MESSAGE);
					tf1.requestFocus();
					// tf1과 tf2가 빈칸이 아닐 때
				}else {
					// ulist에서 u_name와 tf1의 내용과 u_bd와 tf2의 내용이 같은 것을 뽑아오고 list에 저장한다
					DBmgr.sql = "select * from ulist where u_name = '" + tf1.getText() + "'";
		               list = mgr.user();
		               bean = list.get(0);
		               String id = bean.getUid();
		               String pw = bean.getUpw();
		               String name = bean.getUname();
		               
		               int npw;
		               Container ct = getContentPane();
		               
		               JLabel lb1 = new JLabel("새 비밀번호");
		               JPasswordField npf = new JPasswordField(10);
		               JPanel p1 = new JPanel(new GridLayout(2,2));
		               p1.add(lb1);	// 새 비밀번호
		               p1.add(npf);	// 새 비밀번호
		               
		               JLabel lb2 = new JLabel("새 비밀번호 확인");
		               JPasswordField cpf = new JPasswordField(10);
		               p1.add(lb2);	// 새 비밀번호 확인
		               p1.add(cpf);	// 새 비밀번호 확인
		               
		               while(true) {
		                  npf.setText("");
		                  npf.requestFocus();
		                  npw = JOptionPane.showConfirmDialog(ct, p1, "메시지", JOptionPane.OK_CANCEL_OPTION);
		                  // CANCEL을 누르거나 닫으면 break
		                  if(npw == JOptionPane.CANCEL_OPTION || npw == JOptionPane.CLOSED_OPTION) {
		                     
		                     break;
		                     
		                     // npf가 빈칸이거나 cpf가 빈칸일 때 메시지를 띄우고 npf, cpf를 빈칸으로 만들고 npf에 포커스를 둔다
		                  }else if(npf.getText().equals("") || cpf.getText().equals("")) {
		                     
		                     JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "메시지", JOptionPane.ERROR_MESSAGE);
		                     npf.setText("");
		                     cpf.setText("");
		                     npf.requestFocus();
		                     
		                     // npf와 cpf가 같으면 ulist에 tf1에 쓴 이름을 뽑아 u_pw를 바꾸고 메시지를 띄우고 break
		                  }else if(npf.getText().equals(cpf.getText())) {
		                	 DBmgr.sql = "update ulist set u_pw = '" + npf.getText() + "' where u_name = '" + tf1.getText() + "'";
		                	 list = mgr.update();
		                     JOptionPane.showMessageDialog(null, "비밀번호 설정 완료!!", "메시지", JOptionPane.INFORMATION_MESSAGE);
		                     break;
		                     // npf와 cpf가 같지 않으면 메시지를 띄운다
		                  }else {
		                     JOptionPane.showMessageDialog(null, "비밀번호가 같지 않습니다!!", "메시지", JOptionPane.ERROR_MESSAGE);
		                  }
		               }
		               tf1.setText("");
		               tf2.setText("");
				}
			}
			// btn4가 눌리면 현재창은 종료되고 로그인창이 뜬다
		}else if(obj == btn4) {
			dispose();
			new Login();
		}
	}
}
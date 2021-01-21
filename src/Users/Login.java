package Users;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import Setting.Bean;
import Setting.DBmgr;
import Admin.Admin_Main;
import Chat.ServerFrameThread;

public class Login extends JFrame implements ActionListener {
	public static String id, name, no, bd;
	Container c = getContentPane();
	// ImageIcon 객체를  생성
	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	
	// ImageIcon에서 Image를 추출
	Image originImg = originIcon.getImage();
	
	// 추출된 Image의 크기를 조절해서 새로운 Image객체를 생성
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	
	JLabel lbl1 = new JLabel(); // CGB title
	JLabel lbl2 = new JLabel(); // ID
	JLabel lbl3 = new JLabel();	// PW

	JTextField tf1 = new JTextField("");		 // ID
	JPasswordField pf1 = new JPasswordField(""); // PW
	
	JButton btn1 = new JButton();			//로그인
	JButton btn2 = new JButton("회원가입");
	JButton btn3 = new JButton("ID/PW 찾기");
	JButton btn4 = new JButton("종료");

	JPanel pan1 = new JPanel(); // ID PW 로그인
	JPanel pan2 = new JPanel();	// 회원가입 종료
	JPanel pan3 = new JPanel();	// ID PW

	ArrayList<Bean> list = new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();
	
	public Login() {
		setIconImage(changedImg);
		
		lbl1.setIcon(new ImageIcon(new ImageIcon("resource/icon/title.png").getImage().getScaledInstance(120, 45, Image.SCALE_SMOOTH)));
		lbl1.setHorizontalAlignment(JLabel.CENTER); // 라벨 텍스트를 중앙으로 정렬
		lbl2.setIcon(new ImageIcon(new ImageIcon("resource/icon/id.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		lbl2.setHorizontalAlignment(JLabel.RIGHT); // 라벨 텍스트를 오른쪽으로 정렬
		lbl3.setIcon(new ImageIcon(new ImageIcon("resource/icon/pw.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		lbl3.setHorizontalAlignment(JLabel.RIGHT); // 라벨 텍스트를 오른쪽으로 정렬 
		btn1.setIcon(new ImageIcon(new ImageIcon("resource/icon/login.png").getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH)));
		btn2.setIcon(new ImageIcon(new ImageIcon("resource/icon/singup.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn3.setIcon(new ImageIcon(new ImageIcon("resource/icon/searchs.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn4.setIcon(new ImageIcon(new ImageIcon("resource/icon/exits.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

		pan1.setLayout(null);
		pan3.setLayout(new GridLayout(2,2,10,10));

		pan1.add(pan3);// ID field, PW field
		pan1.add(btn1);// 로그인 버튼

		pan2.add(btn2);// 회원가입 버튼
		pan2.add(btn3);// ID/PW 찾기 버튼
		pan2.add(btn4);// 종료 버튼

		pan3.add(lbl2); pan3.add(tf1);	// ID : tf1
		pan3.add(lbl3); pan3.add(pf1);	// PW : pf1
		
		c.setFont(new Font("나눔바른고딕", Font.BOLD, 12)); // 전체 폰트 변경

		pan3.setBounds(-60, 30, 270, 60);
		btn1.setBounds(230, 30, 80, 60);

		add(lbl1,"North");
		add(pan1,"Center");
		add(pan2, "South");

		tf1.addActionListener(this);
		pf1.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn4.addActionListener(this);
		btn3.addActionListener(this);
		
		// 버튼 영역 배경 표시 설정
		btn2.setContentAreaFilled(false); 
		btn4.setContentAreaFilled(false); 
		btn3.setContentAreaFilled(false); 

		// 배경색 흰색
		c.setBackground(Color.WHITE);
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.WHITE);
		pan3.setBackground(Color.WHITE);
		
		setTitle("로그인");
		setSize(350,230);
		setLocationRelativeTo(null); // 윈도우 창을 화면의 가운데 띄우는 역할
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 메인프레임을 닫을 때 프로그램 종료
		setResizable(false); // 창크기 불변
		setVisible(true); // 창으로 보여준다
		
		tf1.requestFocus();
	}

	public static void main(String[] args) {
		new Login();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 로그인을 누르거나 pf1에서 엔터를 누르거나 tf1에서 엔터를 눌렀을때 check() 실행
		if(e.getSource().equals(btn1) || e.getSource().equals(pf1) || e.getSource().equals(tf1)) {
			check();
		// btn2(회원가입)이 눌렸을때 로그인창은 종료되고 회원가입창이 뜬다 
		}else if(e.getSource().equals(btn2)) {
			dispose();
			new Insert();
		// btn4(종료)가 눌렸을때 프로세스 종료	
		}else if(e.getSource().equals(btn4)) {
			System.exit(0);
		}
		// btn3(ID/PW 찾기)가 눌렸을때 로그인창은 종료되고 ID/PW 찾기가 실행
		else if(e.getSource().equals(btn3)) {
			dispose();
			new Search();
		}
	}

	private void check() {
		// tf1가 빈칸이거나 pf1이 빈칸일때 메시지를 띄우고 tf1과 pf1을 빈칸으로 만들고 tf1로 포커스를 맞춘다.
		if(tf1.getText().equals("")||pf1.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "메시지", JOptionPane.ERROR_MESSAGE);
			tf1.setText("");
			pf1.setText("");
			tf1.requestFocus();
		// tf1과 pf1가 빈칸이 아닐때 실행
		}else {
			// ulist에서 u_id와 tf1이 같고 u_pw와 pf1이 같은것을 뽑아온다.
			DBmgr.sql = "select * from ulist where u_id = '" + tf1.getText() + "' and u_pw = '" + pf1.getText() + "'";
			// 뽑아온것을 list에 넣는다.
			list = mgr.user();
			
			try {
			// list.get(0)번째부터 확인해
				bean = list.get(0);
			// 맞으면 다른곳에서도 쓸 밑에 static으로 올린 no, id, bd, name을 저장시킨다. 
				no = bean.getUno()+"";
				id = bean.getUid();
				bd = bean.getUbd();
				name = bean.getUname();
			// 저장시킨 뒤 로그인창은 종료되고 Main창을 띄운다.  
				dispose();
				new Users_Main();
			}catch(Exception e1) {
			// try문이 맞지 않을 때 여기 catch문을 실행시킨다. 
			// tf1의 내용이 "admin"과 같고 pf1의 내용이 "1234"와 같을때 조건 성립
				if(tf1.getText().equals("admin")&&pf1.getText().equals("1234")) {
			// 조건이 성립되면 로그인창은 종료되고 Admin창과 serverFrameThread창이 뜬다.		
					dispose();
					new Admin_Main();
					new ServerFrameThread();
				}else {
			// 조건 성립이 안 되면 메시지가 뜨고 tf1과 pf1을 빈칸으로 만들고 tf1에 포커스를 둔다.		
					JOptionPane.showMessageDialog(null, "회원정보가 틀립니다.다시입력해주세요.", "메시지", JOptionPane.ERROR_MESSAGE);
					tf1.setText("");
					pf1.setText("");
					tf1.requestFocus();
				}

			}

		}
	}
}
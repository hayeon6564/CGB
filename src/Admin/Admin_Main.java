package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Users.Login;

public class Admin_Main extends JFrame implements ActionListener{

	Container c = getContentPane();

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JButton btinsert = new JButton("영화 등록" );
	JButton btedit = new JButton("영화 관리");
	JButton btmanager = new JButton("회원 관리");
	JButton btlogout = new JButton("나가기");

	JPanel p1 = new JPanel(new GridLayout(4,1,0,5));

	public Admin_Main() {

		this.setLayout(null);

		//배경색 설정
		c.setBackground(Color.WHITE);
		p1.setBackground(Color.WHITE);
		btinsert.setBackground(Color.WHITE);
		btedit.setBackground(Color.WHITE);
		btmanager.setBackground(Color.WHITE);
		btlogout.setBackground(Color.WHITE);

		//전체 폰트 설정
		c.setFont(new Font("나눔바른고딕", Font.BOLD, 12));

		//버튼의 테두리 설정
		btinsert.setBorderPainted(false);
		btedit.setBorderPainted(false);
		btmanager.setBorderPainted(false);
		btlogout.setBorderPainted(false);

		//아이콘 삽입과 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정 
		btinsert.setIcon(new ImageIcon(new ImageIcon("resource/icon/movie.png").getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH)));
		btedit.setIcon(new ImageIcon(new ImageIcon("resource/icon/movieedit.png").getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));
		btmanager.setIcon(new ImageIcon(new ImageIcon("resource/icon/searchU.png").getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH)));
		btlogout.setIcon(new ImageIcon(new ImageIcon("resource/icon/logoutA.png").getImage().getScaledInstance(45, 50, Image.SCALE_SMOOTH)));

		p1.add(btinsert);//영화등록
		p1.add(btedit);//영화관리
		p1.add(btmanager);//회원관리
		p1.add(btlogout);//로그아웃

		//위치 설정
		p1.setBounds(40, 10, 160, 360);

		this.add(p1);

		btinsert.addActionListener(this);
		btedit.addActionListener(this);
		btmanager.addActionListener(this);
		btlogout.addActionListener(this);

		this.setIconImage(changedImg);//타이틀바의 아이콘 설정
		this.setTitle("관리자 메뉴");//타이틀바의 제목 설정
		this.setSize(250, 420);//Frame 출력 크기 설정
		this.setLocationRelativeTo(null);//Frame 출력 위치 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//타이틀바의 X버튼을 클리하면 종료된다.
		this.setResizable(false);//Frame 사이즈 조절 금지
		this.setVisible(true);//실제로 Frame을 화면에 출력

		p1.requestFocus();//p1에 포커스를 둔다.

	}

	public static void main(String[] args) {
		new Admin_Main();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btinsert)) {//등록 버튼이 클릭되면
			dispose();//현재 창을 종료하고
			new Insert();//MInsert() 창을 새로 생성한다.
		}else if(e.getSource().equals(btedit)) {//영화 관리 버튼이 클릭되면
			dispose();//현재 창을 종료하고
			new Edit();//MEdit() 창을 새로 생성한다.
		}else if(e.getSource().equals(btmanager)) {//회원 관리 버튼이 클릭되면
			dispose();//현재 창을 종료하고
			new Manager();//Manager() 창을 새로 생성한다.
		}else if(e.getSource().equals(btlogout)) {//로그아웃 버튼이 클릭되면
			dispose();//현재 창을 종료하고
			new Login();//Login() 창을 새로 생성한다.
		}

	}

}

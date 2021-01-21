package Users;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import Setting.Bean;
import Setting.DBmgr;

public class Insert extends JFrame implements ActionListener {

	Container c = getContentPane();
	int check = 0;
	String overlap = "";

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JLabel lbl1 = new JLabel("이름",JLabel.RIGHT);
	JLabel lbl2 = new JLabel("아이디",JLabel.RIGHT);
	JLabel lbl3 = new JLabel("비밀번호",JLabel.RIGHT);
	JLabel lbl4 = new JLabel("생년월일",JLabel.RIGHT);
	JLabel lbl5 = new JLabel("년");
	JLabel lbl6 = new JLabel("월");
	JLabel lbl7 = new JLabel("일");
	JLabel lbl8 = new JLabel("비밀번호", JLabel.RIGHT);
	JLabel lbl9 = new JLabel("확인", JLabel.RIGHT);

	JTextField tf1 = new JTextField(); 			//이름
	JTextField tf2 = new JTextField();			//아이디
	JPasswordField pf = new JPasswordField(); 	//비밀번호
	JPasswordField pf1 = new JPasswordField(); 	//비밀번호 확인
	
	JComboBox cb1 = new JComboBox();	// 년
	JComboBox cb2 = new JComboBox();	// 월
	JComboBox cb3 = new JComboBox();	// 일

	JButton btn1 = new JButton("가입");
	JButton btn2 = new JButton("취소");
	JButton btn3 = new JButton("ID확인");	

	JPanel pan1 = new JPanel(new GridLayout(3,2,5,20));
	JPanel pan2 =new JPanel();
	JPanel pan3 = new JPanel();

	Calendar cal = Calendar.getInstance();	// getInstance() 메소드 사용하여 현재 날짜와 시간의 객체를 얻어온다.

	ArrayList<Bean> list = new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Insert() {
		setIconImage(changedImg);

		btn1.setIcon(new ImageIcon(new ImageIcon("resource/icon/join.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn2.setIcon(new ImageIcon(new ImageIcon("resource/icon/cancel.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn3.setIcon(new ImageIcon(new ImageIcon("resource/icon/check.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

		setLayout(null);

		pan1.add(lbl1); pan1.add(tf1);				// 이름
		pan1.add(lbl2); pan1.add(tf2); add(btn3);	// 아이디 ID 중복확인
		pan1.add(lbl3); pan1.add(pf);				// 비밀번호 
		add(lbl8);	add(lbl9); 	add(pf1);			// 비밀번호 확인

		pan2.add(lbl4);	// 생년월일
		pan2.add(cb1); pan2.add(lbl5);	// 년
		pan2.add(cb2); pan2.add(lbl6);	// 월
		pan2.add(cb3); pan2.add(lbl7);	// 일

		pan3.add(btn1);	// 가입	
		pan3.add(btn2);	// 취소

		c.setFont(new Font("나눔바른고딕", Font.BOLD, 12));	// 전체 폰트 적용

		pan1.setBounds(-190, 20, 500, 130);
		pan2.setBounds(-130, 215, 520, 35);
		pan3.setBounds(90, 250, 250, 35);
		btn3.setBounds(315, 70, 110, 27);
		lbl8.setBounds(3, 170, 55, 20);
		lbl9.setBounds(15, 185, 30, 20);
		pf1.setBounds(63, 170, 247, 30);

		add(pan1);
		add(pan2);
		add(pan3);
		
		// 버튼 영역 배경 표시 설정
		btn1.setContentAreaFilled(false); 
		btn2.setContentAreaFilled(false); 
		btn3.setContentAreaFilled(false); 
		
		// 콤보박스 배경 흰색
		cb1.setBackground(Color.WHITE);
		cb2.setBackground(Color.WHITE);
		cb3.setBackground(Color.WHITE);
		
		// 배경 흰색
		c.setBackground(Color.WHITE);
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.WHITE);
		pan3.setBackground(Color.WHITE);

		// 현재년도를 가져온다
		int y = cal.get(cal.YEAR);
	
		// 1900년도부터 2019년도까지 뽑아온다. 뽑아온걸 cb1에 저장한다.	
		for(int i = 1900 ; i <= y; i++ ) {
			cb1.addItem(i);
		}
		
		// 1월부터 12월까지 뽑아온다. 뽑아온걸 cb2에 저장한다.
		for(int i= 1 ; i <= 12 ; i++) {
			cb2.addItem(i);
		}
		
		cb1.setSelectedItem(y);	// 초기값이 빈칸으로 설정
		cb2.setSelectedIndex(0);	// 초기값이 빈칸으로 설정
		dd();
		cb3.setSelectedIndex(0);

		cb1.addItemListener(new ItemListener() {
			@Override
			// 아이템 상태가 선택되었을 때 
			public void itemStateChanged(ItemEvent e) {
				dd();
				cb3.setSelectedIndex(0);
			}
		});

		cb2.addItemListener(new ItemListener() {
			@Override
			// 아이템 상태가 선택되었을 때
			public void itemStateChanged(ItemEvent e) {
				dd();
				cb3.setSelectedIndex(0);
			}
		});

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		// 윈도우 이벤트 등록
		addWindowListener(new WindowAdapter() {
			@Override
		// 윈도우가 닫히려고 할때
			public void windowClosing(WindowEvent e) {
				new Login(); // 로그인창
			}
		});

		setTitle("회원가입");
		setSize(450,330); 		// 창 크기
		setLocationRelativeTo(null);
		setResizable(false); 	// 창크기 불변
		setVisible(true);

	}

	void dd() {
		try {
			cb3.removeAllItems(); // cb3의 모든 항목을 삭제한다.
			// cb1의 선택된 것과 cb2의 선택된 것을 형 변환한다. 컴퓨터가 0~11까지 알기 때문에 -1을 해 월을 구한다. 
			cal.set(Integer.parseInt(cb1.getSelectedItem()+""), Integer.parseInt(cb2.getSelectedItem()+"")-1,1);
			for(int i = 1 ; i <= cal.getActualMaximum(Calendar.DATE); i ++) { // 날짜가 셋팅된 Calender가 가질수 있는 값
				cb3.addItem(i);
			}
		}catch(Exception e) { }
	}
	
	public static void main(String[] args) {
		//new Insert();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// btn1(가입)이 눌렸을 때
		if(e.getSource().equals(btn1)) {
		// check가 0이면 메시지를 띄운다.
			if(check == 0) {
	            JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요!", "메시지", JOptionPane.ERROR_MESSAGE);
	         }
		// tf2 ID가 overlap과 같을 때 
			else if(tf2.getText().equals(overlap)) {
				// tf1가 빈칸 또는 tf2가 빈칸 또는 pf가 빈칸 또는 cb1가 -1로 빈칸 또는 cb2가 -1로 빈칸 또는 cb3 -1로 빈칸일때 메시지를 띄운다
				if(tf1.getText().equals("")||tf2.getText().equals("")||pf.getText().equals("")||cb1.getSelectedIndex()==-1||cb2.getSelectedIndex()==-1||cb3.getSelectedIndex()==-1) {
					
					JOptionPane.showMessageDialog(null, "누락된 항목이 있습니다.", "메시지", JOptionPane.ERROR_MESSAGE);
				
				}else if(!pf.getText().equals(pf1.getText())) {
					JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.", "메시지", JOptionPane.ERROR_MESSAGE);
				}
				// check가 1일 때
				else if(check == 1) {
		            DecimalFormat df = new DecimalFormat("00"); // 두자리 형식으로 만들기위해 쓴 포맷 아래에 월과 일을 표현하기 위해 사용
		            DBmgr.sql = "insert into ulist(u_no, u_id, u_pw, u_name, u_bd, u_point) values(su_no.nextval,'" + tf2.getText() + "','" + pf.getText() + "', '" + tf1.getText() + "','" + (cb1.getSelectedItem() + "-" + df.format(cb2.getSelectedItem()) + "-" + df.format(cb3.getSelectedItem())) + "', 0)";
		            list = mgr.update();

		            JOptionPane.showMessageDialog(null, "가입완료되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);

		            dispose();
		            new Login();
		         }

				// tf2를 저장한 overlap이 중복검사한것이랑 다르고 가입을 누르면 메시지를 띄운다
			}else{
				JOptionPane.showMessageDialog(null, "증복검사한 ID가 다릅니다.", "메시지", JOptionPane.ERROR_MESSAGE);
				btn3.setForeground(Color.BLACK);
			}
			
			// btn3(ID 확인)을 눌렀을 때
		}else if(e.getSource().equals(btn3)) {
			// tf2(ID)가 비었을 때 메시지를 띄우고 텍스트 색을 빨간색으로 변경
			if (tf2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "ID를 입력해주세요", "메시지", JOptionPane.ERROR_MESSAGE);
				btn3.setForeground(Color.RED);
				// tf2(ID)가 4자리 이상이 아닐 때 메시지를 띄운다
			}else if(tf2.getText().length() < 4) {
				JOptionPane.showMessageDialog(null, "아이디를 4자리 이상 작성해주세요!", "메시지", JOptionPane.ERROR_MESSAGE);
				// tf2(ID)가 "admin"일 때 메시지를 띄운다. *admin은 관리자 ID이다. 
			}else if(tf2.getText().equals("admin")) {
				JOptionPane.showMessageDialog(null, "이 ID로는 생성할 수 없습니다.", "메시지", JOptionPane.ERROR_MESSAGE);
				// 위에 조건들이 아닐 때
			}else {
				// DB에 ulist에 u_id가 tf2랑 같은것을 뽑고 list에 저장
				DBmgr.sql = "select * from ulist where u_id = '" + tf2.getText() + "'";
				list = mgr.user();
				try {
					// 0번째부터 비교확인해 DB에 있는것과 같으면 메시지를 띄우고 텍스트는 빨간색으로 변경하고 check는 0으로 한다.
					bean = list.get(0);
					JOptionPane.showMessageDialog(null, "아이디가 중복되었습니다.", "메시지", JOptionPane.ERROR_MESSAGE);
					btn3.setForeground(Color.RED);
					check = 0;
				}catch(Exception e1) {
					// try문이 성립되지 않았을 시 메시지를 띄우고 텍스트는 파란색으로 하고 check는 1로 저장하고 tf2의 내용을 overlap에 저장한다.
					JOptionPane.showMessageDialog(null, "사용가능 ID입니다..", "메시지", JOptionPane.INFORMATION_MESSAGE);
					btn3.setForeground(Color.BLUE);
					check = 1;
					overlap = tf2.getText();
				}
			}
			// btn2(취소)가 눌렸을 때 현재 창은 종료되고 로그인창이 실행된다.
		}else if(e.getSource().equals(btn2)) {
			dispose();
			new Login();

		}
	}
}

package Users;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Setting.Bean;
import Setting.DBmgr;
import Setting.RandomStr;

public class User_Info extends JFrame implements ActionListener {

	private ImageIcon originIcon;
	private Image originImg, changedImg;

	private JButton btOk, btCancle; //확인, 취소버튼

	private JTextField tfPass, tfNewpass, tfNewpassOk; //현재패스워드, 새패스워드, 새패스워드확인 입력창

	private JLabel lbNo1, lbNo2, lbNo3; //회원 정보(이름, 아이디, 생년월일) 담을 라벨

	private JTextField tfOuto, tfOutoIn; //자동입력방지문자, 자동입력방지문자 입력창

	private RandomStr rs = new RandomStr(); //자동입력방지문자 만들 랜덤문자열
	private String a = rs.getStr();

	private String ePass; //DB에 업데이트 하기위해 새 패스워드 getText() 담을 string

	private DBmgr mgr = new DBmgr(); //원하는 값 가져올 함수가 담겨있는 클래스
	private ArrayList<Bean> list =new ArrayList<Bean>(); //sql문으로 가져온 결과값 리스트 저장
	private Bean bean; //sql문 처리, list로 가져온 값 각각 나눠서 저장

	public User_Info() {

		// ImageIcon 객체를  생성한다.
		originIcon = new ImageIcon("resource/icon/pop.png");
		// ImageIcon에서 Image를 추출한다.
		originImg = originIcon.getImage();
		// 추출된 Image의 크기를 조절해서 새로운 Image객체를 생성한다.
		changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		setIconImage(changedImg);

		JLabel lbName = new JLabel("이름");
		JLabel lbName2 = new JLabel(); //해당 유저의 이름 담을 라벨

		JLabel lbId = new JLabel("아이디");
		JLabel lbId2 = new JLabel(); //해당 유저의 아이디 담을 라벨

		JLabel lbPass = new JLabel("현재 패스워드");
		tfPass = new JTextField(); //현재 password 입력창
		tfPass.addActionListener(this);

		JLabel lbNewpass = new JLabel("새 패스워드");
		tfNewpass = new JTextField(); //새 password 입력창
		tfNewpass.addActionListener(this);
		lbNo1 = new JLabel (); //새 비밀번호 틀렸을 때 나타날 아이콘

		JLabel lbNewpassOk = new JLabel("새 패스워드 확인");  
		tfNewpassOk = new JTextField(); //새 password 확인
		tfNewpassOk.addActionListener(this);
		lbNo2 = new JLabel (); //새 비밀번호확인 틀렸을 때 나타날 아이콘

		JLabel lbBirth = new JLabel("생년월일"); 			
		JLabel lbBirth2 = new JLabel(); // 해당 유저의 생년월일 담을 라벨

		tfOuto = new JTextField(a); //랜덤문자열 저장한 a를 띄움
		tfOuto.setEditable(false); //건들지 못하게하기
		tfOuto.addActionListener(this);

		tfOutoIn = new JTextField(); //자동입력 방지문자 입력하는 부분
		tfOutoIn.addActionListener(this);
		lbNo3 = new JLabel (); //자동입력방지문자 틀렸을 때 나타날 아이콘

		ImageIcon imgOk = new ImageIcon("resource\\Icon\\checked.png"); //확인버튼 아이콘
		btOk = new JButton("확인",imgOk);
		btOk.setBackground(Color.WHITE);
		btOk.addActionListener(this);

		ImageIcon imgCancle = new ImageIcon("resource\\Icon\\exit.png"); //취소버튼 아이콘
		btCancle = new JButton("취소",imgCancle);
		btCancle.setBackground(Color.WHITE);
		btCancle.addActionListener(this);

		//로그인한 해당 유저의 정보를 각 라벨에 저장
		String UName = Login.name;
		lbName2.setText(UName);

		String UId = Login.id;
		lbId2.setText(UId);

		String UBd = Login.bd;
		lbBirth2.setText(UBd);

		JPanel p1 = new JPanel(new GridLayout(8,2,10,10));
		p1.setBackground(Color.WHITE);
		p1.add(lbName);			p1.add(lbName2);
		p1.add(lbId);			p1.add(lbId2);
		p1.add(lbBirth);		p1.add(lbBirth2);
		p1.add(lbPass);			p1.add(tfPass);
		p1.add(lbNewpass);		p1.add(tfNewpass);
		p1.add(lbNewpassOk);	p1.add(tfNewpassOk);
		p1.add(tfOuto);			p1.add(tfOutoIn);

		JPanel p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.add(btOk);	p2.add(btCancle);

		JPanel p3 = new JPanel();
		p3.setLayout(null);
		p3.setBackground(Color.WHITE);
		p3.add(lbNo1); //새 비밀번호 틀렸을 때 나타날 아이콘
		p3.add(lbNo2); //새 비밀번호확인 틀렸을 때 나타날 아이콘
		p3.add(lbNo3); //자동입력방지문자 틀렸을 때 나타날 아이콘
		p3.add(p1);
		p3.add(p2);

		lbNo1.setBounds(255, 110, 100, 100);
		lbNo2.setBounds(255, 142, 100, 100);
		lbNo3.setBounds(255, 176, 15, 100); 
		p1.setBounds(20,20,230,250);
		p2.setBounds(20,280,250,200);
		p3.setBounds(0, 10, 250, 500);

		add(p3);

		setTitle("회원정보 수정");
		setSize(300, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		tfPass.requestFocus();

		this.addWindowListener(new WindowAdapter() {@Override
			public void windowClosing(WindowEvent arg0) {
			dispose();
			new Users_Main();
		}
		});
	}

	public static void main(String[] args) {
		new User_Info();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(tfPass)||e.getSource().equals(tfNewpass)||e.getSource().equals(tfNewpassOk)||e.getSource().equals(tfOutoIn)||e.getSource().equals(btOk)) {
			checkPass();
		}else if(e.getSource().equals(btCancle)){
			dispose();
			new Users_Main();
		}
	}

	public void clear() { //무언가를 틀렸을 때 입력창 초기화
		tfPass.setText("");
		tfNewpass.setText("");
		tfNewpassOk.setText("");
		tfOutoIn.setText("");
		rs = new RandomStr();
		a = rs.getStr();
		tfOuto.setText(a);
		tfPass.requestFocus();
	}

	public void checkPass() {

		DBmgr.sql = "select * from ulist where u_no = '"+Login.no+"'";
		list = mgr.user();
		bean = list.get(0);

		//빈칸이 존재하면 다이얼로그 메시지 띄우기
		if(tfPass.getText().equals("")||tfNewpass.getText().equals("")||tfNewpassOk.getText().equals("")||tfOutoIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "메시지", JOptionPane.ERROR_MESSAGE);
			lbNo1.setIcon(new ImageIcon());
			lbNo2.setIcon(new ImageIcon());
			lbNo3.setIcon(new ImageIcon());
			clear();

		}else { //빈칸이 존재하지 않을때

			//현재 비밀번호가 틀리면 다이얼로그 메시지 띄우기
			if(!tfPass.getText().equals(bean.getUpw())) {
				JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.", "메시지", JOptionPane.ERROR_MESSAGE);
				lbNo1.setIcon(new ImageIcon());
				lbNo2.setIcon(new ImageIcon());
				lbNo3.setIcon(new ImageIcon());
				clear();

			} else { //현재 비밀번호가 맞을때

				//새 패스워드와 새 패스워드 확인, 자동입력방문자가 전부 틀릴때 x아이콘 표시
				if(!tfNewpass.getText().equals(tfNewpassOk.getText())&&!tfOutoIn.getText().equals(a)) {
					lbNo1.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					lbNo2.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					lbNo3.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					clear();
				}

				//새 패스워드와 새 패스워드가 틀리면 x아이콘 표시
				else if(!tfNewpass.getText().equals(tfNewpassOk.getText())) {
					lbNo3.setIcon(new ImageIcon());
					lbNo1.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					lbNo2.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					clear();

					//자동입력방지문자가 틀리면 x아이콘 표시	
				} else if(!tfOutoIn.getText().equals(a)) {
					lbNo1.setIcon(new ImageIcon());
					lbNo2.setIcon(new ImageIcon());
					lbNo3.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					clear();

					//현재 패스워드가 같고, 새 패스워드와 새 패스워드확인, 자동입력방지 문자를 모두 올바르게 입력하면 해당 유저의 패스워드 수정
				} else {
					if(tfNewpassOk.getText().equals(bean.getUpw())&&tfNewpass.getText().equals(bean.getUpw())) {
						lbNo1.setIcon(new ImageIcon());
						lbNo2.setIcon(new ImageIcon());
						lbNo3.setIcon(new ImageIcon());
						JOptionPane.showMessageDialog(null, "입력한 비밀번호가 현재 비밀번호와 동일합니다\n새로운 비밀번호를 입력해주세요.");
						clear();

					}else {
						ePass = tfNewpass.getText();
						DBmgr.sql = "update ulist set u_pw = + '" + ePass + "' where u_id = '"+Login.id+"'";
						list = mgr.update();
						JOptionPane.showMessageDialog(null, "정보가 수정되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
						dispose(); //패스워드 변경 후 종료
						new Login();

					}
				}
			}
		}
	}
}

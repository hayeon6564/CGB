package Users;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import Setting.Bean;
import Setting.DBmgr;
import Admin.Staff;
import Chat.ClientFrameThread;


public class Users_Main extends JFrame implements ActionListener {
	public static int ch;
	static int mno, mprice;
	static String mgenre, mage;
	int hap = 0;
	String m = "SF";
	private JPanel pan;

	Container c = getContentPane();

	Calendar cal = Calendar.getInstance();	// getInstance() 메소드 사용하여 현재 날짜와 시간의 객체를 얻어온다.

	JLabel img2 = new JLabel(); //pan

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JLabel lbl1 = new JLabel("환영합니다. 이기민님의 총 누적 포인트는 14210P입니다.");
	JLabel lbl2 = new JLabel("영화명:");  //pan3
	JLabel lbl3 = new JLabel("관람연령:"); //pan3
	JLabel lbl4 = new JLabel("가       격:"); //pan3
	JLabel lbl5 = new JLabel("개봉날짜:"); //pan3
	JLabel lbl6 = new JLabel("상영시간:"); //pan3

	JTextField tf1 = new JTextField(20); //pan3
	JTextField tf2 = new JTextField(); //pan3
	JTextField tf3 = new JTextField(15); //pan3
	JTextField tf4 = new JTextField(); //pan3
	JTextField tf5 = new JTextField(); //pan3

	JButton btn1 = new JButton("구매내역"); // pan1
	JButton btn2 = new JButton("장바구니"); //pan1
	JButton btn3 = new JButton("인기 영화"); // pan1
	JButton btn4 = new JButton("회원정보 수정"); // pan1
	JButton btn5 = new JButton("Logout"); // pan1

	JButton btn6 = new JButton("SF"); // pan2
	JButton btn7 = new JButton("공포"); // pan2
	JButton btn8 = new JButton("로맨스"); // pan2
	JButton btn9 = new JButton("애니메이션"); // pan2
	JButton btn10 = new JButton("액션"); // pan2
	JButton btn11 = new JButton("코미디"); // pan2
	JButton btn12 = new JButton("출연진 정보"); //pan4
	JButton btn13 = new JButton("영화 담기"); //pan4
	JButton btn14 = new JButton("구매하기"); //pan4
	JButton btn15 = new JButton("채팅"); 

	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel(new GridLayout(6,1,0,-1));
	JPanel pan3 = new JPanel(new GridLayout(2,2,-10,10));
	JPanel pan4 = new JPanel();
	JPanel pan5 = new JPanel();
	JPanel pan6 = new JPanel(new GridLayout(2,2,-10,10));

	JScrollPane jsp = new JScrollPane();

	JLabel lbl[] = new JLabel[1000];
	JLabel mname[] = new JLabel[1000];
	JLabel img[] = new JLabel[1000];

	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");	// 날짜를 원하는 형식으로 출력하기

	ArrayList<Bean> list, list2, list3;
	DBmgr mgr =new DBmgr();
	Bean bean;

	public Users_Main() {
		setIconImage(changedImg);

		btn1.setIcon(new ImageIcon(new ImageIcon("resource/icon/order.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn2.setIcon(new ImageIcon(new ImageIcon("resource/icon/basket.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn3.setIcon(new ImageIcon(new ImageIcon("resource/icon/rank.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn4.setIcon(new ImageIcon(new ImageIcon("resource/icon/memberedit.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn5.setIcon(new ImageIcon(new ImageIcon("resource/icon/logout.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn12.setIcon(new ImageIcon(new ImageIcon("resource/icon/information.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn13.setIcon(new ImageIcon(new ImageIcon("resource/icon/add.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn14.setIcon(new ImageIcon(new ImageIcon("resource/icon/purse.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn15.setIcon(new ImageIcon(new ImageIcon("resource/icon/chat.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

		img2.setBorder(BorderFactory.createLineBorder(Color.BLACK));	// 영화 포스터 테두리
		tf1.setEditable(false); // 영화명 tf
		tf2.setEditable(false); // 관람연령 tf
		tf3.setEditable(false); // 가격 tf
		tf4.setEditable(false); // 개봉날짜 tf
		tf5.setEditable(false); // 상영시간 ta

		setLayout(null);

		pan1.add(btn1); // 구매내역
		pan1.add(btn2); // 장바구니
		pan1.add(btn3); // 인기영화 Top5
		pan1.add(btn4); // 회원정보수정
		pan1.add(btn5); // Logout

		// 폰트 설정
		c.setFont(new Font("나눔바른고딕", Font.BOLD,12));
		lbl1.setFont(new Font("나눔바른고딕", Font.BOLD,17));		
		btn9.setFont(new Font("나눔바른고딕",Font.BOLD,12));
		btn15.setFont(new Font("나눔바른고딕",Font.BOLD,14));

		pan2.add(btn6); // SF
		pan2.add(btn7); // 공포
		pan2.add(btn8); // 로맨스
		pan2.add(btn9); // 애니메이션
		pan2.add(btn10); // 액션
		pan2.add(btn11); // 코미디

		add(btn15);	// 채팅

		lbl1.setBounds(5, 5, 600, 30);
		pan1.setBounds(0, 40, 600, 35);
		pan2.setBounds(5, 80, 90, 250);
		jsp.setBounds(100, 80, 650, 450);
		btn15.setBounds(5, 500, 90, 30);

		add(lbl1);
		add(pan1);
		add(jsp);
		add(pan2);

		pan5.add(lbl2); pan5.add(tf1); // 영화명: 
		pan3.add(lbl3); pan3.add(tf2); // 관람연령: 
		pan3.add(lbl4); pan3.add(tf3); // 가격: 
		pan6.add(lbl5); pan6.add(tf4); // 개봉날짜: 
		pan6.add(lbl6); pan6.add(tf5); // 줄거리: 

		pan4.add(btn12); // 출연진 정보
		pan4.add(btn13); // 장바구니에 담기
		pan4.add(btn14); // 구매하기

		img2.setBounds(850, 80, 220, 220); 
		pan5.setBounds(750, 330, 400, 50);	
		pan3.setBounds(800, 380, 170, 50);	
		pan4.setBounds(770, 490, 400, 35);	
		pan6.setBounds(970, 380, 170, 50);	

		add(img2);	// 포스터
		add(pan3);	// 관람연령 가격
		add(pan4);	// 출연진 정보 장바구니에 담기 구매하기
		add(pan5);	// 영화명
		add(pan6);	// 개봉날짜 줄거리

		// ulist에서 u_id에서 Loing.id와 같은 것을 뽑아온다.
		DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
		list = mgr.user();
		bean = list.get(0);

		lbl1.setText("환영합니다. " + bean.getUname() + "님의 총 누적 포인트는 " + bean.getUpoint() + "P입니다.");

		set(); // 영화 리스트와 관련된 메소드

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		btn7.addActionListener(this);
		btn8.addActionListener(this);
		btn9.addActionListener(this);
		btn10.addActionListener(this);
		btn11.addActionListener(this);
		btn12.addActionListener(this);
		btn13.addActionListener(this);
		btn14.addActionListener(this);
		btn15.addActionListener(this);

		// 버튼 영역 채우기
		btn1.setContentAreaFilled(false); 
		btn2.setContentAreaFilled(false); 
		btn3.setContentAreaFilled(false); 
		btn4.setContentAreaFilled(false); 
		btn5.setContentAreaFilled(false); 
		btn6.setContentAreaFilled(true); 
		btn7.setContentAreaFilled(true); 
		btn8.setContentAreaFilled(true); 
		btn9.setContentAreaFilled(true); 
		btn10.setContentAreaFilled(true); 
		btn11.setContentAreaFilled(true); 
		btn12.setContentAreaFilled(true); 
		btn13.setContentAreaFilled(true); 
		btn14.setContentAreaFilled(true); 
		btn15.setContentAreaFilled(false); 

		// 텍스트 색상
		btn6.setForeground(Color.WHITE);	
		btn7.setForeground(Color.WHITE);	
		btn8.setForeground(Color.WHITE);	
		btn9.setForeground(Color.WHITE);	
		btn10.setForeground(Color.WHITE);	
		btn11.setForeground(Color.WHITE);	
		btn12.setForeground(Color.WHITE);	
		btn13.setForeground(Color.WHITE);	
		btn14.setForeground(Color.WHITE);	

		// 버튼 색 변경
		btn6.setBackground(new Color(69, 68, 66));
		btn7.setBackground(new Color(69, 68, 66));
		btn8.setBackground(new Color(69, 68, 66));
		btn9.setBackground(new Color(69, 68, 66));
		btn10.setBackground(new Color(69, 68, 66));
		btn11.setBackground(new Color(69, 68, 66));
		btn12.setBackground(new Color(69, 68, 66));
		btn13.setBackground(new Color(69, 68, 66));
		btn14.setBackground(new Color(69, 68, 66));

		// 배경색 흰색
		c.setBackground(Color.WHITE);
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.WHITE);
		pan3.setBackground(Color.WHITE);
		pan4.setBackground(Color.WHITE);
		pan5.setBackground(Color.WHITE);
		pan6.setBackground(Color.WHITE);
		pan.setBackground(Color.WHITE);

		// tf색 흰색
		tf1.setBackground(Color.WHITE);
		tf2.setBackground(Color.WHITE);
		tf3.setBackground(Color.WHITE);
		tf4.setBackground(Color.WHITE);
		tf5.setBackground(Color.WHITE);

		setTitle("CGB");
		setSize(780, 600); // 780,600 
		setLocation(250, 100);	// 창 위치 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 윈도우창 종료시 프로그램 종료
		setResizable(false); // 창크기 불변
		setVisible(true);

	}
	// 영화 리스트와 관련된 메소드
	void set() {
		pan = new JPanel();
		pan.setLayout(null);

		int size = 0; // 목록에 있는 포스터의 y
		int size2 = 0;	// 목록에 있는 포스터의 x
		int count = 0;	// 포스터 개수
		int imgname = 190; // 영화 제목 height

		// mlist에 m_genre와 m이 같은 것을 뽑아 list에 저장한다.
		DBmgr.sql = "select * from mlist where m_genre = '" + m + "'";
		list = mgr.movie();

		for(int k =0 ; k < list.size(); k++) {
			bean = list.get(k);	// bean에 0번째부터 각각 저장

			lbl[k] = new JLabel(bean.getMname());
			img[k] = new JLabel(new ImageIcon(new ImageIcon("DBFiles\\image\\" + bean.getMname() + ".jpg").getImage().getScaledInstance(190, 190, Image.SCALE_SMOOTH)));
			mname[k] = new JLabel(bean.getMname(),JLabel.CENTER);

			img[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));	// 포스터 목록 이미지 테두리 블랙

			mname[k].setBounds(size2, imgname, 190, 30);	// 제목
			img[k].setBounds(size2, size, 190, 190);	// 포스터 목록 이미지 테두리 크기
			count+=1;

			pan.add(img[k]);
			pan.add(mname[k]);

			if(count == 3) { // 한줄에 3개가 되면 다음줄로 넘어감
				size += 230; // 영화들 폭 x
				size2 = 0;	// 영화들 y
				count = 0;	// 영화 개수
				imgname += 230;	// 제목 y
				// 3개가 아닐때
			}else { 
				size2 += 220;
			}

			img[k].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// mlist에서 m_genre가 m(SF, 액션, 로맨스...)인것을 뽑아와 list에 저장
					DBmgr.sql = "select * from mlist where m_genre = '" + m + "'";
					list = mgr.movie();

					for(int j = 0 ; j < list.size(); j++) {
						// 포스터를 눌렀을때 list의 0번부터 가져오고 mno, mgenre, mprice를 가져오고 bean에 각각 저장하고 창 크기를 설정한다.
						if(e.getSource().equals(img[j])) {
							bean = list.get(j);
							mno = bean.getMno();
							mgenre = bean.getMgenre();
							mprice = bean.getMprice();
							mage = bean.getMage();

							setSize(1200, 600);
							// bean.getMname()과 같은 .jpg 파일을 가져오고 tf1부터 tf5까지 DB에 있는 내용으로 채운다.
							img2.setIcon(new ImageIcon(new ImageIcon("DBFiles\\image\\" + bean.getMname() + ".jpg").getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH)));
							tf1.setText(bean.getMname());
							tf2.setText(bean.getMage());
							tf3.setText(bean.getMprice()+"");
							tf4.setText(bean.getMdate());
							tf5.setText(bean.getMtime());

							amount(); // 등급에 따라 할인에 관련된 메소드 
						}
					}
				}
			});
		}
		// list사이즈를 3으로 나머지 연산한 값이 0이 아닐 때
		if(list.size()%3!=0) {
			size += 230;	// 영화들 폭x
		}
		pan.setPreferredSize(new Dimension(630,size));	// 스크롤팬의 전체 사이즈 지정
		jsp.setViewportView(pan);	//스크롤팬 위에 pan을 올린다
	}

	// 등급에 따라 할인에 관련된 메소드 
	void amount() {
		//	ulist에서 u_id가 Login.id인 것을 뽑고 list에 저장하고 bean에 각각 저장
		DBmgr.sql = "select * from ulist where u_id = '"  + Login.id + "'";
		list = mgr.user();
		bean = list.get(0);
		//tf3(가격)을 형변환해 hap에 저장
		hap = Integer.parseInt(tf3.getText());
		// DB에 저장된 등급이 VIP일 때 
		if(bean.getUgrade().equals("VIP")) {
			hap = (int) (hap * 0.97);
			// DB에 저장된 등급이 VVIP일 때
		}else if(bean.getUgrade().equals("VVIP")) {
			hap = (int) (hap * 0.95);
			// DB에 저장된 등급이 SVIP일 때
		}else if(bean.getUgrade().equals("SVIP")) {
			hap = (int) (hap * 0.9);
		}
	}
	// 포인트, 등급과 관련된 메소드
	void gg() {
		int point;
		point = (int) (Integer.parseInt(tf3.getText()) * 0.05);

		// ulist에 u_id가 Login.id인 것을 뽑아 u_point를 위에서 작업한 point로 바꾸고 list에 저장한다
		DBmgr.sql = "update ulist set u_point = u_point + '" + point +"' where u_id = '" + Login.id + "'";
		list = mgr.update();

		DBmgr.sql = "insert into olist(o_no, o_date, u_no, m_no, o_genre, o_price) values(so_no.nextval, '" + sf.format(System.currentTimeMillis()) + "', '" + Login.no + "', '" + mno + "' ,'" + m + "', '" + tf3.getText() + "')";
		list = mgr.update();

		JOptionPane.showMessageDialog(null, "구매되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);

		int sum = 0;
		DBmgr.sql = "select * from olist where u_no = '" + Login.no + "'";
		list = mgr.orderlist();

		for(int i= 0;i < list.size() ; i++) {
			bean = list.get(i);
			sum = sum + bean.getOprice(); //누적금액
		}
		//ulist에 u_id가 Login.id인 것을 뽑아 list에 저장하고 bean에 각각 저장한다
		DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
		list = mgr.user();
		bean = list.get(0);

		String grade = bean.getUgrade();
		if(sum >= 300000) {	// SVIP 300,000 <= sum
			grade = "SVIP";
		}else if(sum >= 200000) {	// VVIP(200,000 <= sum)
			grade = "VVIP";
		}else if(sum >= 100000) {	// VIP(100,000 <= sum)
			grade = "VIP";
		}
		if(bean.getUgrade().equals(grade)) {

		} else {
			JOptionPane.showMessageDialog(null, "축하합니다!\n회원님 등급이"+ grade +"로 승급하셨습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		}
		DBmgr.sql = "update ulist set u_grade ='" + grade + "' where u_id = '" + Login.id + "'";
		list = mgr.update();

		dispose();
		new Users_Main();
	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// btn6일때 m에 "SF"를 저장하고 창 크기를 설정하고 영화 리스트와 관련된 메소드를 실행한다.
		if(e.getSource().equals(btn6)) {
			m = "SF";
			setSize(780, 600);
			set();
			// btn7일때 m에 "공포"를 저장하고 창 크기를 설정하고 영화 리스트와 관련된 메소드를 실행한다.
		}else if(e.getSource().equals(btn7)) {
			m = "공포";
			setSize(780, 600);
			set();
			// btn8일때 m에 "로맨스"를 저장하고 창 크기를 설정하고 영화 리스트와 관련된 메소드를 실행한다.
		}else if(e.getSource().equals(btn8)) {
			m = "로맨스";
			setSize(780, 600);
			set();
			// btn9일때 m에 "애니메이션"를 저장하고 창 크기를 설정하고 영화 리스트와 관련된 메소드를 실행한다.
		}else if(e.getSource().equals(btn9)) {
			m = "애니메이션";
			setSize(780, 600);
			set();
			// btn10일때 m에 "액션"를 저장하고 창 크기를 설정하고 영화 리스트와 관련된 메소드를 실행한다.
		}else if(e.getSource().equals(btn10)) {
			m = "액션";
			setSize(780, 600);
			set();
			// btn11일때 m에 "코미디"를 저장하고 창 크기를 설정하고 영화 리스트와 관련된 메소드를 실행한다.
		}else if(e.getSource().equals(btn11)) {
			m = "코미디";
			setSize(780, 600);
			set();
			// btn5가 눌렸을 때 현재창은 종료되고 로그인 창이 실행된다
		}else  if(e.getSource().equals(btn5)) {
			dispose();
			new Login();
			// btn2가 눌렸을 때 현재창위에 장바구니 창이 실행된다
		}else if (e.getSource().equals(btn2)) {
			new Basket();
			// btn1가 눌렸을 때 현재창 위에 주문내역 창이 실행된다
		}else if(e.getSource().equals(btn1)) {
			ch = 1;
			new Orderlist();
			// btn3가 눌렸을 때 현재창은 종료되고 인기영화 창이 실행된다
		}else if(e.getSource().equals(btn3)) {
			dispose();
			new Chart();
			// btn4가 눌렸을 때 현재창은 종료되고 회원정보수정 창이 실행된다
		}else if(e.getSource().equals(btn4)) {
			dispose();
			new User_Info();
			// btn12가 눌렸을 때  MStaff 창이 실행되고 pan1에 포커스가 맞춰진다
		}else if(e.getSource().equals(btn12)) {
			new Staff("출연진 정보", this, mno);
			setVisible(false);
			pan1.requestFocus();
			// btn13가 눌렸을 때 mlist에서 m_name이 tf1과 같은 것을 뽑고 list에 저장하고 저장한것들중에 DB에 있는 영화번호를 mno1에 저장한다
		}else if(e.getSource().equals(btn13)) {	// 장바구니에 담기
			DBmgr.sql = "select * from mlist where m_name = '" + tf1.getText() + "'";
			list = mgr.movie();
			bean = list.get(0);
			// DB에서 뽑아온 영화넘버를 스트링으로 변환해 mno1에 저장
			String mno1 = bean.getMno()+"";
			// 영화가 장바구니에 담겨있는지를 확인할려고 만들었다
			int c = 0;
			// ulist에서 u_id가 Login.id와 같은 것을 뽑아와 list3에 저장한 뒤 bean에 각각 저장
			DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
			list3 = mgr.user();
			bean = list3.get(0);

			String birth = bean.getUbd();	//DB에 있는 생년월일을 birth에 저장한디
			String ff[] = birth.split("-");	// -을 기준으로 문자열 자르는 것
			String uyear = ff[0];	// 0 일 때 년도가 뽑혀 나온다, 1일 때 월이 뽑혀 나온다, 2일 때 일이 뽑혀나온다
			int tyear = cal.get(cal.YEAR);	// 현재년도를 가져온다 

			if(mage.equals("청소년 관람불가")) {

				int adult = tyear - Integer.parseInt(uyear);
				System.out.println(adult);

				if(adult < 19) {

					JOptionPane.showMessageDialog(null, "청소년 관람 불가 영화입니다!" + "\n장바구니에 추가하 실 수 없습니다!", "메시지", JOptionPane.ERROR_MESSAGE);

				}
				else {
					// shoplist에 dptj u_no와 Login.no가 같은 것을 뽑아 list2에 저장한다
					DBmgr.sql = "select * from shoplist where u_no = '" + Login.no + "'";
					list2 = mgr.shoplist();

					for (int i = 0; i < list2.size(); i++) {
						bean = list2.get(i);

						String mno2 = bean.getMno()+"";
						System.out.println(mno1);
						System.out.println(mno2);
						// mon1과 mno2가 같으면 c = 1이고 메시지를 띄운다
						if(mno1.equals(mno2)) {
							c = 1;
							JOptionPane.showMessageDialog(null, "장바구니에 이미 담겨있는 영화입니다.", "메시지", JOptionPane.ERROR_MESSAGE);
						}
					}

					if(c == 0) {
						DBmgr.sql = "insert into shoplist(s_no, u_no, m_no) values(ss_no.nextval, '" + Login.no + "', '" + mno + "')";
						list = mgr.update();
						JOptionPane.showMessageDialog(null, "장바구니에 영화가 담겼습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}else {
				// shoplist에 dptj u_no와 Login.no가 같은 것을 뽑아 list2에 저장한다
				DBmgr.sql = "select * from shoplist where u_no = '" + Login.no + "'";
				list2 = mgr.shoplist();

				for (int i = 0; i < list2.size(); i++) {
					bean = list2.get(i);

					String mno2 = bean.getMno()+"";
					System.out.println(mno1);
					System.out.println(mno2);
					// mon1과 mno2가 같으면 c = 1이고 메시지를 띄운다
					if(mno1.equals(mno2)) {
						c = 1;
						JOptionPane.showMessageDialog(null, "장바구니에 이미 담겨있는 영화입니다.", "메시지", JOptionPane.ERROR_MESSAGE);
					}
				}

				if(c == 0) {
					DBmgr.sql = "insert into shoplist(s_no, u_no, m_no) values(ss_no.nextval, '" + Login.no + "', '" + mno + "')";
					list = mgr.update();
					JOptionPane.showMessageDialog(null, "장바구니에 영화가 담겼습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			// btn14가 눌렸을 때 
		}else if(e.getSource().equals(btn14)) {	// 구매하기
			// ulist에 u_id와 Login.id가 같으면 뽑아 list에 저장한다
			DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
			list = mgr.user();
			bean = list.get(0);

			String birth = bean.getUbd();	//DB에 있는 생년월일을 birth에 저장한디
			String ff[] = birth.split("-");	// -을 기준으로 문자열 자르는 것
			String uyear = ff[0];	// 0 일 때 년도가 뽑혀 나온다, 1일 때 월이 뽑혀 나온다, 2일 때 일이 뽑혀나온다

			int tyear = cal.get(cal.YEAR);	// 현재년도를 가져온다 

			if(mage.equals("청소년 관람불가")) {

				int adult = tyear - Integer.parseInt(uyear);	// 현재년도 - 고객의 년도

				if(adult < 19) {

					JOptionPane.showMessageDialog(null, "청소년 관람 불가 영화입니다!" + "\n구매하실 수 없습니다!", "메시지", JOptionPane.ERROR_MESSAGE);

				}
				//	형 변환한 tf3이 DB에 저장된 포인트보다 적거나 같을 때
			}else if(Integer.parseInt(tf3.getText())<=bean.getUpoint()) {

				int ok = JOptionPane.showConfirmDialog(null, "회원님의 총 포인트 : " + bean.getUpoint() + "\n할인된 가격 : " + hap +"\n포인트로 결제하시겠습니까?\n(NO 클릭 시 현금결제가 됩니다.)","결제수단",JOptionPane.YES_NO_CANCEL_OPTION);

				if(ok == 0) {
					// ulist에 u_id가 Login.id인 것을 뽑아 u_point를  tf3으로 바꾸고 list에 저장한다
					DBmgr.sql = "update ulist set u_point = u_point - '" + tf3.getText() +"' where u_id = '" + Login.id + "'";
					list = mgr.update();
					// ulist에서 u_id가 Login.id인것을 뽑아 list에 저장한다
					DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
					list = mgr.user();
					bean = list.get(0);
					JOptionPane.showMessageDialog(null, "포인트로 결제 완료되었습니다.\n남은 포인트 : " + bean.getUpoint(), "메시지", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Users_Main();
				}else if(ok == JOptionPane.NO_OPTION){
					//NO가 눌렸을 때 현금결제
					gg();
				}else if(ok == JOptionPane.CANCEL_OPTION) {
					// CANCEL을 눌렀을 때 메시지를 띄우고 현재창은 종료되고 Main()창이 뜬다(새로고침)
					JOptionPane.showMessageDialog(null, "구매 취소 되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Users_Main();
				}
			}else {
				gg();
			}
			// btn15가 눌렸을 때 ClientFrameThread()가 실행된다.
		}else if(e.getSource().equals(btn15)) {
			new ClientFrameThread();
		}
	}
}

package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import Setting.Bean;
import Setting.DBmgr;

public class Insert extends JFrame implements ActionListener {

	static String insert, mname;

	Container c = getContentPane();

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JLabel lbposter = new JLabel();

	JLabel lbgenre = new JLabel("장르");
	JLabel lbage = new JLabel("관람 연령");
	JLabel lbname = new JLabel("영화명");
	JLabel lbprice = new JLabel("가격");

	JLabel lbdate = new JLabel("개봉 날짜");
	JLabel lbtime = new JLabel("상영 시간");
	JLabel lbminute = new JLabel("분");

	JTextField tfname = new JTextField(15);
	JTextField tfprice = new JTextField(15);
	JTextField tftime = new JTextField(10);

	String cgenre[] = {"SF","공포","로맨스","애니메이션","액션","코미디"};
	String cage[] = {"전체 관람가", "12세 관람가", "15세 관람가","청소년 관람불가"};
	JComboBox cbgenre = new JComboBox(cgenre);
	JComboBox cbage = new JComboBox(cage);

	JLabel lbyear = new JLabel("년");
	JLabel lbmonth = new JLabel("월");
	JLabel lbday = new JLabel("일");

	JComboBox cbyear = new JComboBox();
	JComboBox cbmonth = new JComboBox();
	JComboBox cbday = new JComboBox();

	JButton btposter = new JButton("포스터 등록");
	JButton btsave = new JButton("저장");
	JButton btcancel = new JButton("취소");

	JPanel p1 = new JPanel(new GridLayout(4,2,-80,8));
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel(new GridLayout(1, 1));
	JPanel p5 = new JPanel(new GridLayout(1, 2,-60, 8));

	Calendar cal = Calendar.getInstance();

	ArrayList<Bean> list =new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Insert() {

		this.setLayout(null);

		//콤보박스 설정
		cbgenre.setSelectedIndex(-1);
		cbage.setSelectedIndex(-1);

		//배경색 설정
		c.setBackground(Color.WHITE);
		p1.setBackground(Color.WHITE);
		p2.setBackground(Color.WHITE);
		p3.setBackground(Color.WHITE);
		p4.setBackground(Color.WHITE);
		p5.setBackground(Color.WHITE);
		cbgenre.setBackground(Color.WHITE);
		cbage.setBackground(Color.WHITE);
		cbyear.setBackground(Color.WHITE);
		cbmonth.setBackground(Color.WHITE);
		cbday.setBackground(Color.WHITE);
		btposter.setBackground(new Color(69, 68, 66));
		btsave.setBackground(new Color(69, 68, 66));
		btcancel.setBackground(new Color(69, 68, 66));

		//글자색 설정
		btposter.setForeground(Color.WHITE);
		btsave.setForeground(Color.WHITE);
		btcancel.setForeground(Color.WHITE);

		//전체 폰트 설정
		c.setFont(new Font("나눔바른고딕", Font.BOLD, 12));

		//아이콘 삽입과 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정 
		btposter.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btsave.setIcon(new ImageIcon(new ImageIcon("resource/icon/save.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btcancel.setIcon(new ImageIcon(new ImageIcon("resource/icon/mcancel.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

		//라벨에 테두리 설정
		lbposter.setBorder(new LineBorder(Color.BLACK));

		p1.add(lbgenre); p1.add(cbgenre);//장르
		p1.add(lbage); p1.add(cbage);//관람연령
		p1.add(lbname); p1.add(tfname);//영화이름
		p1.add(lbprice); p1.add(tfprice);//영화가격

		p4.add(lbdate);//개봉날짜
		p3.add(cbyear); p3.add(lbyear);//년
		p3.add(cbmonth); p3.add(lbmonth);//월
		p3.add(cbday); p3.add(lbday);//일

		p5.add(lbtime); p5.add(tftime);//상영시간

		p2.add(btsave);//저장
		p2.add(btcancel);//취소

		//위치 지정
		p1.setBounds(15,15,250,130);
		p4.setBounds(15,148,60,30);
		p3.setBounds(90, 148, 200, 30);
		p5.setBounds(15,190,230,25);
		lbminute.setBounds(250,190,20,25);
		lbposter.setBounds(320,15,150,150);
		btposter.setBounds(320,170,150,35);
		p2.setBounds(35,250,400,35);

		this.add(p1);
		this.add(p4);
		this.add(p3);
		this.add(p5);
		this.add(lbminute);
		this.add(lbposter);
		this.add(btposter);
		this.add(p2);

		//년,월,일 콤보박스 설정
		int y = cal.get(cal.YEAR);//오늘년도 가져오기
		for(int i = 1900 ; i <= y;i++ ) {//1900년부터 오늘년도 까지로 설정
			cbyear.addItem(i);
		}
		for(int i= 1 ; i <= 12 ; i++) {//1월부터 12월까지로 설정
			cbmonth.addItem(i);
		}
		
		//콤보박스 설정
		cbyear.setSelectedItem(y);
		cbmonth.setSelectedIndex(0);
		dateset();
		cbday.setSelectedIndex(0);
		

		cbyear.addItemListener(new ItemListener() {//몇년도가 선택되었는지 확인하고
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				dateset();//dateset()실행
			}
		});
		cbmonth.addItemListener(new ItemListener() {//몇월이 선택되었는지 확인하고
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				dateset();//dateset()실행
			}
		});

		btposter.addActionListener(this);
		btsave.addActionListener(this);
		btcancel.addActionListener(this);

		addWindowListener(new WindowAdapter() {//window 종료버튼(X) 설정
			@Override
			public void windowClosing(WindowEvent arg0) {//타이틀바의 X버튼을 누르면 Admin()이 생성된다.
				new Admin_Main();
			}
		});
		this.setIconImage(changedImg);//타이틀바의 아이콘 설정
		this.setTitle("영화 등록");//타이블바의 제목 설정
		this.setSize(500, 350);//Frame 출력 크기 설정
		this.setLocationRelativeTo(null);//Frame 출력 위치 설졍
		this.setResizable(false);//Frame 사이즈 조절 금지
		this.setVisible(true);//실제로 Frame을 화면에 출력

	}

	void dateset() {
		try {

			cbday.removeAllItems();//일의 콤보박스를 비운다.
			//주의할 점 Calenger.MONTH 값은 0 ~ 11 까지 존재하면 각각의 값이 1월부터 12월을 의마한다.
			//즉 콤보박스에서 선택한 월을 구하기 위해서는 -1을 해줘야 합니다.
			cal.set(Integer.parseInt(cbyear.getSelectedItem()+""), Integer.parseInt(cbmonth.getSelectedItem()+"")-1,1);
			
			//선택된 달의 마지막 날을 구하고 콤보박스에 추가시키는 코드이다.
			for(int i = 1 ; i <= cal.getActualMaximum(Calendar.DATE); i ++) {
				cbday.addItem(i);
			}

		}catch(Exception e) {
		}
	}

	public static void main(String[] args) {
		new Insert();

	}

	JFileChooser fc= new JFileChooser();
	int check = 0;//파일 저장버튼 눌렸는지 확인하기 위한 변수
	int ok = 0;//이미지를 설정하였는지 확인하기 위한 변수
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btposter)) {//포스터 등록 버튼을 클릭했을 경우

			//파일 필터 지정
			fc.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//열기용 창을 만든다.
			check = fc.showOpenDialog(this);

			if(check == JFileChooser.APPROVE_OPTION ) {//저장버튼을 클릭했는지 확인

				//선택된 파일을 가져온다.
				File file = fc.getSelectedFile();
				//선택된 파일을 라벨에 설정한다. 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정
				lbposter.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok = 1;//이미지 등록 확인

			}

		}else if(e.getSource().equals(btsave)) {//저장 버튼을 클릭했을 경우
			//장르, 관람연령, 영화명, 가격, 개봉날짜, 상영시간이 빈칸인지 확인
			if(cbgenre.getSelectedIndex() == -1 || cbage.getSelectedIndex() == -1 || tfname.getText().equals("")||tfprice.getText().equals("")|| tftime.getText().equals("") || cbyear.getSelectedIndex()==-1||cbmonth.getSelectedIndex()==-1||cbday.getSelectedIndex()==-1) {
				
				JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "메시지", JOptionPane.ERROR_MESSAGE);
			}if(ok == 0) {//포스터 등록을 하였는지 확인

				JOptionPane.showMessageDialog(null, "포스터가 없습니다.", "메시지", JOptionPane.ERROR_MESSAGE);

			}else {
				//가격과 시간이 숫자형식인지 확인
				//[] : 문자의 집합이나 범위를 나타내면 두 문자 사이은 - 기호로 범위를 나타낸다. + : 앞 문자가 하나 이상
				if(tfprice.getText().matches("[0-9]+") && tftime.getText().matches("[0-9]+")) {
					//mlist에서 영화이름이 텍스트필드의 이름과 같은 행을 list에 넣는다.
					DBmgr.sql = "select * from mlist where m_name = '" + tfname.getText() + "'";
					list = mgr.movie();

					try {//try문으로 들어가면 이미 있는 영화를 넣는 것이기 때문에
						bean = list.get(0);
						//에러 메시지를 띄운다.
						JOptionPane.showMessageDialog(null, "이미 존재하는 영화입니다.", "메시지", JOptionPane.ERROR_MESSAGE);

					}catch(Exception e1) {//catch로 들어왔을 경우
						//DecimalFormat이란 10진수의 값을 원하는 포멧으로 변형해 주는 클래스이다.
						//cbmonth와 cbday를 두자리, 즉 00의 형태로 포맷을 해야하기 때문에 아래와 같은 코드를 사용하였다.
						DecimalFormat df = new DecimalFormat("00");
						
						//현재 창에서 insert가 실행되면 안되기 때문에 String 변수로 저장해놓고 MStaff에서 저장을 클릭하면 영화정보와 출연진정보가 차례대로 insert 된다.
						insert = "insert into mlist(m_no, m_genre, m_name, m_age, m_price, m_date, m_time) values( sm_no.nextval, '" 
								+ cbgenre.getSelectedItem() + "', '" + tfname.getText() + "', '" + cbage.getSelectedItem() + "', '" + tfprice.getText() + "', '" 
								+ (cbyear.getSelectedItem() + "-" + df.format(cbmonth.getSelectedItem()) + "-" + df.format(cbday.getSelectedItem())) + "', '" + tftime.getText() + "분" + "')";

						try {
							//이미지 파일을 읽어와서 BufferedImage에 넣는다.
							BufferedImage bi = ImageIO.read(new File(fc.getSelectedFile()+""));
							//DBFiles\\image\\라는 경로와 파일이름을 지정해주고
							File f2 = new File("DBFiles\\image\\" + tfname.getText() + ".jpg");
							//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
							ImageIO.write(bi, "jpg", f2);

						}catch(Exception e2) {

						}
						
						//출연진 등록에서 사용할 수 있게 영화이름을 static한 형태로 저장해둔다.
						mname = tfname.getText();
						
						//영화 정보가 알맞게 들어갔다면 감독&출연진 정보를 등록하는 창을 새로 띄운다.
						new Staff("감독 & 출연진 정보 등록", this);
						//현재 작성하던 영화등록 창은 안보이게 설정한다.
						setVisible(false);

					}

				}else{//가격과 상영시간이 숫자 형태로 입력이 안되었을 경우 오류 메시지 창을 띄운다. 
					if(!tfprice.getText().matches("[0-9]+")) {
						JOptionPane.showMessageDialog(null, "가격은 숫자로 입력해주세요.", "메세지", JOptionPane.ERROR_MESSAGE);
					}else if(!tftime.getText().matches("[0-9]+")) {
						JOptionPane.showMessageDialog(null, "상영시간은 숫자로 입력해주세요.", "메세지", JOptionPane.ERROR_MESSAGE);
					}

				}

			}

		}else if(e.getSource().equals(btcancel)) {//취소 버튼을 클릭하면 현재  MInsert 프레임만 종료시키고 Admin() 창을 새로 띄운다.
			dispose();
			new Admin_Main();
		}

	}

}

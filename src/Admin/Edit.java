package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Setting.Bean;
import Setting.DBmgr;

public class Edit extends JFrame implements ActionListener {

	static int no;
	int mno;
	String fname, date;

	Container c = getContentPane();

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JLabel lbposter = new JLabel();

	JLabel lbsearch = new JLabel("검색");
	JLabel lbgenre = new JLabel("장르");
	JLabel lbage = new JLabel("관람연령");
	JLabel lbname = new JLabel("영화명");
	JLabel lbprice = new JLabel("가격");
	JLabel lbdate = new JLabel("개봉날짜");
	JLabel lbtime = new JLabel("상영시간");

	JTextField tfsearch = new JTextField(16);
	JTextField tfname = new JTextField();
	JTextField tfprice = new JTextField(15);
	JTextField tftime = new JTextField(15);

	String csearch[] = {"전체","SF","공포","로맨스","애니메이션","액션","코미디"};
	String cgenre[] = {"SF","공포","로맨스","애니메이션","액션","코미디"};
	String cage[] = {"전체 관람가", "12세 관람가", "15세 관람가","청소년 관람불가"};

	JComboBox cbsearch = new JComboBox(csearch);
	JComboBox cbgenre = new JComboBox(cgenre);
	JComboBox cbage = new JComboBox(cage);

	JButton btsearch = new JButton("찾기");
	JButton btposter = new JButton("포스터 선택");
	JButton btstaff = new JButton("출연 정보");
	JButton btdelete = new JButton("삭제");
	JButton btsave = new JButton("저장");
	JButton btcancel = new JButton("취소");

	JComboBox cbyear = new JComboBox();
	JComboBox cbmonth = new JComboBox();
	JComboBox cbday = new JComboBox();

	JLabel lbyear = new JLabel("년");
	JLabel lbmonth = new JLabel("월");
	JLabel lbday = new JLabel("일");
	JLabel lbminute = new JLabel("분");

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel(new GridLayout(4,2,-90,5));
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel(new GridLayout(1, 1));
	JPanel p6 = new JPanel(new GridLayout(1, 2,-73, 8));

	String col[] = {"장르","영화명","관람연령","가격","개봉날짜","상영시간"};
	JTable table = new JTable();
	JScrollPane jsp = new JScrollPane();

	Calendar cal = Calendar.getInstance();

	ArrayList<Bean> list = new ArrayList<Bean>();
	ArrayList<Bean> list2 = new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Edit() {

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
		p6.setBackground(Color.WHITE);
		cbsearch.setBackground(Color.WHITE);
		cbgenre.setBackground(Color.WHITE);
		cbage.setBackground(Color.WHITE);
		cbyear.setBackground(Color.WHITE);
		cbmonth.setBackground(Color.WHITE);
		cbday.setBackground(Color.WHITE);
		btsearch.setBackground(new Color(69, 68, 66));
		btposter.setBackground(new Color(69, 68, 66));
		btstaff.setBackground(new Color(69, 68, 66));
		btdelete.setBackground(new Color(69, 68, 66));
		btsave.setBackground(new Color(69, 68, 66));
		btcancel.setBackground(new Color(69, 68, 66));

		//글자색 설정
		btsearch.setForeground(Color.WHITE);
		btposter.setForeground(Color.WHITE);
		btstaff.setForeground(Color.WHITE);
		btdelete.setForeground(Color.WHITE);
		btsave.setForeground(Color.WHITE);
		btcancel.setForeground(Color.WHITE);

		//전체 폰트 설정
		c.setFont(new Font("나눔바른고딕", Font.BOLD, 12));

		//아이콘 삽입과 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정
		btsearch.setIcon(new ImageIcon(new ImageIcon("resource/icon/search.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btposter.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btstaff.setIcon(new ImageIcon(new ImageIcon("resource/icon/information.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btdelete.setIcon(new ImageIcon(new ImageIcon("resource/icon/delete.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btsave.setIcon(new ImageIcon(new ImageIcon("resource/icon/save.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btcancel.setIcon(new ImageIcon(new ImageIcon("resource/icon/mcancel.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

		//라벨에 테두리 설정
		lbposter.setBorder(new LineBorder(Color.BLACK));

		//검색하는 부분
		p1.add(lbsearch);
		p1.add(cbsearch);
		p1.add(tfsearch);
		p1.add(btsearch);

		p2.add(lbgenre); p2.add(cbgenre);//장르
		p2.add(lbage); p2.add(cbage);//관람연령
		p2.add(lbname); p2.add(tfname);//영화이름
		p2.add(lbprice); p2.add(tfprice);//영화가격

		p5.add(lbdate);//개봉날짜
		p4.add(cbyear); p4.add(lbyear);//년
		p4.add(cbmonth); p4.add(lbmonth);//월
		p4.add(cbday); p4.add(lbday);//일
		
		p6.add(lbtime); p6.add(tftime);//상영시간

		p3.add(btstaff);//출연진 정보
		p3.add(btdelete);//삭제
		p3.add(btsave);//저장
		p3.add(btcancel);//취소

		//위치 지정
		p1.setBounds(0, 0, 400, 35);
		jsp.setBounds(5, 40, 570, 300);
		p2.setBounds(595, 50, 220, 130);
		p5.setBounds(595,183,60,30);
		p4.setBounds(657, 183, 200, 30);
		p6.setBounds(595,220,205,25);
		lbminute.setBounds(800, 220, 20, 25);
		lbposter.setBounds(860, 50, 150, 150);
		btposter.setBounds(860, 205, 150, 35);
		p3.setBounds(605, 280, 400, 30);
		
		this.add(p1);
		this.add(jsp);
		this.add(p2);
		this.add(p5);
		this.add(p4);
		this.add(p6);
		this.add(lbminute);
		this.add(lbposter);
		this.add(btposter);
		this.add(p3);

		//년,월,일 콤보박스 설정
		int y = cal.get(cal.YEAR);//오늘년도 가져오기
		for(int i = 1900 ; i <= y;i++ ) {//1900년부터 오늘년도 까지로 설정

			cbyear.addItem(i);
		}

		for(int i= 1 ; i <= 12 ; i++) {//1월부터 12일까지로 설정

			cbmonth.addItem(i);

		}
		//콤보박스 설정
		cbyear.setSelectedIndex(-1);
		cbmonth.setSelectedIndex(-1);

		cbyear.addItemListener(new ItemListener() {//몇년도가 선택되었는지 확인하고
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				dateset();//dateset()실행
			}
		});
		cbmonth.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {//몇월이 선택되었는지 확인하고
				dateset();//dateset()실행
			}
		});

		tfsearch.addActionListener(this);
		btsearch.addActionListener(this);
		btposter.addActionListener(this);
		btstaff.addActionListener(this);
		btdelete.addActionListener(this);
		btsave.addActionListener(this);
		btcancel.addActionListener(this);

		//table을 만들기 위해  mlist의 전체를 뽑아낸다.
		DBmgr.sql = "select * from mlist";
		table();

		addWindowListener(new WindowAdapter() {//window 종료버튼(X) 설정
			@Override
			public void windowClosing(WindowEvent arg0) {//타이틀바의 X버튼을 누르면 Admin()이 생성된다.
				new Admin_Main();
			}
		});

		this.setIconImage(changedImg);//타이틀바의 아이콘 설정
		this.setTitle("영화 관리 & 수정");//타이블바의 제목 설정
		this.setSize(1040,380);//Frame 출력 크기 설정
		this.setLocationRelativeTo(null);//Frame 출력 위치 설정
		this.setResizable(false);//Frame 사이즈 조절 금지
		this.setVisible(true);//실제로 Frame을 화면에 출력
		p1.requestFocus();//p1에 포커스를 둔다.
	}

	void dateset() {
		try {

			cbday.removeAllItems();//일의 콤보박스를 비운다.
			//주의할 점 Calenger.MONTH 값은 0 ~ 11 까지 존재하면 각각의 값이 1월부터 12월을 의마한다.
			//즉 콤보박스에서 선택한 월을 구하기 위해서는 -1을 해줘야 합니다.
			cal.set(Integer.parseInt(cbyear.getSelectedItem()+""), Integer.parseInt(cbmonth.getSelectedItem()+"")-1,1);
			
			//선택된 달의 마지막 날을 구하고 콤보박스에 추가시키는 코드이다.
			for(int i = 1 ; i <= cal.getActualMaximum(Calendar.DATE) ; i ++) {
				cbday.addItem(i);
			}

		}catch(Exception e) {
		}

	}

	public static void main(String[] args) {
		new Edit();

	}

	void table() {//테이블을 생성한다.
		//list에 mlist의 내용을 넣는다.
		list = mgr.movie();
		//행은 list의 사이즈이고 열은 col의 길이이다.
		String record[][] = new String[list.size()][col.length];
		for(int i = 0 ; i<list.size() ; i++) {
			//list 행의 i번째에 있는 것은 bean에 넣고
			bean = list.get(i);
			//각 행과 열에 맞게 데이터를 넣는다.
			record[i][0] = bean.getMgenre();
			record[i][1] = bean.getMname();
			record[i][2] = bean.getMage();
			record[i][3] = bean.getMprice()+"";
			record[i][4] = bean.getMdate();
			record[i][5] = bean.getMtime();

		}
		//DefaultTableModel 클래스를 사용해야 테이블에 행단위로 쓰거나 삭제가 용이하다.
		//DefaultTableModel 객체를 생성하면서 데이터를 담는다.
		DefaultTableModel model = new DefaultTableModel(record, col) {
			public boolean isCellEditable(int r, int c	) {//isCellEditable은 테이블을 직접 수정 할 수 없게 막는 곳이다.
				return false;
			}

		};
		//JTable에 model을 넣는다.
		table= new JTable(model);
		//jsp에 table을 담는다.
		jsp.setViewportView(table);
		
		DefaultTableCellRenderer al = new DefaultTableCellRenderer();//셀 안에 들어가는 데이터의 정렬을 조절한다.
		al.setHorizontalAlignment(JLabel.CENTER);//가운데 정렬을 해주었다.
		for(int i = 0 ; i < col.length ; i ++) {
			if(i!=1) {//1열인 영화이름은 빼고
				//가운데 정렬을 해준다.
				table.getColumnModel().getColumn(i).setCellRenderer(al);

			}
		}

		table.getColumnModel().getColumn(1).setMinWidth(180);//1열의 폭을 설정한다.
		table.getColumnModel().getColumn(2).setMinWidth(90);//2열의 폭을 설정한다.

		table.addMouseListener(new MouseAdapter() {@Override//테이블의 마우스 리스너이다.
			public void mouseClicked(MouseEvent arg0) {
			//mlist에서 선택된 행의 영화이름을 가진 정보를 list에 넣는다.
			DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
			list = mgr.movie();
			bean = list.get(0);
			//영화번호를 static하게 설정한다.
			no = bean.getMno();
			//콤보박스에 bean에서 가져온 장르와 관람연령을 set해준다. 
			cbgenre.setSelectedItem(bean.getMgenre());
			cbage.setSelectedItem(bean.getMage());
			//개봉날짜는 하이픈을 구분하여 문자열을 추출해서
			String mdate = bean.getMdate();
			String ff[] = mdate.split("-");
			//각각 년,월,일의 콤보박스에 set해준다.
			cbyear.setSelectedItem(Integer.parseInt(ff[0]));
			cbmonth.setSelectedItem(Integer.parseInt(ff[1]));
			cbday.setSelectedItem(Integer.parseInt(ff[2]));
			//bean에서 가져온 영화이름은 영화이름 텍스트필드에 영화가격은 영화가격 텍스트필드에 set해준다.
			tfname.setText(bean.getMname());
			tfprice.setText(bean.getMprice()+"");
			//상영시간은 bean에서 가져온 문자열에서 분을 잘라낸 문자열을 상영시간 텍스트필드에 set해준다.
			String t1 = bean.getMtime();
			String t2[] = t1.split("분");
			tftime.setText(t2[0]);
			//영화이름으로 포스터 파일을 가져오고 이미지 크기 등을 설정해준다.
			fname= "DBFiles\\image\\" + table.getValueAt(table.getSelectedRow(), 1) + ".jpg";
			lbposter.setIcon(new ImageIcon(new ImageIcon(fname).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
			
			//영화번호를 가지고 비교해야 하는 부분이 있기에 변수로 설정해놓았다.
			mno = bean.getMno();

		}
		});

	}

	JFileChooser fc = new JFileChooser();
	int check = 0;//저장버튼 눌렸는지 확인하기 위한 변수
	void edit() {
		//DecimalFormat이란 10진수의 값을 원하는 포멧으로 변형해 주는 클래스이다.
		//cbmonth와 cbday를 두자리, 즉 00의 형태로 포맷을 해야하기 때문에 아래와 같은 코드를 사용하였다.
		DecimalFormat df = new DecimalFormat("00");
		//상영시간이 같은지 비교하는 부분이 있기 때문에 변수로 설정해놓았다.
		date = (cbyear.getSelectedItem() + "-" + df.format(cbmonth.getSelectedItem()) + "-" + df.format(cbday.getSelectedItem()));
		
		//영화를 수정한다면 update를 해준다.
		DBmgr.sql = "update mlist set m_genre = '" + cbgenre.getSelectedItem() + "' , m_age = '" + cbage.getSelectedItem() + "', m_name = '" + tfname.getText() + "', m_price = '" + tfprice.getText() + "', m_date = '" + date + "', m_time = '" + tftime.getText()+"분" + "' where m_no = '" + bean.getMno() + "'";
		list = mgr.update();
		//수정이 완료되었다면 정보 메시지를 띄어준다.
		JOptionPane.showMessageDialog(null, "수정되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);

		try {
			//포스터가 선택이 안되어있다면
			if(fc.getSelectedFile()==null) {
				//원래의 이미지 파일을 읽어와서 BufferedImage에 넣는다.
				BufferedImage bi = ImageIO.read(new File(fname));
				//DBFiles\\image\\라는 경로와 파일이름을 저장해주고
				File f2 =new File("DBFiles\\image\\" + tfname.getText() + ".jpg");
				
				//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
				ImageIO.write(bi, "jpg", f2);

			}else {//포스터가 선택이 되었다면
				//새로운 이미지 파일을 읽어와서 BufferedImage에 넣는다.
				BufferedImage bi = ImageIO.read(new File(fc.getSelectedFile()+""));
				//새로운 이미지 파일을 DBFiles\\image\\라는 경로와 파일이름을 저장해주고
				File f2 = new File("DBFiles\\image\\" + tfname.getText() + ".jpg");

				//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
				ImageIO.write(bi, "jpg", f2);

			}

		}catch(Exception e1) {

		}
		//수정이 다 되었기에 현재  MEdit 프레임을 종료시키고 다시 MEdit() 창을 새로 띄워줘야한다.
		dispose();
		new Edit();

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btsearch) || e.getSource().equals(tfsearch)) {//검색버튼을 클릭하거나 텍스트필드에서 엔터키를 누르면

			if(cbsearch.getSelectedIndex() == 0) {//검색의 콤보박스가 전체로 선택되어 있다면

				if(tfsearch.getText().equals("")) {//검색어가 공백이면
					DBmgr.sql = "select * from mlist";//모든 영화를 검색한다.

				}else {//검색어가 있다면
					DBmgr.sql = "select * from mlist where m_name like '%" + tfsearch.getText() + "%'";//영화이름으로 검색한다.

				}

			}else {//검색의 콤보박스가 다른 것으로 선택되어 있다면

				if(tfsearch.getText().equals("")) {//검색어가 공백이면
					DBmgr.sql = "select * from mlist where m_genre = '" + cbsearch.getSelectedItem() + "'";//검색 콤보박스에 선택된 장르의 모든 영화를 검색한다.

				}else {//검색어가 있다면
					DBmgr.sql = "select * from mlist where m_name like '%" + tfsearch.getText() + "%' and m_genre = '" + cbsearch.getSelectedItem() + "'";//영화이름과 장르로 검색한다.

				}

			}
			//table()을 실행시킨다.
			table();

		}else if(e.getSource().equals(btposter)) {//포스터 선택버튼을 클릭하면
			//파일 필터 지정
			fc.setFileFilter(new FileNameExtensionFilter("JPGE image", "jpg"));
			//열기용 창을 만든다.
			check = fc.showOpenDialog(this);
			
			if(check == JFileChooser.APPROVE_OPTION) {//저장버튼을 클릭했는지 확인

				//선택된 파일을 가져온다.
				File f2 = fc.getSelectedFile();
				//선택된 파일을 라벨에 설정한다. 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정
				lbposter.setIcon(new ImageIcon(new ImageIcon(f2+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));

			}


		}else if(e.getSource().equals(btstaff)) {//출연 정보버튼을 클릭하면
			
			if(table.getSelectedRowCount()==0) {//테이블에서 선택된 것이 없다면 경고메시지를 띄어준다.
				
				JOptionPane.showMessageDialog(null, "출연 정보를 볼 영화를 선택해주세요.", "메시지", JOptionPane.WARNING_MESSAGE);

			}else {//테이블에서 선택된것이 있다면 선택된 영화의 번호를 가지고 출연진 정보 수정 창을 새로 띄어준다.

				new Staff("감독 & 출연진 정보 수정", this, no);
				//현재 작성하던 영화등록 창은 안보이게 설정한다.
				setVisible(false);

			}
		}else if(e.getSource().equals(btdelete)) {//삭제 버튼을 클릭했을 경우
			
			if(table.getSelectedRowCount()==0) {//테이블에서 선택된 것이 없다면 경고메시지를 띄어준다.

				JOptionPane.showMessageDialog(null, "삭제할 영화를 선택해주세요.", "메시지", JOptionPane.WARNING_MESSAGE);

			}else{//테이블에서 선택된것이 있다면 선택된 영화로 삭제를 실행한다.

				//선택된 영화이름으로 검색을 실행하고 나온 결과를 list에 넣는다.
				DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
				list = mgr.movie();
				//list에 0번째에 있는 것을 bean에 넣는다.
				bean = list.get(0);
				
				//출연진 사진을 삭제하기위해 영화번호에 맞는 출연진 정보를 가져와서 list2에 넣는다
				DBmgr.sql = "select * from stafflist where m_no = '" + bean.getMno() + "'";
				list2 = mgr.staff();
				bean = list2.get(0);
				
				//출연진 사진 삭제
				String fdirec= "DBFiles\\direc\\" + bean.getStdirec() + ".jpg";
				File fd = new File(fdirec);
				fd.delete();
				String factor1= "DBFiles\\actor\\" + bean.getStactor1() + ".jpg";
				File fa1 = new File(factor1);
				fa1.delete();
				String factor2= "DBFiles\\actor\\" + bean.getStactor2() + ".jpg";
				File fa2 = new File(factor2);
				fa2.delete();
				String factor3= "DBFiles\\actor\\" + bean.getStactor3() + ".jpg";
				File fa3 = new File(factor3);
				fa3.delete();

				//stafflist에서 영화번호가 같은 행을 삭제한다.
				DBmgr.sql = "delete from stafflist where m_no = '" + bean.getMno() + "'";
				list = mgr.update();
				//shopping에서 영화번호가 같은 행을 삭제한다.
				DBmgr.sql = "delete from shopping where m_no = '" + bean.getMno() + "'";
				list = mgr.update();
				//olist에서 영화번호가 같은 행을 삭제한다.
				DBmgr.sql = "delete from olist where m_no = '" + bean.getMno() + "'";
				list = mgr.update();
				//mlist에서 영화번호가 같은 행을 삭제한다.
				DBmgr.sql = "delete from mlist where m_no = '" + bean.getMno() + "'";
				list = mgr.update();
				//이러한 순서로 삭제하는 이유는 외래키가 참조되어 있기 때문이다.

				//마지막으로 포스터 사진도 삭제한다.
				File ff = new File(fname);
				ff.delete();
				
				//삭제가 된 후 정보메시지 창을 띄어준다.
				JOptionPane.showMessageDialog(null, "삭제되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				
				//삭제가 되었기 때문에 테이블을 다시 생성하기 위해
				//현재 창을 종료하고 새 MEdit()창을 띄운다.
				dispose();
				new Edit();
			}

		}else if(e.getSource().equals(btsave)) {//저장 버튼을 클릭했을 경우
			
			if(table.getSelectedRowCount()==0) {//테이블에서 선택된 것이 없다면 경고메시지를 띄어준다.

				JOptionPane.showMessageDialog(null, "수정할 영화를 선택해주세요,", "메세지", JOptionPane.WARNING_MESSAGE);

			}else {//테이블에서 선택된것이 있다면 선택된 영화로 수정을 실행한다.
				
				//선택된 영화이름으로 검색을 실행하고 나온 결과를 list에 넣는다.
				DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
				list = mgr.movie();
				//list에 0번째에 있는 것을 bean에 넣는다.
				bean = list.get(0);
				
				//DecimalFormat이란 10진수의 값을 원하는 포멧으로 변형해 주는 클래스이다.
				//cbmonth와 cbday를 두자리, 즉 00의 형태로 포맷을 해야하기 때문에 아래와 같은 코드를 사용하였다.
				DecimalFormat df = new DecimalFormat("00");
				date = (cbyear.getSelectedItem() + "-" + df.format(cbmonth.getSelectedItem()) + "-" + df.format(cbday.getSelectedItem()));
				
				//상영시간을 t1에 넣고
				String t1 = bean.getMtime();
				//가져온 문자열에서 분을 잘라낸 문자열 t2에 넣는다.
				String t2[] = t1.split("분");
				
				//빈칸 확인을 하고 빈칸이 있으면 에러 메시지를 띄운다.
				if(cbgenre.getSelectedIndex() == -1 || cbage.getSelectedIndex() == -1 || cbyear.getSelectedIndex() == -1 || cbmonth.getSelectedIndex() == -1 || cbday.getSelectedIndex() == -1 || tfname.getText().equals("") || tfprice.getText().equals("") || tftime.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "빈칸이 존재합니다", "메세지", JOptionPane.ERROR_MESSAGE);

				}//수정된 내용이 없을 수도 있기 때문에 이것을 확인하는 코드도 넣어준다.
				//현재 영화정보와 DB에 있는 영화정보를 비교하여 모두 다 같으면 변경된 내용이 없다는 에러 메시지 창을 띄어준다.
				else if(cbgenre.getSelectedItem().equals(bean.getMgenre())&& cbage.getSelectedItem().equals(bean.getMage())&&tfname.getText().equals(bean.getMname())&&tfprice.getText().equals(bean.getMprice()+"")&&date.equals(bean.getMdate())&&tftime.getText().equals(t2[0])){
					
					JOptionPane.showMessageDialog(null, "변경된 내용이 없습니다.", "메시지", JOptionPane.ERROR_MESSAGE);
				
				}else{//빈칸이 없고 변경된 내용이 있을 경우
					
					if(tfprice.getText().matches("[0-9]+") && tftime.getText().matches("[0-9]+")) {//가격과 상영시간이 숫자 형태인지 확인한다.
						//영화이름으로 영화정보를 가져와서 list에 넣는다.
						DBmgr.sql = "select * from mlist where m_name = '" + tfname.getText() + "'";
						list = mgr.movie();

						try {
							bean = list.get(0);

							if(mno!=bean.getMno()) {//테이블에서 선택된 영화번호와 가져온 list에서 영화번호가 다르다면 영화이름이 겹처지는 것이기 때문에
								//이미 존재하는 영화라는 에러 메시지를 띄어준다.
								JOptionPane.showMessageDialog(null, "이미 존재하는 영화명입니다.", "메시지", JOptionPane.ERROR_MESSAGE);

							}else {
								//위의 if문이 아니라면 edit()을 실행한다.
								edit();

							}

						}catch(Exception e1) {
							//에러가 나도 수정하는 edit()을 실행한다.
							edit();

						}

					}else {
						if(!tfprice.getText().matches("[0-9]+")){//가격이 숫자 형태가 아니라면 에러 메시지를 띄어준다.

							JOptionPane.showMessageDialog(null, "가격은 숫자로 입력해 주세요.", "메시지", JOptionPane.ERROR_MESSAGE);

						}
						else if(!tftime.getText().matches("[0-9]+")) {//상영시간이 숫자 형태가 아니라면 에러 메시지를 띄어준다.
							JOptionPane.showMessageDialog(null, "상영시간은 숫자로 입력해주세요.", "메세지", JOptionPane.ERROR_MESSAGE);
						}

					}

				}

			}

		}else if(e.getSource().equals(btcancel)) {//취소 버튼을 클릭했을 경우 현재  MEdit 프레임만 종료시키고 Admin() 창을 새로 띄운다
			dispose();
			new Admin_Main();
		}

	}

}

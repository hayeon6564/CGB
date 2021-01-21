package Users;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Setting.Bean;
import Setting.DBmgr;
import Admin.Manager;

public class Orderlist extends JFrame implements ActionListener {
	
	private ImageIcon originIcon;
	private Image originImg, changedImg;

	private int sum = 0; //누적 금액 연산할 변수
	
	private DecimalFormat df = new DecimalFormat("#,##0"); //가격 나타낼 형태 포맷
	
	private JLabel lb1, grade; //~님의 구매내역, 등급
	private JLabel img; //영화 포스터 틀
	private JLabel tf1, tf2, tf3, tf4, tf5; //영화정보
	
	private JTextField tfSum1; //총 결제금액

	private JButton bt1; //닫기

	private String col[] = {"No.", "구매일자","영화명","결제금액","누적금액"}; //테이블 칼럼

	private JTable table =new JTable();
	private JScrollPane jsp = new JScrollPane();

	private DBmgr mgr = new DBmgr(); //원하는 값 가져올 함수가 담겨있는 클래스
	private ArrayList<Bean> list =new ArrayList<Bean>(); //sql문으로 가져온 결과값 리스트 저장
	private Bean bean; //sql문 처리, list로 가져온 값 각각 나눠서 저장

	public Orderlist() {

		// ImageIcon 객체를  생성한다.
		originIcon = new ImageIcon("resource/icon/pop.png");
		// ImageIcon에서 Image를 추출한다.
		originImg = originIcon.getImage();
		// 추출된 Image의 크기를 조절해서 새로운 Image객체를 생성한다.
		changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		setIconImage(changedImg);

		lb1 = new JLabel("",JLabel.RIGHT);
		lb1.setText(Login.name + "님의 구매내역");
		lb1.setFont(new Font("나눔바른고딕",Font.BOLD,30));
		lb1.setBackground(Color.WHITE);
		
		String o = "";
		
		if(Users_Main.ch == 1) {
			lb1.setText(Login.name + "님의 구매내역");
			o = "select * from ulist where u_id = '" + Login.id + "'";

		}else if(Users_Main.ch == 2) {
			lb1.setText(Manager.name + "님의 구매내역");
			o = "select * from ulist where u_id = '" + Manager.id + "'";

		}
		//해당 유저 정보 가져와서
		DBmgr.sql = o;
		list = mgr.user();
		bean = list.get(0);

		//해당 유저의 등급 설정,저장
		String gradeg = "";
		if(bean.getUgrade().equals(" ")) {
			gradeg = "일반";
		}else {
			gradeg = bean.getUgrade();
		}

		grade = new JLabel("등급 : " + gradeg,JLabel.CENTER); //저장한 유저 등급 표시
		grade.setFont(new Font("나눔바른고딕",Font.BOLD,20));

		JLabel lb2 = new JLabel("총 결제 금액");
		tfSum1 = new JTextField(25); //총결제금액
		tfSum1.setHorizontalAlignment(JTextField.CENTER); //가운데 정렬
		tfSum1.setEditable(false); //텍스트필드 못건들게 하기
		tfSum1.setBackground(Color.WHITE);

		ImageIcon i1 = new ImageIcon("resource\\icon\\exit.png"); //닫기버튼아이콘
		bt1 = new JButton("닫기",i1); //닫기버튼
		bt1.setBackground(Color.WHITE);
		bt1.addActionListener(this);

		img = new JLabel();
		img.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //영화포스터 외곽선

		//늘어나는 부분
		JLabel lbl1 = new JLabel("영화명:");
		JLabel lbl2 = new JLabel("관람연령:");
		JLabel lbl3 = new JLabel("가       격:");
		JLabel lbl4 = new JLabel("개봉날짜:");
		JLabel lbl5 = new JLabel("상영시간:");

		tf1 = new JLabel(); //선택한 영화 정보 담을 라벨
		tf2 = new JLabel();
		tf3 = new JLabel();
		tf4 = new JLabel();
		tf5 = new JLabel();

		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.add(lb2); //총 결제금액 라벨
		p1.add(tfSum1); //총 결제금액 표시
		p1.add(bt1); //닫기버튼

		JPanel p2 = new JPanel(new GridLayout(2,1,10,10));
		p2.setBackground(Color.WHITE);
		p2.setLayout(new GridLayout(1,2,0,0));
		p2.add(lb1); //~님의 구매내역
		p2.add(grade); //등급

		JPanel p4 = new JPanel(new GridLayout(5,2,0,15));
		p4.setBackground(Color.WHITE);
		p4.add(lbl1);	p4.add(tf1); //영화리스트 클릭하면 나오는 정보들
		p4.add(lbl2);	p4.add(tf2);
		p4.add(lbl3);	p4.add(tf3);
		p4.add(lbl4);	p4.add(tf4);
		p4.add(lbl5);	p4.add(tf5);

		table();

		JPanel p3 = new JPanel(); //전체
		p3.setLayout(null);
		p3.setBackground(Color.WHITE);
		p3.add(p1); //총 결제금액, 닫기버튼
		p3.add(p2); //~님의 구매내역, 등급
		p3.add(p4); //영화리스트 클릭하면 나오는 정보들
		p3.add(jsp); //구매내역테이블 담은 스크롤패널
		p3.add(img); //영화포스터 외곽선

		p1.setBounds(0,300,650,400);
		jsp.setBounds(0,50,636,250);
		p2.setBounds(0,0,650,50);
		p4.setBounds(920,50,250,250);
		img.setBounds(700,50,190,250);

		bt1.addActionListener(this);
		
		add(p3);

		setTitle("구매내역");
		setSize(650,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {@Override
			public void windowClosing(WindowEvent arg0) {
			if(Users_Main.ch == 1) {
				dispose(); //X 누를 때의 이벤트
			}else if(Users_Main.ch == 2) {
				dispose();
				new Manager();
			}
		}
		});
	}

	void table() {

		//해당 유저의 구매내역DB의 영화번호로 테이블에 나타낼 영화DB의 값을 구매내역번호 순서대로 가져옴
		if(Users_Main.ch == 1) {
			DBmgr.sql = "select olist.o_date, mlist.m_name, olist.o_price from olist inner join mlist on olist.m_no = mlist.m_no where olist.u_no = '" + Login.no + "' order by o_no";

		}else if(Users_Main.ch == 2) {
			DBmgr.sql = "select olist.o_date, mlist.m_name, olist.o_price from olist inner join mlist on olist.m_no = mlist.m_no where olist.u_no = '" + Manager.no + "' order by o_no";

		}
		list = mgr.orderjoin();

		int data;
		String record[][] = new String[list.size()][col.length]; //가져온 값의 크기만큼 테이블에 나타낼 값 담을 2차원 배열 생성
		for(int i= 0 ; i < list.size() ; i++) {
			bean = list.get(i);

			data = bean.getOprice();
			sum = sum + data; //구매한 영화의 가격 모두 합산

			record[i][0] = Integer.toString(i+1);
			record[i][1] = bean.getOdate()+"";
			record[i][2] = bean.getMname()+"";
			record[i][3] = Integer.toString(bean.getOprice())+"";
			record[i][4] = Integer.toString(sum);     
		}

		tfSum1.setText(df.format(sum));

		DefaultTableModel model = new DefaultTableModel(record, col) { //record = 테이블의 데이터가 담긴 2차원 배열, col = 테이블 컬럼로 테이블을 만든다.
			public boolean isCellEditable(int r, int c) {
				return false; //테이블의 각 셀을 건들지 못하도록 함
			}
		};

		table = new JTable(model); //테이블생성
		jsp.setViewportView(table); //스크롤페널에 테이블 담기

		DefaultTableCellRenderer al = new DefaultTableCellRenderer();
		al.setHorizontalAlignment(JLabel.CENTER); //각 셀의 데이터 가운데정렬

		for(int i =0 ; i < col.length ; i ++) {
			table.getColumnModel().getColumn(i).setCellRenderer(al);
		} //가운데정렬 렌더링(테이블에 맞게 설정)

		table.addMouseListener(new MouseAdapter() { //테이블에서 마우스 이벤트 발생했을 때 실행

			@Override
			public void mouseClicked(MouseEvent e) {

				//마우스가 선택한 행의 1번째 값(영화이름)의 내용들을 영화DB에서 가져와서 리스트로 저장 후 bean에서 각각 저장
				DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(),2) + "'";
				list = mgr.movie();
				bean = list.get(0);

				if(e.getSource().equals(table)) {

					setSize(1200, 400); //오른쪽으로 창이 커지고 마우스로 클릭한 해당 영화의 정보가 나타남.

					//아이콘을 이미지로 가져와 크기조절, 품질 유지 설정
					img.setIcon(new ImageIcon(new ImageIcon("DBFiles\\image\\" + bean.getMname() + ".jpg").getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));

					tf1.setText(bean.getMname()); //마우스로 클릭한 영화 정보 저장
					tf2.setText(bean.getMage());
					tf3.setText(bean.getMprice()+"");
					tf4.setText(bean.getMdate());
					tf5.setText(bean.getMtime());
				}
			}
		});
	}

	public static void main(String[] args) {
		//new OrderList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bt1)) {
		if(Users_Main.ch == 1) {
			dispose();
		}else if(Users_Main.ch == 2) {
			Users_Main.ch = 0;
			dispose();
			new Manager();
		}
		}
	}
}
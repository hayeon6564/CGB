package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import Setting.Bean;
import Setting.DBmgr;
import Users.Users_Main;
import Users.Orderlist;

public class Manager extends JFrame implements ActionListener {

	public static int no;
	public static String name, id;

	Container c = getContentPane();

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JLabel lbsearch = new JLabel("검색");

	JTextField tfsearch = new JTextField(10);

	String csearch[] = { "전체", "ID", "이름", "생년월일", "회원등급" };

	JComboBox cbsearch = new JComboBox(csearch);

	JButton btsearch = new JButton("찾기");
	JButton btorder = new JButton("구매내역 보기");
	JButton btcancel = new JButton("취소");

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	String col[] = { "회원번호", "ID", "PW", "이름", "생년월일", "포인트", "회원등급" };
	JTable table = new JTable();
	JScrollPane jsp = new JScrollPane();

	ArrayList<Bean> list = new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Manager() {

		this.setLayout(null);

		//배경색 설정
		c.setBackground(Color.WHITE);
		cbsearch.setBackground(Color.WHITE);
		p1.setBackground(Color.WHITE);
		p2.setBackground(Color.WHITE);
		btsearch.setBackground(new Color(69, 68, 66));
		btorder.setBackground(new Color(69, 68, 66));
		btcancel.setBackground(new Color(69, 68, 66));

		//아이콘 삽입과 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정 
		btsearch.setIcon(new ImageIcon(new ImageIcon("resource/icon/search.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btorder.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btcancel.setIcon(new ImageIcon(new ImageIcon("resource/icon/mcancel.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

		//글자색 설정
		btsearch.setForeground(Color.WHITE);
		btorder.setForeground(Color.WHITE);
		btcancel.setForeground(Color.WHITE);

		//전체 폰트 설정
		c.setFont(new Font("나눔바른고딕", Font.BOLD, 12));

		//콤보박스 설정
		cbsearch.setSelectedIndex(0);

		//검색하는 부분
		p1.add(lbsearch);
		p1.add(cbsearch);
		p1.add(tfsearch);
		p1.add(btsearch);

		p2.add(btorder);//구매내역 보기
		p2.add(btcancel);//취소

		//위치 지정
		p1.setBounds(130, 10, 400, 35);
		jsp.setBounds(10, 55, 645, 260);
		p2.setBounds(190, 320, 300, 35);

		btsearch.addActionListener(this);
		tfsearch.addActionListener(this);
		btorder.addActionListener(this);
		btcancel.addActionListener(this);

		this.add(p1);
		this.add(jsp);
		this.add(p2);

		//table을 만들기 위해 ulist의 전체를 뽑아낸다.
		DBmgr.sql = "select u.u_no, u.u_id, u.u_pw, u.u_name, u.u_bd, u.u_point, u.u_grade"
				+ " from ulist u";
		table();

		addWindowListener(new WindowAdapter() {//window 종료버튼(X) 설정
			@Override
			public void windowClosing(WindowEvent arg0) {//타이틀바의 X버튼을 누르면 Admin()이 생성된다.
				new Admin_Main();
			}
		});

		this.setIconImage(changedImg);//타이틀바의 아이콘 설정
		this.setTitle("회원 관리");//타이틀바의 제목 설정
		this.setSize(680, 400);//Frame 출력 크기 설정
		this.setLocationRelativeTo(null);//Frame 출력 위치 설정
		this.setResizable(false);//Frame 사이즈 조절 금지
		this.setVisible(true);//실제로 Frame을 화면에 출력
		p1.requestFocus();//p1에 포커스를 둔다.

	}

	public static void main(String[] args) {
		//new Manager();

	}

	void table() {//테이블을 생성한다.
		//list에 ulist의 내용을 넣는다.
		list = mgr.user();
		//행은 list의 사이즈이고 열은 col의 길이다.
		String record[][] = new String[list.size()][col.length];
		for(int i= 0 ; i < list.size() ; i++) {
			//list 행의 i번째에 있는 것은 bean에 넣고
			bean = list.get(i);
			//각 행과 열에 맞게 데이터를 넣는다.
			record[i][0] = bean.getUno()+"";
			record[i][1] = bean.getUid();
			record[i][2] = bean.getUpw();
			record[i][3] = bean.getUname();
			record[i][4] = bean.getUbd();
			record[i][5] = bean.getUpoint()+"";
			record[i][6] = bean.getUgrade();
		}
		//DefaultTableModel 클래스를 사용해야 테이블에 행단위로 쓰거나 삭제가 용이하다.
		//DefaultTableModel 객체를 생성하면서 데이터를 담는다.
		DefaultTableModel model = new DefaultTableModel(record, col) {
			public boolean isCellEditable(int r, int c) {//isCellEditable은 테이블을 직접 수정 할 수 없게 막는 곳이다.
				return false;
			}
		};
		//JTable에 model을 넣는다.
		table = new JTable(model);
		//jsp에 table을 담는다.
		jsp.setViewportView(table);

		DefaultTableCellRenderer al = new DefaultTableCellRenderer();//셀 안에 들어가는 데이터의 정렬을 조절한다.
		al.setHorizontalAlignment(JLabel.CENTER);//가운데 정렬을 해주었다.
		for(int i =0 ; i < col.length ; i ++) {
			//모든 열의 정렬을 가운데 정렬로 해준다.
			table.getColumnModel().getColumn(i).setCellRenderer(al);

		}
		table.getColumnModel().getColumn(0).setMaxWidth(60);//0열의 폭을 설정한다.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btsearch) || e.getSource().equals(tfsearch)) {//구매내역보기 버튼을 클릭하거나 검색 텍스트필드에서 엔터를 누를 경우

			if(cbsearch.getSelectedIndex() == 0) {//검색 콤보박스가 전체로 설졍되어 있는 경우

				if(tfsearch.getText().equals("")) {//검색어가 공백인 경우
					//ulist의 전체를 뽑아온다.
					DBmgr.sql = "select * from ulist";

				}else {
					//전체에서 검색어의 내용을 이름으로 검색한다.
					DBmgr.sql = "select * from ulist where u_name like '%" + tfsearch.getText() + "%'";

				}

			}else {//검색 콤보박스가  다른 값으로 선택되어 있는 경우

				if(tfsearch.getText().equals("")) {//검색어가 공백인 경우
					//검색어를 입력하라는 경고 메시지를 띄어준다.
					JOptionPane.showMessageDialog(null, "검색어를 한글자 이상 입력해주세요.", "메시지", JOptionPane.WARNING_MESSAGE);

				}else if(cbsearch.getSelectedIndex() == 1){//콤보박스가 ID로 선택된 경우

					DBmgr.sql = "select * from ulist where u_id like '%" + tfsearch.getText() + "%'";

				}else if(cbsearch.getSelectedIndex() == 2){//콤보박스가 이름으로 선택된 경우

					DBmgr.sql = "select * from ulist where u_name like '%" + tfsearch.getText() + "%'";

				}else if(cbsearch.getSelectedIndex() == 3){//콤보박스가 생년월일로 선택된 경우

					DBmgr.sql = "select * from ulist where u_bd like '%" + tfsearch.getText() + "%'";

				}else if(cbsearch.getSelectedIndex() == 4) {//콤보박스가 회원 등급으로 선택된 경우

					DBmgr.sql = "select * from ulist where u_grade like '%" + tfsearch.getText() + "%'";
				}

			}
			//검색된 조건으로
			//table()을 실행한다.
			table();

		}else if(e.getSource().equals(btorder)){//구매내역 보기 버튼을 클릭한 경우
			
			if(table.getSelectedRowCount()==0) {

				JOptionPane.showMessageDialog(null, "구매내역을 볼 회원을 선택해주세요.", "메시지", JOptionPane.WARNING_MESSAGE);

			}
			else {
			//테이블에서 선택된 행의 회원 id로 검색하여 DB를 뽑아오고
			DBmgr.sql = "select * from ulist where u_id = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
			list = mgr.user();
			bean = list.get(0);

			//관리자가 회원의 구매내역을 보는 상황을 져장한다.
			Users_Main.ch = 2;

			//구매내역 클래스에서 사용하기 위해 static한 형태로 저장한다.
			no = bean.getUno();
			id = bean.getUid();
			name = bean.getUname();

			dispose();
			new Orderlist();//OrderList() 창을 새로 띄운다.
			}

		}else if(e.getSource().equals(btcancel)) {//취소 버튼을 클린한 경우

			dispose();//현재 창을 종료하고
			new Admin_Main();//Admin() 창을 새로 띄운다.

		}		
	}

}

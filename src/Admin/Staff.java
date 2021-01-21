package Admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import Setting.Bean;
import Setting.DBmgr;
import Users.Users_Main;

public class Staff extends JFrame implements ActionListener{

	Insert insert;
	Edit edit;
	Users_Main main;

	Container c = getContentPane();

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	int check;
	String find, find1, find2, find3;
	int no;

	JLabel imgdirec = new JLabel();
	JLabel imgactor1 = new JLabel();
	JLabel imgactor2 = new JLabel();
	JLabel imgactor3 = new JLabel();

	JTextField tfdi = new JTextField();//감독
	JTextField tfac1 = new JTextField();//배우1
	JTextField tfro1 = new JTextField();//역1
	JTextField tfac2 = new JTextField();//배우2
	JTextField tfro2 = new JTextField();//역2
	JTextField tfac3 = new JTextField();//배우3
	JTextField tfro3 = new JTextField();//역3

	JLabel lbdi = new JLabel("감독");
	JLabel lbem = new JLabel("");
	JLabel lbac1 = new JLabel("배우");
	JLabel lbro1 = new JLabel("역");
	JLabel lbac2 = new JLabel("배우");
	JLabel lbro2 = new JLabel("역");
	JLabel lbac3 = new JLabel("배우");
	JLabel lbro3 = new JLabel("역");

	JButton btdi = new JButton("사진 등록");
	JButton btac1 = new JButton("사진 등록");
	JButton btac2 = new JButton("사진 등록");
	JButton btac3 = new JButton("사진 등록");
	JButton btsave = new JButton("등록");
	JButton btcancel = new JButton("취소");

	JPanel p1 = new JPanel(new GridLayout(1, 4, 10, 10));
	JPanel p2 = new JPanel(new GridLayout(1, 4, 10, 10));
	JPanel p3 = new JPanel(new GridLayout(2, 2, -90, 3));
	JPanel p4 = new JPanel(new GridLayout(2, 2, -90, 3));
	JPanel p5 = new JPanel(new GridLayout(2, 2, -90, 3));
	JPanel p6 = new JPanel(new GridLayout(2, 2, -90, 3));
	JPanel p7 = new JPanel();

	ArrayList<Bean> list =new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Staff(String title, Insert insert) {//MInsert 창에서 출연자 등록 창을 실행했을 때

		this.insert = insert;
		check = 1;//insert인 경우

		make();

		this.setTitle(title);//타이블바의 제목 설정

	}
	public Staff(String title, Edit edit, int no) {//MEdit 창에서 출연자 등록 창을 실행했을 때

		this.edit = edit;
		this.no = no;//edit의 영화번호
		check = 2;//edit인 경우

		make();

		image();

		this.setTitle(title);//타이블바의 제목 설정

	}
	public Staff(String title, Users_Main main, int no) {//Main 창에서 출연자 등록 창을 실행했을 때

		this.main = main;
		this.no = no;//main의 영화번호
		check = 3;//main인 경우

		make();

		image();

		this.setTitle(title);//타이블바의 제목 설정

	}

	public static void main(String[] args) {
		//new MStaff();
	}

	private void make() {
		this.setLayout(null);

		//배경색 설정
		c.setBackground(Color.WHITE);
		p1.setBackground(Color.WHITE);
		p2.setBackground(Color.WHITE);
		p3.setBackground(Color.WHITE);
		p4.setBackground(Color.WHITE);
		p5.setBackground(Color.WHITE);
		p6.setBackground(Color.WHITE);
		p7.setBackground(Color.WHITE);
		btdi.setBackground(new Color(69, 68, 66));
		btac1.setBackground(new Color(69, 68, 66));
		btac2.setBackground(new Color(69, 68, 66));
		btac3.setBackground(new Color(69, 68, 66));
		btsave.setBackground(new Color(69, 68, 66));
		btcancel.setBackground(new Color(69, 68, 66));

		//글자색 설정
		btdi.setForeground(Color.WHITE);
		btac1.setForeground(Color.WHITE);
		btac2.setForeground(Color.WHITE);
		btac3.setForeground(Color.WHITE);
		btsave.setForeground(Color.WHITE);
		btcancel.setForeground(Color.WHITE);

		//전체 폰트 설정
		c.setFont(new Font("나눔바른고딕", Font.BOLD, 12));

		//아이콘 삽입과 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정
		btdi.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btac1.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btac2.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btac3.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btsave.setIcon(new ImageIcon(new ImageIcon("resource/icon/save.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btcancel.setIcon(new ImageIcon(new ImageIcon("resource/icon/mcancel.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

		//라벨에 테두리 설정
		imgdirec.setBorder(new LineBorder(Color.BLACK));
		imgactor1.setBorder(new LineBorder(Color.BLACK));
		imgactor2.setBorder(new LineBorder(Color.BLACK));
		imgactor3.setBorder(new LineBorder(Color.BLACK));

		//이미지 라벨 부분
		p1.add(imgdirec);
		p1.add(imgactor1);
		p1.add(imgactor2);
		p1.add(imgactor3);

		//이미지 등록 버튼 부분
		p2.add(btdi);
		p2.add(btac1);
		p2.add(btac2);
		p2.add(btac3);

		//감독
		p3.add(lbdi);
		p3.add(tfdi);
		p3.add(lbem);

		//배우1
		p4.add(lbac1);
		p4.add(tfac1);
		p4.add(lbro1);
		p4.add(tfro1);

		//배우2
		p5.add(lbac2);
		p5.add(tfac2);
		p5.add(lbro2);
		p5.add(tfro2);

		//배우3
		p6.add(lbac3);
		p6.add(tfac3);
		p6.add(lbro3);
		p6.add(tfro3);

		//위치 설정
		p1.setBounds(20, 15, 630, 170);
		p2.setBounds(20, 190, 630, 30);
		p3.setBounds(20, 230, 150, 60);
		p4.setBounds(180, 230, 150, 60);
		p5.setBounds(340, 230, 150, 60);
		p6.setBounds(500, 230, 150, 60);
		p7.setBounds(230, 310, 200, 35);

		if(check == 1 || check == 2) {//MInsert와 MEdit일 경우

			//등록과 취소 버튼을 넣는다.
			p7.add(btsave);
			p7.add(btcancel);
			//이미지 등록 버튼을 넣는다.
			this.add(p2);

		}else if(check == 3) {//Main일 경우
			//텍스트 필드를 수정이 불가능하게 설정한다.
			tfdi.setEditable(false);
			tfac1.setEditable(false);
			tfro1.setEditable(false);
			tfac2.setEditable(false);
			tfro2.setEditable(false);
			tfac3.setEditable(false);
			tfro3.setEditable(false);

			//확인 버튼을 넣는다.
			btcancel.setText("확인");
			btcancel.setIcon(new ImageIcon(new ImageIcon("DBFiles/icon/ok.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
			p7.add(btcancel);
		}

		btdi.addActionListener(this);
		btac1.addActionListener(this);
		btac2.addActionListener(this);
		btac3.addActionListener(this);
		btsave.addActionListener(this);
		btcancel.addActionListener(this);

		this.add(p1);
		this.add(p3);
		this.add(p4);
		this.add(p5);
		this.add(p6);
		this.add(p7);

		this.addWindowListener(new WindowAdapter() {@Override//window 종료버튼(X) 설정
			public void windowClosing(WindowEvent arg0) {
			if(check == 1) {//MInsert일 경우
				insert.setVisible(true);//작성하던 영화등록 창을 다시 보이게 설정한다.
			}else if(check == 2) {//MEdit일 경우
				edit.setVisible(true);//작성하던 영화관리 창을 다시 보이게 설정한다.
			}else if(check ==3) {//Main일 경우
				main.setVisible(true);//작성하던 Main 창을 다시 보이게 설정한다.
			}
		}
		});

		this.setIconImage(changedImg);//타이틀바의 아이콘 설정
		this.setSize(680, 400);//Frame 출력 크기 설정
		this.setLocationRelativeTo(null);//Frame 출력 위치 설정
		this.setResizable(false);//Frame 사이즈 조절 금지
		this.setVisible(true);//실제로 Frame을 화면에 출력

	}
	private void image() {
		//영화번호로 stafflist를 검색한다.
		DBmgr.sql = "select * from stafflist where m_no = '" + no + "'";
		list = mgr.staff();
		bean = list.get(0);

		//감독의 사진을 가져와서 크기와 텍스트필드 설정을 해준다.
		find= "DBFiles\\direc\\" + bean.getStdirec() + ".jpg";
		imgdirec.setIcon(new ImageIcon(new ImageIcon(find).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
		tfdi.setText(bean.getStdirec());

		//배우1의 사진을 가져와서 크기와 텍스트필드 설정을 해준다.
		find1= "DBFiles\\actor\\" + bean.getStactor1() + ".jpg";
		imgactor1.setIcon(new ImageIcon(new ImageIcon(find1).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
		tfac1.setText(bean.getStactor1());
		tfro1.setText(bean.getStrole1());

		//배우2의 사진을 가져와서 크기와 텍스트필드 설정을 해준다.
		find2= "DBFiles\\actor\\" + bean.getStactor2() + ".jpg";
		imgactor2.setIcon(new ImageIcon(new ImageIcon(find2).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
		tfac2.setText(bean.getStactor2());
		tfro2.setText(bean.getStrole2());

		//배우3의 사진을 가져와서 크기와 텍스트필드 설정을 해준다.
		find3= "DBFiles\\actor\\" + bean.getStactor3() + ".jpg";
		imgactor3.setIcon(new ImageIcon(new ImageIcon(find3).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
		tfac3.setText(bean.getStactor3());
		tfro3.setText(bean.getStrole3());
	}

	JFileChooser fc1= new JFileChooser();
	JFileChooser fc2= new JFileChooser();
	JFileChooser fc3= new JFileChooser();
	JFileChooser fc4= new JFileChooser();
	int fcheck = 0;//파일 저장버튼 눌렸는지 확인하기 위한 변수
	int ok1 = 0;//이미지를 설정하였는지 확인하기 위한 변수
	int ok2 = 0;//이미지를 설정하였는지 확인하기 위한 변수
	int ok3 = 0;//이미지를 설정하였는지 확인하기 위한 변수
	int ok4 = 0;//이미지를 설정하였는지 확인하기 위한 변수

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btdi)) {//감독 이미지 등록 버튼을 클릭했을 경우

			//파일 필터 지정
			fc1.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//열기용 창을 만든다.
			fcheck = fc1.showOpenDialog(this);

			if(fcheck == JFileChooser.APPROVE_OPTION ) {//저장버튼을 클릭했는지 확인
				//선택된 파일을 가져온다.
				File file = fc1.getSelectedFile();
				//선택된 파일을 라벨에 설정한다. 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정
				imgdirec.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok1 = 1;//이미지 등록 확인
			}
		}else if(e.getSource().equals(btac1)) {//배우1 이미지 등록 버튼을 클릭했을 경우

			//파일 필터 지정
			fc2.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//열기용 창을 만든다.
			fcheck = fc2.showOpenDialog(this);

			if(fcheck == JFileChooser.APPROVE_OPTION ) {//저장버튼을 클릭했는지 확인
				//선택된 파일을 가져온다.
				File file = fc2.getSelectedFile();
				//선택된 파일을 라벨에 설정한다. 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정
				imgactor1.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok2 = 1;
			}
		}else if(e.getSource().equals(btac2)) {//배우2 이미지 등록 버튼을 클릭했을 경우

			//파일 필터 지정
			fc3.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//열기용 창을 만든다.
			fcheck = fc3.showOpenDialog(this);

			if(fcheck == JFileChooser.APPROVE_OPTION ) {//저장버튼을 클릭했는지 확인
				//선택된 파일을 가져온다.
				File file = fc3.getSelectedFile();
				//선택된 파일을 라벨에 설정한다. 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정
				imgactor2.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok3 = 1;
			}
		}else if(e.getSource().equals(btac3)) {//배우3 이미지 등록 버튼을 클릭했을 경우

			//파일 필터 지정
			fc4.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//열기용 창을 만든다.
			fcheck = fc4.showOpenDialog(this);

			if(fcheck == JFileChooser.APPROVE_OPTION ) {//저장버튼을 클릭했는지 확인
				//선택된 파일을 가져온다.
				File file = fc4.getSelectedFile();
				//선택된 파일을 라벨에 설정한다. 이미지 크키 변환 설정과 속도보다는 Smoothing을 중요시 하게 하는 설정
				imgactor3.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok4 = 1;
			}
		}else if(e.getSource().equals(btsave)) {//저장 버튼을 클릭했을 경우

			if(check == 1) {//MInsert일 경우

				if(tfdi.getText().equals("") || tfac1.getText().equals("") || tfro1.getText().equals("") || tfac2.getText().equals("") || tfro2.getText().equals("") || tfac3.getText().equals("") || tfro3.getText().equals("")) {
					//텍스트 필드가 하나라도 공백이라면 에러 메시지를 띄운다.
					JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "메시지", JOptionPane.ERROR_MESSAGE);

				}
				else if(ok1 == 0 || ok2 == 0 || ok3 == 0 || ok4 == 0) {
					//이미지가 등록되지 않았다면 에러 메시지를 띄운다.
					JOptionPane.showMessageDialog(null, "사진이 등록 되지않았습니다.", "메시지", JOptionPane.ERROR_MESSAGE);

				}else {
					//MInsert에서 String 형태로 저장시켜놓은 insert문을 실행시킨다.
					DBmgr.sql = Insert.insert;
					mgr.update();
					//insert 되면서 영화번호가 생성이 되는데 여기서 영화번호를 검색하여					
					DBmgr.sql = "select * from mlist where m_name = '" + Insert.mname + "'";
					list = mgr.movie();
					bean = list.get(0);
					//mno에 생성된 영화번호를 저장하고
					int mno = bean.getMno();
					//그 영화번호를 출연진정보의 insert문에 사용한다.
					DBmgr.sql = "insert into stafflist(st_no, m_no, st_direc, st_actor1, st_role1, st_actor2, st_role2, st_actor3, st_role3) values(sst_no.nextval, '" + mno + "', '" + tfdi.getText() + "', '" + tfac1.getText() 
					+ "', '" + tfro1.getText() + "', '" + tfac2.getText() + "', '" + tfro2.getText() + "', '" + tfac3.getText() + "', '" + tfro3.getText() + "')";
					list = mgr.update();

					try {
						//출연진 정보를 모두 불러와서 이미지 파일이 똑같은 것이 없는지 확인한다.
						DBmgr.sql = "select * from stafflist";
						list = mgr.staff();
						bean = list.get(0);

						if(!tfdi.getText().equals(bean.getStdirec())) {//감독 정보에 같은 것이 없다면 새로 저장한다.

							BufferedImage bi1 = ImageIO.read(new File(fc1.getSelectedFile()+""));
							File fd = new File("DBFiles\\direc\\" + tfdi.getText() + ".jpg");
							ImageIO.write(bi1, "jpg", fd);

						}
						if(!tfac1.getText().equals(bean.getStactor1()) && !tfac1.getText().equals(bean.getStactor2()) && !tfac1.getText().equals(bean.getStactor3()) ) {//배우1이 배우정보에 같은 것이 없다면 새로 저장한다.

							BufferedImage bi2 = ImageIO.read(new File(fc2.getSelectedFile()+""));
							File f1 = new File("DBFiles\\actor\\" + tfac1.getText() + ".jpg");
							ImageIO.write(bi2, "jpg", f1);

						}
						if(!tfac2.getText().equals(bean.getStactor1()) && !tfac2.getText().equals(bean.getStactor2()) && !tfac2.getText().equals(bean.getStactor3()) ) {//배우2가 배우정보에 같은 것이 없다면 새로 저장한다.

							BufferedImage bi3 = ImageIO.read(new File(fc3.getSelectedFile()+""));
							File f2 = new File("DBFiles\\actor\\" + tfac2.getText() + ".jpg");
							ImageIO.write(bi3, "jpg", f2);

						}
						if(!tfac3.getText().equals(bean.getStactor1()) && !tfac3.getText().equals(bean.getStactor2()) && !tfac3.getText().equals(bean.getStactor3()) ) {//배우3이 배우정보에 같은 것이 없다면 새로 저장한다.

							BufferedImage bi4 = ImageIO.read(new File(fc4.getSelectedFile()+""));
							File f3 = new File("DBFiles\\actor\\" + tfac3.getText() + ".jpg");
							ImageIO.write(bi4, "jpg", f3);

						}

					}catch(Exception e2) {

					}
					//알맞게 실행이 되었다면 출연자 정보가 등록되었다는 정보 메시지를 띄운다.
					JOptionPane.showMessageDialog(null, "출연자 정보가 등록되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);

					dispose();//현재 창을 종료하고
					insert.setVisible(true);//작성하던 영화등록 창을 다시 보이게 설정한다.

					//또한 영화정보도 등록되었다는 정보 메시지를 띄운다.
					JOptionPane.showMessageDialog(null, "영화가 등록되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);

					insert.dispose();//작성하던 MInsert창을 종료하고
					new Insert();//새로운 MInsert창을 생성한다.
				}
			}else if(check == 2) {//MEdit일 경우

				if(tfdi.getText().equals("") || tfac1.getText().equals("") || tfro1.getText().equals("") || tfac2.getText().equals("") || tfro2.getText().equals("") || tfac3.getText().equals("") || tfro3.getText().equals("")) {
					//텍스트 필드가 하나라도 공백이라면 에러 메시지를 띄운다.
					JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "메시지", JOptionPane.ERROR_MESSAGE);

				}else {
					//update하는 sql문을 실행한다.
					DBmgr.sql = "update stafflist set st_direc = '" + tfdi.getText() + "', st_actor1 = '" + tfac1.getText() + "', st_role1 = '" + tfro1.getText() + "', st_actor2 = '" + tfac2.getText() 
					+ "', st_role2 = '" + tfro2.getText() + "', st_actor3 = '" + tfac3.getText() + "', st_role3 = '" + tfro3.getText() + "' where m_no = '" + Edit.no + "'";
					list = mgr.update();

					try {

						if(fc1.getSelectedFile()==null) {//감독의 이미지가 선택이 안되어있다면
							//원래의 이미지 파일을 읽어와서 BufferedImage에 넣는다.
							BufferedImage bi = ImageIO.read(new File(find));
							//DBFiles\\direc\\라는 경로와 파일이름을 저장해주고
							File f2 =new File("DBFiles\\direc\\" + tfdi.getText() + ".jpg");
							//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
							ImageIO.write(bi, "jpg", f2);

						}else {//새로운 이미지가 선택되었다면
							//새로운 이미지 파일을 읽어와서 BufferedImage에 넣는다.
							BufferedImage bi = ImageIO.read(new File(fc1.getSelectedFile()+""));
							//새로운 이미지 파일을 DBFiles\\direc\\라는 경로와 파일이름을 저장해주고
							File f2 = new File("DBFiles\\direc\\" + tfdi.getText() + ".jpg");
							//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
							ImageIO.write(bi, "jpg", f2);

						}
						if(fc2.getSelectedFile()==null) {//배우1의 이미지가 선택이 안되어있다면
							//원래의 이미지 파일을 읽어와서 BufferedImage에 넣는다.
							BufferedImage bi = ImageIO.read(new File(find1));
							//DBFiles\\actor\\라는 경로와 파일이름을 저장해주고
							File f2 =new File("DBFiles\\actor\\" + tfac1.getText() + ".jpg");
							//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
							ImageIO.write(bi, "jpg", f2);

						}else {//새로운 이미지가 선택되었다면
							//새로운 이미지 파일을 읽어와서 BufferedImage에 넣는다.
							BufferedImage bi = ImageIO.read(new File(fc2.getSelectedFile()+""));
							//새로운 이미지 파일을 DBFiles\\actor\\라는 경로와 파일이름을 저장해주고
							File f2 = new File("DBFiles\\actor\\" + tfac1.getText() + ".jpg");
							//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
							ImageIO.write(bi, "jpg", f2);

						}
						if(fc3.getSelectedFile()==null) {//배우2의 이미지가 선택이 안되어있다면
							//원래의 이미지 파일을 읽어와서 BufferedImage에 넣는다.
							BufferedImage bi = ImageIO.read(new File(find2));
							//DBFiles\\actor\\라는 경로와 파일이름을 저장해주고
							File f2 =new File("DBFiles\\actor\\" + tfac2.getText() + ".jpg");
							//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
							ImageIO.write(bi, "jpg", f2);

						}else {//새로운 이미지가 선택되었다면
							//새로운 이미지 파일을 읽어와서 BufferedImage에 넣는다.
							BufferedImage bi = ImageIO.read(new File(fc3.getSelectedFile()+""));
							//새로운 이미지 파일을 DBFiles\\actor\\라는 경로와 파일이름을 저장해주고
							File f2 = new File("DBFiles\\actor\\" + tfac2.getText() + ".jpg");
							//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
							ImageIO.write(bi, "jpg", f2);

						}
						if(fc4.getSelectedFile()==null) {//배우3의 이미지가 선택이 안되어있다면
							//원래의 이미지 파일을 읽어와서 BufferedImage에 넣는다.
							BufferedImage bi = ImageIO.read(new File(find3));
							//DBFiles\\actor\\라는 경로와 파일이름을 저장해주고
							File f2 =new File("DBFiles\\actor\\" + tfac3.getText() + ".jpg");
							//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
							ImageIO.write(bi, "jpg", f2);

						}else {//새로운 이미지가 선택되었다면
							//새로운 이미지 파일을 읽어와서 BufferedImage에 넣는다.
							BufferedImage bi = ImageIO.read(new File(fc4.getSelectedFile()+""));
							//새로운 이미지 파일을 DBFiles\\actor\\라는 경로와 파일이름을 저장해주고
							File f2 = new File("DBFiles\\actor\\" + tfac3.getText() + ".jpg");
							//f2인 해당 경로에 bi인 파일을 이미지를 저장해준다.
							ImageIO.write(bi, "jpg", f2);

						}

					}catch(Exception e2) {

					}
					//출연자 정보가 잘 수정이 되었다는 정보 메시지 창을 띄어준다.
					JOptionPane.showMessageDialog(null, "출연자 정보가 수정되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);

					dispose();//현재 창을 종료하고
					edit.setVisible(true);//작성하던 영화관리 창을 다시 보이게 설정한다.

				}

			}

		}else if(e.getSource().equals(btcancel)) {//취소 버튼을 클릭했을 경우
			if(check == 1) {//MInsert일 경우
				dispose();//현재 Frame을 종료하고
				insert.setVisible(true);//작성하던 영화등록 창을 다시 보이게 설정한다.
			}else if(check == 2) {//MEdit일 경우
				dispose();//현재 Frame을 종료하고
				edit.setVisible(true);//작성하던 영화관리 창을 다시 보이게 설정한다.
			}else if(check == 3) {//Main일 경우
				dispose();//현재 Frame을 종료하고
				main.setVisible(true);//작성하던 main 창을 다시 보이게 설정한다.
			}
		}

	}
}

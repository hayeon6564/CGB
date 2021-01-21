package Users;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import Setting.Bean;
import Setting.DBmgr;

public class Chart extends JFrame implements ActionListener {
	
	private ImageIcon originIcon;
	private Image originImg, changedImg;

	private String name[] = new String[5]; //구매 내역이 많은 영화이름 5개 저장할 배열
	private String mName[] = new String[5]; //구매 내역이 많은 영화포스터 5개 저장할 배열

	private JButton bt1, bt2, bt3, bt4, bt5, bt6; //장르 선택 버튼

	private JLabel title; //인기 영화 순위
	private String genre = "SF"; //버튼 text를 얻어올 string

	private JLabel img1, img2, img3, img4, img5; //top5영화 포스터
	private JLabel lb1, lb2, lb3, lb4, lb5; //top5영화 이름

	private DBmgr mgr = new DBmgr(); //원하는 값 가져올 함수가 담겨있는 클래스
	private ArrayList<Bean> list = new ArrayList<Bean>(); //sql문 처리, list로 가져온 값 각각 나눠서 저장
	private Bean bean; //원하는 값 가져올 함수가 담겨있는 클래스

	public Chart() {

		// ImageIcon 객체를  생성한다.
		originIcon = new ImageIcon("resource/icon/pop.png");
		// ImageIcon에서 Image를 추출한다.
		originImg = originIcon.getImage();
		// 추출된 Image의 크기를 조절해서 새로운 Image객체를 생성한다.
		changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		setIconImage(changedImg);

		ImageIcon genre1 = new ImageIcon("resource\\icon\\sf.png"); //장르 선택하는 버튼에 넣을 아이콘
		ImageIcon genre2 = new ImageIcon("resource\\icon\\scary.png");
		ImageIcon genre3 = new ImageIcon("resource\\icon\\romance.png");
		ImageIcon genre4 = new ImageIcon("resource\\icon\\animation.png");
		ImageIcon genre5 = new ImageIcon("resource\\icon\\action.png");
		ImageIcon genre6 = new ImageIcon("resource\\icon\\comedy.png");

		bt1 = new JButton("SF", genre1); //장르 선택하는 버튼
		bt2 = new JButton("공포", genre2);
		bt3 = new JButton("로맨스", genre3);
		bt4 = new JButton("애니메이션", genre4);
		bt5 = new JButton("액션", genre5);
		bt6 = new JButton("코미디", genre6);

		bt1.setBorderPainted(false);
		bt2.setBorderPainted(false);
		bt3.setBorderPainted(false);
		bt4.setBorderPainted(false);
		bt5.setBorderPainted(false);
		bt6.setBorderPainted(false);

		bt1.setBackground(Color.white);
		bt2.setBackground(Color.white);
		bt3.setBackground(Color.white);
		bt4.setBackground(Color.white);
		bt5.setBackground(Color.white);
		bt6.setBackground(Color.white);

		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		bt6.addActionListener(this);

		title = new JLabel("인기 SF 영화 순위", JLabel.CENTER);
		title.setFont(new Font("나눔바른고딕", Font.BOLD, 50));

		ImageIcon imgFirst = new ImageIcon("DBFiles\\Icon\\first.png"); //top5 트로피
		ImageIcon imgSecond = new ImageIcon("DBFiles\\Icon\\second.png");
		ImageIcon imgThird = new ImageIcon("DBFiles\\Icon\\third.png");
		ImageIcon imgFourth = new ImageIcon("DBFiles\\Icon\\fourth.png");
		ImageIcon imgFifth = new ImageIcon("DBFiles\\Icon\\fifth.png");
		JLabel first = new JLabel("", imgFirst, JLabel.CENTER);
		JLabel second = new JLabel("", imgSecond, JLabel.CENTER);
		JLabel third = new JLabel("", imgThird, JLabel.CENTER);
		JLabel fourth = new JLabel("", imgFourth, JLabel.CENTER);
		JLabel fifth = new JLabel("", imgFifth, JLabel.CENTER);

		img1 = new JLabel(); //top5영화 포스터
		img2 = new JLabel();
		img3 = new JLabel();
		img4 = new JLabel();
		img5 = new JLabel();

		img1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); ////top5영화 포스터 외곽선
		img2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		img3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		img4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		img5.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lb1 = new JLabel("", JLabel.CENTER); //top5영화 이름
		lb2 = new JLabel("", JLabel.CENTER); 
		lb3 = new JLabel("", JLabel.CENTER); 
		lb4 = new JLabel("", JLabel.CENTER); 
		lb5 = new JLabel("", JLabel.CENTER);

		lb1.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		lb2.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		lb3.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		lb4.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		lb5.setFont(new Font("나눔바른고딕", Font.BOLD, 16));

		JPanel pan1 = new JPanel(new GridLayout(1, 5, 20, 10)); //top5영화 포스터
		pan1.setBackground(Color.WHITE);

		pan1.add(img1);
		pan1.add(img2);
		pan1.add(img3);
		pan1.add(img4);
		pan1.add(img5);

		JPanel pan2 = new JPanel(new GridLayout(1, 5, 10, 10)); //top5영화 이름
		pan2.setBackground(Color.WHITE);

		pan2.add(lb1);
		pan2.add(lb2);
		pan2.add(lb3);
		pan2.add(lb4);
		pan2.add(lb5);

		JPanel pan3 = new JPanel(new GridLayout(1, 6, 0, 0)); //장르선택 버튼

		pan3.add(bt1);
		pan3.add(bt2);
		pan3.add(bt3);
		pan3.add(bt4);
		pan3.add(bt5);
		pan3.add(bt6);

		JPanel pan4 = new JPanel(new GridLayout(1, 5, 20, 0)); //top5 트로피
		pan4.setBackground(new Color(255, 0, 0, 0));

		pan4.add(first);
		pan4.add(second);
		pan4.add(third);
		pan4.add(fourth);
		pan4.add(fifth);

		JPanel pan5 = new JPanel(); //전체
		pan5.setLayout(null);
		pan5.setBackground(Color.WHITE);
		pan5.add(title);
		pan5.add(pan3);
		pan5.add(pan4);
		pan5.add(pan2);
		pan5.add(pan1);

		title.setBounds(0, 80, 1056, 50);
		pan1.setBounds(20, 230, 1000, 250);
		pan2.setBounds(20, 490, 1000, 50);
		pan3.setBounds(0, 0, 1056, 30);
		pan4.setBounds(-38, 140, 1000, 100);

		add(pan5);

		paint();

		setSize(1056, 600);
		setTitle("인기차트");
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {@Override
			public void windowClosing(WindowEvent arg0) {
			dispose(); //X 누를 때의 이벤트
			new Users_Main();
		}
		});
	}

	public static void main(String[] args) {
		new Chart();
	}

	public void paint() {
		//영화DB와 구매내역DB에서 영화번호가 같은 내역을 각 장르 내에서 영화이름별로 합산하여 높은 순으로 저장
		DBmgr.sql = "select mlist.m_name, count(*) from olist inner join mlist on olist.m_no = mlist.m_no where olist.o_genre = '"
				+ genre + "' group by m_name having count(*) >= 0 order by count(*) desc";
		list = mgr.chartjoin();

		for (int i = 0; i < 5; i++) {

			bean = list.get(i);

			name[i] = bean.getMname(); //영화 이름 가져와서 
			mName[i] = "DBFiles\\image\\" + name[i] + ".jpg"; //영화 포스터 경로 저장
		}
		//아이콘을 이미지로 가져와 크기조절, 품질 유지 설정
		img1.setIcon(new ImageIcon(new ImageIcon(mName[0]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH))); 
		img2.setIcon(new ImageIcon(new ImageIcon(mName[1]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));
		img3.setIcon(new ImageIcon(new ImageIcon(mName[2]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));
		img4.setIcon(new ImageIcon(new ImageIcon(mName[3]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));
		img5.setIcon(new ImageIcon(new ImageIcon(mName[4]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));

		lb1.setText(name[0]); //해당 영화 이름 표시
		lb2.setText(name[1]);
		lb3.setText(name[2]);
		lb4.setText(name[3]);
		lb5.setText(name[4]);
	}

	@Override
	public void actionPerformed(ActionEvent e) { //각 장르 버튼 눌리면 해당 장르에 대한 순위 라벨 나타남
		Object obj = e.getSource();
		if (obj == bt1) {
			title.setText("인기 SF 영화 순위");
			genre = "SF";
		} else if (obj == bt2) {
			title.setText("인기 공포 영화 순위");
			genre = "공포";
		} else if (obj == bt3) {
			title.setText("인기 로맨스 영화 순위");
			genre = "로맨스";
		} else if (obj == bt4) {
			title.setText("인기 애니메이션 영화 순위");
			genre = "애니메이션";
		} else if (obj == bt5) {
			title.setText("인기 액션 영화 순위");
			genre = "액션";
		} else if (obj == bt6) {
			title.setText("인기 코미디 영화 순위");
			genre = "코미디";
		}
		paint(); //해당 장르에 맞는 포스터 나타내기
	}
}

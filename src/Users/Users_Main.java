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

	Calendar cal = Calendar.getInstance();	// getInstance() �޼ҵ� ����Ͽ� ���� ��¥�� �ð��� ��ü�� ���´�.

	JLabel img2 = new JLabel(); //pan

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JLabel lbl1 = new JLabel("ȯ���մϴ�. �̱�δ��� �� ���� ����Ʈ�� 14210P�Դϴ�.");
	JLabel lbl2 = new JLabel("��ȭ��:");  //pan3
	JLabel lbl3 = new JLabel("��������:"); //pan3
	JLabel lbl4 = new JLabel("��       ��:"); //pan3
	JLabel lbl5 = new JLabel("������¥:"); //pan3
	JLabel lbl6 = new JLabel("�󿵽ð�:"); //pan3

	JTextField tf1 = new JTextField(20); //pan3
	JTextField tf2 = new JTextField(); //pan3
	JTextField tf3 = new JTextField(15); //pan3
	JTextField tf4 = new JTextField(); //pan3
	JTextField tf5 = new JTextField(); //pan3

	JButton btn1 = new JButton("���ų���"); // pan1
	JButton btn2 = new JButton("��ٱ���"); //pan1
	JButton btn3 = new JButton("�α� ��ȭ"); // pan1
	JButton btn4 = new JButton("ȸ������ ����"); // pan1
	JButton btn5 = new JButton("Logout"); // pan1

	JButton btn6 = new JButton("SF"); // pan2
	JButton btn7 = new JButton("����"); // pan2
	JButton btn8 = new JButton("�θǽ�"); // pan2
	JButton btn9 = new JButton("�ִϸ��̼�"); // pan2
	JButton btn10 = new JButton("�׼�"); // pan2
	JButton btn11 = new JButton("�ڹ̵�"); // pan2
	JButton btn12 = new JButton("�⿬�� ����"); //pan4
	JButton btn13 = new JButton("��ȭ ���"); //pan4
	JButton btn14 = new JButton("�����ϱ�"); //pan4
	JButton btn15 = new JButton("ä��"); 

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

	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");	// ��¥�� ���ϴ� �������� ����ϱ�

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

		img2.setBorder(BorderFactory.createLineBorder(Color.BLACK));	// ��ȭ ������ �׵θ�
		tf1.setEditable(false); // ��ȭ�� tf
		tf2.setEditable(false); // �������� tf
		tf3.setEditable(false); // ���� tf
		tf4.setEditable(false); // ������¥ tf
		tf5.setEditable(false); // �󿵽ð� ta

		setLayout(null);

		pan1.add(btn1); // ���ų���
		pan1.add(btn2); // ��ٱ���
		pan1.add(btn3); // �α⿵ȭ Top5
		pan1.add(btn4); // ȸ����������
		pan1.add(btn5); // Logout

		// ��Ʈ ����
		c.setFont(new Font("�����ٸ����", Font.BOLD,12));
		lbl1.setFont(new Font("�����ٸ����", Font.BOLD,17));		
		btn9.setFont(new Font("�����ٸ����",Font.BOLD,12));
		btn15.setFont(new Font("�����ٸ����",Font.BOLD,14));

		pan2.add(btn6); // SF
		pan2.add(btn7); // ����
		pan2.add(btn8); // �θǽ�
		pan2.add(btn9); // �ִϸ��̼�
		pan2.add(btn10); // �׼�
		pan2.add(btn11); // �ڹ̵�

		add(btn15);	// ä��

		lbl1.setBounds(5, 5, 600, 30);
		pan1.setBounds(0, 40, 600, 35);
		pan2.setBounds(5, 80, 90, 250);
		jsp.setBounds(100, 80, 650, 450);
		btn15.setBounds(5, 500, 90, 30);

		add(lbl1);
		add(pan1);
		add(jsp);
		add(pan2);

		pan5.add(lbl2); pan5.add(tf1); // ��ȭ��: 
		pan3.add(lbl3); pan3.add(tf2); // ��������: 
		pan3.add(lbl4); pan3.add(tf3); // ����: 
		pan6.add(lbl5); pan6.add(tf4); // ������¥: 
		pan6.add(lbl6); pan6.add(tf5); // �ٰŸ�: 

		pan4.add(btn12); // �⿬�� ����
		pan4.add(btn13); // ��ٱ��Ͽ� ���
		pan4.add(btn14); // �����ϱ�

		img2.setBounds(850, 80, 220, 220); 
		pan5.setBounds(750, 330, 400, 50);	
		pan3.setBounds(800, 380, 170, 50);	
		pan4.setBounds(770, 490, 400, 35);	
		pan6.setBounds(970, 380, 170, 50);	

		add(img2);	// ������
		add(pan3);	// �������� ����
		add(pan4);	// �⿬�� ���� ��ٱ��Ͽ� ��� �����ϱ�
		add(pan5);	// ��ȭ��
		add(pan6);	// ������¥ �ٰŸ�

		// ulist���� u_id���� Loing.id�� ���� ���� �̾ƿ´�.
		DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
		list = mgr.user();
		bean = list.get(0);

		lbl1.setText("ȯ���մϴ�. " + bean.getUname() + "���� �� ���� ����Ʈ�� " + bean.getUpoint() + "P�Դϴ�.");

		set(); // ��ȭ ����Ʈ�� ���õ� �޼ҵ�

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

		// ��ư ���� ä���
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

		// �ؽ�Ʈ ����
		btn6.setForeground(Color.WHITE);	
		btn7.setForeground(Color.WHITE);	
		btn8.setForeground(Color.WHITE);	
		btn9.setForeground(Color.WHITE);	
		btn10.setForeground(Color.WHITE);	
		btn11.setForeground(Color.WHITE);	
		btn12.setForeground(Color.WHITE);	
		btn13.setForeground(Color.WHITE);	
		btn14.setForeground(Color.WHITE);	

		// ��ư �� ����
		btn6.setBackground(new Color(69, 68, 66));
		btn7.setBackground(new Color(69, 68, 66));
		btn8.setBackground(new Color(69, 68, 66));
		btn9.setBackground(new Color(69, 68, 66));
		btn10.setBackground(new Color(69, 68, 66));
		btn11.setBackground(new Color(69, 68, 66));
		btn12.setBackground(new Color(69, 68, 66));
		btn13.setBackground(new Color(69, 68, 66));
		btn14.setBackground(new Color(69, 68, 66));

		// ���� ���
		c.setBackground(Color.WHITE);
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.WHITE);
		pan3.setBackground(Color.WHITE);
		pan4.setBackground(Color.WHITE);
		pan5.setBackground(Color.WHITE);
		pan6.setBackground(Color.WHITE);
		pan.setBackground(Color.WHITE);

		// tf�� ���
		tf1.setBackground(Color.WHITE);
		tf2.setBackground(Color.WHITE);
		tf3.setBackground(Color.WHITE);
		tf4.setBackground(Color.WHITE);
		tf5.setBackground(Color.WHITE);

		setTitle("CGB");
		setSize(780, 600); // 780,600 
		setLocation(250, 100);	// â ��ġ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ������â ����� ���α׷� ����
		setResizable(false); // âũ�� �Һ�
		setVisible(true);

	}
	// ��ȭ ����Ʈ�� ���õ� �޼ҵ�
	void set() {
		pan = new JPanel();
		pan.setLayout(null);

		int size = 0; // ��Ͽ� �ִ� �������� y
		int size2 = 0;	// ��Ͽ� �ִ� �������� x
		int count = 0;	// ������ ����
		int imgname = 190; // ��ȭ ���� height

		// mlist�� m_genre�� m�� ���� ���� �̾� list�� �����Ѵ�.
		DBmgr.sql = "select * from mlist where m_genre = '" + m + "'";
		list = mgr.movie();

		for(int k =0 ; k < list.size(); k++) {
			bean = list.get(k);	// bean�� 0��°���� ���� ����

			lbl[k] = new JLabel(bean.getMname());
			img[k] = new JLabel(new ImageIcon(new ImageIcon("DBFiles\\image\\" + bean.getMname() + ".jpg").getImage().getScaledInstance(190, 190, Image.SCALE_SMOOTH)));
			mname[k] = new JLabel(bean.getMname(),JLabel.CENTER);

			img[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));	// ������ ��� �̹��� �׵θ� ��

			mname[k].setBounds(size2, imgname, 190, 30);	// ����
			img[k].setBounds(size2, size, 190, 190);	// ������ ��� �̹��� �׵θ� ũ��
			count+=1;

			pan.add(img[k]);
			pan.add(mname[k]);

			if(count == 3) { // ���ٿ� 3���� �Ǹ� �����ٷ� �Ѿ
				size += 230; // ��ȭ�� �� x
				size2 = 0;	// ��ȭ�� y
				count = 0;	// ��ȭ ����
				imgname += 230;	// ���� y
				// 3���� �ƴҶ�
			}else { 
				size2 += 220;
			}

			img[k].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// mlist���� m_genre�� m(SF, �׼�, �θǽ�...)�ΰ��� �̾ƿ� list�� ����
					DBmgr.sql = "select * from mlist where m_genre = '" + m + "'";
					list = mgr.movie();

					for(int j = 0 ; j < list.size(); j++) {
						// �����͸� �������� list�� 0������ �������� mno, mgenre, mprice�� �������� bean�� ���� �����ϰ� â ũ�⸦ �����Ѵ�.
						if(e.getSource().equals(img[j])) {
							bean = list.get(j);
							mno = bean.getMno();
							mgenre = bean.getMgenre();
							mprice = bean.getMprice();
							mage = bean.getMage();

							setSize(1200, 600);
							// bean.getMname()�� ���� .jpg ������ �������� tf1���� tf5���� DB�� �ִ� �������� ä���.
							img2.setIcon(new ImageIcon(new ImageIcon("DBFiles\\image\\" + bean.getMname() + ".jpg").getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH)));
							tf1.setText(bean.getMname());
							tf2.setText(bean.getMage());
							tf3.setText(bean.getMprice()+"");
							tf4.setText(bean.getMdate());
							tf5.setText(bean.getMtime());

							amount(); // ��޿� ���� ���ο� ���õ� �޼ҵ� 
						}
					}
				}
			});
		}
		// list����� 3���� ������ ������ ���� 0�� �ƴ� ��
		if(list.size()%3!=0) {
			size += 230;	// ��ȭ�� ��x
		}
		pan.setPreferredSize(new Dimension(630,size));	// ��ũ������ ��ü ������ ����
		jsp.setViewportView(pan);	//��ũ���� ���� pan�� �ø���
	}

	// ��޿� ���� ���ο� ���õ� �޼ҵ� 
	void amount() {
		//	ulist���� u_id�� Login.id�� ���� �̰� list�� �����ϰ� bean�� ���� ����
		DBmgr.sql = "select * from ulist where u_id = '"  + Login.id + "'";
		list = mgr.user();
		bean = list.get(0);
		//tf3(����)�� ����ȯ�� hap�� ����
		hap = Integer.parseInt(tf3.getText());
		// DB�� ����� ����� VIP�� �� 
		if(bean.getUgrade().equals("VIP")) {
			hap = (int) (hap * 0.97);
			// DB�� ����� ����� VVIP�� ��
		}else if(bean.getUgrade().equals("VVIP")) {
			hap = (int) (hap * 0.95);
			// DB�� ����� ����� SVIP�� ��
		}else if(bean.getUgrade().equals("SVIP")) {
			hap = (int) (hap * 0.9);
		}
	}
	// ����Ʈ, ��ް� ���õ� �޼ҵ�
	void gg() {
		int point;
		point = (int) (Integer.parseInt(tf3.getText()) * 0.05);

		// ulist�� u_id�� Login.id�� ���� �̾� u_point�� ������ �۾��� point�� �ٲٰ� list�� �����Ѵ�
		DBmgr.sql = "update ulist set u_point = u_point + '" + point +"' where u_id = '" + Login.id + "'";
		list = mgr.update();

		DBmgr.sql = "insert into olist(o_no, o_date, u_no, m_no, o_genre, o_price) values(so_no.nextval, '" + sf.format(System.currentTimeMillis()) + "', '" + Login.no + "', '" + mno + "' ,'" + m + "', '" + tf3.getText() + "')";
		list = mgr.update();

		JOptionPane.showMessageDialog(null, "���ŵǾ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);

		int sum = 0;
		DBmgr.sql = "select * from olist where u_no = '" + Login.no + "'";
		list = mgr.orderlist();

		for(int i= 0;i < list.size() ; i++) {
			bean = list.get(i);
			sum = sum + bean.getOprice(); //�����ݾ�
		}
		//ulist�� u_id�� Login.id�� ���� �̾� list�� �����ϰ� bean�� ���� �����Ѵ�
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
			JOptionPane.showMessageDialog(null, "�����մϴ�!\nȸ���� �����"+ grade +"�� �±��ϼ̽��ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
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
		// btn6�϶� m�� "SF"�� �����ϰ� â ũ�⸦ �����ϰ� ��ȭ ����Ʈ�� ���õ� �޼ҵ带 �����Ѵ�.
		if(e.getSource().equals(btn6)) {
			m = "SF";
			setSize(780, 600);
			set();
			// btn7�϶� m�� "����"�� �����ϰ� â ũ�⸦ �����ϰ� ��ȭ ����Ʈ�� ���õ� �޼ҵ带 �����Ѵ�.
		}else if(e.getSource().equals(btn7)) {
			m = "����";
			setSize(780, 600);
			set();
			// btn8�϶� m�� "�θǽ�"�� �����ϰ� â ũ�⸦ �����ϰ� ��ȭ ����Ʈ�� ���õ� �޼ҵ带 �����Ѵ�.
		}else if(e.getSource().equals(btn8)) {
			m = "�θǽ�";
			setSize(780, 600);
			set();
			// btn9�϶� m�� "�ִϸ��̼�"�� �����ϰ� â ũ�⸦ �����ϰ� ��ȭ ����Ʈ�� ���õ� �޼ҵ带 �����Ѵ�.
		}else if(e.getSource().equals(btn9)) {
			m = "�ִϸ��̼�";
			setSize(780, 600);
			set();
			// btn10�϶� m�� "�׼�"�� �����ϰ� â ũ�⸦ �����ϰ� ��ȭ ����Ʈ�� ���õ� �޼ҵ带 �����Ѵ�.
		}else if(e.getSource().equals(btn10)) {
			m = "�׼�";
			setSize(780, 600);
			set();
			// btn11�϶� m�� "�ڹ̵�"�� �����ϰ� â ũ�⸦ �����ϰ� ��ȭ ����Ʈ�� ���õ� �޼ҵ带 �����Ѵ�.
		}else if(e.getSource().equals(btn11)) {
			m = "�ڹ̵�";
			setSize(780, 600);
			set();
			// btn5�� ������ �� ����â�� ����ǰ� �α��� â�� ����ȴ�
		}else  if(e.getSource().equals(btn5)) {
			dispose();
			new Login();
			// btn2�� ������ �� ����â���� ��ٱ��� â�� ����ȴ�
		}else if (e.getSource().equals(btn2)) {
			new Basket();
			// btn1�� ������ �� ����â ���� �ֹ����� â�� ����ȴ�
		}else if(e.getSource().equals(btn1)) {
			ch = 1;
			new Orderlist();
			// btn3�� ������ �� ����â�� ����ǰ� �α⿵ȭ â�� ����ȴ�
		}else if(e.getSource().equals(btn3)) {
			dispose();
			new Chart();
			// btn4�� ������ �� ����â�� ����ǰ� ȸ���������� â�� ����ȴ�
		}else if(e.getSource().equals(btn4)) {
			dispose();
			new User_Info();
			// btn12�� ������ ��  MStaff â�� ����ǰ� pan1�� ��Ŀ���� ��������
		}else if(e.getSource().equals(btn12)) {
			new Staff("�⿬�� ����", this, mno);
			setVisible(false);
			pan1.requestFocus();
			// btn13�� ������ �� mlist���� m_name�� tf1�� ���� ���� �̰� list�� �����ϰ� �����Ѱ͵��߿� DB�� �ִ� ��ȭ��ȣ�� mno1�� �����Ѵ�
		}else if(e.getSource().equals(btn13)) {	// ��ٱ��Ͽ� ���
			DBmgr.sql = "select * from mlist where m_name = '" + tf1.getText() + "'";
			list = mgr.movie();
			bean = list.get(0);
			// DB���� �̾ƿ� ��ȭ�ѹ��� ��Ʈ������ ��ȯ�� mno1�� ����
			String mno1 = bean.getMno()+"";
			// ��ȭ�� ��ٱ��Ͽ� ����ִ����� Ȯ���ҷ��� �������
			int c = 0;
			// ulist���� u_id�� Login.id�� ���� ���� �̾ƿ� list3�� ������ �� bean�� ���� ����
			DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
			list3 = mgr.user();
			bean = list3.get(0);

			String birth = bean.getUbd();	//DB�� �ִ� ��������� birth�� �����ѵ�
			String ff[] = birth.split("-");	// -�� �������� ���ڿ� �ڸ��� ��
			String uyear = ff[0];	// 0 �� �� �⵵�� ���� ���´�, 1�� �� ���� ���� ���´�, 2�� �� ���� �������´�
			int tyear = cal.get(cal.YEAR);	// ����⵵�� �����´� 

			if(mage.equals("û�ҳ� �����Ұ�")) {

				int adult = tyear - Integer.parseInt(uyear);
				System.out.println(adult);

				if(adult < 19) {

					JOptionPane.showMessageDialog(null, "û�ҳ� ���� �Ұ� ��ȭ�Դϴ�!" + "\n��ٱ��Ͽ� �߰��� �� �� �����ϴ�!", "�޽���", JOptionPane.ERROR_MESSAGE);

				}
				else {
					// shoplist�� dptj u_no�� Login.no�� ���� ���� �̾� list2�� �����Ѵ�
					DBmgr.sql = "select * from shoplist where u_no = '" + Login.no + "'";
					list2 = mgr.shoplist();

					for (int i = 0; i < list2.size(); i++) {
						bean = list2.get(i);

						String mno2 = bean.getMno()+"";
						System.out.println(mno1);
						System.out.println(mno2);
						// mon1�� mno2�� ������ c = 1�̰� �޽����� ����
						if(mno1.equals(mno2)) {
							c = 1;
							JOptionPane.showMessageDialog(null, "��ٱ��Ͽ� �̹� ����ִ� ��ȭ�Դϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
						}
					}

					if(c == 0) {
						DBmgr.sql = "insert into shoplist(s_no, u_no, m_no) values(ss_no.nextval, '" + Login.no + "', '" + mno + "')";
						list = mgr.update();
						JOptionPane.showMessageDialog(null, "��ٱ��Ͽ� ��ȭ�� �����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}else {
				// shoplist�� dptj u_no�� Login.no�� ���� ���� �̾� list2�� �����Ѵ�
				DBmgr.sql = "select * from shoplist where u_no = '" + Login.no + "'";
				list2 = mgr.shoplist();

				for (int i = 0; i < list2.size(); i++) {
					bean = list2.get(i);

					String mno2 = bean.getMno()+"";
					System.out.println(mno1);
					System.out.println(mno2);
					// mon1�� mno2�� ������ c = 1�̰� �޽����� ����
					if(mno1.equals(mno2)) {
						c = 1;
						JOptionPane.showMessageDialog(null, "��ٱ��Ͽ� �̹� ����ִ� ��ȭ�Դϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
					}
				}

				if(c == 0) {
					DBmgr.sql = "insert into shoplist(s_no, u_no, m_no) values(ss_no.nextval, '" + Login.no + "', '" + mno + "')";
					list = mgr.update();
					JOptionPane.showMessageDialog(null, "��ٱ��Ͽ� ��ȭ�� �����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			// btn14�� ������ �� 
		}else if(e.getSource().equals(btn14)) {	// �����ϱ�
			// ulist�� u_id�� Login.id�� ������ �̾� list�� �����Ѵ�
			DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
			list = mgr.user();
			bean = list.get(0);

			String birth = bean.getUbd();	//DB�� �ִ� ��������� birth�� �����ѵ�
			String ff[] = birth.split("-");	// -�� �������� ���ڿ� �ڸ��� ��
			String uyear = ff[0];	// 0 �� �� �⵵�� ���� ���´�, 1�� �� ���� ���� ���´�, 2�� �� ���� �������´�

			int tyear = cal.get(cal.YEAR);	// ����⵵�� �����´� 

			if(mage.equals("û�ҳ� �����Ұ�")) {

				int adult = tyear - Integer.parseInt(uyear);	// ����⵵ - ���� �⵵

				if(adult < 19) {

					JOptionPane.showMessageDialog(null, "û�ҳ� ���� �Ұ� ��ȭ�Դϴ�!" + "\n�����Ͻ� �� �����ϴ�!", "�޽���", JOptionPane.ERROR_MESSAGE);

				}
				//	�� ��ȯ�� tf3�� DB�� ����� ����Ʈ���� ���ų� ���� ��
			}else if(Integer.parseInt(tf3.getText())<=bean.getUpoint()) {

				int ok = JOptionPane.showConfirmDialog(null, "ȸ������ �� ����Ʈ : " + bean.getUpoint() + "\n���ε� ���� : " + hap +"\n����Ʈ�� �����Ͻðڽ��ϱ�?\n(NO Ŭ�� �� ���ݰ����� �˴ϴ�.)","��������",JOptionPane.YES_NO_CANCEL_OPTION);

				if(ok == 0) {
					// ulist�� u_id�� Login.id�� ���� �̾� u_point��  tf3���� �ٲٰ� list�� �����Ѵ�
					DBmgr.sql = "update ulist set u_point = u_point - '" + tf3.getText() +"' where u_id = '" + Login.id + "'";
					list = mgr.update();
					// ulist���� u_id�� Login.id�ΰ��� �̾� list�� �����Ѵ�
					DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
					list = mgr.user();
					bean = list.get(0);
					JOptionPane.showMessageDialog(null, "����Ʈ�� ���� �Ϸ�Ǿ����ϴ�.\n���� ����Ʈ : " + bean.getUpoint(), "�޽���", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Users_Main();
				}else if(ok == JOptionPane.NO_OPTION){
					//NO�� ������ �� ���ݰ���
					gg();
				}else if(ok == JOptionPane.CANCEL_OPTION) {
					// CANCEL�� ������ �� �޽����� ���� ����â�� ����ǰ� Main()â�� ���(���ΰ�ħ)
					JOptionPane.showMessageDialog(null, "���� ��� �Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Users_Main();
				}
			}else {
				gg();
			}
			// btn15�� ������ �� ClientFrameThread()�� ����ȴ�.
		}else if(e.getSource().equals(btn15)) {
			new ClientFrameThread();
		}
	}
}

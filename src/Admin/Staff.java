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

	JTextField tfdi = new JTextField();//����
	JTextField tfac1 = new JTextField();//���1
	JTextField tfro1 = new JTextField();//��1
	JTextField tfac2 = new JTextField();//���2
	JTextField tfro2 = new JTextField();//��2
	JTextField tfac3 = new JTextField();//���3
	JTextField tfro3 = new JTextField();//��3

	JLabel lbdi = new JLabel("����");
	JLabel lbem = new JLabel("");
	JLabel lbac1 = new JLabel("���");
	JLabel lbro1 = new JLabel("��");
	JLabel lbac2 = new JLabel("���");
	JLabel lbro2 = new JLabel("��");
	JLabel lbac3 = new JLabel("���");
	JLabel lbro3 = new JLabel("��");

	JButton btdi = new JButton("���� ���");
	JButton btac1 = new JButton("���� ���");
	JButton btac2 = new JButton("���� ���");
	JButton btac3 = new JButton("���� ���");
	JButton btsave = new JButton("���");
	JButton btcancel = new JButton("���");

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

	public Staff(String title, Insert insert) {//MInsert â���� �⿬�� ��� â�� �������� ��

		this.insert = insert;
		check = 1;//insert�� ���

		make();

		this.setTitle(title);//Ÿ�̺���� ���� ����

	}
	public Staff(String title, Edit edit, int no) {//MEdit â���� �⿬�� ��� â�� �������� ��

		this.edit = edit;
		this.no = no;//edit�� ��ȭ��ȣ
		check = 2;//edit�� ���

		make();

		image();

		this.setTitle(title);//Ÿ�̺���� ���� ����

	}
	public Staff(String title, Users_Main main, int no) {//Main â���� �⿬�� ��� â�� �������� ��

		this.main = main;
		this.no = no;//main�� ��ȭ��ȣ
		check = 3;//main�� ���

		make();

		image();

		this.setTitle(title);//Ÿ�̺���� ���� ����

	}

	public static void main(String[] args) {
		//new MStaff();
	}

	private void make() {
		this.setLayout(null);

		//���� ����
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

		//���ڻ� ����
		btdi.setForeground(Color.WHITE);
		btac1.setForeground(Color.WHITE);
		btac2.setForeground(Color.WHITE);
		btac3.setForeground(Color.WHITE);
		btsave.setForeground(Color.WHITE);
		btcancel.setForeground(Color.WHITE);

		//��ü ��Ʈ ����
		c.setFont(new Font("�����ٸ����", Font.BOLD, 12));

		//������ ���԰� �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ����
		btdi.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btac1.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btac2.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btac3.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btsave.setIcon(new ImageIcon(new ImageIcon("resource/icon/save.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btcancel.setIcon(new ImageIcon(new ImageIcon("resource/icon/mcancel.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

		//�󺧿� �׵θ� ����
		imgdirec.setBorder(new LineBorder(Color.BLACK));
		imgactor1.setBorder(new LineBorder(Color.BLACK));
		imgactor2.setBorder(new LineBorder(Color.BLACK));
		imgactor3.setBorder(new LineBorder(Color.BLACK));

		//�̹��� �� �κ�
		p1.add(imgdirec);
		p1.add(imgactor1);
		p1.add(imgactor2);
		p1.add(imgactor3);

		//�̹��� ��� ��ư �κ�
		p2.add(btdi);
		p2.add(btac1);
		p2.add(btac2);
		p2.add(btac3);

		//����
		p3.add(lbdi);
		p3.add(tfdi);
		p3.add(lbem);

		//���1
		p4.add(lbac1);
		p4.add(tfac1);
		p4.add(lbro1);
		p4.add(tfro1);

		//���2
		p5.add(lbac2);
		p5.add(tfac2);
		p5.add(lbro2);
		p5.add(tfro2);

		//���3
		p6.add(lbac3);
		p6.add(tfac3);
		p6.add(lbro3);
		p6.add(tfro3);

		//��ġ ����
		p1.setBounds(20, 15, 630, 170);
		p2.setBounds(20, 190, 630, 30);
		p3.setBounds(20, 230, 150, 60);
		p4.setBounds(180, 230, 150, 60);
		p5.setBounds(340, 230, 150, 60);
		p6.setBounds(500, 230, 150, 60);
		p7.setBounds(230, 310, 200, 35);

		if(check == 1 || check == 2) {//MInsert�� MEdit�� ���

			//��ϰ� ��� ��ư�� �ִ´�.
			p7.add(btsave);
			p7.add(btcancel);
			//�̹��� ��� ��ư�� �ִ´�.
			this.add(p2);

		}else if(check == 3) {//Main�� ���
			//�ؽ�Ʈ �ʵ带 ������ �Ұ����ϰ� �����Ѵ�.
			tfdi.setEditable(false);
			tfac1.setEditable(false);
			tfro1.setEditable(false);
			tfac2.setEditable(false);
			tfro2.setEditable(false);
			tfac3.setEditable(false);
			tfro3.setEditable(false);

			//Ȯ�� ��ư�� �ִ´�.
			btcancel.setText("Ȯ��");
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

		this.addWindowListener(new WindowAdapter() {@Override//window �����ư(X) ����
			public void windowClosing(WindowEvent arg0) {
			if(check == 1) {//MInsert�� ���
				insert.setVisible(true);//�ۼ��ϴ� ��ȭ��� â�� �ٽ� ���̰� �����Ѵ�.
			}else if(check == 2) {//MEdit�� ���
				edit.setVisible(true);//�ۼ��ϴ� ��ȭ���� â�� �ٽ� ���̰� �����Ѵ�.
			}else if(check ==3) {//Main�� ���
				main.setVisible(true);//�ۼ��ϴ� Main â�� �ٽ� ���̰� �����Ѵ�.
			}
		}
		});

		this.setIconImage(changedImg);//Ÿ��Ʋ���� ������ ����
		this.setSize(680, 400);//Frame ��� ũ�� ����
		this.setLocationRelativeTo(null);//Frame ��� ��ġ ����
		this.setResizable(false);//Frame ������ ���� ����
		this.setVisible(true);//������ Frame�� ȭ�鿡 ���

	}
	private void image() {
		//��ȭ��ȣ�� stafflist�� �˻��Ѵ�.
		DBmgr.sql = "select * from stafflist where m_no = '" + no + "'";
		list = mgr.staff();
		bean = list.get(0);

		//������ ������ �����ͼ� ũ��� �ؽ�Ʈ�ʵ� ������ ���ش�.
		find= "DBFiles\\direc\\" + bean.getStdirec() + ".jpg";
		imgdirec.setIcon(new ImageIcon(new ImageIcon(find).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
		tfdi.setText(bean.getStdirec());

		//���1�� ������ �����ͼ� ũ��� �ؽ�Ʈ�ʵ� ������ ���ش�.
		find1= "DBFiles\\actor\\" + bean.getStactor1() + ".jpg";
		imgactor1.setIcon(new ImageIcon(new ImageIcon(find1).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
		tfac1.setText(bean.getStactor1());
		tfro1.setText(bean.getStrole1());

		//���2�� ������ �����ͼ� ũ��� �ؽ�Ʈ�ʵ� ������ ���ش�.
		find2= "DBFiles\\actor\\" + bean.getStactor2() + ".jpg";
		imgactor2.setIcon(new ImageIcon(new ImageIcon(find2).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
		tfac2.setText(bean.getStactor2());
		tfro2.setText(bean.getStrole2());

		//���3�� ������ �����ͼ� ũ��� �ؽ�Ʈ�ʵ� ������ ���ش�.
		find3= "DBFiles\\actor\\" + bean.getStactor3() + ".jpg";
		imgactor3.setIcon(new ImageIcon(new ImageIcon(find3).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
		tfac3.setText(bean.getStactor3());
		tfro3.setText(bean.getStrole3());
	}

	JFileChooser fc1= new JFileChooser();
	JFileChooser fc2= new JFileChooser();
	JFileChooser fc3= new JFileChooser();
	JFileChooser fc4= new JFileChooser();
	int fcheck = 0;//���� �����ư ���ȴ��� Ȯ���ϱ� ���� ����
	int ok1 = 0;//�̹����� �����Ͽ����� Ȯ���ϱ� ���� ����
	int ok2 = 0;//�̹����� �����Ͽ����� Ȯ���ϱ� ���� ����
	int ok3 = 0;//�̹����� �����Ͽ����� Ȯ���ϱ� ���� ����
	int ok4 = 0;//�̹����� �����Ͽ����� Ȯ���ϱ� ���� ����

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btdi)) {//���� �̹��� ��� ��ư�� Ŭ������ ���

			//���� ���� ����
			fc1.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//����� â�� �����.
			fcheck = fc1.showOpenDialog(this);

			if(fcheck == JFileChooser.APPROVE_OPTION ) {//�����ư�� Ŭ���ߴ��� Ȯ��
				//���õ� ������ �����´�.
				File file = fc1.getSelectedFile();
				//���õ� ������ �󺧿� �����Ѵ�. �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ����
				imgdirec.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok1 = 1;//�̹��� ��� Ȯ��
			}
		}else if(e.getSource().equals(btac1)) {//���1 �̹��� ��� ��ư�� Ŭ������ ���

			//���� ���� ����
			fc2.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//����� â�� �����.
			fcheck = fc2.showOpenDialog(this);

			if(fcheck == JFileChooser.APPROVE_OPTION ) {//�����ư�� Ŭ���ߴ��� Ȯ��
				//���õ� ������ �����´�.
				File file = fc2.getSelectedFile();
				//���õ� ������ �󺧿� �����Ѵ�. �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ����
				imgactor1.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok2 = 1;
			}
		}else if(e.getSource().equals(btac2)) {//���2 �̹��� ��� ��ư�� Ŭ������ ���

			//���� ���� ����
			fc3.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//����� â�� �����.
			fcheck = fc3.showOpenDialog(this);

			if(fcheck == JFileChooser.APPROVE_OPTION ) {//�����ư�� Ŭ���ߴ��� Ȯ��
				//���õ� ������ �����´�.
				File file = fc3.getSelectedFile();
				//���õ� ������ �󺧿� �����Ѵ�. �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ����
				imgactor2.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok3 = 1;
			}
		}else if(e.getSource().equals(btac3)) {//���3 �̹��� ��� ��ư�� Ŭ������ ���

			//���� ���� ����
			fc4.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//����� â�� �����.
			fcheck = fc4.showOpenDialog(this);

			if(fcheck == JFileChooser.APPROVE_OPTION ) {//�����ư�� Ŭ���ߴ��� Ȯ��
				//���õ� ������ �����´�.
				File file = fc4.getSelectedFile();
				//���õ� ������ �󺧿� �����Ѵ�. �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ����
				imgactor3.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok4 = 1;
			}
		}else if(e.getSource().equals(btsave)) {//���� ��ư�� Ŭ������ ���

			if(check == 1) {//MInsert�� ���

				if(tfdi.getText().equals("") || tfac1.getText().equals("") || tfro1.getText().equals("") || tfac2.getText().equals("") || tfro2.getText().equals("") || tfac3.getText().equals("") || tfro3.getText().equals("")) {
					//�ؽ�Ʈ �ʵ尡 �ϳ��� �����̶�� ���� �޽����� ����.
					JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);

				}
				else if(ok1 == 0 || ok2 == 0 || ok3 == 0 || ok4 == 0) {
					//�̹����� ��ϵ��� �ʾҴٸ� ���� �޽����� ����.
					JOptionPane.showMessageDialog(null, "������ ��� �����ʾҽ��ϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);

				}else {
					//MInsert���� String ���·� ������ѳ��� insert���� �����Ų��.
					DBmgr.sql = Insert.insert;
					mgr.update();
					//insert �Ǹ鼭 ��ȭ��ȣ�� ������ �Ǵµ� ���⼭ ��ȭ��ȣ�� �˻��Ͽ�					
					DBmgr.sql = "select * from mlist where m_name = '" + Insert.mname + "'";
					list = mgr.movie();
					bean = list.get(0);
					//mno�� ������ ��ȭ��ȣ�� �����ϰ�
					int mno = bean.getMno();
					//�� ��ȭ��ȣ�� �⿬�������� insert���� ����Ѵ�.
					DBmgr.sql = "insert into stafflist(st_no, m_no, st_direc, st_actor1, st_role1, st_actor2, st_role2, st_actor3, st_role3) values(sst_no.nextval, '" + mno + "', '" + tfdi.getText() + "', '" + tfac1.getText() 
					+ "', '" + tfro1.getText() + "', '" + tfac2.getText() + "', '" + tfro2.getText() + "', '" + tfac3.getText() + "', '" + tfro3.getText() + "')";
					list = mgr.update();

					try {
						//�⿬�� ������ ��� �ҷ��ͼ� �̹��� ������ �Ȱ��� ���� ������ Ȯ���Ѵ�.
						DBmgr.sql = "select * from stafflist";
						list = mgr.staff();
						bean = list.get(0);

						if(!tfdi.getText().equals(bean.getStdirec())) {//���� ������ ���� ���� ���ٸ� ���� �����Ѵ�.

							BufferedImage bi1 = ImageIO.read(new File(fc1.getSelectedFile()+""));
							File fd = new File("DBFiles\\direc\\" + tfdi.getText() + ".jpg");
							ImageIO.write(bi1, "jpg", fd);

						}
						if(!tfac1.getText().equals(bean.getStactor1()) && !tfac1.getText().equals(bean.getStactor2()) && !tfac1.getText().equals(bean.getStactor3()) ) {//���1�� ��������� ���� ���� ���ٸ� ���� �����Ѵ�.

							BufferedImage bi2 = ImageIO.read(new File(fc2.getSelectedFile()+""));
							File f1 = new File("DBFiles\\actor\\" + tfac1.getText() + ".jpg");
							ImageIO.write(bi2, "jpg", f1);

						}
						if(!tfac2.getText().equals(bean.getStactor1()) && !tfac2.getText().equals(bean.getStactor2()) && !tfac2.getText().equals(bean.getStactor3()) ) {//���2�� ��������� ���� ���� ���ٸ� ���� �����Ѵ�.

							BufferedImage bi3 = ImageIO.read(new File(fc3.getSelectedFile()+""));
							File f2 = new File("DBFiles\\actor\\" + tfac2.getText() + ".jpg");
							ImageIO.write(bi3, "jpg", f2);

						}
						if(!tfac3.getText().equals(bean.getStactor1()) && !tfac3.getText().equals(bean.getStactor2()) && !tfac3.getText().equals(bean.getStactor3()) ) {//���3�� ��������� ���� ���� ���ٸ� ���� �����Ѵ�.

							BufferedImage bi4 = ImageIO.read(new File(fc4.getSelectedFile()+""));
							File f3 = new File("DBFiles\\actor\\" + tfac3.getText() + ".jpg");
							ImageIO.write(bi4, "jpg", f3);

						}

					}catch(Exception e2) {

					}
					//�˸°� ������ �Ǿ��ٸ� �⿬�� ������ ��ϵǾ��ٴ� ���� �޽����� ����.
					JOptionPane.showMessageDialog(null, "�⿬�� ������ ��ϵǾ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);

					dispose();//���� â�� �����ϰ�
					insert.setVisible(true);//�ۼ��ϴ� ��ȭ��� â�� �ٽ� ���̰� �����Ѵ�.

					//���� ��ȭ������ ��ϵǾ��ٴ� ���� �޽����� ����.
					JOptionPane.showMessageDialog(null, "��ȭ�� ��ϵǾ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);

					insert.dispose();//�ۼ��ϴ� MInsertâ�� �����ϰ�
					new Insert();//���ο� MInsertâ�� �����Ѵ�.
				}
			}else if(check == 2) {//MEdit�� ���

				if(tfdi.getText().equals("") || tfac1.getText().equals("") || tfro1.getText().equals("") || tfac2.getText().equals("") || tfro2.getText().equals("") || tfac3.getText().equals("") || tfro3.getText().equals("")) {
					//�ؽ�Ʈ �ʵ尡 �ϳ��� �����̶�� ���� �޽����� ����.
					JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);

				}else {
					//update�ϴ� sql���� �����Ѵ�.
					DBmgr.sql = "update stafflist set st_direc = '" + tfdi.getText() + "', st_actor1 = '" + tfac1.getText() + "', st_role1 = '" + tfro1.getText() + "', st_actor2 = '" + tfac2.getText() 
					+ "', st_role2 = '" + tfro2.getText() + "', st_actor3 = '" + tfac3.getText() + "', st_role3 = '" + tfro3.getText() + "' where m_no = '" + Edit.no + "'";
					list = mgr.update();

					try {

						if(fc1.getSelectedFile()==null) {//������ �̹����� ������ �ȵǾ��ִٸ�
							//������ �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
							BufferedImage bi = ImageIO.read(new File(find));
							//DBFiles\\direc\\��� ��ο� �����̸��� �������ְ�
							File f2 =new File("DBFiles\\direc\\" + tfdi.getText() + ".jpg");
							//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
							ImageIO.write(bi, "jpg", f2);

						}else {//���ο� �̹����� ���õǾ��ٸ�
							//���ο� �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
							BufferedImage bi = ImageIO.read(new File(fc1.getSelectedFile()+""));
							//���ο� �̹��� ������ DBFiles\\direc\\��� ��ο� �����̸��� �������ְ�
							File f2 = new File("DBFiles\\direc\\" + tfdi.getText() + ".jpg");
							//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
							ImageIO.write(bi, "jpg", f2);

						}
						if(fc2.getSelectedFile()==null) {//���1�� �̹����� ������ �ȵǾ��ִٸ�
							//������ �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
							BufferedImage bi = ImageIO.read(new File(find1));
							//DBFiles\\actor\\��� ��ο� �����̸��� �������ְ�
							File f2 =new File("DBFiles\\actor\\" + tfac1.getText() + ".jpg");
							//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
							ImageIO.write(bi, "jpg", f2);

						}else {//���ο� �̹����� ���õǾ��ٸ�
							//���ο� �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
							BufferedImage bi = ImageIO.read(new File(fc2.getSelectedFile()+""));
							//���ο� �̹��� ������ DBFiles\\actor\\��� ��ο� �����̸��� �������ְ�
							File f2 = new File("DBFiles\\actor\\" + tfac1.getText() + ".jpg");
							//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
							ImageIO.write(bi, "jpg", f2);

						}
						if(fc3.getSelectedFile()==null) {//���2�� �̹����� ������ �ȵǾ��ִٸ�
							//������ �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
							BufferedImage bi = ImageIO.read(new File(find2));
							//DBFiles\\actor\\��� ��ο� �����̸��� �������ְ�
							File f2 =new File("DBFiles\\actor\\" + tfac2.getText() + ".jpg");
							//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
							ImageIO.write(bi, "jpg", f2);

						}else {//���ο� �̹����� ���õǾ��ٸ�
							//���ο� �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
							BufferedImage bi = ImageIO.read(new File(fc3.getSelectedFile()+""));
							//���ο� �̹��� ������ DBFiles\\actor\\��� ��ο� �����̸��� �������ְ�
							File f2 = new File("DBFiles\\actor\\" + tfac2.getText() + ".jpg");
							//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
							ImageIO.write(bi, "jpg", f2);

						}
						if(fc4.getSelectedFile()==null) {//���3�� �̹����� ������ �ȵǾ��ִٸ�
							//������ �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
							BufferedImage bi = ImageIO.read(new File(find3));
							//DBFiles\\actor\\��� ��ο� �����̸��� �������ְ�
							File f2 =new File("DBFiles\\actor\\" + tfac3.getText() + ".jpg");
							//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
							ImageIO.write(bi, "jpg", f2);

						}else {//���ο� �̹����� ���õǾ��ٸ�
							//���ο� �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
							BufferedImage bi = ImageIO.read(new File(fc4.getSelectedFile()+""));
							//���ο� �̹��� ������ DBFiles\\actor\\��� ��ο� �����̸��� �������ְ�
							File f2 = new File("DBFiles\\actor\\" + tfac3.getText() + ".jpg");
							//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
							ImageIO.write(bi, "jpg", f2);

						}

					}catch(Exception e2) {

					}
					//�⿬�� ������ �� ������ �Ǿ��ٴ� ���� �޽��� â�� ����ش�.
					JOptionPane.showMessageDialog(null, "�⿬�� ������ �����Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);

					dispose();//���� â�� �����ϰ�
					edit.setVisible(true);//�ۼ��ϴ� ��ȭ���� â�� �ٽ� ���̰� �����Ѵ�.

				}

			}

		}else if(e.getSource().equals(btcancel)) {//��� ��ư�� Ŭ������ ���
			if(check == 1) {//MInsert�� ���
				dispose();//���� Frame�� �����ϰ�
				insert.setVisible(true);//�ۼ��ϴ� ��ȭ��� â�� �ٽ� ���̰� �����Ѵ�.
			}else if(check == 2) {//MEdit�� ���
				dispose();//���� Frame�� �����ϰ�
				edit.setVisible(true);//�ۼ��ϴ� ��ȭ���� â�� �ٽ� ���̰� �����Ѵ�.
			}else if(check == 3) {//Main�� ���
				dispose();//���� Frame�� �����ϰ�
				main.setVisible(true);//�ۼ��ϴ� main â�� �ٽ� ���̰� �����Ѵ�.
			}
		}

	}
}

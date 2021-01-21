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

	private String name[] = new String[5]; //���� ������ ���� ��ȭ�̸� 5�� ������ �迭
	private String mName[] = new String[5]; //���� ������ ���� ��ȭ������ 5�� ������ �迭

	private JButton bt1, bt2, bt3, bt4, bt5, bt6; //�帣 ���� ��ư

	private JLabel title; //�α� ��ȭ ����
	private String genre = "SF"; //��ư text�� ���� string

	private JLabel img1, img2, img3, img4, img5; //top5��ȭ ������
	private JLabel lb1, lb2, lb3, lb4, lb5; //top5��ȭ �̸�

	private DBmgr mgr = new DBmgr(); //���ϴ� �� ������ �Լ��� ����ִ� Ŭ����
	private ArrayList<Bean> list = new ArrayList<Bean>(); //sql�� ó��, list�� ������ �� ���� ������ ����
	private Bean bean; //���ϴ� �� ������ �Լ��� ����ִ� Ŭ����

	public Chart() {

		// ImageIcon ��ü��  �����Ѵ�.
		originIcon = new ImageIcon("resource/icon/pop.png");
		// ImageIcon���� Image�� �����Ѵ�.
		originImg = originIcon.getImage();
		// ����� Image�� ũ�⸦ �����ؼ� ���ο� Image��ü�� �����Ѵ�.
		changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		setIconImage(changedImg);

		ImageIcon genre1 = new ImageIcon("resource\\icon\\sf.png"); //�帣 �����ϴ� ��ư�� ���� ������
		ImageIcon genre2 = new ImageIcon("resource\\icon\\scary.png");
		ImageIcon genre3 = new ImageIcon("resource\\icon\\romance.png");
		ImageIcon genre4 = new ImageIcon("resource\\icon\\animation.png");
		ImageIcon genre5 = new ImageIcon("resource\\icon\\action.png");
		ImageIcon genre6 = new ImageIcon("resource\\icon\\comedy.png");

		bt1 = new JButton("SF", genre1); //�帣 �����ϴ� ��ư
		bt2 = new JButton("����", genre2);
		bt3 = new JButton("�θǽ�", genre3);
		bt4 = new JButton("�ִϸ��̼�", genre4);
		bt5 = new JButton("�׼�", genre5);
		bt6 = new JButton("�ڹ̵�", genre6);

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

		title = new JLabel("�α� SF ��ȭ ����", JLabel.CENTER);
		title.setFont(new Font("�����ٸ����", Font.BOLD, 50));

		ImageIcon imgFirst = new ImageIcon("DBFiles\\Icon\\first.png"); //top5 Ʈ����
		ImageIcon imgSecond = new ImageIcon("DBFiles\\Icon\\second.png");
		ImageIcon imgThird = new ImageIcon("DBFiles\\Icon\\third.png");
		ImageIcon imgFourth = new ImageIcon("DBFiles\\Icon\\fourth.png");
		ImageIcon imgFifth = new ImageIcon("DBFiles\\Icon\\fifth.png");
		JLabel first = new JLabel("", imgFirst, JLabel.CENTER);
		JLabel second = new JLabel("", imgSecond, JLabel.CENTER);
		JLabel third = new JLabel("", imgThird, JLabel.CENTER);
		JLabel fourth = new JLabel("", imgFourth, JLabel.CENTER);
		JLabel fifth = new JLabel("", imgFifth, JLabel.CENTER);

		img1 = new JLabel(); //top5��ȭ ������
		img2 = new JLabel();
		img3 = new JLabel();
		img4 = new JLabel();
		img5 = new JLabel();

		img1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); ////top5��ȭ ������ �ܰ���
		img2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		img3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		img4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		img5.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lb1 = new JLabel("", JLabel.CENTER); //top5��ȭ �̸�
		lb2 = new JLabel("", JLabel.CENTER); 
		lb3 = new JLabel("", JLabel.CENTER); 
		lb4 = new JLabel("", JLabel.CENTER); 
		lb5 = new JLabel("", JLabel.CENTER);

		lb1.setFont(new Font("�����ٸ����", Font.BOLD, 16));
		lb2.setFont(new Font("�����ٸ����", Font.BOLD, 16));
		lb3.setFont(new Font("�����ٸ����", Font.BOLD, 16));
		lb4.setFont(new Font("�����ٸ����", Font.BOLD, 16));
		lb5.setFont(new Font("�����ٸ����", Font.BOLD, 16));

		JPanel pan1 = new JPanel(new GridLayout(1, 5, 20, 10)); //top5��ȭ ������
		pan1.setBackground(Color.WHITE);

		pan1.add(img1);
		pan1.add(img2);
		pan1.add(img3);
		pan1.add(img4);
		pan1.add(img5);

		JPanel pan2 = new JPanel(new GridLayout(1, 5, 10, 10)); //top5��ȭ �̸�
		pan2.setBackground(Color.WHITE);

		pan2.add(lb1);
		pan2.add(lb2);
		pan2.add(lb3);
		pan2.add(lb4);
		pan2.add(lb5);

		JPanel pan3 = new JPanel(new GridLayout(1, 6, 0, 0)); //�帣���� ��ư

		pan3.add(bt1);
		pan3.add(bt2);
		pan3.add(bt3);
		pan3.add(bt4);
		pan3.add(bt5);
		pan3.add(bt6);

		JPanel pan4 = new JPanel(new GridLayout(1, 5, 20, 0)); //top5 Ʈ����
		pan4.setBackground(new Color(255, 0, 0, 0));

		pan4.add(first);
		pan4.add(second);
		pan4.add(third);
		pan4.add(fourth);
		pan4.add(fifth);

		JPanel pan5 = new JPanel(); //��ü
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
		setTitle("�α���Ʈ");
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {@Override
			public void windowClosing(WindowEvent arg0) {
			dispose(); //X ���� ���� �̺�Ʈ
			new Users_Main();
		}
		});
	}

	public static void main(String[] args) {
		new Chart();
	}

	public void paint() {
		//��ȭDB�� ���ų���DB���� ��ȭ��ȣ�� ���� ������ �� �帣 ������ ��ȭ�̸����� �ջ��Ͽ� ���� ������ ����
		DBmgr.sql = "select mlist.m_name, count(*) from olist inner join mlist on olist.m_no = mlist.m_no where olist.o_genre = '"
				+ genre + "' group by m_name having count(*) >= 0 order by count(*) desc";
		list = mgr.chartjoin();

		for (int i = 0; i < 5; i++) {

			bean = list.get(i);

			name[i] = bean.getMname(); //��ȭ �̸� �����ͼ� 
			mName[i] = "DBFiles\\image\\" + name[i] + ".jpg"; //��ȭ ������ ��� ����
		}
		//�������� �̹����� ������ ũ������, ǰ�� ���� ����
		img1.setIcon(new ImageIcon(new ImageIcon(mName[0]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH))); 
		img2.setIcon(new ImageIcon(new ImageIcon(mName[1]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));
		img3.setIcon(new ImageIcon(new ImageIcon(mName[2]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));
		img4.setIcon(new ImageIcon(new ImageIcon(mName[3]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));
		img5.setIcon(new ImageIcon(new ImageIcon(mName[4]).getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));

		lb1.setText(name[0]); //�ش� ��ȭ �̸� ǥ��
		lb2.setText(name[1]);
		lb3.setText(name[2]);
		lb4.setText(name[3]);
		lb5.setText(name[4]);
	}

	@Override
	public void actionPerformed(ActionEvent e) { //�� �帣 ��ư ������ �ش� �帣�� ���� ���� �� ��Ÿ��
		Object obj = e.getSource();
		if (obj == bt1) {
			title.setText("�α� SF ��ȭ ����");
			genre = "SF";
		} else if (obj == bt2) {
			title.setText("�α� ���� ��ȭ ����");
			genre = "����";
		} else if (obj == bt3) {
			title.setText("�α� �θǽ� ��ȭ ����");
			genre = "�θǽ�";
		} else if (obj == bt4) {
			title.setText("�α� �ִϸ��̼� ��ȭ ����");
			genre = "�ִϸ��̼�";
		} else if (obj == bt5) {
			title.setText("�α� �׼� ��ȭ ����");
			genre = "�׼�";
		} else if (obj == bt6) {
			title.setText("�α� �ڹ̵� ��ȭ ����");
			genre = "�ڹ̵�";
		}
		paint(); //�ش� �帣�� �´� ������ ��Ÿ����
	}
}

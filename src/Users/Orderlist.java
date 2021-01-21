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

	private int sum = 0; //���� �ݾ� ������ ����
	
	private DecimalFormat df = new DecimalFormat("#,##0"); //���� ��Ÿ�� ���� ����
	
	private JLabel lb1, grade; //~���� ���ų���, ���
	private JLabel img; //��ȭ ������ Ʋ
	private JLabel tf1, tf2, tf3, tf4, tf5; //��ȭ����
	
	private JTextField tfSum1; //�� �����ݾ�

	private JButton bt1; //�ݱ�

	private String col[] = {"No.", "��������","��ȭ��","�����ݾ�","�����ݾ�"}; //���̺� Į��

	private JTable table =new JTable();
	private JScrollPane jsp = new JScrollPane();

	private DBmgr mgr = new DBmgr(); //���ϴ� �� ������ �Լ��� ����ִ� Ŭ����
	private ArrayList<Bean> list =new ArrayList<Bean>(); //sql������ ������ ����� ����Ʈ ����
	private Bean bean; //sql�� ó��, list�� ������ �� ���� ������ ����

	public Orderlist() {

		// ImageIcon ��ü��  �����Ѵ�.
		originIcon = new ImageIcon("resource/icon/pop.png");
		// ImageIcon���� Image�� �����Ѵ�.
		originImg = originIcon.getImage();
		// ����� Image�� ũ�⸦ �����ؼ� ���ο� Image��ü�� �����Ѵ�.
		changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		setIconImage(changedImg);

		lb1 = new JLabel("",JLabel.RIGHT);
		lb1.setText(Login.name + "���� ���ų���");
		lb1.setFont(new Font("�����ٸ����",Font.BOLD,30));
		lb1.setBackground(Color.WHITE);
		
		String o = "";
		
		if(Users_Main.ch == 1) {
			lb1.setText(Login.name + "���� ���ų���");
			o = "select * from ulist where u_id = '" + Login.id + "'";

		}else if(Users_Main.ch == 2) {
			lb1.setText(Manager.name + "���� ���ų���");
			o = "select * from ulist where u_id = '" + Manager.id + "'";

		}
		//�ش� ���� ���� �����ͼ�
		DBmgr.sql = o;
		list = mgr.user();
		bean = list.get(0);

		//�ش� ������ ��� ����,����
		String gradeg = "";
		if(bean.getUgrade().equals(" ")) {
			gradeg = "�Ϲ�";
		}else {
			gradeg = bean.getUgrade();
		}

		grade = new JLabel("��� : " + gradeg,JLabel.CENTER); //������ ���� ��� ǥ��
		grade.setFont(new Font("�����ٸ����",Font.BOLD,20));

		JLabel lb2 = new JLabel("�� ���� �ݾ�");
		tfSum1 = new JTextField(25); //�Ѱ����ݾ�
		tfSum1.setHorizontalAlignment(JTextField.CENTER); //��� ����
		tfSum1.setEditable(false); //�ؽ�Ʈ�ʵ� ���ǵ�� �ϱ�
		tfSum1.setBackground(Color.WHITE);

		ImageIcon i1 = new ImageIcon("resource\\icon\\exit.png"); //�ݱ��ư������
		bt1 = new JButton("�ݱ�",i1); //�ݱ��ư
		bt1.setBackground(Color.WHITE);
		bt1.addActionListener(this);

		img = new JLabel();
		img.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //��ȭ������ �ܰ���

		//�þ�� �κ�
		JLabel lbl1 = new JLabel("��ȭ��:");
		JLabel lbl2 = new JLabel("��������:");
		JLabel lbl3 = new JLabel("��       ��:");
		JLabel lbl4 = new JLabel("������¥:");
		JLabel lbl5 = new JLabel("�󿵽ð�:");

		tf1 = new JLabel(); //������ ��ȭ ���� ���� ��
		tf2 = new JLabel();
		tf3 = new JLabel();
		tf4 = new JLabel();
		tf5 = new JLabel();

		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.add(lb2); //�� �����ݾ� ��
		p1.add(tfSum1); //�� �����ݾ� ǥ��
		p1.add(bt1); //�ݱ��ư

		JPanel p2 = new JPanel(new GridLayout(2,1,10,10));
		p2.setBackground(Color.WHITE);
		p2.setLayout(new GridLayout(1,2,0,0));
		p2.add(lb1); //~���� ���ų���
		p2.add(grade); //���

		JPanel p4 = new JPanel(new GridLayout(5,2,0,15));
		p4.setBackground(Color.WHITE);
		p4.add(lbl1);	p4.add(tf1); //��ȭ����Ʈ Ŭ���ϸ� ������ ������
		p4.add(lbl2);	p4.add(tf2);
		p4.add(lbl3);	p4.add(tf3);
		p4.add(lbl4);	p4.add(tf4);
		p4.add(lbl5);	p4.add(tf5);

		table();

		JPanel p3 = new JPanel(); //��ü
		p3.setLayout(null);
		p3.setBackground(Color.WHITE);
		p3.add(p1); //�� �����ݾ�, �ݱ��ư
		p3.add(p2); //~���� ���ų���, ���
		p3.add(p4); //��ȭ����Ʈ Ŭ���ϸ� ������ ������
		p3.add(jsp); //���ų������̺� ���� ��ũ���г�
		p3.add(img); //��ȭ������ �ܰ���

		p1.setBounds(0,300,650,400);
		jsp.setBounds(0,50,636,250);
		p2.setBounds(0,0,650,50);
		p4.setBounds(920,50,250,250);
		img.setBounds(700,50,190,250);

		bt1.addActionListener(this);
		
		add(p3);

		setTitle("���ų���");
		setSize(650,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {@Override
			public void windowClosing(WindowEvent arg0) {
			if(Users_Main.ch == 1) {
				dispose(); //X ���� ���� �̺�Ʈ
			}else if(Users_Main.ch == 2) {
				dispose();
				new Manager();
			}
		}
		});
	}

	void table() {

		//�ش� ������ ���ų���DB�� ��ȭ��ȣ�� ���̺� ��Ÿ�� ��ȭDB�� ���� ���ų�����ȣ ������� ������
		if(Users_Main.ch == 1) {
			DBmgr.sql = "select olist.o_date, mlist.m_name, olist.o_price from olist inner join mlist on olist.m_no = mlist.m_no where olist.u_no = '" + Login.no + "' order by o_no";

		}else if(Users_Main.ch == 2) {
			DBmgr.sql = "select olist.o_date, mlist.m_name, olist.o_price from olist inner join mlist on olist.m_no = mlist.m_no where olist.u_no = '" + Manager.no + "' order by o_no";

		}
		list = mgr.orderjoin();

		int data;
		String record[][] = new String[list.size()][col.length]; //������ ���� ũ�⸸ŭ ���̺� ��Ÿ�� �� ���� 2���� �迭 ����
		for(int i= 0 ; i < list.size() ; i++) {
			bean = list.get(i);

			data = bean.getOprice();
			sum = sum + data; //������ ��ȭ�� ���� ��� �ջ�

			record[i][0] = Integer.toString(i+1);
			record[i][1] = bean.getOdate()+"";
			record[i][2] = bean.getMname()+"";
			record[i][3] = Integer.toString(bean.getOprice())+"";
			record[i][4] = Integer.toString(sum);     
		}

		tfSum1.setText(df.format(sum));

		DefaultTableModel model = new DefaultTableModel(record, col) { //record = ���̺��� �����Ͱ� ��� 2���� �迭, col = ���̺� �÷��� ���̺��� �����.
			public boolean isCellEditable(int r, int c) {
				return false; //���̺��� �� ���� �ǵ��� ���ϵ��� ��
			}
		};

		table = new JTable(model); //���̺����
		jsp.setViewportView(table); //��ũ����ο� ���̺� ���

		DefaultTableCellRenderer al = new DefaultTableCellRenderer();
		al.setHorizontalAlignment(JLabel.CENTER); //�� ���� ������ �������

		for(int i =0 ; i < col.length ; i ++) {
			table.getColumnModel().getColumn(i).setCellRenderer(al);
		} //������� ������(���̺� �°� ����)

		table.addMouseListener(new MouseAdapter() { //���̺��� ���콺 �̺�Ʈ �߻����� �� ����

			@Override
			public void mouseClicked(MouseEvent e) {

				//���콺�� ������ ���� 1��° ��(��ȭ�̸�)�� ������� ��ȭDB���� �����ͼ� ����Ʈ�� ���� �� bean���� ���� ����
				DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(),2) + "'";
				list = mgr.movie();
				bean = list.get(0);

				if(e.getSource().equals(table)) {

					setSize(1200, 400); //���������� â�� Ŀ���� ���콺�� Ŭ���� �ش� ��ȭ�� ������ ��Ÿ��.

					//�������� �̹����� ������ ũ������, ǰ�� ���� ����
					img.setIcon(new ImageIcon(new ImageIcon("DBFiles\\image\\" + bean.getMname() + ".jpg").getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH)));

					tf1.setText(bean.getMname()); //���콺�� Ŭ���� ��ȭ ���� ����
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
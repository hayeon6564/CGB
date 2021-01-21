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

	JLabel lbsearch = new JLabel("�˻�");

	JTextField tfsearch = new JTextField(10);

	String csearch[] = { "��ü", "ID", "�̸�", "�������", "ȸ�����" };

	JComboBox cbsearch = new JComboBox(csearch);

	JButton btsearch = new JButton("ã��");
	JButton btorder = new JButton("���ų��� ����");
	JButton btcancel = new JButton("���");

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	String col[] = { "ȸ����ȣ", "ID", "PW", "�̸�", "�������", "����Ʈ", "ȸ�����" };
	JTable table = new JTable();
	JScrollPane jsp = new JScrollPane();

	ArrayList<Bean> list = new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Manager() {

		this.setLayout(null);

		//���� ����
		c.setBackground(Color.WHITE);
		cbsearch.setBackground(Color.WHITE);
		p1.setBackground(Color.WHITE);
		p2.setBackground(Color.WHITE);
		btsearch.setBackground(new Color(69, 68, 66));
		btorder.setBackground(new Color(69, 68, 66));
		btcancel.setBackground(new Color(69, 68, 66));

		//������ ���԰� �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ���� 
		btsearch.setIcon(new ImageIcon(new ImageIcon("resource/icon/search.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btorder.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btcancel.setIcon(new ImageIcon(new ImageIcon("resource/icon/mcancel.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

		//���ڻ� ����
		btsearch.setForeground(Color.WHITE);
		btorder.setForeground(Color.WHITE);
		btcancel.setForeground(Color.WHITE);

		//��ü ��Ʈ ����
		c.setFont(new Font("�����ٸ����", Font.BOLD, 12));

		//�޺��ڽ� ����
		cbsearch.setSelectedIndex(0);

		//�˻��ϴ� �κ�
		p1.add(lbsearch);
		p1.add(cbsearch);
		p1.add(tfsearch);
		p1.add(btsearch);

		p2.add(btorder);//���ų��� ����
		p2.add(btcancel);//���

		//��ġ ����
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

		//table�� ����� ���� ulist�� ��ü�� �̾Ƴ���.
		DBmgr.sql = "select u.u_no, u.u_id, u.u_pw, u.u_name, u.u_bd, u.u_point, u.u_grade"
				+ " from ulist u";
		table();

		addWindowListener(new WindowAdapter() {//window �����ư(X) ����
			@Override
			public void windowClosing(WindowEvent arg0) {//Ÿ��Ʋ���� X��ư�� ������ Admin()�� �����ȴ�.
				new Admin_Main();
			}
		});

		this.setIconImage(changedImg);//Ÿ��Ʋ���� ������ ����
		this.setTitle("ȸ�� ����");//Ÿ��Ʋ���� ���� ����
		this.setSize(680, 400);//Frame ��� ũ�� ����
		this.setLocationRelativeTo(null);//Frame ��� ��ġ ����
		this.setResizable(false);//Frame ������ ���� ����
		this.setVisible(true);//������ Frame�� ȭ�鿡 ���
		p1.requestFocus();//p1�� ��Ŀ���� �д�.

	}

	public static void main(String[] args) {
		//new Manager();

	}

	void table() {//���̺��� �����Ѵ�.
		//list�� ulist�� ������ �ִ´�.
		list = mgr.user();
		//���� list�� �������̰� ���� col�� ���̴�.
		String record[][] = new String[list.size()][col.length];
		for(int i= 0 ; i < list.size() ; i++) {
			//list ���� i��°�� �ִ� ���� bean�� �ְ�
			bean = list.get(i);
			//�� ��� ���� �°� �����͸� �ִ´�.
			record[i][0] = bean.getUno()+"";
			record[i][1] = bean.getUid();
			record[i][2] = bean.getUpw();
			record[i][3] = bean.getUname();
			record[i][4] = bean.getUbd();
			record[i][5] = bean.getUpoint()+"";
			record[i][6] = bean.getUgrade();
		}
		//DefaultTableModel Ŭ������ ����ؾ� ���̺� ������� ���ų� ������ �����ϴ�.
		//DefaultTableModel ��ü�� �����ϸ鼭 �����͸� ��´�.
		DefaultTableModel model = new DefaultTableModel(record, col) {
			public boolean isCellEditable(int r, int c) {//isCellEditable�� ���̺��� ���� ���� �� �� ���� ���� ���̴�.
				return false;
			}
		};
		//JTable�� model�� �ִ´�.
		table = new JTable(model);
		//jsp�� table�� ��´�.
		jsp.setViewportView(table);

		DefaultTableCellRenderer al = new DefaultTableCellRenderer();//�� �ȿ� ���� �������� ������ �����Ѵ�.
		al.setHorizontalAlignment(JLabel.CENTER);//��� ������ ���־���.
		for(int i =0 ; i < col.length ; i ++) {
			//��� ���� ������ ��� ���ķ� ���ش�.
			table.getColumnModel().getColumn(i).setCellRenderer(al);

		}
		table.getColumnModel().getColumn(0).setMaxWidth(60);//0���� ���� �����Ѵ�.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btsearch) || e.getSource().equals(tfsearch)) {//���ų������� ��ư�� Ŭ���ϰų� �˻� �ؽ�Ʈ�ʵ忡�� ���͸� ���� ���

			if(cbsearch.getSelectedIndex() == 0) {//�˻� �޺��ڽ��� ��ü�� �����Ǿ� �ִ� ���

				if(tfsearch.getText().equals("")) {//�˻�� ������ ���
					//ulist�� ��ü�� �̾ƿ´�.
					DBmgr.sql = "select * from ulist";

				}else {
					//��ü���� �˻����� ������ �̸����� �˻��Ѵ�.
					DBmgr.sql = "select * from ulist where u_name like '%" + tfsearch.getText() + "%'";

				}

			}else {//�˻� �޺��ڽ���  �ٸ� ������ ���õǾ� �ִ� ���

				if(tfsearch.getText().equals("")) {//�˻�� ������ ���
					//�˻�� �Է��϶�� ��� �޽����� ����ش�.
					JOptionPane.showMessageDialog(null, "�˻�� �ѱ��� �̻� �Է����ּ���.", "�޽���", JOptionPane.WARNING_MESSAGE);

				}else if(cbsearch.getSelectedIndex() == 1){//�޺��ڽ��� ID�� ���õ� ���

					DBmgr.sql = "select * from ulist where u_id like '%" + tfsearch.getText() + "%'";

				}else if(cbsearch.getSelectedIndex() == 2){//�޺��ڽ��� �̸����� ���õ� ���

					DBmgr.sql = "select * from ulist where u_name like '%" + tfsearch.getText() + "%'";

				}else if(cbsearch.getSelectedIndex() == 3){//�޺��ڽ��� ������Ϸ� ���õ� ���

					DBmgr.sql = "select * from ulist where u_bd like '%" + tfsearch.getText() + "%'";

				}else if(cbsearch.getSelectedIndex() == 4) {//�޺��ڽ��� ȸ�� ������� ���õ� ���

					DBmgr.sql = "select * from ulist where u_grade like '%" + tfsearch.getText() + "%'";
				}

			}
			//�˻��� ��������
			//table()�� �����Ѵ�.
			table();

		}else if(e.getSource().equals(btorder)){//���ų��� ���� ��ư�� Ŭ���� ���
			
			if(table.getSelectedRowCount()==0) {

				JOptionPane.showMessageDialog(null, "���ų����� �� ȸ���� �������ּ���.", "�޽���", JOptionPane.WARNING_MESSAGE);

			}
			else {
			//���̺��� ���õ� ���� ȸ�� id�� �˻��Ͽ� DB�� �̾ƿ���
			DBmgr.sql = "select * from ulist where u_id = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
			list = mgr.user();
			bean = list.get(0);

			//�����ڰ� ȸ���� ���ų����� ���� ��Ȳ�� �����Ѵ�.
			Users_Main.ch = 2;

			//���ų��� Ŭ�������� ����ϱ� ���� static�� ���·� �����Ѵ�.
			no = bean.getUno();
			id = bean.getUid();
			name = bean.getUname();

			dispose();
			new Orderlist();//OrderList() â�� ���� ����.
			}

		}else if(e.getSource().equals(btcancel)) {//��� ��ư�� Ŭ���� ���

			dispose();//���� â�� �����ϰ�
			new Admin_Main();//Admin() â�� ���� ����.

		}		
	}

}

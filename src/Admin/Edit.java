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

	JLabel lbsearch = new JLabel("�˻�");
	JLabel lbgenre = new JLabel("�帣");
	JLabel lbage = new JLabel("��������");
	JLabel lbname = new JLabel("��ȭ��");
	JLabel lbprice = new JLabel("����");
	JLabel lbdate = new JLabel("������¥");
	JLabel lbtime = new JLabel("�󿵽ð�");

	JTextField tfsearch = new JTextField(16);
	JTextField tfname = new JTextField();
	JTextField tfprice = new JTextField(15);
	JTextField tftime = new JTextField(15);

	String csearch[] = {"��ü","SF","����","�θǽ�","�ִϸ��̼�","�׼�","�ڹ̵�"};
	String cgenre[] = {"SF","����","�θǽ�","�ִϸ��̼�","�׼�","�ڹ̵�"};
	String cage[] = {"��ü ������", "12�� ������", "15�� ������","û�ҳ� �����Ұ�"};

	JComboBox cbsearch = new JComboBox(csearch);
	JComboBox cbgenre = new JComboBox(cgenre);
	JComboBox cbage = new JComboBox(cage);

	JButton btsearch = new JButton("ã��");
	JButton btposter = new JButton("������ ����");
	JButton btstaff = new JButton("�⿬ ����");
	JButton btdelete = new JButton("����");
	JButton btsave = new JButton("����");
	JButton btcancel = new JButton("���");

	JComboBox cbyear = new JComboBox();
	JComboBox cbmonth = new JComboBox();
	JComboBox cbday = new JComboBox();

	JLabel lbyear = new JLabel("��");
	JLabel lbmonth = new JLabel("��");
	JLabel lbday = new JLabel("��");
	JLabel lbminute = new JLabel("��");

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel(new GridLayout(4,2,-90,5));
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel(new GridLayout(1, 1));
	JPanel p6 = new JPanel(new GridLayout(1, 2,-73, 8));

	String col[] = {"�帣","��ȭ��","��������","����","������¥","�󿵽ð�"};
	JTable table = new JTable();
	JScrollPane jsp = new JScrollPane();

	Calendar cal = Calendar.getInstance();

	ArrayList<Bean> list = new ArrayList<Bean>();
	ArrayList<Bean> list2 = new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Edit() {

		this.setLayout(null);
		
		//�޺��ڽ� ����
		cbgenre.setSelectedIndex(-1);
		cbage.setSelectedIndex(-1);
		
		//���� ����
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

		//���ڻ� ����
		btsearch.setForeground(Color.WHITE);
		btposter.setForeground(Color.WHITE);
		btstaff.setForeground(Color.WHITE);
		btdelete.setForeground(Color.WHITE);
		btsave.setForeground(Color.WHITE);
		btcancel.setForeground(Color.WHITE);

		//��ü ��Ʈ ����
		c.setFont(new Font("�����ٸ����", Font.BOLD, 12));

		//������ ���԰� �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ����
		btsearch.setIcon(new ImageIcon(new ImageIcon("resource/icon/search.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btposter.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btstaff.setIcon(new ImageIcon(new ImageIcon("resource/icon/information.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btdelete.setIcon(new ImageIcon(new ImageIcon("resource/icon/delete.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btsave.setIcon(new ImageIcon(new ImageIcon("resource/icon/save.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		btcancel.setIcon(new ImageIcon(new ImageIcon("resource/icon/mcancel.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

		//�󺧿� �׵θ� ����
		lbposter.setBorder(new LineBorder(Color.BLACK));

		//�˻��ϴ� �κ�
		p1.add(lbsearch);
		p1.add(cbsearch);
		p1.add(tfsearch);
		p1.add(btsearch);

		p2.add(lbgenre); p2.add(cbgenre);//�帣
		p2.add(lbage); p2.add(cbage);//��������
		p2.add(lbname); p2.add(tfname);//��ȭ�̸�
		p2.add(lbprice); p2.add(tfprice);//��ȭ����

		p5.add(lbdate);//������¥
		p4.add(cbyear); p4.add(lbyear);//��
		p4.add(cbmonth); p4.add(lbmonth);//��
		p4.add(cbday); p4.add(lbday);//��
		
		p6.add(lbtime); p6.add(tftime);//�󿵽ð�

		p3.add(btstaff);//�⿬�� ����
		p3.add(btdelete);//����
		p3.add(btsave);//����
		p3.add(btcancel);//���

		//��ġ ����
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

		//��,��,�� �޺��ڽ� ����
		int y = cal.get(cal.YEAR);//���ó⵵ ��������
		for(int i = 1900 ; i <= y;i++ ) {//1900����� ���ó⵵ ������ ����

			cbyear.addItem(i);
		}

		for(int i= 1 ; i <= 12 ; i++) {//1������ 12�ϱ����� ����

			cbmonth.addItem(i);

		}
		//�޺��ڽ� ����
		cbyear.setSelectedIndex(-1);
		cbmonth.setSelectedIndex(-1);

		cbyear.addItemListener(new ItemListener() {//��⵵�� ���õǾ����� Ȯ���ϰ�
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				dateset();//dateset()����
			}
		});
		cbmonth.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {//����� ���õǾ����� Ȯ���ϰ�
				dateset();//dateset()����
			}
		});

		tfsearch.addActionListener(this);
		btsearch.addActionListener(this);
		btposter.addActionListener(this);
		btstaff.addActionListener(this);
		btdelete.addActionListener(this);
		btsave.addActionListener(this);
		btcancel.addActionListener(this);

		//table�� ����� ����  mlist�� ��ü�� �̾Ƴ���.
		DBmgr.sql = "select * from mlist";
		table();

		addWindowListener(new WindowAdapter() {//window �����ư(X) ����
			@Override
			public void windowClosing(WindowEvent arg0) {//Ÿ��Ʋ���� X��ư�� ������ Admin()�� �����ȴ�.
				new Admin_Main();
			}
		});

		this.setIconImage(changedImg);//Ÿ��Ʋ���� ������ ����
		this.setTitle("��ȭ ���� & ����");//Ÿ�̺���� ���� ����
		this.setSize(1040,380);//Frame ��� ũ�� ����
		this.setLocationRelativeTo(null);//Frame ��� ��ġ ����
		this.setResizable(false);//Frame ������ ���� ����
		this.setVisible(true);//������ Frame�� ȭ�鿡 ���
		p1.requestFocus();//p1�� ��Ŀ���� �д�.
	}

	void dateset() {
		try {

			cbday.removeAllItems();//���� �޺��ڽ��� ����.
			//������ �� Calenger.MONTH ���� 0 ~ 11 ���� �����ϸ� ������ ���� 1������ 12���� �Ǹ��Ѵ�.
			//�� �޺��ڽ����� ������ ���� ���ϱ� ���ؼ��� -1�� ����� �մϴ�.
			cal.set(Integer.parseInt(cbyear.getSelectedItem()+""), Integer.parseInt(cbmonth.getSelectedItem()+"")-1,1);
			
			//���õ� ���� ������ ���� ���ϰ� �޺��ڽ��� �߰���Ű�� �ڵ��̴�.
			for(int i = 1 ; i <= cal.getActualMaximum(Calendar.DATE) ; i ++) {
				cbday.addItem(i);
			}

		}catch(Exception e) {
		}

	}

	public static void main(String[] args) {
		new Edit();

	}

	void table() {//���̺��� �����Ѵ�.
		//list�� mlist�� ������ �ִ´�.
		list = mgr.movie();
		//���� list�� �������̰� ���� col�� �����̴�.
		String record[][] = new String[list.size()][col.length];
		for(int i = 0 ; i<list.size() ; i++) {
			//list ���� i��°�� �ִ� ���� bean�� �ְ�
			bean = list.get(i);
			//�� ��� ���� �°� �����͸� �ִ´�.
			record[i][0] = bean.getMgenre();
			record[i][1] = bean.getMname();
			record[i][2] = bean.getMage();
			record[i][3] = bean.getMprice()+"";
			record[i][4] = bean.getMdate();
			record[i][5] = bean.getMtime();

		}
		//DefaultTableModel Ŭ������ ����ؾ� ���̺� ������� ���ų� ������ �����ϴ�.
		//DefaultTableModel ��ü�� �����ϸ鼭 �����͸� ��´�.
		DefaultTableModel model = new DefaultTableModel(record, col) {
			public boolean isCellEditable(int r, int c	) {//isCellEditable�� ���̺��� ���� ���� �� �� ���� ���� ���̴�.
				return false;
			}

		};
		//JTable�� model�� �ִ´�.
		table= new JTable(model);
		//jsp�� table�� ��´�.
		jsp.setViewportView(table);
		
		DefaultTableCellRenderer al = new DefaultTableCellRenderer();//�� �ȿ� ���� �������� ������ �����Ѵ�.
		al.setHorizontalAlignment(JLabel.CENTER);//��� ������ ���־���.
		for(int i = 0 ; i < col.length ; i ++) {
			if(i!=1) {//1���� ��ȭ�̸��� ����
				//��� ������ ���ش�.
				table.getColumnModel().getColumn(i).setCellRenderer(al);

			}
		}

		table.getColumnModel().getColumn(1).setMinWidth(180);//1���� ���� �����Ѵ�.
		table.getColumnModel().getColumn(2).setMinWidth(90);//2���� ���� �����Ѵ�.

		table.addMouseListener(new MouseAdapter() {@Override//���̺��� ���콺 �������̴�.
			public void mouseClicked(MouseEvent arg0) {
			//mlist���� ���õ� ���� ��ȭ�̸��� ���� ������ list�� �ִ´�.
			DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
			list = mgr.movie();
			bean = list.get(0);
			//��ȭ��ȣ�� static�ϰ� �����Ѵ�.
			no = bean.getMno();
			//�޺��ڽ��� bean���� ������ �帣�� ���������� set���ش�. 
			cbgenre.setSelectedItem(bean.getMgenre());
			cbage.setSelectedItem(bean.getMage());
			//������¥�� �������� �����Ͽ� ���ڿ��� �����ؼ�
			String mdate = bean.getMdate();
			String ff[] = mdate.split("-");
			//���� ��,��,���� �޺��ڽ��� set���ش�.
			cbyear.setSelectedItem(Integer.parseInt(ff[0]));
			cbmonth.setSelectedItem(Integer.parseInt(ff[1]));
			cbday.setSelectedItem(Integer.parseInt(ff[2]));
			//bean���� ������ ��ȭ�̸��� ��ȭ�̸� �ؽ�Ʈ�ʵ忡 ��ȭ������ ��ȭ���� �ؽ�Ʈ�ʵ忡 set���ش�.
			tfname.setText(bean.getMname());
			tfprice.setText(bean.getMprice()+"");
			//�󿵽ð��� bean���� ������ ���ڿ����� ���� �߶� ���ڿ��� �󿵽ð� �ؽ�Ʈ�ʵ忡 set���ش�.
			String t1 = bean.getMtime();
			String t2[] = t1.split("��");
			tftime.setText(t2[0]);
			//��ȭ�̸����� ������ ������ �������� �̹��� ũ�� ���� �������ش�.
			fname= "DBFiles\\image\\" + table.getValueAt(table.getSelectedRow(), 1) + ".jpg";
			lbposter.setIcon(new ImageIcon(new ImageIcon(fname).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
			
			//��ȭ��ȣ�� ������ ���ؾ� �ϴ� �κ��� �ֱ⿡ ������ �����س��Ҵ�.
			mno = bean.getMno();

		}
		});

	}

	JFileChooser fc = new JFileChooser();
	int check = 0;//�����ư ���ȴ��� Ȯ���ϱ� ���� ����
	void edit() {
		//DecimalFormat�̶� 10������ ���� ���ϴ� �������� ������ �ִ� Ŭ�����̴�.
		//cbmonth�� cbday�� ���ڸ�, �� 00�� ���·� ������ �ؾ��ϱ� ������ �Ʒ��� ���� �ڵ带 ����Ͽ���.
		DecimalFormat df = new DecimalFormat("00");
		//�󿵽ð��� ������ ���ϴ� �κ��� �ֱ� ������ ������ �����س��Ҵ�.
		date = (cbyear.getSelectedItem() + "-" + df.format(cbmonth.getSelectedItem()) + "-" + df.format(cbday.getSelectedItem()));
		
		//��ȭ�� �����Ѵٸ� update�� ���ش�.
		DBmgr.sql = "update mlist set m_genre = '" + cbgenre.getSelectedItem() + "' , m_age = '" + cbage.getSelectedItem() + "', m_name = '" + tfname.getText() + "', m_price = '" + tfprice.getText() + "', m_date = '" + date + "', m_time = '" + tftime.getText()+"��" + "' where m_no = '" + bean.getMno() + "'";
		list = mgr.update();
		//������ �Ϸ�Ǿ��ٸ� ���� �޽����� ����ش�.
		JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);

		try {
			//�����Ͱ� ������ �ȵǾ��ִٸ�
			if(fc.getSelectedFile()==null) {
				//������ �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
				BufferedImage bi = ImageIO.read(new File(fname));
				//DBFiles\\image\\��� ��ο� �����̸��� �������ְ�
				File f2 =new File("DBFiles\\image\\" + tfname.getText() + ".jpg");
				
				//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
				ImageIO.write(bi, "jpg", f2);

			}else {//�����Ͱ� ������ �Ǿ��ٸ�
				//���ο� �̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
				BufferedImage bi = ImageIO.read(new File(fc.getSelectedFile()+""));
				//���ο� �̹��� ������ DBFiles\\image\\��� ��ο� �����̸��� �������ְ�
				File f2 = new File("DBFiles\\image\\" + tfname.getText() + ".jpg");

				//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
				ImageIO.write(bi, "jpg", f2);

			}

		}catch(Exception e1) {

		}
		//������ �� �Ǿ��⿡ ����  MEdit �������� �����Ű�� �ٽ� MEdit() â�� ���� �������Ѵ�.
		dispose();
		new Edit();

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btsearch) || e.getSource().equals(tfsearch)) {//�˻���ư�� Ŭ���ϰų� �ؽ�Ʈ�ʵ忡�� ����Ű�� ������

			if(cbsearch.getSelectedIndex() == 0) {//�˻��� �޺��ڽ��� ��ü�� ���õǾ� �ִٸ�

				if(tfsearch.getText().equals("")) {//�˻�� �����̸�
					DBmgr.sql = "select * from mlist";//��� ��ȭ�� �˻��Ѵ�.

				}else {//�˻�� �ִٸ�
					DBmgr.sql = "select * from mlist where m_name like '%" + tfsearch.getText() + "%'";//��ȭ�̸����� �˻��Ѵ�.

				}

			}else {//�˻��� �޺��ڽ��� �ٸ� ������ ���õǾ� �ִٸ�

				if(tfsearch.getText().equals("")) {//�˻�� �����̸�
					DBmgr.sql = "select * from mlist where m_genre = '" + cbsearch.getSelectedItem() + "'";//�˻� �޺��ڽ��� ���õ� �帣�� ��� ��ȭ�� �˻��Ѵ�.

				}else {//�˻�� �ִٸ�
					DBmgr.sql = "select * from mlist where m_name like '%" + tfsearch.getText() + "%' and m_genre = '" + cbsearch.getSelectedItem() + "'";//��ȭ�̸��� �帣�� �˻��Ѵ�.

				}

			}
			//table()�� �����Ų��.
			table();

		}else if(e.getSource().equals(btposter)) {//������ ���ù�ư�� Ŭ���ϸ�
			//���� ���� ����
			fc.setFileFilter(new FileNameExtensionFilter("JPGE image", "jpg"));
			//����� â�� �����.
			check = fc.showOpenDialog(this);
			
			if(check == JFileChooser.APPROVE_OPTION) {//�����ư�� Ŭ���ߴ��� Ȯ��

				//���õ� ������ �����´�.
				File f2 = fc.getSelectedFile();
				//���õ� ������ �󺧿� �����Ѵ�. �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ����
				lbposter.setIcon(new ImageIcon(new ImageIcon(f2+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));

			}


		}else if(e.getSource().equals(btstaff)) {//�⿬ ������ư�� Ŭ���ϸ�
			
			if(table.getSelectedRowCount()==0) {//���̺��� ���õ� ���� ���ٸ� ���޽����� ����ش�.
				
				JOptionPane.showMessageDialog(null, "�⿬ ������ �� ��ȭ�� �������ּ���.", "�޽���", JOptionPane.WARNING_MESSAGE);

			}else {//���̺��� ���õȰ��� �ִٸ� ���õ� ��ȭ�� ��ȣ�� ������ �⿬�� ���� ���� â�� ���� ����ش�.

				new Staff("���� & �⿬�� ���� ����", this, no);
				//���� �ۼ��ϴ� ��ȭ��� â�� �Ⱥ��̰� �����Ѵ�.
				setVisible(false);

			}
		}else if(e.getSource().equals(btdelete)) {//���� ��ư�� Ŭ������ ���
			
			if(table.getSelectedRowCount()==0) {//���̺��� ���õ� ���� ���ٸ� ���޽����� ����ش�.

				JOptionPane.showMessageDialog(null, "������ ��ȭ�� �������ּ���.", "�޽���", JOptionPane.WARNING_MESSAGE);

			}else{//���̺��� ���õȰ��� �ִٸ� ���õ� ��ȭ�� ������ �����Ѵ�.

				//���õ� ��ȭ�̸����� �˻��� �����ϰ� ���� ����� list�� �ִ´�.
				DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
				list = mgr.movie();
				//list�� 0��°�� �ִ� ���� bean�� �ִ´�.
				bean = list.get(0);
				
				//�⿬�� ������ �����ϱ����� ��ȭ��ȣ�� �´� �⿬�� ������ �����ͼ� list2�� �ִ´�
				DBmgr.sql = "select * from stafflist where m_no = '" + bean.getMno() + "'";
				list2 = mgr.staff();
				bean = list2.get(0);
				
				//�⿬�� ���� ����
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

				//stafflist���� ��ȭ��ȣ�� ���� ���� �����Ѵ�.
				DBmgr.sql = "delete from stafflist where m_no = '" + bean.getMno() + "'";
				list = mgr.update();
				//shopping���� ��ȭ��ȣ�� ���� ���� �����Ѵ�.
				DBmgr.sql = "delete from shopping where m_no = '" + bean.getMno() + "'";
				list = mgr.update();
				//olist���� ��ȭ��ȣ�� ���� ���� �����Ѵ�.
				DBmgr.sql = "delete from olist where m_no = '" + bean.getMno() + "'";
				list = mgr.update();
				//mlist���� ��ȭ��ȣ�� ���� ���� �����Ѵ�.
				DBmgr.sql = "delete from mlist where m_no = '" + bean.getMno() + "'";
				list = mgr.update();
				//�̷��� ������ �����ϴ� ������ �ܷ�Ű�� �����Ǿ� �ֱ� �����̴�.

				//���������� ������ ������ �����Ѵ�.
				File ff = new File(fname);
				ff.delete();
				
				//������ �� �� �����޽��� â�� ����ش�.
				JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
				
				//������ �Ǿ��� ������ ���̺��� �ٽ� �����ϱ� ����
				//���� â�� �����ϰ� �� MEdit()â�� ����.
				dispose();
				new Edit();
			}

		}else if(e.getSource().equals(btsave)) {//���� ��ư�� Ŭ������ ���
			
			if(table.getSelectedRowCount()==0) {//���̺��� ���õ� ���� ���ٸ� ���޽����� ����ش�.

				JOptionPane.showMessageDialog(null, "������ ��ȭ�� �������ּ���,", "�޼���", JOptionPane.WARNING_MESSAGE);

			}else {//���̺��� ���õȰ��� �ִٸ� ���õ� ��ȭ�� ������ �����Ѵ�.
				
				//���õ� ��ȭ�̸����� �˻��� �����ϰ� ���� ����� list�� �ִ´�.
				DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
				list = mgr.movie();
				//list�� 0��°�� �ִ� ���� bean�� �ִ´�.
				bean = list.get(0);
				
				//DecimalFormat�̶� 10������ ���� ���ϴ� �������� ������ �ִ� Ŭ�����̴�.
				//cbmonth�� cbday�� ���ڸ�, �� 00�� ���·� ������ �ؾ��ϱ� ������ �Ʒ��� ���� �ڵ带 ����Ͽ���.
				DecimalFormat df = new DecimalFormat("00");
				date = (cbyear.getSelectedItem() + "-" + df.format(cbmonth.getSelectedItem()) + "-" + df.format(cbday.getSelectedItem()));
				
				//�󿵽ð��� t1�� �ְ�
				String t1 = bean.getMtime();
				//������ ���ڿ����� ���� �߶� ���ڿ� t2�� �ִ´�.
				String t2[] = t1.split("��");
				
				//��ĭ Ȯ���� �ϰ� ��ĭ�� ������ ���� �޽����� ����.
				if(cbgenre.getSelectedIndex() == -1 || cbage.getSelectedIndex() == -1 || cbyear.getSelectedIndex() == -1 || cbmonth.getSelectedIndex() == -1 || cbday.getSelectedIndex() == -1 || tfname.getText().equals("") || tfprice.getText().equals("") || tftime.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�", "�޼���", JOptionPane.ERROR_MESSAGE);

				}//������ ������ ���� ���� �ֱ� ������ �̰��� Ȯ���ϴ� �ڵ嵵 �־��ش�.
				//���� ��ȭ������ DB�� �ִ� ��ȭ������ ���Ͽ� ��� �� ������ ����� ������ ���ٴ� ���� �޽��� â�� ����ش�.
				else if(cbgenre.getSelectedItem().equals(bean.getMgenre())&& cbage.getSelectedItem().equals(bean.getMage())&&tfname.getText().equals(bean.getMname())&&tfprice.getText().equals(bean.getMprice()+"")&&date.equals(bean.getMdate())&&tftime.getText().equals(t2[0])){
					
					JOptionPane.showMessageDialog(null, "����� ������ �����ϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
				
				}else{//��ĭ�� ���� ����� ������ ���� ���
					
					if(tfprice.getText().matches("[0-9]+") && tftime.getText().matches("[0-9]+")) {//���ݰ� �󿵽ð��� ���� �������� Ȯ���Ѵ�.
						//��ȭ�̸����� ��ȭ������ �����ͼ� list�� �ִ´�.
						DBmgr.sql = "select * from mlist where m_name = '" + tfname.getText() + "'";
						list = mgr.movie();

						try {
							bean = list.get(0);

							if(mno!=bean.getMno()) {//���̺��� ���õ� ��ȭ��ȣ�� ������ list���� ��ȭ��ȣ�� �ٸ��ٸ� ��ȭ�̸��� ��ó���� ���̱� ������
								//�̹� �����ϴ� ��ȭ��� ���� �޽����� ����ش�.
								JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ��ȭ���Դϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);

							}else {
								//���� if���� �ƴ϶�� edit()�� �����Ѵ�.
								edit();

							}

						}catch(Exception e1) {
							//������ ���� �����ϴ� edit()�� �����Ѵ�.
							edit();

						}

					}else {
						if(!tfprice.getText().matches("[0-9]+")){//������ ���� ���°� �ƴ϶�� ���� �޽����� ����ش�.

							JOptionPane.showMessageDialog(null, "������ ���ڷ� �Է��� �ּ���.", "�޽���", JOptionPane.ERROR_MESSAGE);

						}
						else if(!tftime.getText().matches("[0-9]+")) {//�󿵽ð��� ���� ���°� �ƴ϶�� ���� �޽����� ����ش�.
							JOptionPane.showMessageDialog(null, "�󿵽ð��� ���ڷ� �Է����ּ���.", "�޼���", JOptionPane.ERROR_MESSAGE);
						}

					}

				}

			}

		}else if(e.getSource().equals(btcancel)) {//��� ��ư�� Ŭ������ ��� ����  MEdit �����Ӹ� �����Ű�� Admin() â�� ���� ����
			dispose();
			new Admin_Main();
		}

	}

}

package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import Setting.Bean;
import Setting.DBmgr;

public class Insert extends JFrame implements ActionListener {

	static String insert, mname;

	Container c = getContentPane();

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JLabel lbposter = new JLabel();

	JLabel lbgenre = new JLabel("�帣");
	JLabel lbage = new JLabel("���� ����");
	JLabel lbname = new JLabel("��ȭ��");
	JLabel lbprice = new JLabel("����");

	JLabel lbdate = new JLabel("���� ��¥");
	JLabel lbtime = new JLabel("�� �ð�");
	JLabel lbminute = new JLabel("��");

	JTextField tfname = new JTextField(15);
	JTextField tfprice = new JTextField(15);
	JTextField tftime = new JTextField(10);

	String cgenre[] = {"SF","����","�θǽ�","�ִϸ��̼�","�׼�","�ڹ̵�"};
	String cage[] = {"��ü ������", "12�� ������", "15�� ������","û�ҳ� �����Ұ�"};
	JComboBox cbgenre = new JComboBox(cgenre);
	JComboBox cbage = new JComboBox(cage);

	JLabel lbyear = new JLabel("��");
	JLabel lbmonth = new JLabel("��");
	JLabel lbday = new JLabel("��");

	JComboBox cbyear = new JComboBox();
	JComboBox cbmonth = new JComboBox();
	JComboBox cbday = new JComboBox();

	JButton btposter = new JButton("������ ���");
	JButton btsave = new JButton("����");
	JButton btcancel = new JButton("���");

	JPanel p1 = new JPanel(new GridLayout(4,2,-80,8));
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel(new GridLayout(1, 1));
	JPanel p5 = new JPanel(new GridLayout(1, 2,-60, 8));

	Calendar cal = Calendar.getInstance();

	ArrayList<Bean> list =new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Insert() {

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
		cbgenre.setBackground(Color.WHITE);
		cbage.setBackground(Color.WHITE);
		cbyear.setBackground(Color.WHITE);
		cbmonth.setBackground(Color.WHITE);
		cbday.setBackground(Color.WHITE);
		btposter.setBackground(new Color(69, 68, 66));
		btsave.setBackground(new Color(69, 68, 66));
		btcancel.setBackground(new Color(69, 68, 66));

		//���ڻ� ����
		btposter.setForeground(Color.WHITE);
		btsave.setForeground(Color.WHITE);
		btcancel.setForeground(Color.WHITE);

		//��ü ��Ʈ ����
		c.setFont(new Font("�����ٸ����", Font.BOLD, 12));

		//������ ���԰� �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ���� 
		btposter.setIcon(new ImageIcon(new ImageIcon("resource/icon/input.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btsave.setIcon(new ImageIcon(new ImageIcon("resource/icon/save.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btcancel.setIcon(new ImageIcon(new ImageIcon("resource/icon/mcancel.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

		//�󺧿� �׵θ� ����
		lbposter.setBorder(new LineBorder(Color.BLACK));

		p1.add(lbgenre); p1.add(cbgenre);//�帣
		p1.add(lbage); p1.add(cbage);//��������
		p1.add(lbname); p1.add(tfname);//��ȭ�̸�
		p1.add(lbprice); p1.add(tfprice);//��ȭ����

		p4.add(lbdate);//������¥
		p3.add(cbyear); p3.add(lbyear);//��
		p3.add(cbmonth); p3.add(lbmonth);//��
		p3.add(cbday); p3.add(lbday);//��

		p5.add(lbtime); p5.add(tftime);//�󿵽ð�

		p2.add(btsave);//����
		p2.add(btcancel);//���

		//��ġ ����
		p1.setBounds(15,15,250,130);
		p4.setBounds(15,148,60,30);
		p3.setBounds(90, 148, 200, 30);
		p5.setBounds(15,190,230,25);
		lbminute.setBounds(250,190,20,25);
		lbposter.setBounds(320,15,150,150);
		btposter.setBounds(320,170,150,35);
		p2.setBounds(35,250,400,35);

		this.add(p1);
		this.add(p4);
		this.add(p3);
		this.add(p5);
		this.add(lbminute);
		this.add(lbposter);
		this.add(btposter);
		this.add(p2);

		//��,��,�� �޺��ڽ� ����
		int y = cal.get(cal.YEAR);//���ó⵵ ��������
		for(int i = 1900 ; i <= y;i++ ) {//1900����� ���ó⵵ ������ ����
			cbyear.addItem(i);
		}
		for(int i= 1 ; i <= 12 ; i++) {//1������ 12�������� ����
			cbmonth.addItem(i);
		}
		
		//�޺��ڽ� ����
		cbyear.setSelectedItem(y);
		cbmonth.setSelectedIndex(0);
		dateset();
		cbday.setSelectedIndex(0);
		

		cbyear.addItemListener(new ItemListener() {//��⵵�� ���õǾ����� Ȯ���ϰ�
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				dateset();//dateset()����
			}
		});
		cbmonth.addItemListener(new ItemListener() {//����� ���õǾ����� Ȯ���ϰ�
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				dateset();//dateset()����
			}
		});

		btposter.addActionListener(this);
		btsave.addActionListener(this);
		btcancel.addActionListener(this);

		addWindowListener(new WindowAdapter() {//window �����ư(X) ����
			@Override
			public void windowClosing(WindowEvent arg0) {//Ÿ��Ʋ���� X��ư�� ������ Admin()�� �����ȴ�.
				new Admin_Main();
			}
		});
		this.setIconImage(changedImg);//Ÿ��Ʋ���� ������ ����
		this.setTitle("��ȭ ���");//Ÿ�̺���� ���� ����
		this.setSize(500, 350);//Frame ��� ũ�� ����
		this.setLocationRelativeTo(null);//Frame ��� ��ġ ����
		this.setResizable(false);//Frame ������ ���� ����
		this.setVisible(true);//������ Frame�� ȭ�鿡 ���

	}

	void dateset() {
		try {

			cbday.removeAllItems();//���� �޺��ڽ��� ����.
			//������ �� Calenger.MONTH ���� 0 ~ 11 ���� �����ϸ� ������ ���� 1������ 12���� �Ǹ��Ѵ�.
			//�� �޺��ڽ����� ������ ���� ���ϱ� ���ؼ��� -1�� ����� �մϴ�.
			cal.set(Integer.parseInt(cbyear.getSelectedItem()+""), Integer.parseInt(cbmonth.getSelectedItem()+"")-1,1);
			
			//���õ� ���� ������ ���� ���ϰ� �޺��ڽ��� �߰���Ű�� �ڵ��̴�.
			for(int i = 1 ; i <= cal.getActualMaximum(Calendar.DATE); i ++) {
				cbday.addItem(i);
			}

		}catch(Exception e) {
		}
	}

	public static void main(String[] args) {
		new Insert();

	}

	JFileChooser fc= new JFileChooser();
	int check = 0;//���� �����ư ���ȴ��� Ȯ���ϱ� ���� ����
	int ok = 0;//�̹����� �����Ͽ����� Ȯ���ϱ� ���� ����
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btposter)) {//������ ��� ��ư�� Ŭ������ ���

			//���� ���� ����
			fc.setFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
			//����� â�� �����.
			check = fc.showOpenDialog(this);

			if(check == JFileChooser.APPROVE_OPTION ) {//�����ư�� Ŭ���ߴ��� Ȯ��

				//���õ� ������ �����´�.
				File file = fc.getSelectedFile();
				//���õ� ������ �󺧿� �����Ѵ�. �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ����
				lbposter.setIcon(new ImageIcon(new ImageIcon(file+"").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				ok = 1;//�̹��� ��� Ȯ��

			}

		}else if(e.getSource().equals(btsave)) {//���� ��ư�� Ŭ������ ���
			//�帣, ��������, ��ȭ��, ����, ������¥, �󿵽ð��� ��ĭ���� Ȯ��
			if(cbgenre.getSelectedIndex() == -1 || cbage.getSelectedIndex() == -1 || tfname.getText().equals("")||tfprice.getText().equals("")|| tftime.getText().equals("") || cbyear.getSelectedIndex()==-1||cbmonth.getSelectedIndex()==-1||cbday.getSelectedIndex()==-1) {
				
				JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
			}if(ok == 0) {//������ ����� �Ͽ����� Ȯ��

				JOptionPane.showMessageDialog(null, "�����Ͱ� �����ϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);

			}else {
				//���ݰ� �ð��� ������������ Ȯ��
				//[] : ������ �����̳� ������ ��Ÿ���� �� ���� ������ - ��ȣ�� ������ ��Ÿ����. + : �� ���ڰ� �ϳ� �̻�
				if(tfprice.getText().matches("[0-9]+") && tftime.getText().matches("[0-9]+")) {
					//mlist���� ��ȭ�̸��� �ؽ�Ʈ�ʵ��� �̸��� ���� ���� list�� �ִ´�.
					DBmgr.sql = "select * from mlist where m_name = '" + tfname.getText() + "'";
					list = mgr.movie();

					try {//try������ ���� �̹� �ִ� ��ȭ�� �ִ� ���̱� ������
						bean = list.get(0);
						//���� �޽����� ����.
						JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ��ȭ�Դϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);

					}catch(Exception e1) {//catch�� ������ ���
						//DecimalFormat�̶� 10������ ���� ���ϴ� �������� ������ �ִ� Ŭ�����̴�.
						//cbmonth�� cbday�� ���ڸ�, �� 00�� ���·� ������ �ؾ��ϱ� ������ �Ʒ��� ���� �ڵ带 ����Ͽ���.
						DecimalFormat df = new DecimalFormat("00");
						
						//���� â���� insert�� ����Ǹ� �ȵǱ� ������ String ������ �����س��� MStaff���� ������ Ŭ���ϸ� ��ȭ������ �⿬�������� ���ʴ�� insert �ȴ�.
						insert = "insert into mlist(m_no, m_genre, m_name, m_age, m_price, m_date, m_time) values( sm_no.nextval, '" 
								+ cbgenre.getSelectedItem() + "', '" + tfname.getText() + "', '" + cbage.getSelectedItem() + "', '" + tfprice.getText() + "', '" 
								+ (cbyear.getSelectedItem() + "-" + df.format(cbmonth.getSelectedItem()) + "-" + df.format(cbday.getSelectedItem())) + "', '" + tftime.getText() + "��" + "')";

						try {
							//�̹��� ������ �о�ͼ� BufferedImage�� �ִ´�.
							BufferedImage bi = ImageIO.read(new File(fc.getSelectedFile()+""));
							//DBFiles\\image\\��� ��ο� �����̸��� �������ְ�
							File f2 = new File("DBFiles\\image\\" + tfname.getText() + ".jpg");
							//f2�� �ش� ��ο� bi�� ������ �̹����� �������ش�.
							ImageIO.write(bi, "jpg", f2);

						}catch(Exception e2) {

						}
						
						//�⿬�� ��Ͽ��� ����� �� �ְ� ��ȭ�̸��� static�� ���·� �����صд�.
						mname = tfname.getText();
						
						//��ȭ ������ �˸°� ���ٸ� ����&�⿬�� ������ ����ϴ� â�� ���� ����.
						new Staff("���� & �⿬�� ���� ���", this);
						//���� �ۼ��ϴ� ��ȭ��� â�� �Ⱥ��̰� �����Ѵ�.
						setVisible(false);

					}

				}else{//���ݰ� �󿵽ð��� ���� ���·� �Է��� �ȵǾ��� ��� ���� �޽��� â�� ����. 
					if(!tfprice.getText().matches("[0-9]+")) {
						JOptionPane.showMessageDialog(null, "������ ���ڷ� �Է����ּ���.", "�޼���", JOptionPane.ERROR_MESSAGE);
					}else if(!tftime.getText().matches("[0-9]+")) {
						JOptionPane.showMessageDialog(null, "�󿵽ð��� ���ڷ� �Է����ּ���.", "�޼���", JOptionPane.ERROR_MESSAGE);
					}

				}

			}

		}else if(e.getSource().equals(btcancel)) {//��� ��ư�� Ŭ���ϸ� ����  MInsert �����Ӹ� �����Ű�� Admin() â�� ���� ����.
			dispose();
			new Admin_Main();
		}

	}

}

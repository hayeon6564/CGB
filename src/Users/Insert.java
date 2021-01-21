package Users;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import Setting.Bean;
import Setting.DBmgr;

public class Insert extends JFrame implements ActionListener {

	Container c = getContentPane();
	int check = 0;
	String overlap = "";

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JLabel lbl1 = new JLabel("�̸�",JLabel.RIGHT);
	JLabel lbl2 = new JLabel("���̵�",JLabel.RIGHT);
	JLabel lbl3 = new JLabel("��й�ȣ",JLabel.RIGHT);
	JLabel lbl4 = new JLabel("�������",JLabel.RIGHT);
	JLabel lbl5 = new JLabel("��");
	JLabel lbl6 = new JLabel("��");
	JLabel lbl7 = new JLabel("��");
	JLabel lbl8 = new JLabel("��й�ȣ", JLabel.RIGHT);
	JLabel lbl9 = new JLabel("Ȯ��", JLabel.RIGHT);

	JTextField tf1 = new JTextField(); 			//�̸�
	JTextField tf2 = new JTextField();			//���̵�
	JPasswordField pf = new JPasswordField(); 	//��й�ȣ
	JPasswordField pf1 = new JPasswordField(); 	//��й�ȣ Ȯ��
	
	JComboBox cb1 = new JComboBox();	// ��
	JComboBox cb2 = new JComboBox();	// ��
	JComboBox cb3 = new JComboBox();	// ��

	JButton btn1 = new JButton("����");
	JButton btn2 = new JButton("���");
	JButton btn3 = new JButton("IDȮ��");	

	JPanel pan1 = new JPanel(new GridLayout(3,2,5,20));
	JPanel pan2 =new JPanel();
	JPanel pan3 = new JPanel();

	Calendar cal = Calendar.getInstance();	// getInstance() �޼ҵ� ����Ͽ� ���� ��¥�� �ð��� ��ü�� ���´�.

	ArrayList<Bean> list = new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Insert() {
		setIconImage(changedImg);

		btn1.setIcon(new ImageIcon(new ImageIcon("resource/icon/join.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn2.setIcon(new ImageIcon(new ImageIcon("resource/icon/cancel.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn3.setIcon(new ImageIcon(new ImageIcon("resource/icon/check.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

		setLayout(null);

		pan1.add(lbl1); pan1.add(tf1);				// �̸�
		pan1.add(lbl2); pan1.add(tf2); add(btn3);	// ���̵� ID �ߺ�Ȯ��
		pan1.add(lbl3); pan1.add(pf);				// ��й�ȣ 
		add(lbl8);	add(lbl9); 	add(pf1);			// ��й�ȣ Ȯ��

		pan2.add(lbl4);	// �������
		pan2.add(cb1); pan2.add(lbl5);	// ��
		pan2.add(cb2); pan2.add(lbl6);	// ��
		pan2.add(cb3); pan2.add(lbl7);	// ��

		pan3.add(btn1);	// ����	
		pan3.add(btn2);	// ���

		c.setFont(new Font("�����ٸ����", Font.BOLD, 12));	// ��ü ��Ʈ ����

		pan1.setBounds(-190, 20, 500, 130);
		pan2.setBounds(-130, 215, 520, 35);
		pan3.setBounds(90, 250, 250, 35);
		btn3.setBounds(315, 70, 110, 27);
		lbl8.setBounds(3, 170, 55, 20);
		lbl9.setBounds(15, 185, 30, 20);
		pf1.setBounds(63, 170, 247, 30);

		add(pan1);
		add(pan2);
		add(pan3);
		
		// ��ư ���� ��� ǥ�� ����
		btn1.setContentAreaFilled(false); 
		btn2.setContentAreaFilled(false); 
		btn3.setContentAreaFilled(false); 
		
		// �޺��ڽ� ��� ���
		cb1.setBackground(Color.WHITE);
		cb2.setBackground(Color.WHITE);
		cb3.setBackground(Color.WHITE);
		
		// ��� ���
		c.setBackground(Color.WHITE);
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.WHITE);
		pan3.setBackground(Color.WHITE);

		// ����⵵�� �����´�
		int y = cal.get(cal.YEAR);
	
		// 1900�⵵���� 2019�⵵���� �̾ƿ´�. �̾ƿ°� cb1�� �����Ѵ�.	
		for(int i = 1900 ; i <= y; i++ ) {
			cb1.addItem(i);
		}
		
		// 1������ 12������ �̾ƿ´�. �̾ƿ°� cb2�� �����Ѵ�.
		for(int i= 1 ; i <= 12 ; i++) {
			cb2.addItem(i);
		}
		
		cb1.setSelectedItem(y);	// �ʱⰪ�� ��ĭ���� ����
		cb2.setSelectedIndex(0);	// �ʱⰪ�� ��ĭ���� ����
		dd();
		cb3.setSelectedIndex(0);

		cb1.addItemListener(new ItemListener() {
			@Override
			// ������ ���°� ���õǾ��� �� 
			public void itemStateChanged(ItemEvent e) {
				dd();
				cb3.setSelectedIndex(0);
			}
		});

		cb2.addItemListener(new ItemListener() {
			@Override
			// ������ ���°� ���õǾ��� ��
			public void itemStateChanged(ItemEvent e) {
				dd();
				cb3.setSelectedIndex(0);
			}
		});

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		// ������ �̺�Ʈ ���
		addWindowListener(new WindowAdapter() {
			@Override
		// �����찡 �������� �Ҷ�
			public void windowClosing(WindowEvent e) {
				new Login(); // �α���â
			}
		});

		setTitle("ȸ������");
		setSize(450,330); 		// â ũ��
		setLocationRelativeTo(null);
		setResizable(false); 	// âũ�� �Һ�
		setVisible(true);

	}

	void dd() {
		try {
			cb3.removeAllItems(); // cb3�� ��� �׸��� �����Ѵ�.
			// cb1�� ���õ� �Ͱ� cb2�� ���õ� ���� �� ��ȯ�Ѵ�. ��ǻ�Ͱ� 0~11���� �˱� ������ -1�� �� ���� ���Ѵ�. 
			cal.set(Integer.parseInt(cb1.getSelectedItem()+""), Integer.parseInt(cb2.getSelectedItem()+"")-1,1);
			for(int i = 1 ; i <= cal.getActualMaximum(Calendar.DATE); i ++) { // ��¥�� ���õ� Calender�� ������ �ִ� ��
				cb3.addItem(i);
			}
		}catch(Exception e) { }
	}
	
	public static void main(String[] args) {
		//new Insert();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// btn1(����)�� ������ ��
		if(e.getSource().equals(btn1)) {
		// check�� 0�̸� �޽����� ����.
			if(check == 0) {
	            JOptionPane.showMessageDialog(null, "���̵� �ߺ�Ȯ���� ���ּ���!", "�޽���", JOptionPane.ERROR_MESSAGE);
	         }
		// tf2 ID�� overlap�� ���� �� 
			else if(tf2.getText().equals(overlap)) {
				// tf1�� ��ĭ �Ǵ� tf2�� ��ĭ �Ǵ� pf�� ��ĭ �Ǵ� cb1�� -1�� ��ĭ �Ǵ� cb2�� -1�� ��ĭ �Ǵ� cb3 -1�� ��ĭ�϶� �޽����� ����
				if(tf1.getText().equals("")||tf2.getText().equals("")||pf.getText().equals("")||cb1.getSelectedIndex()==-1||cb2.getSelectedIndex()==-1||cb3.getSelectedIndex()==-1) {
					
					JOptionPane.showMessageDialog(null, "������ �׸��� �ֽ��ϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
				
				}else if(!pf.getText().equals(pf1.getText())) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٸ��ϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
				}
				// check�� 1�� ��
				else if(check == 1) {
		            DecimalFormat df = new DecimalFormat("00"); // ���ڸ� �������� ��������� �� ���� �Ʒ��� ���� ���� ǥ���ϱ� ���� ���
		            DBmgr.sql = "insert into ulist(u_no, u_id, u_pw, u_name, u_bd, u_point) values(su_no.nextval,'" + tf2.getText() + "','" + pf.getText() + "', '" + tf1.getText() + "','" + (cb1.getSelectedItem() + "-" + df.format(cb2.getSelectedItem()) + "-" + df.format(cb3.getSelectedItem())) + "', 0)";
		            list = mgr.update();

		            JOptionPane.showMessageDialog(null, "���ԿϷ�Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);

		            dispose();
		            new Login();
		         }

				// tf2�� ������ overlap�� �ߺ��˻��Ѱ��̶� �ٸ��� ������ ������ �޽����� ����
			}else{
				JOptionPane.showMessageDialog(null, "�����˻��� ID�� �ٸ��ϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
				btn3.setForeground(Color.BLACK);
			}
			
			// btn3(ID Ȯ��)�� ������ ��
		}else if(e.getSource().equals(btn3)) {
			// tf2(ID)�� ����� �� �޽����� ���� �ؽ�Ʈ ���� ���������� ����
			if (tf2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "ID�� �Է����ּ���", "�޽���", JOptionPane.ERROR_MESSAGE);
				btn3.setForeground(Color.RED);
				// tf2(ID)�� 4�ڸ� �̻��� �ƴ� �� �޽����� ����
			}else if(tf2.getText().length() < 4) {
				JOptionPane.showMessageDialog(null, "���̵� 4�ڸ� �̻� �ۼ����ּ���!", "�޽���", JOptionPane.ERROR_MESSAGE);
				// tf2(ID)�� "admin"�� �� �޽����� ����. *admin�� ������ ID�̴�. 
			}else if(tf2.getText().equals("admin")) {
				JOptionPane.showMessageDialog(null, "�� ID�δ� ������ �� �����ϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
				// ���� ���ǵ��� �ƴ� ��
			}else {
				// DB�� ulist�� u_id�� tf2�� �������� �̰� list�� ����
				DBmgr.sql = "select * from ulist where u_id = '" + tf2.getText() + "'";
				list = mgr.user();
				try {
					// 0��°���� ��Ȯ���� DB�� �ִ°Ͱ� ������ �޽����� ���� �ؽ�Ʈ�� ���������� �����ϰ� check�� 0���� �Ѵ�.
					bean = list.get(0);
					JOptionPane.showMessageDialog(null, "���̵� �ߺ��Ǿ����ϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
					btn3.setForeground(Color.RED);
					check = 0;
				}catch(Exception e1) {
					// try���� �������� �ʾ��� �� �޽����� ���� �ؽ�Ʈ�� �Ķ������� �ϰ� check�� 1�� �����ϰ� tf2�� ������ overlap�� �����Ѵ�.
					JOptionPane.showMessageDialog(null, "��밡�� ID�Դϴ�..", "�޽���", JOptionPane.INFORMATION_MESSAGE);
					btn3.setForeground(Color.BLUE);
					check = 1;
					overlap = tf2.getText();
				}
			}
			// btn2(���)�� ������ �� ���� â�� ����ǰ� �α���â�� ����ȴ�.
		}else if(e.getSource().equals(btn2)) {
			dispose();
			new Login();

		}
	}
}

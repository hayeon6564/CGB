package Users;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import Setting.Bean;
import Setting.DBmgr;
import Admin.Admin_Main;
import Chat.ServerFrameThread;

public class Login extends JFrame implements ActionListener {
	public static String id, name, no, bd;
	Container c = getContentPane();
	// ImageIcon ��ü��  ����
	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	
	// ImageIcon���� Image�� ����
	Image originImg = originIcon.getImage();
	
	// ����� Image�� ũ�⸦ �����ؼ� ���ο� Image��ü�� ����
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	
	JLabel lbl1 = new JLabel(); // CGB title
	JLabel lbl2 = new JLabel(); // ID
	JLabel lbl3 = new JLabel();	// PW

	JTextField tf1 = new JTextField("");		 // ID
	JPasswordField pf1 = new JPasswordField(""); // PW
	
	JButton btn1 = new JButton();			//�α���
	JButton btn2 = new JButton("ȸ������");
	JButton btn3 = new JButton("ID/PW ã��");
	JButton btn4 = new JButton("����");

	JPanel pan1 = new JPanel(); // ID PW �α���
	JPanel pan2 = new JPanel();	// ȸ������ ����
	JPanel pan3 = new JPanel();	// ID PW

	ArrayList<Bean> list = new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();
	
	public Login() {
		setIconImage(changedImg);
		
		lbl1.setIcon(new ImageIcon(new ImageIcon("resource/icon/title.png").getImage().getScaledInstance(120, 45, Image.SCALE_SMOOTH)));
		lbl1.setHorizontalAlignment(JLabel.CENTER); // �� �ؽ�Ʈ�� �߾����� ����
		lbl2.setIcon(new ImageIcon(new ImageIcon("resource/icon/id.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		lbl2.setHorizontalAlignment(JLabel.RIGHT); // �� �ؽ�Ʈ�� ���������� ����
		lbl3.setIcon(new ImageIcon(new ImageIcon("resource/icon/pw.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		lbl3.setHorizontalAlignment(JLabel.RIGHT); // �� �ؽ�Ʈ�� ���������� ���� 
		btn1.setIcon(new ImageIcon(new ImageIcon("resource/icon/login.png").getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH)));
		btn2.setIcon(new ImageIcon(new ImageIcon("resource/icon/singup.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn3.setIcon(new ImageIcon(new ImageIcon("resource/icon/searchs.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn4.setIcon(new ImageIcon(new ImageIcon("resource/icon/exits.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

		pan1.setLayout(null);
		pan3.setLayout(new GridLayout(2,2,10,10));

		pan1.add(pan3);// ID field, PW field
		pan1.add(btn1);// �α��� ��ư

		pan2.add(btn2);// ȸ������ ��ư
		pan2.add(btn3);// ID/PW ã�� ��ư
		pan2.add(btn4);// ���� ��ư

		pan3.add(lbl2); pan3.add(tf1);	// ID : tf1
		pan3.add(lbl3); pan3.add(pf1);	// PW : pf1
		
		c.setFont(new Font("�����ٸ����", Font.BOLD, 12)); // ��ü ��Ʈ ����

		pan3.setBounds(-60, 30, 270, 60);
		btn1.setBounds(230, 30, 80, 60);

		add(lbl1,"North");
		add(pan1,"Center");
		add(pan2, "South");

		tf1.addActionListener(this);
		pf1.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn4.addActionListener(this);
		btn3.addActionListener(this);
		
		// ��ư ���� ��� ǥ�� ����
		btn2.setContentAreaFilled(false); 
		btn4.setContentAreaFilled(false); 
		btn3.setContentAreaFilled(false); 

		// ���� ���
		c.setBackground(Color.WHITE);
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.WHITE);
		pan3.setBackground(Color.WHITE);
		
		setTitle("�α���");
		setSize(350,230);
		setLocationRelativeTo(null); // ������ â�� ȭ���� ��� ���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������������ ���� �� ���α׷� ����
		setResizable(false); // âũ�� �Һ�
		setVisible(true); // â���� �����ش�
		
		tf1.requestFocus();
	}

	public static void main(String[] args) {
		new Login();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// �α����� �����ų� pf1���� ���͸� �����ų� tf1���� ���͸� �������� check() ����
		if(e.getSource().equals(btn1) || e.getSource().equals(pf1) || e.getSource().equals(tf1)) {
			check();
		// btn2(ȸ������)�� �������� �α���â�� ����ǰ� ȸ������â�� ��� 
		}else if(e.getSource().equals(btn2)) {
			dispose();
			new Insert();
		// btn4(����)�� �������� ���μ��� ����	
		}else if(e.getSource().equals(btn4)) {
			System.exit(0);
		}
		// btn3(ID/PW ã��)�� �������� �α���â�� ����ǰ� ID/PW ã�Ⱑ ����
		else if(e.getSource().equals(btn3)) {
			dispose();
			new Search();
		}
	}

	private void check() {
		// tf1�� ��ĭ�̰ų� pf1�� ��ĭ�϶� �޽����� ���� tf1�� pf1�� ��ĭ���� ����� tf1�� ��Ŀ���� �����.
		if(tf1.getText().equals("")||pf1.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
			tf1.setText("");
			pf1.setText("");
			tf1.requestFocus();
		// tf1�� pf1�� ��ĭ�� �ƴҶ� ����
		}else {
			// ulist���� u_id�� tf1�� ���� u_pw�� pf1�� �������� �̾ƿ´�.
			DBmgr.sql = "select * from ulist where u_id = '" + tf1.getText() + "' and u_pw = '" + pf1.getText() + "'";
			// �̾ƿ°��� list�� �ִ´�.
			list = mgr.user();
			
			try {
			// list.get(0)��°���� Ȯ����
				bean = list.get(0);
			// ������ �ٸ��������� �� �ؿ� static���� �ø� no, id, bd, name�� �����Ų��. 
				no = bean.getUno()+"";
				id = bean.getUid();
				bd = bean.getUbd();
				name = bean.getUname();
			// �����Ų �� �α���â�� ����ǰ� Mainâ�� ����.  
				dispose();
				new Users_Main();
			}catch(Exception e1) {
			// try���� ���� ���� �� ���� catch���� �����Ų��. 
			// tf1�� ������ "admin"�� ���� pf1�� ������ "1234"�� ������ ���� ����
				if(tf1.getText().equals("admin")&&pf1.getText().equals("1234")) {
			// ������ �����Ǹ� �α���â�� ����ǰ� Adminâ�� serverFrameThreadâ�� ���.		
					dispose();
					new Admin_Main();
					new ServerFrameThread();
				}else {
			// ���� ������ �� �Ǹ� �޽����� �߰� tf1�� pf1�� ��ĭ���� ����� tf1�� ��Ŀ���� �д�.		
					JOptionPane.showMessageDialog(null, "ȸ�������� Ʋ���ϴ�.�ٽ��Է����ּ���.", "�޽���", JOptionPane.ERROR_MESSAGE);
					tf1.setText("");
					pf1.setText("");
					tf1.requestFocus();
				}

			}

		}
	}
}
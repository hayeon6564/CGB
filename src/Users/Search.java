package Users;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import Setting.Bean;
import Setting.DBmgr;

public class Search extends JFrame implements ActionListener{
	Container c = getContentPane(); 
	
	JPanel pan1 = new JPanel(new GridLayout(1,2,10,10));
	JPanel pan2 = new JPanel(new GridLayout(2, 2, -90, 15));
	JPanel pan3 = new JPanel();

	//�׻� ������
	JButton btn1 = new JButton("ID"); // pan1
	JButton btn2 = new JButton("PW");	// pan1
	JButton btn3 = new JButton("ID ã��");	// pan3
	JButton btn4 = new JButton("�ݱ�");	// pan3

	JLabel lbl1 = new JLabel("�̸�");	// pan2
	JTextField tf1 = new JTextField(15);	// pan2

	JLabel lbl2 = new JLabel("�������");	// pan2
	JTextField tf2 = new JTextField(15);	// pan2

	JLabel lbl4 = new JLabel("ã���� ��ư�� �����ּ���!"); // ù ȭ��

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	ArrayList<Bean> list =new ArrayList<Bean>();
	Bean bean;
	DBmgr mgr = new DBmgr();

	public Search() {
		setLayout(null);
		
		pan1.setBounds(55, 20, 165, 25);
		pan2.setBounds(21, 70, 200, 60);
		pan3.setBounds(45, 160, 170, 35);
		lbl4.setBounds(60, 70, 300, 50);
		
		btn1.setIcon(new ImageIcon(new ImageIcon("resource/icon/id.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		btn2.setIcon(new ImageIcon(new ImageIcon("resource/icon/pw.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

		pan1.add(btn1);	// ID
		pan1.add(btn2);	// PW
		
		pan2.add(lbl1);	// �̸�
		pan2.add(tf1);	// �̸�	
		pan2.add(lbl2);	// �������
		pan2.add(tf2);	// �������
	
		pan3.add(btn3);	// ID ã��
		pan3.add(btn4);	// �ݱ�
		
		add(lbl4);
		add(pan1);
		add(pan3);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		
		// ��ư ���� ä���
		btn1.setContentAreaFilled(false);
		btn2.setContentAreaFilled(false);
		btn3.setContentAreaFilled(false);
		btn4.setContentAreaFilled(false);

		c.setFont(new Font("�����ٸ����", Font.BOLD, 12));
		
		// ��� ���
		c.setBackground(Color.WHITE);
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.WHITE);
		pan3.setBackground(Color.WHITE);
		
		setIconImage(changedImg);
		
		// ������ �̺�Ʈ ���
		addWindowListener(new WindowAdapter() {
			@Override
			// �����찡 �������� �Ҷ�
			public void windowClosing(WindowEvent arg0) {
				new Login();	// �α���â�� ����.
			}
			});
		
		setTitle("ID/PW ã��");
		setSize(300,250);
		setLocationRelativeTo(null);	// ������ â�� ȭ���� ��� ���� ����
		setResizable(false); // âũ�� �Һ�
		setVisible(true);
		
		pan1.requestFocus();
	}

	public static void main(String[] args) {
		//new Search();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// btn1(ID)�� ������ ��
		if(obj == btn1) {
			lbl4.setText("");
			lbl1.setText("�̸�");
			lbl2.setText("�������");
			btn3.setText("ID ã��");
			tf1.setText("");
			tf2.setText("");

			add(pan2);
			// btn2(PW)�� ������ ��
		}else if(obj == btn2){
			lbl4.setText("");
			lbl1.setText("�̸�");
			lbl2.setText("ID");
			btn3.setText("PW ã��");
			tf1.setText("");
			tf2.setText("");

			add(pan2);
			// btn3�� ������ ��
		}else if(obj == btn3) {
			// btn3�� ����� "ID ã��"�� ���� ��
			if(btn3.getText().equals("ID ã��")) {
				// tf1�� ��ĭ�̰ų� tf2�� ��ĭ�� �� �޽����� ���� tf1�� ��Ŀ���� �����
				if(tf1.getText().equals("") || tf2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
					tf1.requestFocus();
					// tf1�� tf2�� ��ĭ�� �ƴ� ��
				}else {
					// ulist���� u_name�� tf1�� ����� u_bd�� tf2�� ������ ���� ���� �̾ƿ��� list�� �����Ѵ�
					DBmgr.sql = "select * from ulist where u_name = '" + tf1.getText() + "' and u_bd = '" + tf2.getText() + "'";
					list = mgr.user();
					try {
					bean = list.get(0);
					String id = bean.getUid();
					String name = bean.getUname();
					String bd = bean.getUbd();
					// id ù��°���� 4��°���� �̾ƿ��� �������� *�� �˷��ְ� tf1�� tf2�� ��ĭ���� ������ش�. 
					JOptionPane.showMessageDialog(null, name + "���� ID�� " + id.substring(0, 4) + "*** �Դϴ�.", "�޽���", JOptionPane.WARNING_MESSAGE);
					tf1.setText("");
					tf2.setText("");
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "ȸ�������� Ʋ���ϴ�.�ٽ��Է����ּ���.", "�޽���", JOptionPane.ERROR_MESSAGE);
						tf1.setText("");
						tf2.setText("");
						tf1.requestFocus();
					}
				}
				// btn3�� ����� "PW ã��"�� ���� ��
			}else if(btn3.getText().equals("PW ã��")) {
				// tf1�� ��ĭ�̰ų� tf2�� ��ĭ�� �� �޽����� ���� tf1�� ��Ŀ���� ����� 
				if(tf1.getText().equals("") || tf2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
					tf1.requestFocus();
					// tf1�� tf2�� ��ĭ�� �ƴ� ��
				}else {
					// ulist���� u_name�� tf1�� ����� u_bd�� tf2�� ������ ���� ���� �̾ƿ��� list�� �����Ѵ�
					DBmgr.sql = "select * from ulist where u_name = '" + tf1.getText() + "'";
		               list = mgr.user();
		               bean = list.get(0);
		               String id = bean.getUid();
		               String pw = bean.getUpw();
		               String name = bean.getUname();
		               
		               int npw;
		               Container ct = getContentPane();
		               
		               JLabel lb1 = new JLabel("�� ��й�ȣ");
		               JPasswordField npf = new JPasswordField(10);
		               JPanel p1 = new JPanel(new GridLayout(2,2));
		               p1.add(lb1);	// �� ��й�ȣ
		               p1.add(npf);	// �� ��й�ȣ
		               
		               JLabel lb2 = new JLabel("�� ��й�ȣ Ȯ��");
		               JPasswordField cpf = new JPasswordField(10);
		               p1.add(lb2);	// �� ��й�ȣ Ȯ��
		               p1.add(cpf);	// �� ��й�ȣ Ȯ��
		               
		               while(true) {
		                  npf.setText("");
		                  npf.requestFocus();
		                  npw = JOptionPane.showConfirmDialog(ct, p1, "�޽���", JOptionPane.OK_CANCEL_OPTION);
		                  // CANCEL�� �����ų� ������ break
		                  if(npw == JOptionPane.CANCEL_OPTION || npw == JOptionPane.CLOSED_OPTION) {
		                     
		                     break;
		                     
		                     // npf�� ��ĭ�̰ų� cpf�� ��ĭ�� �� �޽����� ���� npf, cpf�� ��ĭ���� ����� npf�� ��Ŀ���� �д�
		                  }else if(npf.getText().equals("") || cpf.getText().equals("")) {
		                     
		                     JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
		                     npf.setText("");
		                     cpf.setText("");
		                     npf.requestFocus();
		                     
		                     // npf�� cpf�� ������ ulist�� tf1�� �� �̸��� �̾� u_pw�� �ٲٰ� �޽����� ���� break
		                  }else if(npf.getText().equals(cpf.getText())) {
		                	 DBmgr.sql = "update ulist set u_pw = '" + npf.getText() + "' where u_name = '" + tf1.getText() + "'";
		                	 list = mgr.update();
		                     JOptionPane.showMessageDialog(null, "��й�ȣ ���� �Ϸ�!!", "�޽���", JOptionPane.INFORMATION_MESSAGE);
		                     break;
		                     // npf�� cpf�� ���� ������ �޽����� ����
		                  }else {
		                     JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� �ʽ��ϴ�!!", "�޽���", JOptionPane.ERROR_MESSAGE);
		                  }
		               }
		               tf1.setText("");
		               tf2.setText("");
				}
			}
			// btn4�� ������ ����â�� ����ǰ� �α���â�� ���
		}else if(obj == btn4) {
			dispose();
			new Login();
		}
	}
}
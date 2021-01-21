package Users;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Setting.Bean;
import Setting.DBmgr;
import Setting.RandomStr;

public class User_Info extends JFrame implements ActionListener {

	private ImageIcon originIcon;
	private Image originImg, changedImg;

	private JButton btOk, btCancle; //Ȯ��, ��ҹ�ư

	private JTextField tfPass, tfNewpass, tfNewpassOk; //�����н�����, ���н�����, ���н�����Ȯ�� �Է�â

	private JLabel lbNo1, lbNo2, lbNo3; //ȸ�� ����(�̸�, ���̵�, �������) ���� ��

	private JTextField tfOuto, tfOutoIn; //�ڵ��Է¹�������, �ڵ��Է¹������� �Է�â

	private RandomStr rs = new RandomStr(); //�ڵ��Է¹������� ���� �������ڿ�
	private String a = rs.getStr();

	private String ePass; //DB�� ������Ʈ �ϱ����� �� �н����� getText() ���� string

	private DBmgr mgr = new DBmgr(); //���ϴ� �� ������ �Լ��� ����ִ� Ŭ����
	private ArrayList<Bean> list =new ArrayList<Bean>(); //sql������ ������ ����� ����Ʈ ����
	private Bean bean; //sql�� ó��, list�� ������ �� ���� ������ ����

	public User_Info() {

		// ImageIcon ��ü��  �����Ѵ�.
		originIcon = new ImageIcon("resource/icon/pop.png");
		// ImageIcon���� Image�� �����Ѵ�.
		originImg = originIcon.getImage();
		// ����� Image�� ũ�⸦ �����ؼ� ���ο� Image��ü�� �����Ѵ�.
		changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		setIconImage(changedImg);

		JLabel lbName = new JLabel("�̸�");
		JLabel lbName2 = new JLabel(); //�ش� ������ �̸� ���� ��

		JLabel lbId = new JLabel("���̵�");
		JLabel lbId2 = new JLabel(); //�ش� ������ ���̵� ���� ��

		JLabel lbPass = new JLabel("���� �н�����");
		tfPass = new JTextField(); //���� password �Է�â
		tfPass.addActionListener(this);

		JLabel lbNewpass = new JLabel("�� �н�����");
		tfNewpass = new JTextField(); //�� password �Է�â
		tfNewpass.addActionListener(this);
		lbNo1 = new JLabel (); //�� ��й�ȣ Ʋ���� �� ��Ÿ�� ������

		JLabel lbNewpassOk = new JLabel("�� �н����� Ȯ��");  
		tfNewpassOk = new JTextField(); //�� password Ȯ��
		tfNewpassOk.addActionListener(this);
		lbNo2 = new JLabel (); //�� ��й�ȣȮ�� Ʋ���� �� ��Ÿ�� ������

		JLabel lbBirth = new JLabel("�������"); 			
		JLabel lbBirth2 = new JLabel(); // �ش� ������ ������� ���� ��

		tfOuto = new JTextField(a); //�������ڿ� ������ a�� ���
		tfOuto.setEditable(false); //�ǵ��� ���ϰ��ϱ�
		tfOuto.addActionListener(this);

		tfOutoIn = new JTextField(); //�ڵ��Է� �������� �Է��ϴ� �κ�
		tfOutoIn.addActionListener(this);
		lbNo3 = new JLabel (); //�ڵ��Է¹������� Ʋ���� �� ��Ÿ�� ������

		ImageIcon imgOk = new ImageIcon("resource\\Icon\\checked.png"); //Ȯ�ι�ư ������
		btOk = new JButton("Ȯ��",imgOk);
		btOk.setBackground(Color.WHITE);
		btOk.addActionListener(this);

		ImageIcon imgCancle = new ImageIcon("resource\\Icon\\exit.png"); //��ҹ�ư ������
		btCancle = new JButton("���",imgCancle);
		btCancle.setBackground(Color.WHITE);
		btCancle.addActionListener(this);

		//�α����� �ش� ������ ������ �� �󺧿� ����
		String UName = Login.name;
		lbName2.setText(UName);

		String UId = Login.id;
		lbId2.setText(UId);

		String UBd = Login.bd;
		lbBirth2.setText(UBd);

		JPanel p1 = new JPanel(new GridLayout(8,2,10,10));
		p1.setBackground(Color.WHITE);
		p1.add(lbName);			p1.add(lbName2);
		p1.add(lbId);			p1.add(lbId2);
		p1.add(lbBirth);		p1.add(lbBirth2);
		p1.add(lbPass);			p1.add(tfPass);
		p1.add(lbNewpass);		p1.add(tfNewpass);
		p1.add(lbNewpassOk);	p1.add(tfNewpassOk);
		p1.add(tfOuto);			p1.add(tfOutoIn);

		JPanel p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.add(btOk);	p2.add(btCancle);

		JPanel p3 = new JPanel();
		p3.setLayout(null);
		p3.setBackground(Color.WHITE);
		p3.add(lbNo1); //�� ��й�ȣ Ʋ���� �� ��Ÿ�� ������
		p3.add(lbNo2); //�� ��й�ȣȮ�� Ʋ���� �� ��Ÿ�� ������
		p3.add(lbNo3); //�ڵ��Է¹������� Ʋ���� �� ��Ÿ�� ������
		p3.add(p1);
		p3.add(p2);

		lbNo1.setBounds(255, 110, 100, 100);
		lbNo2.setBounds(255, 142, 100, 100);
		lbNo3.setBounds(255, 176, 15, 100); 
		p1.setBounds(20,20,230,250);
		p2.setBounds(20,280,250,200);
		p3.setBounds(0, 10, 250, 500);

		add(p3);

		setTitle("ȸ������ ����");
		setSize(300, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		tfPass.requestFocus();

		this.addWindowListener(new WindowAdapter() {@Override
			public void windowClosing(WindowEvent arg0) {
			dispose();
			new Users_Main();
		}
		});
	}

	public static void main(String[] args) {
		new User_Info();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(tfPass)||e.getSource().equals(tfNewpass)||e.getSource().equals(tfNewpassOk)||e.getSource().equals(tfOutoIn)||e.getSource().equals(btOk)) {
			checkPass();
		}else if(e.getSource().equals(btCancle)){
			dispose();
			new Users_Main();
		}
	}

	public void clear() { //���𰡸� Ʋ���� �� �Է�â �ʱ�ȭ
		tfPass.setText("");
		tfNewpass.setText("");
		tfNewpassOk.setText("");
		tfOutoIn.setText("");
		rs = new RandomStr();
		a = rs.getStr();
		tfOuto.setText(a);
		tfPass.requestFocus();
	}

	public void checkPass() {

		DBmgr.sql = "select * from ulist where u_no = '"+Login.no+"'";
		list = mgr.user();
		bean = list.get(0);

		//��ĭ�� �����ϸ� ���̾�α� �޽��� ����
		if(tfPass.getText().equals("")||tfNewpass.getText().equals("")||tfNewpassOk.getText().equals("")||tfOutoIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
			lbNo1.setIcon(new ImageIcon());
			lbNo2.setIcon(new ImageIcon());
			lbNo3.setIcon(new ImageIcon());
			clear();

		}else { //��ĭ�� �������� ������

			//���� ��й�ȣ�� Ʋ���� ���̾�α� �޽��� ����
			if(!tfPass.getText().equals(bean.getUpw())) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ���ϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
				lbNo1.setIcon(new ImageIcon());
				lbNo2.setIcon(new ImageIcon());
				lbNo3.setIcon(new ImageIcon());
				clear();

			} else { //���� ��й�ȣ�� ������

				//�� �н������ �� �н����� Ȯ��, �ڵ��Է¹湮�ڰ� ���� Ʋ���� x������ ǥ��
				if(!tfNewpass.getText().equals(tfNewpassOk.getText())&&!tfOutoIn.getText().equals(a)) {
					lbNo1.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					lbNo2.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					lbNo3.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					clear();
				}

				//�� �н������ �� �н����尡 Ʋ���� x������ ǥ��
				else if(!tfNewpass.getText().equals(tfNewpassOk.getText())) {
					lbNo3.setIcon(new ImageIcon());
					lbNo1.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					lbNo2.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					clear();

					//�ڵ��Է¹������ڰ� Ʋ���� x������ ǥ��	
				} else if(!tfOutoIn.getText().equals(a)) {
					lbNo1.setIcon(new ImageIcon());
					lbNo2.setIcon(new ImageIcon());
					lbNo3.setIcon(new ImageIcon("DBFiles\\icon\\no.png"));
					clear();

					//���� �н����尡 ����, �� �н������ �� �н�����Ȯ��, �ڵ��Է¹��� ���ڸ� ��� �ùٸ��� �Է��ϸ� �ش� ������ �н����� ����
				} else {
					if(tfNewpassOk.getText().equals(bean.getUpw())&&tfNewpass.getText().equals(bean.getUpw())) {
						lbNo1.setIcon(new ImageIcon());
						lbNo2.setIcon(new ImageIcon());
						lbNo3.setIcon(new ImageIcon());
						JOptionPane.showMessageDialog(null, "�Է��� ��й�ȣ�� ���� ��й�ȣ�� �����մϴ�\n���ο� ��й�ȣ�� �Է����ּ���.");
						clear();

					}else {
						ePass = tfNewpass.getText();
						DBmgr.sql = "update ulist set u_pw = + '" + ePass + "' where u_id = '"+Login.id+"'";
						list = mgr.update();
						JOptionPane.showMessageDialog(null, "������ �����Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
						dispose(); //�н����� ���� �� ����
						new Login();

					}
				}
			}
		}
	}
}

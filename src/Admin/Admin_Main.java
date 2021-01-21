package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Users.Login;

public class Admin_Main extends JFrame implements ActionListener{

	Container c = getContentPane();

	ImageIcon originIcon = new ImageIcon("resource/icon/pop.png");
	Image originImg = originIcon.getImage();
	Image changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	JButton btinsert = new JButton("��ȭ ���" );
	JButton btedit = new JButton("��ȭ ����");
	JButton btmanager = new JButton("ȸ�� ����");
	JButton btlogout = new JButton("������");

	JPanel p1 = new JPanel(new GridLayout(4,1,0,5));

	public Admin_Main() {

		this.setLayout(null);

		//���� ����
		c.setBackground(Color.WHITE);
		p1.setBackground(Color.WHITE);
		btinsert.setBackground(Color.WHITE);
		btedit.setBackground(Color.WHITE);
		btmanager.setBackground(Color.WHITE);
		btlogout.setBackground(Color.WHITE);

		//��ü ��Ʈ ����
		c.setFont(new Font("�����ٸ����", Font.BOLD, 12));

		//��ư�� �׵θ� ����
		btinsert.setBorderPainted(false);
		btedit.setBorderPainted(false);
		btmanager.setBorderPainted(false);
		btlogout.setBorderPainted(false);

		//������ ���԰� �̹��� ũŰ ��ȯ ������ �ӵ����ٴ� Smoothing�� �߿�� �ϰ� �ϴ� ���� 
		btinsert.setIcon(new ImageIcon(new ImageIcon("resource/icon/movie.png").getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH)));
		btedit.setIcon(new ImageIcon(new ImageIcon("resource/icon/movieedit.png").getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));
		btmanager.setIcon(new ImageIcon(new ImageIcon("resource/icon/searchU.png").getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH)));
		btlogout.setIcon(new ImageIcon(new ImageIcon("resource/icon/logoutA.png").getImage().getScaledInstance(45, 50, Image.SCALE_SMOOTH)));

		p1.add(btinsert);//��ȭ���
		p1.add(btedit);//��ȭ����
		p1.add(btmanager);//ȸ������
		p1.add(btlogout);//�α׾ƿ�

		//��ġ ����
		p1.setBounds(40, 10, 160, 360);

		this.add(p1);

		btinsert.addActionListener(this);
		btedit.addActionListener(this);
		btmanager.addActionListener(this);
		btlogout.addActionListener(this);

		this.setIconImage(changedImg);//Ÿ��Ʋ���� ������ ����
		this.setTitle("������ �޴�");//Ÿ��Ʋ���� ���� ����
		this.setSize(250, 420);//Frame ��� ũ�� ����
		this.setLocationRelativeTo(null);//Frame ��� ��ġ ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ÿ��Ʋ���� X��ư�� Ŭ���ϸ� ����ȴ�.
		this.setResizable(false);//Frame ������ ���� ����
		this.setVisible(true);//������ Frame�� ȭ�鿡 ���

		p1.requestFocus();//p1�� ��Ŀ���� �д�.

	}

	public static void main(String[] args) {
		new Admin_Main();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btinsert)) {//��� ��ư�� Ŭ���Ǹ�
			dispose();//���� â�� �����ϰ�
			new Insert();//MInsert() â�� ���� �����Ѵ�.
		}else if(e.getSource().equals(btedit)) {//��ȭ ���� ��ư�� Ŭ���Ǹ�
			dispose();//���� â�� �����ϰ�
			new Edit();//MEdit() â�� ���� �����Ѵ�.
		}else if(e.getSource().equals(btmanager)) {//ȸ�� ���� ��ư�� Ŭ���Ǹ�
			dispose();//���� â�� �����ϰ�
			new Manager();//Manager() â�� ���� �����Ѵ�.
		}else if(e.getSource().equals(btlogout)) {//�α׾ƿ� ��ư�� Ŭ���Ǹ�
			dispose();//���� â�� �����ϰ�
			new Login();//Login() â�� ���� �����Ѵ�.
		}

	}

}

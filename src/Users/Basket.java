package Users;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Setting.Bean;
import Setting.DBmgr;

public class Basket extends JFrame implements ActionListener {
   
   private ImageIcon originIcon;
   private Image originImg, changedImg;
   
   private int hap = 0; //����Ʈ ��� ����

   private JLabel lb1; //~���� ��ٱ���

   private ImageIcon i1, i2, i3; //����, ����, �ݱ� ��ư�� ���� ������
   private JButton bt1, bt2, bt3;

   private JLabel img = new JLabel(); //��ȭ ������ Ʋ

   private JLabel tf1, tf2, tf3, tf4, tf5; //������ ��ȭ ���� ���� ��

   private String col[] = {"No.","��ȭ��","�帣","��������","����"}; //���̺� �÷�

   private JTable table = new JTable();
   private JScrollPane jsp = new JScrollPane();

   private DBmgr mgr = new DBmgr(); //���ϴ� �� ������ �Լ��� ����ִ� Ŭ����
   private ArrayList<Bean> list =new ArrayList<Bean>(); //sql������ ������ ����� ����Ʈ ����
   private ArrayList<Bean> list2 =new ArrayList<Bean>();
   private ArrayList<Bean> list3 =new ArrayList<Bean>();
   private Bean bean, bean2, bean3, bean4; //sql�� ó��, list�� ������ �� ���� ������ ����


   public Basket() {
      
      // ImageIcon ��ü��  �����Ѵ�.
      originIcon = new ImageIcon("resource/icon/pop.png");
      // ImageIcon���� Image�� �����Ѵ�.
      originImg = originIcon.getImage();
      // ����� Image�� ũ�⸦ �����ؼ� ���ο� Image��ü�� �����Ѵ�.
      changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      setIconImage(changedImg);

      lb1 = new JLabel(Login.name + "���� ��ٱ���",JLabel.CENTER);
      lb1.setFont(new Font("�����ٸ����",Font.BOLD,20));
      lb1.setBackground(Color.WHITE);

      i1 = new ImageIcon("resource\\icon\\credit-card.png"); //���ž�����
      i2 = new ImageIcon("resource\\icon\\bin.png"); //����������
      i3 = new ImageIcon("resource\\icon\\exit.png"); //�ݱ������
      bt1 = new JButton("����",i1);
      bt2 = new JButton("����",i2);
      bt3 = new JButton("�ݱ�",i3);
      bt1.setBackground(Color.WHITE);
      bt2.setBackground(Color.WHITE);
      bt3.setBackground(Color.WHITE);
      bt1.addActionListener(this);
      bt2.addActionListener(this);
      bt3.addActionListener(this);

      //�þ�� �κ� ����
      img.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //��ȭ ������ �ܰ���

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

      //��ġ ����
      JPanel p1 = new JPanel(); //����, ����, �ݱ� ��ư

      p1.add(bt1);
      p1.add(bt2);
      p1.add(bt3);
      p1.setBackground(Color.WHITE);

      JPanel p2 = new JPanel(new GridLayout(5,2,0,15)); //��ȭ����Ʈ Ŭ���ϸ� ������ ������
      p2.setBackground(Color.WHITE);

      p2.add(lbl1);   p2.add(tf1); 
      p2.add(lbl2);   p2.add(tf2);
      p2.add(lbl3);   p2.add(tf3);
      p2.add(lbl4);   p2.add(tf4);
      p2.add(lbl5);   p2.add(tf5);

      JPanel p3 = new JPanel(); //��ü
      p3.setLayout(null);
      p3.setBackground(Color.WHITE);

      p3.add(lb1); //~���� ��ٱ���
      p3.add(jsp); //��ٱ������̺� ���� ��ũ���г� 
      p3.add(p1); //����, ����, �ݱ� ��ư
      p3.add(p2); //��ȭ����Ʈ Ŭ���ϸ� ������ ������
      p3.add(img); //��ȭ ������ �ܰ���

      p1.setBounds(0,300,650,400);
      p2.setBounds(920,50,250,250);
      img.setBounds(700,50,190,250);
      lb1.setBounds(0,0,650,50);
      jsp.setBounds(0,50,650,250);

      add(p3);

      table();

      setTitle("��ٱ���");
      setSize(650,400);
      setResizable(false); //â ũ�� ����
      setVisible(true);

      this.addWindowListener(new WindowAdapter() {@Override
         public void windowClosing(WindowEvent arg0) {
         dispose(); //X ���� ���� �̺�Ʈ
         new Users_Main();
      }
      });
   }

   void table() {

      //��ٱ��Ͽ� �ִ� ��ȭ�� ��ȣ�� ���̺� ��Ÿ�� ������ ��ȭDB���̺��� ������ �� ����Ʈ�� ����.  
      DBmgr.sql = "select mlist.m_name, mlist.m_genre, mlist.m_age, mlist.m_price from shoplist inner join mlist on mlist.m_no = shoplist.m_no where u_no = '"+Login.no+"' order by shoplist.s_no";
      list = mgr.basketjoin();

      Object record[][] = new Object[list.size()][col.length]; //���̺� ���� ������ ������ 2���� �迭

      for(int i= 0 ; i < list.size() ; i++) {

         bean = list.get(i); //select�� ������ ����Ʈ�� ���� ����

         record[i][0] = Integer.toString(i+1);
         record[i][1] = bean.getMname()+"";
         record[i][2] = bean.getMgenre()+"";
         record[i][3] = bean.getMage()+"";
         record[i][4] = Integer.toString(bean.getMprice());
      }

      DefaultTableModel model = new DefaultTableModel(record, col) { //record = ���̺��� �����Ͱ� ��� 2���� �迭, col = ���̺� �÷��� ���̺��� �����.
         public boolean isCellEditable(int r, int c) {
            return false; //���̺��� �� ���� �ǵ��� ���ϵ��� ��
         }
      };

      table = new JTable(model); //���̺� ����
      jsp.setViewportView(table); //��ũ����ο� ���̺� ���
      jsp.getViewport().setBackground(Color.WHITE);

      DefaultTableCellRenderer al = new DefaultTableCellRenderer();
      al.setHorizontalAlignment(JLabel.CENTER); //�� ���� ������ �������

      for(int i =0 ; i < col.length ; i ++) {
         table.getColumnModel().getColumn(i).setCellRenderer(al);
      } //������� ������(���̺� �°� ����)

      if(list.size()==0) { //��ٱ��Ͽ� �ƹ��͵� ������ ����, ���� ��ư ��Ȱ��ȭ

         bt1.setEnabled(false);
         bt2.setEnabled(false); 
      }

      table.addMouseListener(new MouseAdapter() { //���̺��� ���콺 �̺�Ʈ �߻����� �� ����

         @Override
         public void mouseClicked(MouseEvent e) {

            //���콺�� ������ ���� 1��° ��(��ȭ�̸�)�� ������� ��ȭDB���� �����ͼ� ����Ʈ�� ���� �� bean���� ���� ����
            DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(),1) + "'";
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
      new Basket();
   }

   void gg() {//����

      int point = 0;
      point = (int) (hap * 0.05);

      //�α����� ������ �̸����� ����DB���� �˻��Ͽ� �ش� ������ ������ ����Ʈ�� �����ϰ� bean���� ���� ����
      DBmgr.sql = "select * from ulist where u_name = '" +Login.name+ "'";
      list3 = mgr.user();
      bean3 = list3.get(0);

      for(int i = 0 ; i < table.getRowCount() ; i++ ) {

         //������ ��ȭ���� * 0.05 �� �� ����Ʈ ���
         point = (int) (Integer.parseInt(table.getValueAt(i, 4)+"") * 0.05); 
         DBmgr.sql = "update ulist set u_point = u_point + '" + point +"' where u_id = '" + Login.id + "'";
         list = mgr.update();

         DBmgr.sql ="select * from mlist where m_name = '" + table.getValueAt(i, 1) +"'"; //�� ������ ��ȭ �̸����� ��ȭDB���� �ش� ��ȭ ���� ������
         list2 = mgr.movie();

         try {
            bean2 = list2.get(0);

            //���ų������̺� ������ ������ ��ȭ ���� ����
            DBmgr.sql = "insert into olist(o_no, o_date, u_no, m_no, o_genre, o_price) values(so_no.nextval, to_char(sysdate,'yyyy-mm-dd'), '"+bean3.getUno()+ "', '"+bean2.getMno()+ "', '"+ bean2.getMgenre() + "', '"+bean2.getMprice()+"')";         
            mgr.update();
         }catch(Exception e1) {
         }
      }

      JOptionPane.showMessageDialog(null, "���ŵǾ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
      new Users_Main();

      //�α����� ������ ���ų��� ��������
      DBmgr.sql = "select * from olist where u_no = '" + Login.no + "'";
      list = mgr.orderlist();

      int sum = 0;
      for(int i= 0;i < list.size() ; i ++) { //�ش� ������ ������ ��ȭ ���� �ջ�
         bean = list.get(i);
         sum = sum + bean.getOprice(); //�����ݾ�
      }

      //�ش� ���� ���� ��������
      DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
      list = mgr.user();
      bean = list.get(0);

      String grade = bean.getUgrade(); //�ݾ� ���� ��� ����
      if(sum >= 500000) {   // SVIP 300,000 <= sum
         grade = "SVIP";
      }else if(sum >= 200000) {   // VVIP(200,000 <= sum)
         grade = "VVIP";
      }else if(sum >= 100000) {   // VIP(100,000 <= sum)
         grade = "VIP";
      }

      if(bean.getUgrade().equals(grade)) {//grade�� ���� ��ް� ������ 

      } else {//grade�� ���� ��ް� �޶������� ��� ����
         JOptionPane.showMessageDialog(null, "�����մϴ�!\nȸ���� �����"+ grade +"�� �±��ϼ̽��ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
      }

      DBmgr.sql = "update ulist set u_grade ='" + grade + "' where u_id = '" + Login.id + "'";
      list = mgr.update();

      //�����ϸ� ��ٱ��� ����
      DBmgr.sql = "delete from shoplist";
      list = mgr.update();

      dispose();
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if(e.getSource().equals(bt1)) {//���Ź�ư

         //�ش� ���� ���� ��������
         DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
         list = mgr.user();
         bean = list.get(0);

         for(int i = 0 ; i < table.getRowCount() ; i++ ) {

            hap = hap + Integer.parseInt(table.getValueAt(i, 4)+""); //��ٱ����� ��ȭ �ݾ� �ջ�
         }

         if(bean.getUpoint() >= hap) { //������ ��ȭ���� �ݾ׺��� ������ ����Ʈ�� �� ������

            int ok = JOptionPane.showConfirmDialog(null, "ȸ������ �� ����Ʈ : " + bean.getUpoint() + "\n����Ʈ�� �����Ͻðڽ��ϱ�?\n(�ƴϿ��� Ŭ�� �� ���ݰ����� �˴ϴ�.)","��������",JOptionPane.YES_NO_CANCEL_OPTION);
            if(ok==0) {

               for(int i = 0 ; i < table.getRowCount() ; i++ ) {

                  //����� ����Ʈ ��ŭ ������ ����Ʈ���� ����
                  DBmgr.sql = "update ulist set u_point = u_point - '" + hap +"' where u_id = '" + Login.id + "'";
                  list = mgr.update();

                  //update �� �� �����ͼ� ���̾�α� �޽��� ����
                  DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
                  list = mgr.user();
                  bean = list.get(0);

                  JOptionPane.showMessageDialog(null, "����Ʈ�� ���� �Ϸ�Ǿ����ϴ�.\n���� ����Ʈ : " + bean.getUpoint(), "�޽���", JOptionPane.INFORMATION_MESSAGE);


                  DBmgr.sql ="select * from mlist where m_name = '" + table.getValueAt(i, 1) +"'"; //�� ������ ��ȭ �̸����� ��ȭDB���� �ش� ��ȭ ���� ������
                  list2 = mgr.movie();

                  try {
                     bean2 = list2.get(i);

                     //���ų������̺� ������ ������ ��ȭ ���� ����
                     DBmgr.sql = "insert into olist(o_no, o_date, u_no, m_no, o_genre, o_price) values(so_no.nextval, to_char(sysdate,'yyyy-mm-dd'), '"+bean.getUno()+ "', '"+bean2.getMno()+ "', '"+ bean2.getMgenre() + "', '"+bean2.getMprice()+"')";         
                     mgr.update();
                     //System.out.println(DBmgr.sql);
                  }catch(Exception e1) {
                  }

                  DBmgr.sql = "delete from shoplist";
                  list = mgr.update();

                  table();
               }
            }else if(ok==JOptionPane.NO_OPTION){ //�ƴϿ� Ŭ�� �� �Ϲ� ����
               gg(); 
            }else if(ok == JOptionPane.CANCEL_OPTION) {
               JOptionPane.showMessageDialog(null, "���� ��� �Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
               dispose();
              //new Main();
            }
         }else { //������ ��ȭ���� �ݾ׺��� ������ ����Ʈ�� �� ������
            gg();
         }

      }else if(e.getSource().equals(bt2)) {//������ư

         if(table.getSelectedRowCount()==0) {//������ ��ȭ�� ���� ��

            JOptionPane.showMessageDialog(null, "������ �޴��� �������ּ���.", "�޽���", JOptionPane.ERROR_MESSAGE);

         }else {//������ ��ȭ�� ���� ��

            String movie = (String) table.getValueAt(table.getSelectedRow(),1);

            //������ ��ȭ�� ���� ��������
            DBmgr.sql = "select * from mlist where m_name = '"+movie+"'";
            list = mgr.movie();
            bean4 = list.get(0);

            //������ ��ȭ�� �ش� ������ ��ٱ��Ͽ��� ����
            DBmgr.sql = "delete from shoplist where m_no = '"+bean4.getMno()+"' and u_no = '"+Login.no+"'";
            list = mgr.update();

            table();

            setSize(650, 400); //��ȭ ���� �����
         }

      }else if(e.getSource().equals(bt3)) { //�ݱ��ư

         dispose();
         //new Main();
      }
   }
}